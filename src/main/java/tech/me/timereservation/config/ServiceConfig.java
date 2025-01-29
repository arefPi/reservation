package tech.me.timereservation.config;

import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.me.timereservation.config.properties.ReservationServiceLockProperties;
import tech.me.timereservation.persistance.availbale.time.slot.AvailableTimeSlotRepository;
import tech.me.timereservation.persistance.reservation.ReservationRepository;
import tech.me.timereservation.persistance.user.UserRepository;
import tech.me.timereservation.service.reservation.reserve.ReserveService;
import tech.me.timereservation.service.reservation.reserve.ReserveServiceImpl;
import tech.me.timereservation.service.reservation.reserve.proxy.ReserveServiceLockProxy;

@Configuration
public class ServiceConfig {

    @Bean
    ReserveService reservationService(UserRepository userRepository,
                                      AvailableTimeSlotRepository availableTimeSlotRepository,
                                      ReservationRepository reservationRepository,
                                      RedissonClient redissonClient,
                                      ReservationServiceLockProperties reservationServiceLockProperties) {

        final var reservationService = new ReserveServiceImpl(userRepository,
                availableTimeSlotRepository,
                reservationRepository);

        return new ReserveServiceLockProxy(redissonClient, reservationService, reservationServiceLockProperties);
    }

}
