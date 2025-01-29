package tech.me.timereservation.persistance.reservation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import tech.me.timereservation.persistance.availbale.time.slot.AvailableTimeSlot;
import tech.me.timereservation.persistance.user.User;

import java.time.LocalDateTime;

@Entity(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Reservation {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "available_time_slot_id", nullable = false)
    private AvailableTimeSlot availableTimeSlot;

    @Column(name = "reserved_at", nullable = false)
    private LocalDateTime reservedAt;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationState state;

    public boolean isCanceled() {
        return ReservationState.CANCELLED == state;
    }

    public void cancel() {
        this.state = ReservationState.CANCELLED;
    }

    @PrePersist
    public void reservedAt() {
        setReservedAt(LocalDateTime.now());
    }
}
