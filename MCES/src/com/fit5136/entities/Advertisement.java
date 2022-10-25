package com.fit5136.entities;

// advertisement entity class
public class Advertisement {

    // attribute 
    private String title;
    private String categoryName;
    private String subCategoryName;
    private Double price;
    private String description;
    private String tradingWay;
    private String postDateTime;
    private String expiredDateTime;
    private boolean isActive;
    private Integer viewCount;

    // get or set methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTradingWay() {
        return tradingWay;
    }

    public void setTradingWay(String tradingWay) {
        this.tradingWay = tradingWay;
    }

    public String getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(String postDateTime) {
        this.postDateTime = postDateTime;
    }

    public String getExpiredDateTime() {
        return expiredDateTime;
    }

    public void setExpiredDateTime(String expiredDateTime) {
        this.expiredDateTime = expiredDateTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    // override toString method
    @Override
    public String toString() {
        return "Advertisement{" +
                "title='" + title + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", tradingWay='" + tradingWay + '\'' +
                ", postDateTime='" + postDateTime + '\'' +
                ", expiredDateTime='" + expiredDateTime + '\'' +
                ", isActive=" + isActive +
                ", viewCount=" + viewCount +
                '}';
    }

}
