package twitter.simplificado.springsecurity.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tweets")
public class Tweet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long tweetId;
	
	private User user;
	
	private String content;
	
	@CreationTimestamp
	private Instant creationTimestamp;

}
