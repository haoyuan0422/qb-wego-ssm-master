package com.wego.entity.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Article {
    private Long articleId;
//    private Integer articleUserId;
    private String articleTitle;
    private String articleContent;
//    private Integer articleCommentCount;
//    private Integer articleLikeCount;
    private LocalDateTime articleCreateTime;

    /**
     * 更新时间
     */
    private LocalDateTime articleUpdateTime;
//    private User user;

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
//                ", articleUserId=" + articleUserId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContent='" + articleContent + '\'' +
//                ", articleCommentCount=" + articleCommentCount +
//                ", articleLikeCount=" + articleLikeCount +
                ", articleCreateTime='" + articleCreateTime + '\'' +
                ", articleUpdateTime =" + articleUpdateTime +
                '}';
    }

    public Article() {
    }

    public Article(Long articleId, String articleTitle, String articleContent, LocalDateTime articleCreateTime,LocalDateTime articleUpdateTime) {
        this.articleId = articleId;
//        this.articleUserId = articleUserId;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
//        this.articleCommentCount = articleCommentCount;
//        this.articleLikeCount = articleLikeCount;
        this.articleCreateTime = articleCreateTime;
        this.articleUpdateTime = articleUpdateTime;
//        this.user = user;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

//    public Integer getArticleUserId() {
//        return articleUserId;
//    }

//    public void setArticleUserId(Integer articleUserId) {
//        this.articleUserId = articleUserId;
//    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

//    public Integer getArticleCommentCount() {
//        return articleCommentCount;
//    }

//    public void setArticleCommentCount(Integer articleCommentCount) {
//        this.articleCommentCount = articleCommentCount;
//    }

//    public Integer getArticleLikeCount() {
//        return articleLikeCount;
//    }

//    public void setArticleLikeCount(Integer articleLikeCount) {
//        this.articleLikeCount = articleLikeCount;
//    }

    public LocalDateTime getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(LocalDateTime articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public LocalDateTime getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(LocalDateTime articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;

    }

//    public User getUser() {
//        return user;
//    }

//    public void setUser(User user) {
//        this.user = user;
//    }
}