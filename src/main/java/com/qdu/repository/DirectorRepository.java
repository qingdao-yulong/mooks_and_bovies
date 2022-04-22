package com.qdu.repository;

import com.qdu.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {

    @Query("from Director where id = (select director from Movie where id = ?1)")
    public Director getDirectorByMovieId(int movieId);

    public List<Director> getDirectorByFirstName(String firstName);

    public Director getDirectorByLastName(String lastName);

    public List<Director> getDirectorsByPob(String pob);

}
