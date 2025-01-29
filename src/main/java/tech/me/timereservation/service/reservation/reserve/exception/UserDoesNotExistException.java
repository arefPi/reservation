package tech.me.timereservation.service.reservation.reserve.exception;

public class UserDoesNotExistException extends RuntimeException {
    private static final String USER_DOES_NOT_EXIST_EXCEPTION = "User_Does_Not_Exist_Exception";

    public UserDoesNotExistException() {
        super(USER_DOES_NOT_EXIST_EXCEPTION);
    }
}
