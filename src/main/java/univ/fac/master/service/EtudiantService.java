package univ.fac.master.service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;
import univ.fac.master.entities.Etudiant;
import univ.fac.master.entities.Sujet;
import univ.fac.master.repositories.EtudiantRepository;

@Service
public class EtudiantService {

	@Autowired
    private EtudiantRepository er;
     
    @Autowired
    private PasswordEncoder passwordEncoder;
     
    @Autowired
    private JavaMailSender mailSender;
 
     
    public void register(Etudiant etudiant, String siteURL) 
    	throws UnsupportedEncodingException, MessagingException {
    	String encodedPassword = passwordEncoder.encode(etudiant.getPassword());
    	etudiant.setPassword(encodedPassword);
    	     
    	String randomCode = RandomString.make(64);
    	etudiant.setVerificationCode(randomCode);
    	etudiant.setEnabled(false);
    	     
    	er.save(etudiant);
    	     
    	sendVerificationEmail(etudiant, siteURL);
    	}
     
    
     
    public void sendVerificationEmail(Etudiant etudiant, String siteURL) 
    		throws MessagingException, UnsupportedEncodingException {
        String toAddress = etudiant.getEmail();
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
         
        content = content.replace("[[name]]", etudiant.getPrenom());
        String verifyURL = siteURL + "/verify?code=" + etudiant.getVerificationCode();
         
        content = content.replace("[[URL]]", verifyURL);
         
        helper.setFrom("projetpfemaster@gmail.com",senderName);
        helper.setTo(etudiant.getEmail());
        helper.setSubject(subject);
        helper.setText(content, true);
         
        mailSender.send(message);
         
    }
    
    public boolean verify(String verificationCode) {
    	Etudiant etudiant = er.findByVerificationCode(verificationCode);
         
        if (etudiant == null || etudiant.isEnabled()) {
            return false;
        } else {
        	//er.enabled(etudiant.getId());
        	etudiant.setVerificationCode(null);
            etudiant.setEnabled(true);
            er.save(etudiant);
             
            return true;
        }
         
    }
    
    public Etudiant getEtudiantByEmail(String email) {
		return er.findByEmail(email);
	}
}
