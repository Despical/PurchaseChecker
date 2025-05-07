package me.despical.purchasechecker.repositories;

import me.despical.purchasechecker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Despical
 * <p>
 * Created at 19.04.2025
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);
}
