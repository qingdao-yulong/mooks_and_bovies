package com.qdu.controller;

import com.qdu.entity.Director;
import com.qdu.entity.Movie;
import com.qdu.helper.ImageUploadHelper;
import com.qdu.service.DirectorService;
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
@RequestMapping("/directors")
public class DirectorController {

    private DirectorService directorService;
    private ImageUploadHelper imageUploadHelper;
    private MovieService movieService;

    @Autowired
    public DirectorController(DirectorService directorService, ImageUploadHelper imageUploadHelper, MovieService movieService) {
        this.directorService = directorService;
        this.imageUploadHelper = imageUploadHelper;
        this.movieService = movieService;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Director> directors = directorService.getAllDirectors();
        model.addAttribute("directors", directors);
        return "directors/index";
    }

    @GetMapping("/create")
    public String toCreate(Model model) {
        return "directors/create";
    }

    @PostMapping("/create")
    public RedirectView create(Model model, @ModelAttribute Director director, @RequestParam("fileImage") MultipartFile image) throws IOException {
        String fileName = imageUploadHelper.saveImage("directors/", image);
        director.setImage(fileName);
        directorService.createDirector(director);
        return new RedirectView("/directors", false);
    }

    @GetMapping("/edit/{directorId}")
    public String toEdit(Model model, @PathVariable int directorId) {
        Director director = directorService.getDirectorById(directorId);
        model.addAttribute("director", director);
        return "directors/edit";
    };

    @PostMapping("/edit/{directorId}")
    public RedirectView edit(Model model, @PathVariable int directorId, @ModelAttribute Director director, @RequestParam("fileImage") MultipartFile image) throws IOException {
        director.setId(directorId);
        // Check if image field is populated, if not, use the previous image
        if (!image.getOriginalFilename().equals("")) {
            String fileName = imageUploadHelper.saveImage("directors/", image);
            director.setImage(fileName);
        } else {
            Director d = directorService.getDirectorById(directorId);
            director.setImage(d.getImage());
        }
        directorService.updateDirector(director);
        return new RedirectView("/directors", false);
    }

    @GetMapping("/show/{directorId}")
    public String show(Model model, @PathVariable int directorId) {
        Director director = directorService.getDirectorById(directorId);
        List<Movie> movies = movieService.getMoviesByDirector(directorId);

        model.addAttribute("director", director);
        model.addAttribute("movies", movies);

        return "directors/director_profile";
    }

    @GetMapping("/delete/{directorId}")
    @ResponseBody
    public void delete(Model model, @PathVariable int directorId) {
        directorService.deleteDirector(directorId);
    }

}
