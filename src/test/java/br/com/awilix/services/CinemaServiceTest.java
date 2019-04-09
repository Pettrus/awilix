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
import br.com.awilix.models.Horarios;
import br.com.awilix.repository.FilmeRepository;
import br.com.awilix.repository.HorariosRepository;

@RunWith(MockitoJUnitRunner.class)
public class CinemaServiceTest {
	
	@Mock
	private HorariosRepository horarioRepository;
	
	@Mock
	private FilmeRepository filmeRepository;

	@InjectMocks
	private CinemaService service;
	
	@BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);     
    }
	
	@Test
	void listarTodosFilmes() {
		Set<FilmeEmCartaz> lista = new HashSet<FilmeEmCartaz>();
		lista.add(new FilmeEmCartaz("t4123", "Filme 1"));
		lista.add(new FilmeEmCartaz("t35435", "Filme 2"));
		
		when(service.listarFilmes()).thenReturn(lista);
		
		assertThat(service.listarFilmes()).isNotEmpty();
	}
	
	@Test
	void horariosFilme() {
		Set<Horarios> horarios = new HashSet<Horarios>();
		horarios.add(new Horarios());
		horarios.add(new Horarios());
		
		//when(service.horariosFilme("t23432")).thenReturn(horarios);
		
		//assertThat(service.horariosFilme("t23432")).isNotEmpty();
	}
}
