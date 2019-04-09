package br.com.awilix.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.awilix.dto.HorariosDTO;
import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.models.Horarios;
import br.com.awilix.repository.FilmeRepository;
import br.com.awilix.repository.HorariosRepository;
import br.com.awilix.tmdb.TmdbWrapper;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbFind.ExternalSource;
import info.movito.themoviedbapi.model.FindResults;
import info.movito.themoviedbapi.model.MovieDb;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CinemaService {
	
	private final HorariosRepository horarioRepository;

	private final FilmeRepository filmeRepository;
	
	public Set<FilmeEmCartaz> listarFilmes() {
		return filmeRepository.findAll();
	}
	
	public HorariosDTO horariosFilme(String imdb) {
		FindResults pesquisa = TmdbWrapper.getInstance().getFind().find(imdb, ExternalSource.imdb_id, "pt-BR");
		MovieDb filme = pesquisa.getMovieResults().get(0);
		filme.setReleases(new TmdbMovies.ReleaseInfoResults());
		filme.setCredits(TmdbWrapper.getInstance().getMovies().getCredits(filme.getId()));
		
		Set<Horarios> lista = horarioRepository.findByFilmeImdbIdOrderByCinemaNomeAscInicioAsc(imdb);
		
		HorariosDTO dto = new HorariosDTO();
		dto.setHorarios(lista);
		dto.setDetalhes(filme);
		
		return dto;
	}
	
	public int retornaIdTmdb(String imdb) {
		FindResults pesquisa = TmdbWrapper.getInstance().getFind().find(imdb, ExternalSource.imdb_id, "pt-BR");
		
		return pesquisa.getMovieResults().get(0).getId();
	}
}