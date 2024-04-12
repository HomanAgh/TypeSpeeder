package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.News;
import se.ju23.typespeeder.entity.NewsRepository;

import java.util.List;

@Service
public class NewsService {


    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNews(){
        return newsRepository.findAll();
    }

    public void displayNews() {
        List<News> newsList = getAllNews();
        newsList.forEach(news -> {
            System.out.println("Title: " + news.getTitle());
            System.out.println("Content: " + news.getContent());
            System.out.println("Published: " + news.getPublishDate());
        });
    }
}
