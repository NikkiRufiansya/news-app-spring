package com.nikki.webnews.controller.news;


import com.nikki.webnews.model.News;
import com.nikki.webnews.repository.NewsRepository;
import com.nikki.webnews.service.NewsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Controller
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public String viewHomePage(Model model) {
        List<News> newsList = newsRepository.findAll();
        model.addAttribute("newsList", newsList);
        return "news";
    }

    @GetMapping("/add-news")
    public String showAddNewsForm(Model model) {
        model.addAttribute("news", new News());
        return "add-news";
    }


    @GetMapping("/news/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<News> news) throws ServletException, IOException {
        System.out.println("Id : " + id);
        news = newsService.getNewsById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(news.get().getImage());
        response.getOutputStream().close();

    }


    @PostMapping("/news/add")
    public String addNews(@ModelAttribute("news") News news,
                          @RequestParam("imageFile") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            news.setImage(file.getBytes());
        }
        newsRepository.save(news);
        return "redirect:/news";
    }

    @GetMapping("/edit-news/{id}")
    public String showEditNewsForm(@PathVariable("id") long id, Model model) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid news Id: " + id));
        model.addAttribute("news", news);
        return "edit-news";
    }

    @PostMapping("/news/edit/{id}")
    public String updateNews(@PathVariable("id") long id,
                             @ModelAttribute("news") News news,
                             @RequestParam("imageFile") MultipartFile file) throws IOException {
        News existingNews = newsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid news Id: " + id));

        existingNews.setTitle(news.getTitle());
        existingNews.setDescription(news.getDescription());
        existingNews.setContent(news.getContent());

        if (!file.isEmpty()) {
            existingNews.setImage(file.getBytes());
        }

        newsRepository.save(existingNews);
        return "redirect:/news";
    }

    @GetMapping("/news/delete/{id}")
    public String deleteNews(@PathVariable("id") long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid news Id: " + id));

        // Menghapus file terkait jika ada
        String fileName = news.getFileName();
        if (fileName != null && !fileName.isEmpty()) {
            deleteFile(fileName);
        }

        newsRepository.delete(news);
        return "redirect:/news";
    }


    private void deleteFile(String fileName) {
        // Logika untuk menghapus file dari sistem file
        // Misalnya:
        String directory = "src/main/resources/static/images/";
        String fullPath = directory + fileName;
        String absolutePath = ServletUriComponentsBuilder.fromCurrentContextPath().path(fullPath).toUriString();

        File file = new File(absolutePath);
        if (file.exists()) {
            file.delete();
        }
    }

}