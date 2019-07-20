package br.com.awilix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.awilix.models.Feed;

public interface FeedRepository extends CrudRepository<Feed, Long> {

	List<Feed> findByCidade(String cidade);
	
	Optional<Feed> findByEmail(String email);
}