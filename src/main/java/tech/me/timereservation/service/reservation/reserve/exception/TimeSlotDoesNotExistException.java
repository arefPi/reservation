package tech.me.timereservation.service.reservation.reserve.exception;

public class TimeSlotDoesNotExistException extends RuntimeException {
    private static final String TIME_SLOT_DOES_NOT_EXIST = "TIME_SLOT_DOES_NOT_EXIST";

    public TimeSlotDoesNotExistException() {
        super(TIME_SLOT_DOES_NOT_EXIST);
    }
}
