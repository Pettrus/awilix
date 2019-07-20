package br.com.awilix.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.awilix.models.Cinema;
import br.com.awilix.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CinemaService {
	
	private final CinemaRepository cinemaRepository;
	
	public void atualizarCinemas(List<Cinema> cinemas, String cidade) {
		cinemaRepository.deleteByCidade(cidade);
		cinemaRepository.saveAll(cinemas);
	}
}