package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.News;
import se.ju23.typespeeder.entity.NewsRepository;

import java.util.List;

public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> getAllNews(){
        return newsRepository.findAll();
    }
}
