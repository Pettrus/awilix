package br.com.awilix.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.awilix.models.Cinema;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {

	@Modifying
	@Query(value = "DELETE FROM horarios WHERE cinema_id IN (SELECT id FROM cinemas WHERE cidade = :cidade);"
			+ "DELETE FROM cinemas c WHERE c.cidade = :cidade", nativeQuery = true)
	int deleteFromCinemasWhereCidade(String cidade);
}