package com.qdu.repository;

import com.qdu.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    public Publisher getPublisherByName(String name);

    @Query("from Publisher where id = (select publisher from Book where id = ?1)")
    public Publisher getPublisherByBookId(int bookId);

}
