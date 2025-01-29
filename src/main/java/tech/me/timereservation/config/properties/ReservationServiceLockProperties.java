package tech.me.timereservation.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "redisson.lock.reservation-service")
public class ReservationServiceLockProperties {
    private int leaseTime;
    private int waitTime;
}
