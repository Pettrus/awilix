package br.com.awilix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.awilix.enums.Linguagem;
import br.com.awilix.models.FilmeEmCartaz;

public interface FilmeRepository extends CrudRepository<FilmeEmCartaz, Long> {

	List<FilmeEmCartaz> findByDetalhesLinguagem(Linguagem linguagem);
	
	@Query(value = "SELECT f.imdb_id FROM filmes_em_cartaz f "
			+ "INNER JOIN detalhes d ON d.filme_em_cartaz_id = f.imdb_id "
			+ "WHERE f.imdb_id IN (?1) AND d.linguagem = ?2", nativeQuery = true)
	List<String> findByImdbIdInAndDetalhesLinguagem(List<String> ids, int linguagem);
}