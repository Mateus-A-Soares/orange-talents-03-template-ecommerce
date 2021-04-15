package br.com.zupacademy.mateus.mercadolivre.shared.email;

/**
 * Deve tratar o procedimento de envio de email pela aplicação.
 *  
 * @author Mateus Soares
 */
public interface MailSender {
	
	/**
	 * Deve realizar o envio do email, com destinatário, assunto e corpo do email passados como parâmetros.
	 * 
	 * @param recipient	destinatário para o qual o email será enviado;
	 * @param subject	assunto sobre qual trata o email;
	 * @param body		corpo do email a ser enviado.
	 */
	void execute(String recipient, String subject, String body);
}
