package com.qdu.service;

import com.qdu.entity.BookReview;

import java.util.List;

public interface BookReviewService {

    public BookReview getBookReviewById(int id);

    public List<BookReview> getBookReviewsByBook(int book);

    public List<BookReview> getBookReviewsByUser(int user);

    public BookReview createBookReview(BookReview bookReview);

    public BookReview updateBookReview(BookReview bookReview);

    public void deleteBookReview(int bookReview);

}
