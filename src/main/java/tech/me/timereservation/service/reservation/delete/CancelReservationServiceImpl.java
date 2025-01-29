package tech.me.timereservation.service.reservation.delete;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.me.timereservation.persistance.availbale.time.slot.AvailableTimeSlotRepository;
import tech.me.timereservation.persistance.reservation.ReservationRepository;
import tech.me.timereservation.service.reservation.delete.exception.CancelReservationServiceInternalException;
import tech.me.timereservation.service.reservation.delete.exception.ReservationAlreadyCanceledException;
import tech.me.timereservation.service.reservation.delete.exception.ReservationDoesNotExistException;

@Service
@RequiredArgsConstructor
public class CancelReservationServiceImpl implements CancelReservationService {
    private final AvailableTimeSlotRepository availableTimeSlotRepository;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public void cancel(CancelReservationRequest request) {
        final var reservationId = request.reservationId().value();

        reservationRepository.findById(reservationId).ifPresentOrElse(reservation -> {
            if (reservation.isCanceled()) {
                throw new ReservationAlreadyCanceledException();
            }

            reservation.cancel();
            reservationRepository.save(reservation);

            final var availableTimeSlotId = reservation.getAvailableTimeSlot().getId();
            cancelTimeSlotReservation(availableTimeSlotId);
        }, () -> {
            throw new ReservationDoesNotExistException();
        });
    }

    private void cancelTimeSlotReservation(long availableTimeSlotId) {
        availableTimeSlotRepository.findById(availableTimeSlotId)
                .ifPresentOrElse(availableTimeSlot -> {
                    availableTimeSlot.cancelReservation();
                    availableTimeSlotRepository.save(availableTimeSlot);
                }, () -> {
                    throw new CancelReservationServiceInternalException();
                });
    }
}
