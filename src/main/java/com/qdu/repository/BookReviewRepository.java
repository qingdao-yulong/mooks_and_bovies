package com.qdu.repository;

import com.qdu.entity.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReview, Integer> {

    public List<BookReview> getBookReviewsByBookIdOrderByCreatedAtDesc(int bookId);

    public List<BookReview> getBookReviewsByUserId(int userId);

}
