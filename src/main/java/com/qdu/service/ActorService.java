package com.qdu.service;

import com.qdu.entity.Actor;

import java.util.List;

public interface ActorService {

    public Actor getActorById(int id);

    public Actor getActorByMovieId(int movieId);

    public List<Actor> getActorByFirstName(String firstName);

    public Actor getActorByLastName(String lastName);

    public Actor getActorByName(String firstName, String lastName);

    public List<Actor> getActorByPob(String pob);

    public List<Actor> getAllActors();

    public Actor createActor(Actor actor);

    public Actor updateActor(Actor actor);

    public void deleteActor(int actor);

}
