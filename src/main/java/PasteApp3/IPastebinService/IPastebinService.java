package PasteApp3.IPastebinService;

import PasteApp3.Entity.Pastebin;

import java.util.List;
import java.util.Optional;

public interface IPastebinService {
    List<Pastebin> getAllPastes();
    void createPost(Pastebin pastebin);
    void deletePost(Long id);
    Optional<Pastebin> findPost(Long id);
}
