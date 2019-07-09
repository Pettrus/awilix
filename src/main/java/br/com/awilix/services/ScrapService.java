package br.com.awilix.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.awilix.enums.Linguagem;
import br.com.awilix.models.Cinema;
import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.repository.CinemaService;
import br.com.awilix.repository.DetalhesRepository;
import br.com.awilix.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScrapService {

	private final FilmeRepository filmeRepository;
	
	private final FilmeService filmeService;
	
	private final CinemaService cinemaService;
	
	private final DetalhesRepository detalhesRepository;
	
	@Transactional
	public List<FilmeEmCartaz> verificarSalvarFilme(List<FilmeEmCartaz> filmes, List<Cinema> cinemas, Linguagem linguagem, String cidade) {
		List<String> ids = filmes.stream().map(FilmeEmCartaz::getImdbId).collect(Collectors.toList());
		
		List<String> existentes = filmeRepository.findByImdbIdInAndDetalhesLinguagem(ids, linguagem.ordinal());
		
		List<FilmeEmCartaz> filmesNovos = filmes.stream()
	            .filter(e -> !existentes.contains(e.getImdbId()))
	            .collect(Collectors.toList());
		
		detalhesRepository.deleteDetalhesByLinguagemFilme(linguagem.ordinal(), ids);
		cinemaService.substituirCinemas(cinemas, cidade);
		filmeService.salvar(filmes, linguagem);
		
		return filmesNovos;
	}
}