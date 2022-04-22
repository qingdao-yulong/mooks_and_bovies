package com.qdu.service.impl;

import com.qdu.repository.ActorRepository;
import com.qdu.entity.Actor;
import com.qdu.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    public ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Actor getActorById(int id) {
        return actorRepository.findById(id).get();
    }

    @Override
    public Actor getActorByMovieId(int movieId) {
        return actorRepository.getActorByMovieId(movieId);
    }

    @Override
    public List<Actor> getActorByFirstName(String firstName) {
        return actorRepository.getActorByFirstName(firstName);
    }

    @Override
    public Actor getActorByLastName(String lastName) {
        return actorRepository.getActorByLastName(lastName);
    }

    @Override
    public Actor getActorByName(String firstName, String lastName) {
        return actorRepository.getActorByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Actor> getActorByPob(String pob) {
        return actorRepository.getActorByPob(pob);
    }

    @Override
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor updateActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public void deleteActor(int actor) {
        actorRepository.deleteById(actor);
    }
}
