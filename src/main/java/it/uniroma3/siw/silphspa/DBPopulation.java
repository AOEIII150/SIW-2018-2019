package it.uniroma3.siw.silphspa;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.silphspa.model.SilphStaff;
import it.uniroma3.siw.silphspa.repository.SilphRepository;

@Component
public class DBPopulation implements ApplicationRunner{

	@Autowired
    private SilphRepository userRepository;


    public void run(ApplicationArguments args) throws Exception {
        this.deleteAll();
        this.populateDB();
    }

    private void deleteAll() {
        System.out.println("Deleting all users from DB...");
        userRepository.deleteAll();
        System.out.println("Done");
    }

    private void populateDB() throws IOException, InterruptedException {

        System.out.println("Storing users...");

        SilphStaff admin = new SilphStaff("martina", "mpas", "ADMIN");
        String adminPassword = new BCryptPasswordEncoder().encode("mpas");
        admin.setPassword(adminPassword);
        admin = this.userRepository.save(admin);

        SilphStaff admin2 = new SilphStaff("simone", "spas", "ADMIN");
        String admin2Password = new BCryptPasswordEncoder().encode("spas");
        admin2.setPassword(admin2Password);
        admin2 = this.userRepository.save(admin2);

        System.out.println("Done.\n");
    }
	
}
