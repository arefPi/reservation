package tech.me.timereservation.service.reservation.delete.exception;

public class CancelReservationServiceInternalException extends RuntimeException {
    private static final String CANCEL_RESERVATION_SERVICE_INTERNAL_EXCEPTION =
            "CANCEL_RESERVATION_SERVICE_INTERNAL_EXCEPTION";

    public CancelReservationServiceInternalException() {
        super(CANCEL_RESERVATION_SERVICE_INTERNAL_EXCEPTION);
    }
}
