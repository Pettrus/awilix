package br.com.awilix.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "fcm")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fcm {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "token")
	@NotNull
	private String token;
}