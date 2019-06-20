package it.uniroma3.siw.silphspa;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.silphspa.model.*;
import it.uniroma3.siw.silphspa.repository.SilphRepository;
import it.uniroma3.siw.silphspa.services.AlbumService;
import it.uniroma3.siw.silphspa.services.FotoService;
import it.uniroma3.siw.silphspa.services.FotografoService;

@Component
public class DBPopulation implements ApplicationRunner{

	@Autowired
    private SilphRepository userRepository;
	
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
            Fotografo f4 = new Fotografo("Daniele", "Quintarelli", "https://bit.ly/2IsRXZV");
            this.fotografoService.inserisciFotografo(f1);
            this.fotografoService.inserisciFotografo(f2);
            this.fotografoService.inserisciFotografo(f3);
            this.fotografoService.inserisciFotografo(f4);       
            
            
            if(albumService.MostraTutti().isEmpty()) {
            	Album a1 = new Album("Macchine sportive", f1);
                Album a2 = new Album("Architettura", f2);
                Album a3 = new Album("Natura", f2);
                Album a4 = new Album("Natura", f3);
                Album a5 = new Album("Interni", f4);
                this.albumService.inserisciAlbum(a1);
                this.albumService.inserisciAlbum(a2);
                this.albumService.inserisciAlbum(a3);
                this.albumService.inserisciAlbum(a4);
                this.albumService.inserisciAlbum(a5);
                
             
                
                if(fotoService.MostraTutti().isEmpty()) {

                    Foto ft1 = new Foto("Auto rosa", "https://bit.ly/2FidjaH", a1, f1);
                    Foto ft2 = new Foto("Auto verde", "https://bit.ly/2IVsXtm", a1, f1);
                    Foto ft3 = new Foto("Auto classica", "https://hdrihaven.com/files/gallery/L/habib%20roohzendeh_konzerthaus_7AWc.jpg", a1, f1);
                    Foto ft4 = new Foto("Auto sportiva", "https://hdrihaven.com/files/gallery/L/Mohamed%20Khaled_schadowplatz_wEVW.jpg", a1, f1);
                    Foto ft5 = new Foto("Palazzo triangolare", "https://hdrihaven.com/files/gallery/L/Danica%20Tomic_tablemountain1_1Azt.jpg", a2, f2);
                    Foto ft6 = new Foto("Casa", "https://hdrihaven.com/files/gallery/L/Azr%20Studio_waterbucktrial16k_sPNu.jpg", a2, f2);
                    Foto ft7 = new Foto("Palazzo 3000", "https://hdrihaven.com/files/gallery/L/Ehsan%20Akrami_greenpointpark4k_5a38.jpeg", a2, f2);
                    Foto ft8 = new Foto("Dettagli d'erba", "https://bit.ly/2ZD08c5", a3, f2);
                    Foto ft9 = new Foto("Finestra sul mare al tramonto", "https://bit.ly/2RmPm6J", a3, f2);
                    Foto ft10 = new Foto("Finestra sul lago", "https://bit.ly/31FLGln", a3, f2);
                    Foto ft11 = new Foto("Laghi", "https://bit.ly/31CMzv3", a3, f2);
                    Foto ft12 = new Foto("Tramonto rosa", "https://bit.ly/2Is2Fji", a4, f3);
                    Foto ft13 = new Foto("Design confort", "https://bit.ly/2WR02k2", a5, f4);
                    this.fotoService.inserisciFoto(ft1);
                    this.fotoService.inserisciFoto(ft2);
                    this.fotoService.inserisciFoto(ft3);
                    this.fotoService.inserisciFoto(ft4);
                    this.fotoService.inserisciFoto(ft5);
                    this.fotoService.inserisciFoto(ft6);
                    this.fotoService.inserisciFoto(ft7);
                    this.fotoService.inserisciFoto(ft8);
                    this.fotoService.inserisciFoto(ft9);
                    this.fotoService.inserisciFoto(ft10);
                    this.fotoService.inserisciFoto(ft11);
                    this.fotoService.inserisciFoto(ft12);
                    this.fotoService.inserisciFoto(ft13);
                       
                }
          }
        }

    
        System.out.println("Done.\n");
    }
	
}
