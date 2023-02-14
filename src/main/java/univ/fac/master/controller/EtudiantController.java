package univ.fac.master.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import univ.fac.master.entities.AuthenticationBean;
import univ.fac.master.entities.CustomEtudiantDetails;
import univ.fac.master.entities.Etudiant;
import univ.fac.master.entities.Sujet;
import univ.fac.master.repositories.EtudiantRepository;
import univ.fac.master.service.EtudiantService;

//@Controller
@CrossOrigin(origins ="http://localhost:4200/")
@RestController

public class AppController {

	@Autowired
	private EtudiantRepository er;
	
	@Autowired
	private EtudiantService es;
	
	//private CustomEtudiantDetails cd;
	
	@GetMapping(path = "/basicauth")
    public AuthenticationBean basicauth() {
		//Long id = cd.getId();
		//System.out.print(id);
		
        return new AuthenticationBean("You are authenticated");
    }
	

	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	/*@RequestMapping("login")
		public void login(@RequestBody Etudiant etudiant) {
			System.out.println(etudiant.getEmail());
			System.out.println(etudiant.getPassword());
			Etudiant et =(Etudiant) cd.loadUserByUsername(etudiant.getEmail());
			if(et!=null) {
				if(et.getVerificationCode()==null) {
					System.out.println(" email");
				
				}
				else {
					System.out.println("verfier votre email");
				
				}
			}
			else {
				System.out.println(" email incorrect");
			
				
			}
		
		
		}*/
	

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("etudiant", new Etudiant());
		
		return "signup_form";
	}
	
	@GetMapping("etudiant/{email}")
	public Etudiant getEtudiantByEmail(@PathVariable String email){
		return es.getEtudiantByEmail(email);
	}
	
	@PostMapping("process_register")
	public Etudiant processRegister(@RequestBody Etudiant etudiant, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(etudiant.getPassword());
		etudiant.setPassword(encodedPassword);*/
		es.register(etudiant, getSiteURL(request));
		Etudiant et =er.save(etudiant);
		
		//System.out.print(etudiant.getEmail());
		return  et;
	}
	
    private String getSiteURL(HttpServletRequest request) {
	      String siteURL = request.getRequestURL().toString();
	      return siteURL.replace(request.getServletPath(), "");
	}
    
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (es.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

	
	@GetMapping("/etudiants")
	public String listEtudiants(Model model) {
		List<Etudiant> listEtudiants = er.findAll();
		model.addAttribute("listEtudiants", listEtudiants);
		
		return "etudiants";
	}
	
}
