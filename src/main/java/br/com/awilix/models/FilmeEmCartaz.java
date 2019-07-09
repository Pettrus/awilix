package br.com.awilix.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.awilix.enums.Linguagem;
import info.movito.themoviedbapi.model.MovieDb;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name="filmes_em_cartaz")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmeEmCartaz {

	public FilmeEmCartaz(String imdbId, int tmdbId) {
		this.imdbId = imdbId;
		this.tmdbId = tmdbId;
	}
	
	@Id
	@EqualsAndHashCode.Include
	private String imdbId;
	
	@Column(nullable = false)
	private int tmdbId;
	
	@Column(nullable = false)
	@Builder.Default
	private Boolean arquivado = false;
	
	@Column(nullable = true)
	private Float nota;
	
	@Column(nullable = true)
	private LocalDate dataLancamento;
	
	@Column(nullable = true)
	private Integer duracao;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "filme")
	@JsonIgnoreProperties("filme")
	private List<Horarios> horarios;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "filme")
	@JsonIgnoreProperties("filme")
	private List<Detalhes> detalhes;
	
	public void preencherComTmdb(MovieDb filme, Linguagem linguagem) {
		this.dataLancamento = filme.getReleaseDate().isBlank() ? null : LocalDate.parse(filme.getReleaseDate());
		this.nota = filme.getVoteAverage();
		this.duracao = filme.getRuntime();
		
		String video = filme.getVideos().size() > 0 ? filme.getVideos().get(0).getKey() : null;
		
		Detalhes detalhes = Detalhes.builder()
				.backdrop(filme.getBackdropPath())
				.titulo(filme.getTitle())
				.descricao(filme.getOverview())
				.linguagem(linguagem)
				.poster(filme.getPosterPath())
				.trailer(video)
				.filme(this)
				.build();
		this.detalhes = List.of(detalhes);
	}
}