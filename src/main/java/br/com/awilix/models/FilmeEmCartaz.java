package br.com.awilix.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="filmes_em_cartaz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FilmeEmCartaz implements AbstractEntity {

	private static final long serialVersionUID = 6998139061100422451L;
	
	@Id
	@EqualsAndHashCode.Include
	private String imdbId;
	
	@Column(nullable = false)
	private int tmdbId;
	
	@Column(nullable = false, length = 200)
	private String titulo;
	
	@Column(nullable = true, columnDefinition = "TEXT")
	private String descricao;
	
	@Column(nullable = true)
	private Float nota;
	
	@Column(nullable = true)
	private LocalDate dataLancamento;
	
	@Column(nullable = true)
	private Integer duracao;
	
	@Column(nullable = true)
	private String poster;
	
	@Column(nullable = true)
	private String backdrop;
	
	@Column(nullable = true)
	private String trailer;
	
	@JsonManagedReference
	@OneToMany(fetch= FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "filme")
	private List<Horarios> horarios;
}