package PasteApp3.Controller;

import PasteApp3.Entity.Pastebin;
import PasteApp3.Service.PastebinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PastebinController {
    @Autowired
    private PastebinService pastebinService;


    @GetMapping(path = "/posts")
    public String getPosts(Model model){
        model.addAttribute("pastebin", pastebinService.getAllPastes());
        return "posts";
    }
    @GetMapping(path = "/posts/new-post")
    public String newPost(Model model){
        Pastebin pastebin = new Pastebin();
        model.addAttribute("pastebin", pastebin);
        return "new-post";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePost(@ModelAttribute("pastebin") Pastebin pastebin){
        pastebinService.createPost(pastebin);
        return "redirect:/posts";
    }


    @GetMapping(path = "/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        pastebinService.deletePost(id);
        return "redirect:/posts";
    }
    @GetMapping(path = "/edit-post/{id}")
    public String editPost(@PathVariable Long id, Model model){
        model.addAttribute("pastebin", pastebinService.findPost(id));
        return "edit-post";
    }
}
