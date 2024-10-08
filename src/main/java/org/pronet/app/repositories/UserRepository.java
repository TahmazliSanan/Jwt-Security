package org.pronet.app.repositories;

import org.pronet.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameContainingIgnoreCase(String username);
    Boolean existsByUsernameContainingIgnoreCase(String username);
}
