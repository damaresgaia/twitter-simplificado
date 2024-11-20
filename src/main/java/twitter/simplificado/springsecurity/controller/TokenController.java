package twitter.simplificado.springsecurity.controller;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import twitter.simplificado.springsecurity.controller.dto.LoginRequest;
import twitter.simplificado.springsecurity.controller.dto.LoginResponse;
import twitter.simplificado.springsecurity.repository.UserRepository;

@RestController
public class TokenController {
	
	private final JwtEncoder jwtEncoder;
	
	private final UserRepository userRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public TokenController(JwtEncoder jwtEncoder,
				           UserRepository userRepository,
				           BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.jwtEncoder = jwtEncoder;
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		
			var user = userRepository.findByUsername(loginRequest.username());
			
			if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)) {
				throw new BadCredentialsException("user or password is invalid!");
			}
			
			var now = Instant.now();
			var expiresIn = 300L;
			
			var claims = JwtClaimsSet.builder()
					.issuer("mybackend")
					.subject(user.get().getUserId().toString())
					.issuedAt(now)
					.expiresAt(now.plusSeconds(expiresIn))
					.build();
			
			var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
			
			return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
	}
}
