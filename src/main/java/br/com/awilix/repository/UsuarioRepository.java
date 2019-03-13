package br.com.awilix.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.awilix.models.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

}
