package com.qdu.service.impl;

import com.qdu.repository.DirectorRepository;
import com.qdu.entity.Director;
import com.qdu.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository dd;

    @Autowired
    public DirectorServiceImpl(DirectorRepository dd) {
        this.dd = dd;
    }

    @Override
    public Director getDirectorById(int id) {
        return dd.findById(id).get();
    }

    @Override
    public Director getDirectorByMovieId(int movieId) {
        return dd.getDirectorByMovieId(movieId);
    }

    @Override
    public List<Director> getDirectorByFirstName(String firstName) {
        return dd.getDirectorByFirstName(firstName);
    }

    @Override
    public Director getDirectorByLastName(String lastName) {
        return dd.getDirectorByLastName(lastName);
    }

    @Override
    public List<Director> getAllDirectors() {
        return dd.findAll();
    }

    @Override
    public List<Director> getDirectorsByPob(String pob) {
        return dd.getDirectorsByPob(pob);
    }

    @Override
    public Director createDirector(Director director) {
        return dd.save(director);
    }

    @Override
    public Director updateDirector(Director director) {
        return dd.save(director);
    }

    @Override
    public void deleteDirector(int director) {
        dd.deleteById(director);
    }
}
