package br.com.awilix.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.awilix.models.Fcm;

public interface FcmRepository extends CrudRepository<Fcm, Long> {
	
	Optional<Fcm> findByToken(String token);
	
	@Query(value = "SELECT token FROM fcm", nativeQuery = true)
	Set<String> listarTodosTokens();

}