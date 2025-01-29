package tech.me.timereservation.service.reservation.delete.exception;

public class ReservationAlreadyCanceledException extends RuntimeException {
    private static final String RESERVATION_ALREADY_CANCELED = "RESERVATION_ALREADY_CANCELED";

    public ReservationAlreadyCanceledException() {
        super(RESERVATION_ALREADY_CANCELED);
    }
}
