package br.com.awilix.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.awilix.tmdb.TmdbWrapper;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;

@Service
public class FilmeService {

	public List<MovieDb> nosCinemas(Integer pagina) {
		List<MovieDb> lista = TmdbWrapper.getInstance().getMovies().getNowPlayingMovies("pt-BR", pagina, null).getResults();
		
		lista.forEach(filme -> {
			filme.setReleases(new TmdbMovies.ReleaseInfoResults());
		});
		
		return lista;
	}
	
	public MovieDb detalhes(Integer filmeId) {
		MovieDb filme = TmdbWrapper.getInstance().getMovies().getMovie(filmeId, "pt-BR", null);
		filme.setReleases(new TmdbMovies.ReleaseInfoResults());
		
		return filme;
	}
}