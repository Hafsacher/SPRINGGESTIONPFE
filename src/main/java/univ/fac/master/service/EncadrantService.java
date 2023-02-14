package univ.fac.master.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;
import univ.fac.master.entities.Encadrant;
import univ.fac.master.entities.Etudiant;
import univ.fac.master.repositories.EncadrantRepository;
import univ.fac.master.repositories.EtudiantRepository;
@Service
public class EncadrantService {

	@Autowired
    private EncadrantRepository er;
     
    @Autowired
    private PasswordEncoder passwordEncoder;
     
    @Autowired
    private JavaMailSender mailSender;
 
     
    public void register(Encadrant encadrant, String siteURL) 
    	throws UnsupportedEncodingException, MessagingException {
    	String encodedPassword = passwordEncoder.encode(encadrant.getPassword());
    	encadrant.setPassword(encodedPassword);
    	     
    	String randomCode = RandomString.make(64);
    	encadrant.setVerificationCode(randomCode);
    	encadrant.setEnabled(false);
    	     
    	er.save(encadrant);
    	     
    	sendVerificationEmail(encadrant, siteURL);
    	}
     
    
     
    public void sendVerificationEmail(Encadrant encadrant, String siteURL) 
    		throws MessagingException, UnsupportedEncodingException {
        String toAddress = encadrant.getEmail();
        String fromAddress = "Your email address";
        String senderName = "Your company name";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";
         
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
         
        content = content.replace("[[name]]", encadrant.getPrenom());
        String verifyURL = siteURL + "/verify_encadran?code=" + encadrant.getVerificationCode();
         
        content = content.replace("[[URL]]", verifyURL);
         
        helper.setFrom("projetpfemaster@gmail.com",senderName);
        helper.setTo(encadrant.getEmail());
        helper.setSubject(subject);
        helper.setText(content, true);
         
        mailSender.send(message);
         
    }
    
    public boolean verify(String verificationCode) {
    	Encadrant encadrant = er.findByVerificationCode(verificationCode);
         
        if (encadrant == null || encadrant.isEnabled()) {
            return false;
        } else {
        	//er.enabled(etudiant.getId());
        	encadrant.setVerificationCode(null);
        	encadrant.setEnabled(true);
            er.save(encadrant);
             
            return true;
        }
         
    }
    
    public Encadrant getEncadrantByEmail(String email) {
		return er.findByEmail(email);
	}
}
