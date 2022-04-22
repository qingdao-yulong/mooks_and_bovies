package com.qdu.repository;

import com.qdu.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("from Author where id = (select author from Book where id = ?1)")
    public Author getAuthorByBookId(int movieId);

    public List<Author> getAuthorByFirstName(String firstName);

    public Author getAuthorByLastName(String lastName);

    public List<Author> getAuthorsByPob(String pob);

}
