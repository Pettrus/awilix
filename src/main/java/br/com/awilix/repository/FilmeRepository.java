package br.com.awilix.repository;

import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.awilix.models.FilmeEmCartaz;

public interface FilmeRepository extends PagingAndSortingRepository<FilmeEmCartaz, Long> {

	Set<FilmeEmCartaz> findAll();
}