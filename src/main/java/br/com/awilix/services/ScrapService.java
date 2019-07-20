package br.com.awilix.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.awilix.dto.ScrapDTO;
import br.com.awilix.enums.Linguagem;
import br.com.awilix.models.Filme;
import br.com.awilix.repository.DetalhesRepository;
import br.com.awilix.repository.FilmeCinemaRepository;
import br.com.awilix.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScrapService {

	private final FilmeRepository filmeRepository;
	
	private final FilmeService filmeService;
	
	private final CinemaService cinemaService;
	
	private final DetalhesRepository detalhesRepository;
	
	private final FilmeCinemaRepository fcRepository;
	
	@Transactional
	public void atualizarFilmes(ScrapDTO scrap) {
		fcRepository.deleteByCinemaCidade(scrap.getCidade());
		cinemaService.atualizarCinemas(scrap.getCinemas(), scrap.getCidade());
		filmeService.salvar(scrap.getFilmes(), scrap.getLinguagem());
		filmeService.salvarFilmeCinema(scrap.getFilmeCinemas());
		detalhesRepository.deleteFilmeSemHorario();
	}
	
	public List<Filme> encontrarFilmesNovos(List<Filme> filmes, Linguagem linguagem) {
		List<String> ids = filmes.stream().map(Filme::getImdbId).collect(Collectors.toList());
		
		List<String> existentes = filmeRepository.findByImdbIdInAndDetalhesLinguagem(ids, linguagem.ordinal());
		
		List<Filme> filmesNovos = filmes.stream()
	            .filter(e -> !existentes.contains(e.getImdbId()))
	            .collect(Collectors.toList());
		
		return filmesNovos;
	}
}