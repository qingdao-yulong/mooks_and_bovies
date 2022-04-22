package com.qdu.service;

import com.qdu.entity.Publisher;

import java.util.List;

public interface PublisherService {

    public Publisher getPublisherById(int id);

    public Publisher getPublisherByName(String name);

    public Publisher getPublisherByBookId(int bookId);

    public List<Publisher> getAllPublishers();

    Publisher createPublisher(Publisher publisher);

    Publisher updatePublisher(Publisher publisher);

    void deletePublisher(int publisher);

}
