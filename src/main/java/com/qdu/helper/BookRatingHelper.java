package com.qdu.helper;

import com.qdu.entity.Book;
import com.qdu.entity.BookReview;
import com.qdu.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRatingHelper {

    private BookReviewService bookReviewService;

    @Autowired
    public BookRatingHelper(BookReviewService bookReviewService) {
        this.bookReviewService = bookReviewService;
    }

    public void getRatingsForList(List<Book> bookList) {
        float sum = 0f;
        for (Book b : bookList) {
            List<BookReview> bookReviewList = bookReviewService.getBookReviewsByBook(b.getId());
            for (BookReview br : bookReviewList) {
                sum += br.getRating();
            }
            sum = sum / bookReviewList.size();
            b.setRating(sum);
        }
    }

    public void getSingleRating(Book book) {
        float sum = 0f;
        List<BookReview> bookReviewList = bookReviewService.getBookReviewsByBook(book.getId());
        for (BookReview br : bookReviewList) {
            sum += br.getRating();
        }
        sum = sum / bookReviewList.size();
        book.setRating(sum);
    }
}
