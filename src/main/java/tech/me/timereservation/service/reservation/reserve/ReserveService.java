package tech.me.timereservation.service.reservation.reserve;

import tech.me.timereservation.service.reservation.reserve.model.ReservationRequest;
import tech.me.timereservation.service.reservation.reserve.model.ReservationResponse;

public interface ReserveService {
    ReservationResponse reserve(ReservationRequest request);
}
