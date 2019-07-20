package br.com.awilix.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.awilix.models.Cinema;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {

	int deleteByCidade(String cidade);
}