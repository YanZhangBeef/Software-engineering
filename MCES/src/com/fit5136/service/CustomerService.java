package com.fit5136.service;

import com.fit5136.dao.CustomerDao;
import com.fit5136.entities.AdForItem;
import com.fit5136.entities.Customer;
import com.fit5136.entities.TradingWay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// implement some system business needs
public class CustomerService {

    private CustomerDao customerDao = new CustomerDao();

// <<<<<<< Updated upstream
    // public boolean verifyLogin(Customer customer) {
// =======
    // identify whether user loginned sucussfully or not
    public boolean verifyLogin(Customer customer) {
// >>>>>>> Stashed changes
        List<String> data = customerDao.login();
        ArrayList<Customer> customers = new ArrayList<>();

        for (String s : data) {
            String[] info = s.split(",");
            Customer c = new Customer(info[0], info[1]);
            customers.add(c);
        }

        boolean flag = false;
        for (Customer c : customers) {
            // validate email
            if (customer.getMonashEmail().equals(c.getMonashEmail()) &&
                    customer.getPassword().equals(c.getPassword())) {
                flag = true;
            }
        }

        return flag;
    }

    // post Advertisement 
    public void postItemAd(Customer customer, AdForItem ad) {
        customerDao.postItemAd(customer, ad);
    }

    // gathering category details
    public Map<String, List<String>> getCategoryInfo() {
        return customerDao.getCategoryInfo();
    }

    // gathering Trading way details
    public List<TradingWay> getTradingWayInfo() {
        return customerDao.getTradingWayInfo();
    }

    // going through category to find the sepecific advertisement  
    public List<AdForItem> findAdByCategory(String keyword) {
        return customerDao.findAdByCategory(keyword);
    }

    // going through category to find the sepecific advertisement  
    public List<AdForItem> findAdBySubcategory(String keyword) {
        return customerDao.findAdBySubcategory(keyword);
    }

    // find the 10 top newly advertisements  
    public List<AdForItem> findAdByTop10() {
        return customerDao.findAdByTop10();
    }

    // save user browsed history  
    public void saveViewHistory(AdForItem ad) {
        customerDao.saveViewHistory(ad);
    }

    // gather user rencently viewed data
    public List<String> findRecentlyViewed() {
        return customerDao.findRecentlyViewed();
    }

    // display user browsed advertisement Statistics details
    public Map<String, Integer> showViewStats(List<String> recentlyViewedData) {
        return customerDao.showViewStats(recentlyViewedData);
    }
}
