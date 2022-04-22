package com.qdu.repository;

import com.qdu.entity.Screenwriter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenwriterRepository extends JpaRepository<Screenwriter, Integer> {

    public Screenwriter getScreenwriterByFirstNameAndLastName(String firstName, String lastName);

    public List<Screenwriter> getScreenwriterByFirstName(String firstName);

    public Screenwriter getScreenwriterByLastName(String lastName);

    @Query("from Screenwriter where id = (select screenwriter from Movie where id = ?1)")
    public Screenwriter getScreenwriterByMovieId(int movieId);

    public List<Screenwriter> getScreenwriterByPob(String pob);

}
