package kz.orabote.oraboteback.repository;

import kz.orabote.oraboteback.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
