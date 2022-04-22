package com.qdu.service.impl;

import com.qdu.repository.BookReviewRepository;
import com.qdu.entity.BookReview;
import com.qdu.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookReviewServiceImpl implements BookReviewService {

    private final BookReviewRepository bookReviewRepository;

    @Autowired
    public BookReviewServiceImpl(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    @Override
    public BookReview getBookReviewById(int id) {
        return bookReviewRepository.findById(id).get();
    }

    @Override
    public List<BookReview> getBookReviewsByBook(int book) {
        return bookReviewRepository.getBookReviewsByBookIdOrderByCreatedAtDesc(book);
    }

    @Override
    public List<BookReview> getBookReviewsByUser(int user) {

        return bookReviewRepository.getBookReviewsByUserId(user);
    }

    @Override
    public BookReview createBookReview(BookReview bookReview) {
        return bookReviewRepository.save(bookReview);
    }

    @Override
    public BookReview updateBookReview(BookReview bookReview) {
        return bookReviewRepository.save(bookReview);
    }

    @Override
    public void deleteBookReview(int bookReview) {
        bookReviewRepository.deleteById(bookReview);
    }

}
