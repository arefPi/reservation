package tech.me.timereservation.controller.model.reserve;

public record ReservationRequest(long availableTimeSlotId,
                                 String username) {
}
