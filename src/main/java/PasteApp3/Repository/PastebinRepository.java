package PasteApp3.Repository;

import PasteApp3.Entity.Pastebin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PastebinRepository extends JpaRepository<Pastebin,Long> {
}
