package br.com.awilix.services;

import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.awilix.models.Fcm;
import br.com.awilix.models.Filme;
import br.com.awilix.repository.FcmRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FcmService {

	private final FcmRepository repository;
	
	public void salvarToken(String token) {
		if(repository.findByToken(token).isEmpty())
			repository.save(Fcm.builder().token(token).build());
	}
	
	public void notificarUsuarios(List<Filme> filmes) {
		Set<String> tokens = listarTokens();
		
		for (Filme filme : filmes) {
			enviarNotificacao(tokens, "Novo filme em cartaz!", filme.getDetalhes().get(0).getTitulo() + " Já está nos cinemas",
					"https://image.tmdb.org/t/p/w92/" + filme.getDetalhes().get(0).getPoster());
		}
	}
	
	public Set<String> listarTokens() {
		return repository.listarTodosTokens();
	}
	
	public void enviarNotificacao(Set<String> tokens, String titulo, String mensagem, String icone) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "key=" + System.getenv("FCM_KEY"));
		httpHeaders.set("Content-Type", "application/json");
		   
		JSONObject msg = new JSONObject();
		JSONObject obj = new JSONObject();
		
		msg.put("title", titulo);
		msg.put("body", mensagem);
		msg.put("icon", icone);
		
		obj.put("notification", msg);
		obj.put("registration_ids", tokens);
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(obj.toString(),httpHeaders);
		restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", httpEntity,String.class);
	}
}