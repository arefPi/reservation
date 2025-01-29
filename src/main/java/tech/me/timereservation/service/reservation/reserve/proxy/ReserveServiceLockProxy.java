package tech.me.timereservation.service.reservation.reserve.proxy;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import tech.me.timereservation.config.properties.ReservationServiceLockProperties;
import tech.me.timereservation.service.reservation.reserve.ReserveService;
import tech.me.timereservation.service.reservation.reserve.exception.ReservationServiceInternalException;
import tech.me.timereservation.service.reservation.reserve.exception.TimeSlotIsNotAvailableNow;
import tech.me.timereservation.service.reservation.reserve.model.ReservationRequest;
import tech.me.timereservation.service.reservation.reserve.model.ReservationResponse;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class ReserveServiceLockProxy implements ReserveService {
    private final RedissonClient redissonClient;
    private final ReserveService reserveService;
    private final ReservationServiceLockProperties lockProperties;

    @Override
    public ReservationResponse reserve(ReservationRequest request) {
        final var lockKey = createLockKey(request);

        final var redissonLock = redissonClient.getLock(lockKey);

        try {
            final var isLockAcquired = tryLock(redissonLock);

            if (isLockAcquired) {
              return reserveService.reserve(request);
            } else {
                throw new TimeSlotIsNotAvailableNow();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ReservationServiceInternalException(e);
        } finally {
            if (redissonLock.isHeldByCurrentThread()) redissonLock.unlock();
        }
    }

    private String createLockKey(ReservationRequest request) {
        final var availableTimeSlot = request.availableTimeSlotId().value();
        return String.valueOf(availableTimeSlot);
    }

    private boolean tryLock(RLock redissonLock) throws InterruptedException {
        final var waitTime = lockProperties.getWaitTime();
        final var leaseTime = lockProperties.getLeaseTime();

        return redissonLock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
    }
}
