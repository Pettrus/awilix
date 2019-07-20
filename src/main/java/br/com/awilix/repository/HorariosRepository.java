package br.com.awilix.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.awilix.models.Horarios;

public interface HorariosRepository extends CrudRepository<Horarios, Long> {

	@Query(value = "DELETE FROM horarios WHERE filme_cinema_id IN ("
			+ "SELECT id FROM filme_cinema fc "
			+ "INNER JOIN cidades c ON c.id = fc.cidade_id "
			+ "WHERE c.cidade = ?1)", nativeQuery = true)
	void deleteByCidade(String cidade);
}