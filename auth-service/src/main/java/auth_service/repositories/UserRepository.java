package auth_service.repositories;

import auth_service.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    // Spring comprend automatiquement cette méthode grâce à son nom
    boolean existsByUsername(String username);
}