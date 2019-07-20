package br.com.awilix.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.awilix.exception.GeneralException;
import br.com.awilix.models.Feed;
import br.com.awilix.repository.FeedRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedService {

	private final FeedRepository repository;
	
	public void salvar(Feed feed) {
		Optional<Feed> existente = repository.findByEmail(feed.getEmail());
		
		if(existente.isPresent())
			throw new GeneralException("Email já cadastrado");
		
		repository.save(feed);
	}
}
