package com.mathjazz.guihttpapijdbc;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsDaoImpl newsDaoImpl;

    String myNews;

    public void takeNews() {

//          String url = "https://cat-fact.herokuapp.com/facts/random";
//          String url = "https://aws.random.cat/meow";
//          String url = "numbersapi.com/random/math";
            String url = "http://www.boredapi.com/api/activity/";
            RestTemplate restTemplate = new RestTemplate();

            JsonNode result = restTemplate.getForObject(url, JsonNode.class).get("activity");
            myNews = result.asText("Coś poszło nie tak...");
//            return result;
        }


    @GetMapping("/newses")
    public String getALlNewses(Model model) {
        List<News> newses = newsDaoImpl.findAllNewses();
        takeNews();

        model.addAttribute("newNews", new News());
        model.addAttribute("newses", newses);
        model.addAttribute("myNews", myNews);
        //        model.addAttribute("takeNews", takeNews());
        return "newses";
    }

    @GetMapping("/edition")
    public String getNewses(Model model) {
        List<News> newses = newsDaoImpl.findAllNewses();

        model.addAttribute("newNews", new News());
        model.addAttribute("newses", newses);
        model.addAttribute("myNews", myNews);

        return "edition";
    }

    @PostMapping("/save-news")
    public String newNews() {
        News addNews = new News();

        addNews.setId(0);
        addNews.setText(myNews);
        newsDaoImpl.saveNews(addNews.getId(), addNews.getText());

        return "redirect:/newses";
    }
//
//    @PostMapping("/news-by-id")
//    public String getOneNews(@ModelAttribute News news) {
//            myNewsId = news.getId();
//            myNews = newsDaoImpl.findOneNews(myNewsId);
//
//        return "redirect:/newses";
//    }

    @PostMapping("/remove-news")
    public String removeNews(@ModelAttribute News news) {
        newsDaoImpl.deleteNews(news.getId());

        return "redirect:/newses";

    }

    @PostMapping("/modify-news")
    public String modifyNews(@ModelAttribute News news) {
        int n = news.getText().length() - 1;
        newsDaoImpl.updateNews(news.getId(), news.getText().substring(0, n));

        return "redirect:/newses";

    }


}
