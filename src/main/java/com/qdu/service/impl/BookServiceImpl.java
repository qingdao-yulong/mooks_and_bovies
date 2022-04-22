package com.qdu.service.impl;

import com.qdu.repository.BookRepository;
import com.qdu.repository.BookReviewRepository;
import com.qdu.entity.Book;
import com.qdu.entity.BookReview;
import com.qdu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private BookReviewRepository brd;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookReviewRepository brd) {
        this.bookRepository = bookRepository;
        this.brd = brd;
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> getBooksByAuthor(int authorId) {
        return bookRepository.getBooksByAuthor(authorId);
    }

    @Override
    public Book getBookByEnglishName(String englishName) {
        return bookRepository.getBookByEnglishName(englishName);
    }

    @Override
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.getBooksByGenre(genre);
    }

    @Override
    public List<Book> getBooksByPublisher(int publisherId) {
        return bookRepository.getBooksByPublisher(publisherId);
    }

    @Override
    public List<Book> getBooksByCountry(String country) {
        return bookRepository.getBooksByCountry(country);
    }

    @Override
    public List<Book> getBooksByLanguage(String language) {
        return bookRepository.getBooksByLanguage(language);
    }

    public int getBookRating(int book) {
        List<BookReview> reviewList = brd.getBookReviewsByBookIdOrderByCreatedAtDesc(book);
        if (null != reviewList) {
            int sum = 0;
            for (BookReview br : reviewList) {
                sum += br.getRating();
            }
            return sum / reviewList.size();
        } else {
            return 0;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(int book) {
        bookRepository.deleteById(book);
    }
}
