package it.uniroma3.siw.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SiwCatering1Application implements CommandLineRunner {
	
//    @Autowired private CredentialsRepository credR;
//	@Autowired protected PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SiwCatering1Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
//		Admin u = new Admin();
//		u.setNome("Fabio");
//		u.setCognome("Letizia");
//		
//		Credentials c = new Credentials();
//		c.setAdmin(u);
//		c.setPassword(this.passwordEncoder.encode("fabio"));
//		c.setUsername("fabio");
//		c.setRole("ADMIN");
//		
//		credR.save(c);	
	}

}
