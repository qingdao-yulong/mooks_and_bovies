package com.qdu.service;

import com.qdu.entity.Screenwriter;

import java.util.List;

public interface ScreenwriterService {

    public Screenwriter getScreenwriterById(int id);

    public Screenwriter getScreenwriterByName(String firstName, String lastName);

    public List<Screenwriter> getScreenwriterByFirstName(String firstName);

    public Screenwriter getScreenwriterByLastName(String lastName);

    public Screenwriter getScreenwriterByMovieId(int movieId);

    public List<Screenwriter> getScreenwriterByPob(String pob);

    public List<Screenwriter> getAllScreenwriters();

    public Screenwriter createScreenwriter(Screenwriter screenwriter);

    public Screenwriter updateScreenwriter(Screenwriter screenwriter);

    public void deleteScreenwriter(int screenwriter);

}
