package br.com.awilix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.awilix.models.Feed;

public interface FeedRepository extends CrudRepository<Feed, Long> {

	List<Feed> findByCidade(String cidade);
	
	Optional<Feed> findByEmail(String email);
	
	@Modifying
	@Query(value = "DELETE FROM feed WHERE email = ?1", nativeQuery = true)
	void removerFeedPorEmail(String email);
}