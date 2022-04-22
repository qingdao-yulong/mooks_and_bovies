package com.qdu.service;

import com.qdu.entity.Book;

import java.util.List;

public interface BookService {

    public Book getBookById(int id);

    public List<Book> getBooksByAuthor(int authorId);

    public Book getBookByEnglishName(String englishName);

    public List<Book> getBooksByGenre(String genre);

    public List<Book> getBooksByPublisher(int publisherId);

    public List<Book> getBooksByCountry(String country);

    public List<Book> getBooksByLanguage(String language);

    public int getBookRating(int book);

    public List<Book> getAllBooks();

    public Book createBook(Book book);

    public Book updateBook(Book book);

    public void deleteBook(int book);
}
