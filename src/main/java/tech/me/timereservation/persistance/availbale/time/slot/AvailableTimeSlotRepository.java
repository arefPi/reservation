package tech.me.timereservation.persistance.availbale.time.slot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableTimeSlotRepository extends CrudRepository<AvailableTimeSlot, Long> {
}