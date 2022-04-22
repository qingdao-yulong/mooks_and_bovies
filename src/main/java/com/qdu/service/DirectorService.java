package com.qdu.service;

import com.qdu.entity.Director;

import java.util.List;

public interface DirectorService {

    public Director getDirectorById(int id);

    public Director getDirectorByMovieId(int movieId);

    public List<Director> getDirectorByFirstName(String firstName);

    public Director getDirectorByLastName(String lastName);

    public List<Director> getAllDirectors();

    public List<Director> getDirectorsByPob(String pob);

    public Director createDirector(Director director);

    public Director updateDirector(Director director);

    public void deleteDirector(int director);

}
