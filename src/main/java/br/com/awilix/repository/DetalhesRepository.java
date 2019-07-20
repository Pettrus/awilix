package br.com.awilix.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.awilix.models.Detalhes;

public interface DetalhesRepository extends CrudRepository<Detalhes, Long> {
	
	@Modifying
	@Query(value = "DELETE FROM detalhes d WHERE not exists ("
			+ "SELECT 1 FROM horarios h "
			+ "INNER JOIN filme_cinema fc ON fc.id = h.filme_cinema_id "
			+ "WHERE fc.filme_id = d.filme_id"
			+ ")", nativeQuery = true)
	void deleteFilmeSemHorario();
}