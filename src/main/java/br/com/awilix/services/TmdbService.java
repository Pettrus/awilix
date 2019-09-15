package br.com.awilix.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.awilix.enums.Linguagem;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbFind.ExternalSource;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.MovieDb;

@Service
public class TmdbService {
	
	private TmdbApi api = new TmdbApi(System.getenv("API_KEY"));
	
	public Integer pesquisarIdPortImdb(String imdbId, Linguagem linguagem) {
		List<MovieDb> lista = api.getFind().find(imdbId, ExternalSource.imdb_id, 
				(linguagem.equals(Linguagem.PORTUGUES_BRASIL)  ? "pt-BR" : "pt-PT")).getMovieResults();
		
		if(lista.isEmpty())
			return null;
		
		return lista.get(0).getId();
	}
	
	public MovieDb carregarCategoriaPorFilmeId(int id, String lingua, MovieMethod categoria) {
		return api.getMovies().getMovie(id, lingua, categoria);
	}
}