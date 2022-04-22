package com.qdu.service.impl;

import com.qdu.repository.PublisherRepository;
import com.qdu.entity.Publisher;
import com.qdu.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository pd;

    @Autowired
    public PublisherServiceImpl(PublisherRepository pd) {
        this.pd = pd;
    }


    @Override
    public Publisher getPublisherById(int id) {
        return pd.findById(id).get();
    }

    @Override
    public Publisher getPublisherByName(String name) {
        return pd.getPublisherByName(name);
    }

    @Override
    public Publisher getPublisherByBookId(int bookId) {
        return pd.getPublisherByBookId(bookId);
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return pd.findAll();
    }

    @Override
    public Publisher createPublisher(Publisher publisher) {
        return pd.save(publisher);
    }

    @Override
    public Publisher updatePublisher(Publisher publisher) {
        return pd.save(publisher);
    }

    @Override
    public void deletePublisher(int publisher) {
        pd.deleteById(publisher);
    }
}
