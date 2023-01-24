package PasteApp3;

import PasteApp3.Entity.Pastebin;
import PasteApp3.Repository.PastebinRepository;
import PasteApp3.Service.PastebinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class PasteApp3Application implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(PasteApp3Application.class, args);
	}
	@Autowired
	private PastebinRepository pastebinRepository;

	@Override
	public void run(String... args) throws Exception {

	}
}
