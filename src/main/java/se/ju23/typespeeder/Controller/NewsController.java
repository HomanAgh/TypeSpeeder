package se.ju23.typespeeder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import se.ju23.typespeeder.entity.News;
import se.ju23.typespeeder.service.NewsService;

import java.util.List;

public class NewsController {

    @Autowired
    private NewsService newsService;

    public void displayNews() {
        List<News> newsList = newsService.getAllNews();
        newsList.forEach(news -> {
            System.out.println("Title: " + news.getTitle());
            System.out.println("Content: " + news.getContent());
            System.out.println("Published: " + news.getPublishDate());
        });
    }
}
