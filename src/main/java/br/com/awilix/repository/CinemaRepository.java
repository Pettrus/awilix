package br.com.awilix.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.awilix.models.Cinema;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {

	@Modifying
	@Query("DELETE FROM cinemas c WHERE c.cidade = :cidade")
	int deleteFromCinemasWhereCidade(String cidade);
}