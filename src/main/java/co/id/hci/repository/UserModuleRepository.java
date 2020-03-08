package co.id.hci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.hci.entity.UserModule;

@Repository
public interface UserModuleRepository extends JpaRepository<UserModule, Long> {
	UserModule findUserModuleById (Long id);
}
