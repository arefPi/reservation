package tech.me.timereservation.controller.mapper;

import org.mapstruct.Mapper;
import tech.me.timereservation.service.reservation.ReservationId;
import tech.me.timereservation.service.reservation.delete.CancelReservationRequest;

@Mapper
public abstract class CancelReservationMapper {
    public CancelReservationRequest map(String source) {
        final var reservationId = new ReservationId(source);
        return new CancelReservationRequest(reservationId);
    }
}
