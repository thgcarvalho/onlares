package br.com.onlares.email;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * @author Thiago Carvalho
 * 
 */
public class SendEmail {

	public static final String CONTENT_TEXT_PLAIN = "text/plain; charset=utf-8";
	public static final String CONTENT_TEXT_HTML = "text/html; charset=utf-8";
    // servidor
    private String mailSMTPServer = null;
    private String mailSMTPServerPort = null;
    private static final Pattern RFC2822 = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
    private Date date = null;
    // console
    private boolean log = true;
    private boolean debug = false;

    public SendEmail(String smtpServer, String smtpPort) {
        // servidor
        mailSMTPServer = smtpServer;
        mailSMTPServerPort = smtpPort;
        // console
        log = true;
        debug = false;
    } // SendEmail
    
    /**
     * public void send(Login login, String to, String subject, StringBuffer message, File attachFile);
     * 		Envia email para vários destinatários e com anexo.
     * 		Para enviar para vários destinatários os endereços devem ser separados por um espaço em branco " ".
     * 		Para enviar sem anexo o @param attachFile deve ser null.
     * @param login
     * @param to
     * @param subject
     * @param message
     * @param attachFile
     * @throws Exception 
     */
	public void send(Login login, String to, String subject, StringBuffer message, String content, File attachFile) throws Exception{
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", mailSMTPServer);
		props.put("mail.smtp.port", mailSMTPServerPort);
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.user", login.getUsername());

		Session session = Session.getDefaultInstance(props, login);
		session.setDebug(isDebug());

		Message mimeMessage = new MimeMessage(session);
		
        if (log) {
            System.out.println("De: " + login.getUsername());
            System.out.println("Para: " + to);
            System.out.println("Assunto: "  + (subject.length() <= 30 ? subject : subject.substring(0, 30) + "..."));
            System.out.println("Texto: "  + (message.length() <= 30 ? message : message.substring(0, 30) + "..."));
        }
  
        try {
            if (date == null){
                date = new Date();
            }
           
            mimeMessage.setSentDate(date);
            
            String[] arTo = to.split(" ");
            if (arTo.length == 1) {
            	// setando o destinat�rio
            	System.out.println("setando o destinat�rio");
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            } else {
            	 // setando os destinat�rios
            	System.out.println("setando os destinat�rios");
                InternetAddress[] toAddrs = InternetAddress.parse(to, false);
                mimeMessage.setRecipients(Message.RecipientType.TO, toAddrs);
            }
            
            // setando a origem do email
            mimeMessage.setFrom(new InternetAddress(login.getUsername()));
            // setando o assunto
            mimeMessage.setSubject(subject);
            // setando o conteúdo/corpo do email
            if (attachFile != null) {
                // verifica se o arquivo existe no sistema
            	Multipart multipart = null;
                if (attachFile.exists()) {
                    String fileName = attachFile.getName();
                    // cria o corpo do email em duas pater
                    BodyPart messageBodyPart = new MimeBodyPart();
                    // parte 1 - seta o texto do email
                    messageBodyPart.setText(message.toString());
                    multipart = new MimeMultipart();
                    multipart.addBodyPart(messageBodyPart);
                    // parte 2 - adiciona o anexo
                    messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(attachFile);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(fileName);
                    multipart.addBodyPart(messageBodyPart);
                } else {
                    throw new FileNotFoundException();
                }
            	if (multipart != null) {
            		  mimeMessage.setContent(multipart);
            	}
                if (log) {
                    System.out.println("Anexo adicionado!");
                }
            } else {
                // mimeMessage.setContent(message.toString(), "text/plain");
        		mimeMessage.setContent(message.toString(), content);
            }

            mimeMessage.saveChanges();
       
            if (log) {
                System.out.println("Enviando email...");
            }
            
            Transport transport = session.getTransport("smtp");
            transport.connect(mailSMTPServer, login.getUsername(), login.getPassword());
            
            if (log) {
                System.out.println("Autentica��o para " + login.getUsername() + " OK!");
            }
            
            // enviando email
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            
            if (log) {
                System.out.println("Email enviado com sucesso!");
            }
            
            transport.close();
            
        } catch (AuthenticationFailedException afex) {
            if (log) {
                System.out.println("Usu�rio ou senha inv�lidos!");
            }
            Logger.getLogger(SendEmail.class.getName()).log(Level.WARNING, null, afex);
            throw new Exception("Email n�o enviado!");
            //throw new Exception("Usu�rio ou senha inv�lidos!");
        } catch (MessagingException me) {
            if (log) {
                System.out.println("N�o foi poss�vel enviar o Email");
            }
            Logger.getLogger(SendEmail.class.getName()).log(Level.WARNING, null, me);
            throw new Exception("N�o foi poss�vel enviar o Email");
        } catch (FileNotFoundException fnfe) {
        	 if (log) {
                 System.out.println("Anexo n�o encontrado");
             }
             Logger.getLogger(SendEmail.class.getName()).log(Level.WARNING, null, fnfe);
             throw new Exception("Anexo n�o encontrado");
		}
            
    }
    
    /**
     * RFC822 syntax rules are enforced
     */
    public static boolean isValidAddress(String address) {
        if (address != null && !address.equals("") && getRFC2822().matcher(address).matches()) {
            return true;
        } else {
        	System.out.println("Endereço " + address + " � inv�lido!");
            return false;
        }
      
    } // isValidAddress

    /**
     * @return the RFC2822
     */
    public static Pattern getRFC2822() {
        return RFC2822;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Indica a dada no qual o email foi criado e estava pronto para o envio.
     * Se for null a data ser� omitida
     * @param ano
     * @param mes
     * @param dia
     * @param hora
     * @param minuto
     */
    public void setDate(int ano, int mes, int dia, int hora, int minuto) {
        Calendar calendar = new GregorianCalendar();
        // Month value is 0-based. e.g., 0 for January
        mes--;
        calendar.set(ano, mes, dia, hora, minuto);
        this.date = calendar.getTime();
    }

    /**
     * @return the log
     */
    public boolean isLog() {
        return log;
    }

    /**
     * @param log the log to set
     */
    public void setLog(boolean log) {
        this.log = log;
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * Ativa o debug do JavaMail, por padr�o o debug est� desativiado
     * @param debug - true ativa o debug do JavaMail; desativa o debug do JavaMail
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    
}
