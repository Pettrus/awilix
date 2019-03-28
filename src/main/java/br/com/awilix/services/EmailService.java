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
        Mail mail = new Mail(new Email("awilix.com.br"), "Bem vindo ao Awilix!", new Email("pettrus.sherlock@gmail.com"), new Content("text/plain", "texto"));
        mail.setReplyTo(new Email("abc@gmail.com"));
        Request request = new Request();
        
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        this.sendGridClient.api(request);
	}
}