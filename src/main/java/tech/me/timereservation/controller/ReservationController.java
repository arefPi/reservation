package tech.me.timereservation.controller;

import tech.me.timereservation.controller.model.reserve.ReservationRequest;
import tech.me.timereservation.controller.model.reserve.ReservationResponse;

public interface ReservationController {
    ReservationResponse reserve(ReservationRequest reservationRequest);
    void cancel(String reservationId);
}
