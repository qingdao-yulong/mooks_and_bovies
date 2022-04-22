package com.qdu.controller;

import com.qdu.entity.Actor;
import com.qdu.entity.Director;
import com.qdu.entity.Movie;
import com.qdu.helper.ImageUploadHelper;
import com.qdu.service.ActorService;
import com.qdu.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/actors")
public class ActorController {

    private ActorService actorService;
    private MovieService movieService;
    private ImageUploadHelper imageUploadHelper;
    
    @Autowired
    public ActorController(ActorService actorService, MovieService movieService, ImageUploadHelper imageUploadHelper) {
        this.actorService = actorService;
        this.movieService = movieService;
        this.imageUploadHelper = imageUploadHelper;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Actor> actors = actorService.getAllActors();
        model.addAttribute("actors", actors);

        return "actors/index";
    }

    @GetMapping("/create")
    public String toCreate(Model model) {
        return "actors/create";
    }

    @PostMapping("/create")
    public RedirectView create(Model model, @ModelAttribute Actor actor, @RequestParam("fileImage") MultipartFile image) throws IOException {
        String fileName = imageUploadHelper.saveImage("actors/", image);
        actor.setImage(fileName);
        actorService.createActor(actor);
        return new RedirectView("/actors", false);
    }

    @GetMapping("/edit/{actorId}")
    public String toEdit(Model model, @PathVariable int actorId) {
        Actor a = actorService.getActorById(actorId);
        model.addAttribute("actor", a);
        return "actors/edit";
    }

    @PostMapping("/edit/{actorId}")
    public RedirectView edit(Model model, @PathVariable int actorId, @ModelAttribute Actor actor, @RequestParam("fileImage") MultipartFile image) throws IOException {
        actor.setId(actorId);
        // Check if image field is populated, if not, use the previous image
        if (!image.getOriginalFilename().equals("")) {
            String fileName = imageUploadHelper.saveImage("actors/", image);
            actor.setImage(fileName);
        } else {
            Actor a = actorService.getActorById(actorId);
            actor.setImage(a.getImage());
        }
        actorService.updateActor(actor);
        return new RedirectView("/actors", false);
    }

    @GetMapping("/show/{actorId}")
    public String show(Model model, @PathVariable int actorId) {
        Actor a = actorService.getActorById(actorId);
        List<Movie> movies = movieService.getMoviesByStarring(actorId);

        model.addAttribute("actor", a);
        model.addAttribute("movies", movies);

        return "actors/actor_profile";
    }

    @GetMapping("/delete/{actorId}")
    @ResponseBody
    public void delete(Model model, @PathVariable int actorId) {
        actorService.deleteActor(actorId);
    }
}
