package br.com.awilix.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.awilix.enums.Linguagem;
import br.com.awilix.exception.GeneralException;
import br.com.awilix.models.Filme;
import br.com.awilix.models.FilmeCinema;
import br.com.awilix.repository.FilmeCinemaRepository;
import br.com.awilix.repository.FilmeRepository;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.MovieDb;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilmeService {
	
	private final FilmeRepository filmeRepository;
	
	private final TmdbService tmdbService;
	
	private final FilmeCinemaRepository fcRepository;
	
	public List<Filme> listarFilmes() {
		return filmeRepository.findByDetalhesLinguagem(Linguagem.PORTUGUES_BRASIL);
	}
	
	public void salvar(List<Filme> filmes, Linguagem linguagem) {
		for (Filme filme : filmes) {
			Integer id = tmdbService.pesquisarIdPortImdb(filme.getImdbId(), linguagem);
			
			if(id == null) {
				throw new GeneralException("Filme não encontrado");
			}
			
			MovieDb movie = tmdbService.carregarCategoriaPorFilmeId(id, "pt-BR", MovieMethod.videos);
			filme.preencherComTmdb(movie, linguagem);
			
			filmeRepository.save(filme);
		}	
	}
	
	public void salvarFilmeCinema(List<FilmeCinema> filmeCinema) {
		filmeCinema.forEach(fc -> {
			fc.getHorarios().forEach(h -> {
				h.setFilmeCinema(fc);
			});
		});
		
		fcRepository.saveAll(filmeCinema);
	}
}