package tech.me.timereservation.service.reservation.reserve.exception;

public class TimeSlotIsAlreadyReservedException extends RuntimeException {
    private static final String TIME_SLOT_IS_ALREADY_RESERVED = "TIME_SLOT_IS_ALREADY_RESERVED";

    public TimeSlotIsAlreadyReservedException() {
        super(TIME_SLOT_IS_ALREADY_RESERVED);
    }
}
