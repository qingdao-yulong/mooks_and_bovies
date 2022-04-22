package com.qdu.repository;

import com.qdu.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    public List<Book> getBooksByAuthor(int authorId);

    public Book getBookByEnglishName(String Name);

    public List<Book> getBooksByGenre(String genre);

    public List<Book> getBooksByPublisher(int publisherId);

    public List<Book> getBooksByCountry(String country);

    public List<Book> getBooksByLanguage(String language);
}
