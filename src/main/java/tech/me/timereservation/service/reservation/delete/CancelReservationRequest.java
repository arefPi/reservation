package tech.me.timereservation.service.reservation.delete;

import tech.me.timereservation.service.reservation.ReservationId;

public record CancelReservationRequest(ReservationId reservationId) {
}
