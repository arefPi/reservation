package tech.me.timereservation.service.reservation.reserve;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import tech.me.timereservation.persistance.availbale.time.slot.AvailableTimeSlot;
import tech.me.timereservation.persistance.availbale.time.slot.AvailableTimeSlotRepository;
import tech.me.timereservation.persistance.reservation.Reservation;
import tech.me.timereservation.persistance.reservation.ReservationRepository;
import tech.me.timereservation.persistance.reservation.ReservationState;
import tech.me.timereservation.persistance.user.User;
import tech.me.timereservation.persistance.user.UserRepository;
import tech.me.timereservation.service.reservation.Username;
import tech.me.timereservation.service.reservation.reserve.exception.TimeSlotDoesNotExistException;
import tech.me.timereservation.service.reservation.reserve.exception.TimeSlotIsAlreadyReservedException;
import tech.me.timereservation.service.reservation.reserve.exception.UserDoesNotExistException;
import tech.me.timereservation.service.reservation.reserve.model.ReservationRequest;
import tech.me.timereservation.service.reservation.reserve.model.ReservationResponse;

import java.util.UUID;

@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService {
    private final UserRepository userRepository;
    private final AvailableTimeSlotRepository availableTimeSlotRepository;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public ReservationResponse reserve(ReservationRequest request) {
        final var username = request.username();
        final var user = getUser(username);

        final var availableTimeSlotId = request.availableTimeSlotId().value();
        final var availableTimeSlot = getAvailableSlot(availableTimeSlotId);

        if (availableTimeSlot.isReserved()) {
            throw new TimeSlotIsAlreadyReservedException();
        }

        availableTimeSlot.reserve();

        final var reservation = createReservation(user, availableTimeSlot);
        reservationRepository.save(reservation);

        final var reservationId = reservation.getId();
        return new ReservationResponse(reservationId);
    }

    private User getUser(Username username) {
        final var userOptional = userRepository.findByUsername(username.value());

        if (userOptional.isEmpty()) {
            throw new UserDoesNotExistException();
        }

        return userOptional.get();
    }

    private Reservation createReservation(User user, AvailableTimeSlot availableTimeSlot) {
        final var reservationId = UUID.randomUUID().toString();

        return new Reservation()
                .setId(reservationId)
                .setAvailableTimeSlot(availableTimeSlot)
                .setUser(user)
                .setState(ReservationState.CONFIRMED);
    }

    private AvailableTimeSlot getAvailableSlot(long id) {
        final var availableTimeSlotOptional =
                availableTimeSlotRepository.findById(id);

        if (availableTimeSlotOptional.isEmpty())
            throw new TimeSlotDoesNotExistException();

        return availableTimeSlotOptional.get();
    }
}
