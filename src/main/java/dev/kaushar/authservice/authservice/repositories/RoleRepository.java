package dev.kaushar.authservice.authservice.repositories;

import dev.kaushar.authservice.authservice.models.Role;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @NonNull
    Role save(Role role);

    @Query(value = Queries.findRolesOfUser, nativeQuery = true)
    List<Role> findRolesByUser_Id(Long userId);

    Optional<Role> findByRole(String role);
}
