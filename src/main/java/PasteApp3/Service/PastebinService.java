package PasteApp3.Service;
import java.util.List;
import java.util.Optional;

import PasteApp3.Entity.Pastebin;
import PasteApp3.IPastebinService.IPastebinService;
import PasteApp3.Repository.PastebinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PastebinService implements IPastebinService {
    @Autowired
    private PastebinRepository pastebinRepository;

    @Override
    public List<Pastebin> getAllPastes(){
        return pastebinRepository.findAll();
    }
    @Override
    public void createPost(Pastebin pastebin) {
        pastebinRepository.save(pastebin);
    }
    @Override
    public void deletePost(Long id) {
        pastebinRepository.deleteById(id);
    }
    @Override
    public Optional<Pastebin> findPost(Long id) {
       return pastebinRepository.findById(id);
    }
}
