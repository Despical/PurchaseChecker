package me.despical.purchasechecker.repositories;

import me.despical.purchasechecker.entities.VerificationError;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Despical
 * <p>
 * Created at 2.05.2025
 */
public interface ErrorRepository extends JpaRepository<VerificationError, Long> {

    Optional<VerificationError> findByToken(String token);
}
