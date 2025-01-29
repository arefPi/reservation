package tech.me.timereservation.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.me.timereservation.controller.model.reserve.ReservationResponse;
import tech.me.timereservation.service.reservation.reserve.model.ReservationRequest;

@Mapper
public interface ReservationRequestMapper {
    @Mapping(target = "availableTimeSlotId.value", source = "availableTimeSlotId")
    @Mapping(target = "username.value", source = "username")
    ReservationRequest mapRequest(tech.me.timereservation
                                   .controller.model
                                   .reserve.ReservationRequest source);

    ReservationResponse mapResponse(tech.me.timereservation.service.reservation.reserve.model.ReservationResponse source);
}
