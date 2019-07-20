package br.com.awilix.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.awilix.models.FilmeCinema;

public interface FilmeCinemaRepository extends CrudRepository<FilmeCinema, Long> {

	void deleteByCinemaCidade(String cidade);
}
