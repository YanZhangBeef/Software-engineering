package com.fit5136.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// AdForItem entity class, inherit from Class Advertisement, implement Comprable interface
public class AdForItem extends Advertisement implements Comparable<AdForItem> {

    // attribute
    private String condition;

    // get or set methods
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    // Override toString method 
    @Override
    public String toString() {

        return "Advertisement{" +
                "title='" + super.getTitle() + '\'' +
                ", categoryName='" + super.getCategoryName() + '\'' +
                ", subCategoryName='" + super.getSubCategoryName() + '\'' +
                ", price=" + super.getPrice() +
                ", condition='" + condition + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", tradingWay='" + super.getTradingWay() + '\'' +
                ", postDateTime='" + super.getPostDateTime() + '\'' +
                ", expiredDateTime='" + super.getExpiredDateTime() + '\'' +
                ", isActive=" + super.isActive() +
                ", viewCount=" + super.getViewCount() +
                '}';
    }

    // Override compareTo method, compare cost date in descending order
    @Override
    public int compareTo(AdForItem ad) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date1 = null;
        try {
            date1 = sdf.parse(super.getPostDateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date date2 = null;
        try {
            date2 = sdf.parse(ad.getPostDateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) ((date2.getTime() - date1.getTime()) / 1000);

    }
}
