package ir.serenade.minerva.repository;

import ir.serenade.minerva.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByEmailAddress(String email);
}
