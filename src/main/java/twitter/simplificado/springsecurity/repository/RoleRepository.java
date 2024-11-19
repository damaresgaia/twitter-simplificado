package twitter.simplificado.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import twitter.simplificado.springsecurity.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}