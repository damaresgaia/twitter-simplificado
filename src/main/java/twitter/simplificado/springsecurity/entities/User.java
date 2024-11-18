package twitter.simplificado.springsecurity.entities;


import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID userId;
	
	private String username;
	
	private String password;
	
	private Set<Role> roles;

}
