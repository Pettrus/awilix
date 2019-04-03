package br.com.awilix.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	@JsonProperty(access = Access.WRITE_ONLY)
	private String imdbId;
}