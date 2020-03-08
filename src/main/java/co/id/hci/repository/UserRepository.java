package co.id.hci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.hci.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findUserById (Long id);
}
