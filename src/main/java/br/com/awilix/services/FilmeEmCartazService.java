package br.com.awilix.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.awilix.models.Horarios;
import br.com.awilix.repository.HorariosRepository;

@Service
public class FilmeEmCartazService {

	@Autowired
	private HorariosRepository repository;
	
	public Set<Horarios> pesquisarHorariosFilme(String imdb) {
		return repository.findByFilmeImdbIdOrderByCinemaNomeAscInicioAsc(imdb);
	}
}