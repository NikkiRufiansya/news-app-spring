package com.nikki.webnews.controller.news;


import com.nikki.webnews.model.News;
import com.nikki.webnews.repository.NewsRepository;
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

@Controller
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;

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



    @PostMapping("/news/add")
    public String addNews(@ModelAttribute("news") News news,
                          @RequestParam("imageFile") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            news.setImage(file.getBytes());
        }
        String uploadDir = "src/main/resources/static/images/";
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path filePath = Paths.get(uploadDir + fileName);
        Files.write(filePath, file.getBytes());

        news.setImage(fileName.getBytes());
        news.setFileName(fileName);
        newsRepository.save(news);
        return "redirect:/";
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
        News existingNews = newsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid news Id: " + id));

        existingNews.setTitle(news.getTitle());
        existingNews.setDescription(news.getDescription());
        existingNews.setContent(news.getContent());

        if (!file.isEmpty()) {
            byte[] imageBytes = file.getBytes();
            existingNews.setImage(imageBytes);
            existingNews.setFileName(file.getOriginalFilename());

            // Simpan file gambar baru ke dalam direktori yang sesuai
            String uploadDir = "src/main/resources/static/images/";
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());
            existingNews.setFileName(fileName);
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