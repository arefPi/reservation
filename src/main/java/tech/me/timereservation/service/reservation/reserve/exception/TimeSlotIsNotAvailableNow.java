package tech.me.timereservation.service.reservation.reserve.exception;

public class TimeSlotIsNotAvailableNow extends RuntimeException {
    private static final String TIME_SLOT_IS_NOT_AVAILABLE_NOW = "TIME_SLOT_IS_NOT_AVAILABLE_NOW";

    public TimeSlotIsNotAvailableNow() {
        super(TIME_SLOT_IS_NOT_AVAILABLE_NOW);
    }
}
