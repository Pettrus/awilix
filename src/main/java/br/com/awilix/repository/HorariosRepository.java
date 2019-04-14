package br.com.awilix.repository;

import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.awilix.models.Horarios;

public interface HorariosRepository extends PagingAndSortingRepository<Horarios, Long> {

	Set<Horarios> findByFilmeImdbIdOrderByCinemaNomeAscInicioAsc(String imdb);
	
	Set<Horarios> findByFilmeTmdbIdOrderByCinemaNomeAscInicioAsc(int tmdb);
}