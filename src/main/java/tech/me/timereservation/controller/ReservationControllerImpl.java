package tech.me.timereservation.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.me.timereservation.controller.mapper.CancelReservationMapper;
import tech.me.timereservation.controller.mapper.ReservationRequestMapper;
import tech.me.timereservation.controller.model.reserve.ReservationRequest;
import tech.me.timereservation.controller.model.reserve.ReservationResponse;
import tech.me.timereservation.service.reservation.delete.CancelReservationService;
import tech.me.timereservation.service.reservation.reserve.ReserveService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationControllerImpl implements ReservationController {

    private final ReserveService reserveService;
    private final ReservationRequestMapper reservationRequestMapper;

    private final CancelReservationService cancelReservationService;
    private final CancelReservationMapper cancelReservationMapper;

    @Override
    @PostMapping
    @Operation(summary = "Reserve a time slot", description = "This endpoint allows users to make a reservation.")
    public ReservationResponse reserve(@RequestBody ReservationRequest request) {

        final var reservationRequest =
                reservationRequestMapper.mapRequest(request);

        final var reservationResponse =
                reserveService.reserve(reservationRequest);

        return reservationRequestMapper.mapResponse(reservationResponse);

    }

    @Override
    @DeleteMapping("/{reservation-id}")
    @Operation(summary = "Cancel a reservation", description = "This endpoint allows users to cancel a reservation.")
    public void cancel(@PathVariable("reservation-id") String reservationId) {
        final var cancelReservationRequest =
                cancelReservationMapper.map(reservationId);

        cancelReservationService.cancel(cancelReservationRequest);
    }
}
