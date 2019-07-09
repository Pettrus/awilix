package br.com.awilix.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.awilix.enums.Linguagem;
import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.repository.FilmeRepository;
import br.com.awilix.tmdb.TmdbWrapper;
import info.movito.themoviedbapi.TmdbFind.ExternalSource;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.FindResults;
import info.movito.themoviedbapi.model.MovieDb;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilmeService {
	
	private final FilmeRepository filmeRepository;
	
	public List<FilmeEmCartaz> listarFilmes() {
		return filmeRepository.findByDetalhesLinguagem(Linguagem.PORTUGUES_BRASIL);
	}
	
	public void salvar(List<FilmeEmCartaz> filmes, Linguagem linguagem) {
		for (FilmeEmCartaz filme : filmes) {
			FindResults pesquisa = TmdbWrapper.getInstance().getFind().find(filme.getImdbId(), ExternalSource.imdb_id, linguagem.toString());
			
			if(pesquisa.getMovieResults().isEmpty()) {
				//TODO: Informar erro
			}
			
			MovieDb movie = TmdbWrapper.getInstance().getMovies().getMovie(pesquisa.getMovieResults().get(0).getId(), "pt-BR", MovieMethod.videos);
			filme.preencherComTmdb(movie, linguagem);
			
			filme.getHorarios().forEach(h -> h.setFilme(filme));
			
			filmeRepository.save(filme);
		}	
	}
}