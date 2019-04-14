package br.com.awilix.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.repository.FilmeRepository;
import br.com.awilix.repository.HorariosRepository;
import br.com.awilix.tmdb.TmdbWrapper;

@RunWith(MockitoJUnitRunner.class)
public class CinemaServiceTest {
	
	@Mock
	private HorariosRepository horarioRepository;
	
	@Mock
	private FilmeRepository filmeRepository;
	
	@Mock
	private TmdbWrapper wrapper;

	@InjectMocks
	private CinemaService service;
	
	@BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);  
    }
	
	@Test
	void listarFilmes() {
		Set<FilmeEmCartaz> lista = new HashSet<FilmeEmCartaz>();
		lista.add(new FilmeEmCartaz("t4123", 12312, "Filme 1"));
		lista.add(new FilmeEmCartaz("t35435", 3453, "Filme 2"));
		
		when(service.listarFilmes()).thenReturn(lista);
		
		assertThat(service.listarFilmes()).isNotEmpty();
	}
	
	@Test
	void horariosFilme() {
		assertThat(service.horariosFilme(897)).isNotNull();
	}
	
	@Test
	void retornaIdTmdb() {
		assertThat(service.retornaIdTmdb("tt2386490")).isNotNull();
	}
}
