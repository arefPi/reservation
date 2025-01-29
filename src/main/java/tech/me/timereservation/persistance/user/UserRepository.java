package tech.me.timereservation.persistance.user;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Cacheable(value = "users", key = "#username")
    Optional<User> findByUsername(String username);
}