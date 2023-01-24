package PasteApp3.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pastebinDB")
public class Pastebin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String text;
    private String author;

    public Pastebin(String name, String text, String author) {
        this.name = name;
        this.text = text;
        this.author = author;
    }
    public Pastebin(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
