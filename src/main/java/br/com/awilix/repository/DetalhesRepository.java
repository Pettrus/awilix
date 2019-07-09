package br.com.awilix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.awilix.models.Detalhes;

public interface DetalhesRepository extends CrudRepository<Detalhes, Long> {

	@Modifying
	@Query(value = "DELETE FROM detalhes WHERE linguagem = ?1 AND filme_em_cartaz_id IN (?2)", nativeQuery = true)
	int deleteDetalhesByLinguagemFilme(int linguagem, List<String> filmeIds);
}