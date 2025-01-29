package tech.me.timereservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching

public class TimeReservationApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimeReservationApplication.class, args);
    }
}
