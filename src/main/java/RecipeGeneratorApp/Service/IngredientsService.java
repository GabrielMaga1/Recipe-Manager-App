package RecipeGeneratorApp.Service;

import RecipeGeneratorApp.Repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientsService {
    @Autowired
    private IngredientsRepository ingredientsRepository;
}
