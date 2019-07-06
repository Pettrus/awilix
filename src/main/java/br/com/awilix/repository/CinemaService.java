package br.com.awilix.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.awilix.models.Cinema;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CinemaService {
	
	private final CinemaRepository cinemaRepository;
	
	public void substituirCinemas(List<Cinema> cinemas, String cidade) {
		cinemaRepository.deleteFromCinemasWhereCidade(cidade);
		cinemas.stream().forEach(c -> c.setCidade(cidade));
		
		cinemaRepository.saveAll(cinemas);
	}
}