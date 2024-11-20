package twitter.simplificado.springsecurity.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import twitter.simplificado.springsecurity.controller.dto.CreateTweetDto;
import twitter.simplificado.springsecurity.entities.Tweet;
import twitter.simplificado.springsecurity.repository.TweetRepository;
import twitter.simplificado.springsecurity.repository.UserRepository;

@RestController
public class TweetController {
	
	private final TweetRepository tweetRepository;
	
	private final UserRepository userRepository;
	
	public TweetController(TweetRepository tweetRepository,
						   UserRepository userRepository) {
		this.tweetRepository = tweetRepository;
		this.userRepository = userRepository;
	}
	
	@PostMapping("/tweets")
	public ResponseEntity<Void> createTweet(@RequestBody CreateTweetDto dto,
											JwtAuthenticationToken token) {
		var user = userRepository.findById(UUID.fromString(token.getName()));
		
		var tweet = new Tweet();
		tweet.setUser(user.get());
		tweet.setContent(dto.content());
		
		tweetRepository.save(tweet);
		
		return ResponseEntity.ok().build();
	}

}
