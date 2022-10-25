package com.fit5136.dao;

import com.fit5136.entities.AdForItem;
import com.fit5136.entities.Customer;
import com.fit5136.entities.TradingWay;
import com.fit5136.utils.DBUtils;
import com.fit5136.utils.DateUtils;

import java.util.*;

// data access object 
public class CustomerDao {

    // create a list for customer data 
    private List<String> customerData;

    /**
     * return customer data
     */
    public List<String> login() {
        // load all customer data
        customerData = DBUtils.readData("Database_Files/Customer.txt");
        return customerData;
    }

    /**
     * Get all category data, including top-category and its subcategory
     * @return a Map, key is top-category name and value is subcategory List
     */
    public Map<String, List<String>> getCategoryInfo() {
        List<String> categoryData = DBUtils.readData("Database_Files/Category_Info.txt");
        Map<String, List<String>> map = new HashMap<>();

        for (String s : categoryData) {
            String[] info = s.split(":");
            if (!info[1].equals("none")) {
                map.put(info[0], Arrays.asList(info[1].split(",")));
            } else {
                map.put(info[0], Arrays.asList(info[1]));
            }
        }

        return map;
    }

    /**
     * get all trading way data
     */
    public List<TradingWay> getTradingWayInfo() {
        List<String> tradingWayData = DBUtils.readData("Database_Files/TradingWay.txt");
        List<TradingWay> tradingWayList = new ArrayList<>();
        for (String s : tradingWayData) {
            tradingWayList.add(new TradingWay(s));
        }
        return tradingWayList;
    }

    /**
     * store the posted ad info into database files
     * @param customer current login customer
     * @param ad ad to be posted
     */
    public void postItemAd(Customer customer, AdForItem ad) {

        List<String> adData = DBUtils.readData("Database_Files/Advertisements.txt");
        String adInfo = customer.getMonashEmail() + "$post$" +
                ad.getTitle() + "," +
                ad.getCategoryName() + "," +
                ad.getSubCategoryName() + "," +
                ad.getPrice() + "," +
                ad.getCondition() + "," +
                ad.getDescription() + "," +
                ad.getTradingWay() + "," +
                ad.getPostDateTime() + "," +
                ad.getExpiredDateTime() + "," +
                ad.isActive() + "," +
                ad.getViewCount();
        adData.add(adInfo);

        DBUtils.writeData("Database_Files/Advertisements.txt", adData);

        customerData = DBUtils.readData("Database_Files/Customer.txt");

        // find customer index in List<Customer>
        int index = 0;
        for (int i = 0; i < customerData.size(); i++) {
            String[] info = customerData.get(i).split(",");
            if (customer.getMonashEmail().equals(info[0]) &&
                    customer.getPassword().equals(info[1])) {
                index = i;
            }
        }


        // find all ads posted by current customer
        List<String> adList = DBUtils.readData("Database_Files/Advertisements.txt");
        List<AdForItem> adForItems = new ArrayList<>();
        for (String s : adList) {
            String customerEmail = s.split("\\$post\\$")[0];
            String adStr = s.split("\\$post\\$")[1];

            if (customer.getMonashEmail().equals(customerEmail)) {
                String[] adInfoArr = adStr.split(",");
                AdForItem adForItem = new AdForItem();

                adForItem.setTitle(adInfoArr[0]);
                adForItem.setCategoryName(adInfoArr[1]);
                adForItem.setSubCategoryName(adInfoArr[2]);
                adForItem.setPrice(Double.parseDouble(adInfoArr[3]));
                adForItem.setCondition(adInfoArr[4]);
                adForItem.setDescription(adInfoArr[5]);
                adForItem.setTradingWay(adInfoArr[6]);
                adForItem.setPostDateTime(adInfoArr[7]);
                adForItem.setExpiredDateTime(adInfoArr[8]);
                adForItem.setActive(Boolean.parseBoolean(adInfoArr[9]));
                adForItem.setViewCount(Integer.parseInt(adInfoArr[10]));

                adForItems.add(adForItem);
            }
        }

//        adForItems.add(ad);
        customer.setPostAd(adForItems);

        // update the customer postAd content
        String updatedCustomer = customer.getMonashEmail() + "," +
                customer.getPassword() + "," +
                customer.getFirstName() + "," +
                customer.getLastName() + "," +
                customer.getDateOfBirth() + "," +
                customer.getGender() + "," +
                customer.getPhoneNumber() + "," +
                customer.getAddress() + "," +
                customer.getPostAd() + "," +
                customer.getRecentlyViewedAd() + "," +
                customer.getWatchlistAd();

        customerData.remove(index);
        customerData.add(updatedCustomer);

        DBUtils.writeData("Database_Files/Customer.txt", customerData);
    }

    /**
     * return a list of AdForItem with category sepecific category name
     */
    public List<AdForItem> findAdByCategory(String keyword) {
        List<String> data = DBUtils.readData("Database_Files/Advertisements.txt");
        List<AdForItem> adList = new ArrayList<>();

        for (String s : data) {
            String adInfo = s.split("\\$post\\$")[1];
            String categoryName = adInfo.split(",")[1];
            String[] adFiled = adInfo.split(",");
            AdForItem adForItem = new AdForItem();
            if (keyword.equals(categoryName)) {

                adForItem.setTitle(adFiled[0]);
                adForItem.setCategoryName(adFiled[1]);
                adForItem.setSubCategoryName(adFiled[2]);
                adForItem.setPrice(Double.parseDouble(adFiled[3]));
                adForItem.setCondition(adFiled[4]);
                adForItem.setDescription(adFiled[5]);
                adForItem.setTradingWay(adFiled[6]);
                adForItem.setPostDateTime(adFiled[7]);
                adForItem.setExpiredDateTime(adFiled[8]);
                adForItem.setActive(Boolean.parseBoolean(adFiled[9]));
                adForItem.setViewCount(Integer.parseInt(adFiled[10]));

                adList.add(adForItem);
            }
        }

        return adList;
    }

    /**
     * return a list of AdForItem with sepecific sub-category name
     */
    public List<AdForItem> findAdBySubcategory(String keyword) {
        List<String> data = DBUtils.readData("Database_Files/Advertisements.txt");
        List<AdForItem> adList = new ArrayList<>();

        for (String s : data) {
            String adInfo = s.split("\\$post\\$")[1];
            String subcategoryName = adInfo.split(",")[2];
            String[] adFiled = adInfo.split(",");
            AdForItem adForItem = new AdForItem();
            if (keyword.equals(subcategoryName)) {
                adForItem.setTitle(adFiled[0]);
                adForItem.setCategoryName(adFiled[1]);
                adForItem.setSubCategoryName(adFiled[2]);
                adForItem.setPrice(Double.parseDouble(adFiled[3]));
                adForItem.setCondition(adFiled[4]);
                adForItem.setDescription(adFiled[5]);
                adForItem.setTradingWay(adFiled[6]);
                adForItem.setPostDateTime(adFiled[7]);
                adForItem.setExpiredDateTime(adFiled[8]);
                adForItem.setActive(Boolean.parseBoolean(adFiled[9]));
                adForItem.setViewCount(Integer.parseInt(adFiled[10]));

                adList.add(adForItem);
            }
        }

        return adList;
    }

    /**
     * return a lsit of AdForItem Posted 10 newly 
     */
    public List<AdForItem> findAdByTop10() {
        List<String> data = DBUtils.readData("Database_Files/Advertisements.txt");

        List<AdForItem> adForItems = new ArrayList<>();

        for (String s : data) {
            String adInfo = s.split("\\$post\\$")[1];
            String[] adFiled = adInfo.split(",");

            AdForItem adForItem = new AdForItem();
            adForItem.setTitle(adFiled[0]);
            adForItem.setCategoryName(adFiled[1]);
            adForItem.setSubCategoryName(adFiled[2]);
            adForItem.setPrice(Double.parseDouble(adFiled[3]));
            adForItem.setCondition(adFiled[4]);
            adForItem.setDescription(adFiled[5]);
            adForItem.setTradingWay(adFiled[6]);
            adForItem.setPostDateTime(adFiled[7]);
            adForItem.setExpiredDateTime(adFiled[8]);
            adForItem.setActive(Boolean.parseBoolean(adFiled[9]));
            adForItem.setViewCount(Integer.parseInt(adFiled[10]));

            adForItems.add(adForItem);
        }

        Collections.sort(adForItems);

        List<AdForItem> top10Ad = new ArrayList<>();

        if (adForItems.size() < 10) {
            return adForItems;
        } else {
            top10Ad = adForItems.subList(0, 10);
        }
        return top10Ad;
    }

    /**
     * save the ViewHistory, store in database file
     */
    public void saveViewHistory(AdForItem ad) {
        List<String> readData = DBUtils.readData("Database_Files/AdBrowseHistory.txt");


        String adInfo = ad.toString();

        Date viewDateTime = new Date();
        String dateToString = DateUtils.dateToString(viewDateTime);

        String data = adInfo + "$browsed$" + dateToString;

        if (readData.size() >= 0 && readData != null) {
            readData.add(data);
            DBUtils.writeData("Database_Files/AdBrowseHistory.txt", readData);
        } else {
            List<String> historyData = new ArrayList<>();
            historyData.add(data);
            DBUtils.writeData("Database_Files/AdBrowseHistory.txt", historyData);
        }
    }

    /**
     * find the RencentlyViewed Ad within 1 hour
     */
    public List<String> findRecentlyViewed() {
        List<String> historyData = DBUtils.readData("Database_Files/AdBrowseHistory.txt");

        List<String> recentlyViewedData = new ArrayList<>();
        Date currentDate = new Date();

        for (String data : historyData) {
            String[] split = data.split("\\$browsed\\$");
            String viewDateTime = split[1];
            Date viewDate = DateUtils.stringToDate(viewDateTime);
            // find recently viewed ad within one hour
            if ((currentDate.getTime() - viewDate.getTime()) / 1000 <= 3600) {
                recentlyViewedData.add(data);
            }
        }

        return recentlyViewedData;
    }

    /**
     * display RencentlyViewed Ad statistic
     */
    public Map<String, Integer> showViewStats(List<String> recentlyViewedData) {
        Map<String, Integer> stats = new HashMap<>();

        for (String data : recentlyViewedData) {
            String adInfo = data.split("\\$browsed\\$")[0];
            String adTitle = adInfo.split(",")[0].split("\\{")[1].split("=")[1];
            Integer count = 1;

            if (!stats.containsKey(adTitle)) {
                stats.put(adTitle, count);
            } else {
                count++;
                stats.put(adTitle, count);
            }
        }

        return stats;
    }
}
