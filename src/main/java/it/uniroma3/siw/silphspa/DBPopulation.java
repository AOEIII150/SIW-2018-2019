package it.uniroma3.siw.silphspa;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.silphspa.model.*;
import it.uniroma3.siw.silphspa.repository.AlbumRepository;
import it.uniroma3.siw.silphspa.repository.FotoRepository;
import it.uniroma3.siw.silphspa.repository.FotografoRepository;
import it.uniroma3.siw.silphspa.repository.SilphRepository;
import it.uniroma3.siw.silphspa.services.AlbumService;
import it.uniroma3.siw.silphspa.services.FotoService;
import it.uniroma3.siw.silphspa.services.FotografoService;

@Component
public class DBPopulation implements ApplicationRunner{

	@Autowired
    private SilphRepository userRepository;
	
	@Autowired
	private FotografoRepository fotografoRepository;
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private FotografoService fotografoService;


    public void run(ApplicationArguments args) throws Exception {
        this.deleteAll();
        this.populateDB();
    }

    private void deleteAll() {
        System.out.println("Deleting all users from DB...");
        userRepository.deleteAll();
        if(fotoService.MostraTutti().size() == 12) {
        	 fotoRepository.deleteAll();
        }
        if(albumService.MostraTutti().size() == 4) {
        	albumRepository.deleteAll();
        }
        if(fotografoService.MostraTutti().size() == 3) {
        	fotografoRepository.deleteAll();
        }  
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
        
        SilphStaff admin3 = new SilphStaff("professore", "prof", "ADMIN");
        String admin3Password = new BCryptPasswordEncoder().encode("prof");
        admin3.setPassword(admin3Password);
        admin3 = this.userRepository.save(admin3);
        
        if(fotografoService.MostraTutti().isEmpty()){
        	Fotografo f1 = new Fotografo("Simone", "Capparelli", "https://cdn.pixabay.com/photo/2015/10/20/04/12/portrait-997190_960_720.jpg");
            Fotografo f2 = new Fotografo("Martina", "Sasso", "https://bit.ly/2x2jwms");
            Fotografo f3 = new Fotografo("Chiara", "Nardi", "https://bit.ly/2FmBYuv");
            this.fotografoRepository.save(f1);
            this.fotografoRepository.save(f2);
            this.fotografoRepository.save(f3);
            
            
            if(albumService.MostraTutti().isEmpty()) {
          	  Album a1 = new Album("Macchine sportive", f1);
                Album a2 = new Album("Architettura", f2);
                Album a3 = new Album("Natura", f2);
                Album a4 = new Album("Natura", f3);
                this.albumRepository.save(a1);
                this.albumRepository.save(a2);
                this.albumRepository.save(a3);
                this.albumRepository.save(a4);
                
                if(fotoService.MostraTutti().isEmpty()) {

                    Foto ft1 = new Foto("Auto rosa", "https://bit.ly/2FidjaH", a1, f1);
                    Foto ft2 = new Foto("Auto verde", "https://bit.ly/2IVsXtm", a1, f1);
                    Foto ft3 = new Foto("Auto classica", "https://hdrihaven.com/files/gallery/L/habib%20roohzendeh_konzerthaus_7AWc.jpg", a1, f1);
                    Foto ft4 = new Foto("Auto tecnologica", "https://hdrihaven.com/files/gallery/L/Sudhanshu%20Pal_schadowplatz_xRFu.jpg", a1, f1);
                    Foto ft5 = new Foto("Palazzo triangolare", "https://hdrihaven.com/files/gallery/L/Danica%20Tomic_tablemountain1_1Azt.jpg", a2, f2);
                    Foto ft6 = new Foto("Casa", "https://hdrihaven.com/files/gallery/L/Azr%20Studio_waterbucktrial16k_sPNu.jpg", a2, f2);
                    Foto ft7 = new Foto("Palazzo 3000", "https://hdrihaven.com/files/gallery/L/Ehsan%20Akrami_greenpointpark4k_5a38.jpeg", a2, f2);
                    Foto ft8 = new Foto("Dettagli d'erba", "https://bit.ly/2ZD08c5", a3, f2);
                    Foto ft9 = new Foto("Finestra sul mare al tramonto", "https://bit.ly/2RmPm6J", a3, f2);
                    Foto ft10 = new Foto("Finestra sul lago", "https://bit.ly/31FLGln", a3, f2);
                    Foto ft11 = new Foto("Laghi", "https://bit.ly/31CMzv3", a3, f2);
                    Foto ft12 = new Foto("Tramonto rosa", "https://bit.ly/2Is2Fji", a4, f3);
                    this.fotoRepository.save(ft1);
                    this.fotoRepository.save(ft2);
                    this.fotoRepository.save(ft3);
                    this.fotoRepository.save(ft4);
                    this.fotoRepository.save(ft5);
                    this.fotoRepository.save(ft6);
                    this.fotoRepository.save(ft7);
                    this.fotoRepository.save(ft8);
                    this.fotoRepository.save(ft9);
                    this.fotoRepository.save(ft10);
                    this.fotoRepository.save(ft11);
                    this.fotoRepository.save(ft12);
                }
          }
        }
    
        System.out.println("Done.\n");
    }
	
}
