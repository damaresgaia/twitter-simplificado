package twitter.simplificado.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import twitter.simplificado.springsecurity.entities.Tweet;


@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

}
