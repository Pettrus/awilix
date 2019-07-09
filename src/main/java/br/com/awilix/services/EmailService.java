package br.com.awilix.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;

import br.com.awilix.models.Feed;
import br.com.awilix.models.FilmeEmCartaz;
import br.com.awilix.models.Usuario;
import br.com.awilix.repository.FeedRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	
	private final SendGrid sendGridClient;
	
	private final FeedRepository feedRepository;
	
	private final TemplateEngine templateEngine;

	public void cadastroUsuario(Usuario usuario) throws IOException {
		Email from = new Email("nao-responda@awilix.com.br");
		Email para = new Email(usuario.getEmail());
		String titulo = "Bem vindo ao Awilix!";
		String mensagem = "Para ativar sua conta acesse o link: https://awilix.com.br/confirmacao-email/" + usuario.getTokenConfirmacao();
		
        Mail mail = new Mail(from, titulo, para, new Content("text/plain", mensagem));
        
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        this.sendGridClient.api(request);
	}
	
	public void notificarFilmesNovos(List<FilmeEmCartaz> filmes, String cidade) {
		try {
			List<Feed> feeds = feedRepository.findByCidade(cidade);
			Email from = new Email("nao-responda@awilix.com.br");
			
			for (FilmeEmCartaz filme : filmes) {
				String titulo = filme.getDetalhes().get(0).getTitulo();
				
				Context context = new Context();
				context.setVariable("imagem", "https://image.tmdb.org/t/p/w300/" + filme.getDetalhes().get(0).getPoster());
				context.setVariable("titulo", titulo);
				context.setVariable("descricao", filme.getDetalhes().get(0).getDescricao());
				
				String html = templateEngine.process("novo-filme", context);
		        
				for (Feed feed : feeds) {
					Email para = new Email(feed.getEmail());
					
			        Mail mail = new Mail(from, titulo + " Já nos cinemas!", para, new Content("text/html", html));
			        
			        Request request = new Request();
			        request.setMethod(Method.POST);
			        request.setEndpoint("mail/send");
			        request.setBody(mail.build());
			        this.sendGridClient.api(request);
			        
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}