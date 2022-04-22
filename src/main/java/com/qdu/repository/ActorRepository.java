package com.qdu.repository;

import com.qdu.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Query("from Actor where id = (select starring from Movie where id = ?1)")
    public Actor getActorByMovieId(int movieId);

    public List<Actor> getActorByFirstName(String firstName);

    public Actor getActorByLastName(String lastName);

    public Actor getActorByFirstNameAndLastName(String firstName, String lastName);

    public List<Actor> getActorByPob(String pob);

}
