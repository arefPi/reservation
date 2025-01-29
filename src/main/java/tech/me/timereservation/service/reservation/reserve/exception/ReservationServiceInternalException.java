package tech.me.timereservation.service.reservation.reserve.exception;

public class ReservationServiceInternalException extends RuntimeException {
    private static final String RESERVATION_SERVICE_INTERNAL_ERROR = "RESERVATION_SERVICE_INTERNAL_ERROR";

    public ReservationServiceInternalException() {
        super(RESERVATION_SERVICE_INTERNAL_ERROR);
    }

    public ReservationServiceInternalException(Throwable cause) {
        super(RESERVATION_SERVICE_INTERNAL_ERROR, cause);
    }
}
