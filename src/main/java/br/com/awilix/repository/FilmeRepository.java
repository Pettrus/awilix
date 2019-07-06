package br.com.awilix.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import br.com.awilix.enums.Linguagem;
import br.com.awilix.models.FilmeEmCartaz;

public interface FilmeRepository extends CrudRepository<FilmeEmCartaz, Long> {

	Set<FilmeEmCartaz> findAll();
	
	Set<String> findByImdbIdInAndDetalhesLinguagem(List<String> ids, Linguagem linguagem);
}