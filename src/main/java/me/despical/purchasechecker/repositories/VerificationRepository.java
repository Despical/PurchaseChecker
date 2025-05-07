package me.despical.purchasechecker.repositories;

import me.despical.purchasechecker.entities.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Despical
 * <p>
 * Created at 1.05.2025
 */
public interface VerificationRepository extends JpaRepository<Verification, Long> {

    boolean existsByUserIdAndPluginId(Long userId, Long pluginId);
}
