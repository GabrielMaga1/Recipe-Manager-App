package PasteApp3.PasteApp3.RecipeGeneratorApp;

import PasteApp3.PasteApp3.RecipeGeneratorApp.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RecipeGeneratorAppApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(RecipeGeneratorAppApplication.class, args);
	}
	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public void run(String... args) throws Exception {

	}
}
