package br.com.awilix.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.awilix.enums.Linguagem;
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
	
	public List<Filme> listarFilmes(Linguagem linguagem) {
		return filmeRepository.findByDetalhesLinguagem(linguagem);
	}
	
	public List<Filme> preencherDadosFilmes(List<Filme> filmes, Linguagem linguagem) {
		List<Filme> filmesFormatados = new ArrayList<Filme>();
		
		for (Filme filme : filmes) {
			Integer id = tmdbService.pesquisarIdPortImdb(filme.getImdbId(), linguagem);
			
			if(id != null) {
				MovieDb movie = tmdbService.carregarCategoriaPorFilmeId(id, 
						(linguagem.equals(Linguagem.PORTUGUES_BRASIL)  ? "pt-BR" : "pt-PT"), MovieMethod.videos);
				filme.preencherComTmdb(movie, linguagem);
				filmeRepository.save(filme);
				
				filmesFormatados.add(filme);
			}
		}
		
		return filmesFormatados;
	}
	
	public void salvar(List<Filme> filmes) {
		filmeRepository.saveAll(filmes);
	}
	
	public void salvarFilmeCinema(List<FilmeCinema> filmeCinema, List<Filme> filmes) {
		List<FilmeCinema> remover = new ArrayList<FilmeCinema>();
		Set<String> ids = filmes.stream()
		        .map(Filme::getImdbId)
		        .collect(Collectors.toSet());
		
		filmeCinema.forEach(fc -> {
			if(ids.contains(fc.getFilme().getImdbId())) {
				fc.getHorarios().forEach(h -> {
					h.setFilmeCinema(fc);
				});
			}else {
				remover.add(fc);
			}
		});
		
		filmeCinema.removeAll(remover);
		
		fcRepository.saveAll(filmeCinema);
	}
}