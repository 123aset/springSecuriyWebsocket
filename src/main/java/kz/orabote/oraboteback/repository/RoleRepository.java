package kz.orabote.oraboteback.repository;

import kz.orabote.oraboteback.model.Role;
import kz.orabote.oraboteback.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
}
