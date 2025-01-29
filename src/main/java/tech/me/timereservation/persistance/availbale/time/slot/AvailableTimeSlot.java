package tech.me.timereservation.persistance.availbale.time.slot;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "available_time_slots")
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class AvailableTimeSlot implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "is_reserved", nullable = false)
    private boolean isReserved;

    public void reserve() {
        this.isReserved = true;
    }

    public void cancelReservation() {
        this.isReserved = false;
    }
}