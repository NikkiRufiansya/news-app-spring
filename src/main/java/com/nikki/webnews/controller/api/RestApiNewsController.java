package com.nikki.webnews.controller.api;

import com.nikki.webnews.model.News;
import com.nikki.webnews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/")
public class RestApiNewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping(path = "/news")
    @ResponseBody
    public ResponseEntity<?> getAllNews(){
        Map<String, Object> map = new LinkedHashMap<>();
        List<News> newsList = newsService.getAllNews();
        if (!newsList.isEmpty()){
            map.put("status", 200);
            map.put("message", "success");
            map.put("data", newsList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            map.clear();
            map.put("status", 404);
            map.put("message", "Data Not Found");
            map.put("data", newsList);
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

}
