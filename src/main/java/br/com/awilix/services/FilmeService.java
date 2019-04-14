package br.com.awilix.services;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import br.com.awilix.tmdb.TmdbWrapper;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbFind.ExternalSource;
import info.movito.themoviedbapi.model.FindResults;
import info.movito.themoviedbapi.model.MovieDb;

@Service
public class FilmeService {
	
	public HttpResponse<String> pesquisar(String keyword) throws UnirestException {
		keyword = keyword.replace(" ", "%20");
		return Unirest.get(System.getenv("FILMES_URL") + "1?sort=trending&keywords=" + keyword).asString();
	}

	public HttpResponse<String> consultarFilmes(Integer pagina) throws UnirestException {
		return Unirest.get(System.getenv("FILMES_URL") + pagina + "?sort=trending").asString();
	}
	
	public HttpResponse<String> consultarFilmesPorGenero(Integer pagina, String genero) throws UnirestException {
		return Unirest.get(System.getenv("FILMES_URL") + pagina + "?sort=trending&genre=" + genero).asString();
	}
	
	public MovieDb detalhar(String imdbId) {
		FindResults pesquisa = TmdbWrapper.getInstance().getFind().find(imdbId, ExternalSource.imdb_id, "pt-BR");
		MovieDb filme = pesquisa.getMovieResults().get(0);
		filme.setReleases(new TmdbMovies.ReleaseInfoResults());
		filme.setCredits(TmdbWrapper.getInstance().getMovies().getCredits(filme.getId()));
		
		return filme;
	}
}