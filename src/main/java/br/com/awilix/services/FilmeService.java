package br.com.awilix.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.awilix.tmdb.TmdbWrapper;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.Genre;
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
		MovieDb filme = TmdbWrapper.getInstance().getMovies().getMovie(filmeId, "pt-BR", MovieMethod.credits, MovieMethod.videos);
		filme.setReleases(new TmdbMovies.ReleaseInfoResults());
		
		return filme;
	}
	
	public List<Genre> consultarGeneros() {
		return TmdbWrapper.getInstance().getGenre().getGenreList("pt-BR");
	}
	
	public List<MovieDb> pesquisarPorGenero(Integer id, Integer pagina) {
		List<MovieDb> lista = TmdbWrapper.getInstance().getGenre().getGenreMovies(id, "pt-BR", pagina, false).getResults();
		
		lista.forEach(filme -> {
			filme.setReleases(new TmdbMovies.ReleaseInfoResults());
		});
		
		return lista;
	}
	
	public List<MovieDb> pesquisarPorNome(String nome, Integer pagina) {
		List<MovieDb> lista = TmdbWrapper.getInstance().getSearch().searchMovie(nome, null, "pt-BR", false, pagina).getResults();
		
		lista.forEach(filme -> {
			filme.setReleases(new TmdbMovies.ReleaseInfoResults());
		});
		
		return lista;
	}
}