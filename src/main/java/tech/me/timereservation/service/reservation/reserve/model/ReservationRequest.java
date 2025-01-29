package tech.me.timereservation.service.reservation.reserve.model;

import tech.me.timereservation.service.reservation.AvailableTimeSlotId;
import tech.me.timereservation.service.reservation.Username;

public record ReservationRequest(AvailableTimeSlotId availableTimeSlotId,
                                 Username username) {
}
