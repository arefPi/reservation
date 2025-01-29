package tech.me.timereservation.service.reservation.delete.exception;

public class ReservationDoesNotExistException extends RuntimeException {
    private static final String RESERVATION_DOES_NOT_EXIST = "RESERVATION_DOES_NOT_EXIST";

    public ReservationDoesNotExistException() {
        super(RESERVATION_DOES_NOT_EXIST);
    }
}
