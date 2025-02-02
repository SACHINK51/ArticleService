package com.articleservice.articles.dto;

/**
 * DTO (Data Transfer Object) for representing a summarized report of articles.
 * This class is used to return structured data in API responses.
 */
public class ReportResponse {
    private String domain; // The domain extracted from the article URL
    private long urls; // The number of articles associated with the domain
    private int aggSocialScore; // The total aggregated social score of the articles

    
    // Parameterized constructor for creating a ReportResponse object.
    public ReportResponse(String domain, long urls, int aggSocialScore) {
        this.domain = domain;
        this.urls = urls;
        this.aggSocialScore = aggSocialScore;
    }

    
    // @return The domain name of the article URLs.
    public String getDomain() {
        return domain;
    }

    // @return The number of articles associated with this domain.
    public long getUrls() {
        return urls;
    }

    // @return The total aggregated social score of the articles.
    public int getAggSocialScore() {
        return aggSocialScore;
    }
}