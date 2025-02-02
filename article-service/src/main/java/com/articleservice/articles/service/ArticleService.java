package com.articleservice.articles.service;

import com.articleservice.articles.model.Article;
import com.articleservice.articles.repository.ArticleRepository;
import com.articleservice.articles.dto.ReportResponse;
import com.articleservice.articles.exception.ArticleNotFoundException;
import com.articleservice.articles.exception.DuplicateArticleException;
import com.articleservice.articles.exception.InvalidRequestException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Service layer for handling business logic related to articles.
 * This class manages article creation, deletion, and reporting.
 */
@Service
@Transactional
public class ArticleService {

    private final ArticleRepository repository;

    // Regular expression pattern to extract the domain from a URL.
    private static final Pattern DOMAIN_PATTERN = Pattern.compile("https?://(?:www\\.)?([^/]+)");

    // Constructor-based dependency injection for the ArticleRepository.
    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    /**
     * Adds a new article to the database. Throws InvalidRequestException If the article URL is empty
     * Throws DuplicateArticleException If an article with the same URL already exists.
     */
    public void addArticle(Article article) {
        if (article.getUrl() == null || article.getUrl().trim().isEmpty()) {
            throw new InvalidRequestException("Article URL cannot be empty.");
        }

        if (repository.findByUrl(article.getUrl()).isPresent()) {
            throw new DuplicateArticleException("An article with this URL already exists.");
        }

        repository.save(article);
    }

    /**
     * Deletes an article by its URL.
     * Throws InvalidRequestException If the URL is empty, ArticleNotFoundException If no article with the given URL exists. 
     */
    public void deleteArticle(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new InvalidRequestException("URL parameter is required for deletion.");
        }

        Optional<Article> article = repository.findByUrl(url);
        if (!article.isPresent()) {
            throw new ArticleNotFoundException("Article not found: " + url);
        }

        repository.delete(article.get());
    }

    // Generates a report of articles grouped by domain for a given country.
    public List<ReportResponse> getReportByCountry(String countryCode) {
        return repository.findByCountryCode(countryCode).stream()
                .collect(Collectors.groupingBy(
                        this::extractDomain, // Groups articles by domain
                        Collectors.summarizingInt(Article::getSocialScore) // Aggregates social scores
                ))
                .entrySet().stream()
                .map(entry -> new ReportResponse(
                        entry.getKey(),
                        entry.getValue().getCount(),
                        (int) entry.getValue().getSum()
                ))
                .sorted(Comparator.comparingInt(ReportResponse::getAggSocialScore).reversed()) // Sort by highest social score
                .collect(Collectors.toList());
    }

    // Extracts the domain or "unknown" if extraction fails from a given article's URL.
    private String extractDomain(Article article) {
        Matcher matcher = DOMAIN_PATTERN.matcher(article.getUrl());
        return matcher.find() ? matcher.group(1) : "unknown";
    }
}
