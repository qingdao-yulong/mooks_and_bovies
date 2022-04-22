package com.qdu.service.impl;

import com.qdu.repository.ScreenwriterRepository;
import com.qdu.entity.Screenwriter;
import com.qdu.service.ScreenwriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenwriterServiceImpl implements ScreenwriterService {

    private final ScreenwriterRepository sd;

    @Autowired
    public ScreenwriterServiceImpl(ScreenwriterRepository sd) {
        this.sd = sd;
    }


    @Override
    public Screenwriter getScreenwriterById(int id) {
        return sd.findById(id).get();
    }

    @Override
    public Screenwriter getScreenwriterByName(String firstName, String lastName) {
        return sd.getScreenwriterByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Screenwriter> getScreenwriterByFirstName(String firstName) {
        return sd.getScreenwriterByFirstName(firstName);
    }

    @Override
    public Screenwriter getScreenwriterByLastName(String lastName) {
        return sd.getScreenwriterByLastName(lastName);
    }

    @Override
    public Screenwriter getScreenwriterByMovieId(int movieId) {
        return sd.getScreenwriterByMovieId(movieId);
    }

    @Override
    public List<Screenwriter> getScreenwriterByPob(String pob) {
        return sd.getScreenwriterByPob(pob);
    }

    @Override
    public List<Screenwriter> getAllScreenwriters() {
        return sd.findAll();
    }

    @Override
    public Screenwriter createScreenwriter(Screenwriter screenwriter) {
        return sd.save(screenwriter);
    }

    @Override
    public Screenwriter updateScreenwriter(Screenwriter screenwriter) {
        return sd.save(screenwriter);
    }

    @Override
    public void deleteScreenwriter(int screenwriter) {
        sd.deleteById(screenwriter);
    }
}
