package br.com.awilix.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;

import br.com.awilix.models.Usuario;

@Service
public class EmailService {
	
	@Autowired
	private SendGrid sendGridClient;

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
}