package com.qdu.service;

import com.qdu.entity.Author;

import java.util.List;

public interface AuthorService {

    public Author getAuthorById(int id);

    public Author getAuthorByBookId(int movieId);

    public List<Author> getAuthorByFirstName(String firstName);

    public Author getAuthorByLastName(String lastName);

    public List<Author> getAllAuthors();

    public List<Author> getAuthorsByPob(String pob);

    public Author createAuthor(Author author);

    public Author editAuthor(Author author);

    public void deleteAuthor(int authorId);
}
