package com.articleservice.articles.controller; // Package declaration

import com.articleservice.articles.model.Article;
import com.articleservice.articles.service.ArticleService;
import com.articleservice.articles.dto.ReportResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for handling article-related API requests.
 * The base URL for all endpoints is `/api`.
 */
@RestController
@RequestMapping("/api")
public class ArticleController {

    private final ArticleService service;

    // Constructor-based dependency injection for ArticleService.
    public ArticleController(ArticleService service) {
        this.service = service;
    }

    // Adds a new article to the system.
    @PostMapping("/article")
    public ResponseEntity<String> addArticle(@RequestBody Article article) {
        service.addArticle(article);
        return ResponseEntity.ok("Article added successfully!");
    }

    // Deletes an article based on its URL.
    @DeleteMapping("/article")
    public ResponseEntity<String> deleteArticle(@RequestParam String url) {
        service.deleteArticle(url);
        return ResponseEntity.ok("Article deleted successfully!");
    }

    // Retrieves a report of articles based on the given country code.
    @GetMapping("/report/{countryCode}")
    public ResponseEntity<List<ReportResponse>> getReport(@PathVariable String countryCode) {
        return ResponseEntity.ok(service.getReportByCountry(countryCode));
    }
}
