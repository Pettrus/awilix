package br.com.awilix.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.awilix.enums.Linguagem;
import br.com.awilix.models.Cinema;
import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.repository.CinemaService;
import br.com.awilix.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScrapService {

	private final FilmeRepository filmeRepository;
	
	private final FilmeService filmeService;
	
	private final CinemaService cinemaService;
	
	@Transactional
	public void verificarSalvarFilme(List<FilmeEmCartaz> filmes, List<Cinema> cinemas, Linguagem linguagem, String cidade) {
		List<String> ids = filmes.stream().map(FilmeEmCartaz::getImdbId).collect(Collectors.toList());
		
		Set<String> existentes = filmeRepository.findByImdbIdInAndDetalhesLinguagem(ids, linguagem);

		List<FilmeEmCartaz> filmesNovos = filmes.stream()
		            .filter(e -> !existentes.contains(e.getImdbId()))
		            .collect(Collectors.toList());
		
		//TODO: Enviar email para usuarios sobre filmes novos
		
		filmeService.salvar(filmesNovos, linguagem);
		cinemaService.substituirCinemas(cinemas, cidade);
		filmeService.salvarHorarios(filmes);
	}
}