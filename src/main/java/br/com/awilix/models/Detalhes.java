package br.com.awilix.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.awilix.enums.Linguagem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="detalhes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Detalhes {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false)
	private Linguagem linguagem;

	@Column(nullable = false, length = 200)
	private String titulo;
	
	@Column(nullable = true, columnDefinition = "TEXT")
	private String descricao;
	
	@Column(nullable = true)
	private String poster;
	
	@Column(nullable = true)
	private String backdrop;
	
	@Column(nullable = true)
	private String trailer;
	
	@ManyToOne
	@JoinColumn(name = "filme_em_cartaz_id", nullable = false)
	@JsonIgnoreProperties("detalhes")
	private FilmeEmCartaz filme;
}
