package com.qdu.service.impl;

import com.qdu.entity.Author;
import com.qdu.repository.AuthorRepository;
import com.qdu.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getAuthorById(int id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public Author getAuthorByBookId(int movieId) {
        return authorRepository.getAuthorByBookId(movieId);
    }

    @Override
    public List<Author> getAuthorByFirstName(String firstName) {
        return authorRepository.getAuthorByFirstName(firstName);
    }

    @Override
    public Author getAuthorByLastName(String lastName) {
        return authorRepository.getAuthorByLastName(lastName);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> getAuthorsByPob(String pob) {
        return authorRepository.getAuthorsByPob(pob);
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author editAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(int authorId) {
        authorRepository.deleteById(authorId);
    }
}
