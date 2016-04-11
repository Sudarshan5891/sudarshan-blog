package myapp.sudarshan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myapp.sudarshan.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String role);

}
