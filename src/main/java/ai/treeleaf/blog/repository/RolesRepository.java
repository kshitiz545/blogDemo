package ai.treeleaf.blog.repository;

import ai.treeleaf.blog.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {

    Optional<Roles> findByRole(String role);
}
