package com.fit5136.controller;

import com.fit5136.entities.AdForItem;
import com.fit5136.entities.Customer;
import com.fit5136.entities.TradingWay;
import com.fit5136.service.CustomerService;
import com.fit5136.utils.DateUtils;

import java.util.*;

// display ui and let user input details and access it, control the process of the system
public class CustomerController {

    private CustomerService customerService = new CustomerService();

    // let user input details, gathered the user inputs details
    private Scanner sc = new Scanner(System.in);

    /**
     * control the begining of the system 
     */
    public void start() {
        String choice;
        loop:
        // Welcome Screen 
        while (true) {
            System.out.println( "**********************************\n"+"        Login to use MCES\n"+
            "                or\n        Register for first use\n"+
            "**********************************\n"+
            "    Please enter the number for\n          wanted options\n\n"+
            " 1.Login\n 2.Register\n 3.Exit");

            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    Customer customer = inputLoginInfo();

                    boolean loginStatus = login(customer);
                    if (loginStatus) {
                        boolean flag = MainMenu(customer);
                        if (flag) {
                            break;
                        }
//                        break loop;
                    }
                    break;
                case "2":
                    System.out.println("The registration function is under development, please wait patiently");
                    break;

                case "3":
                    System.out.println("Thank you for using this system. Goodbye!");
//                    break loop;
                    System.exit(0);
                default:
                    System.out.println("Error input! Please input again");
                    break;
            }
        }
    }

    // the fuunction is to identity login successfully or not
    public boolean login(Customer customer) {
        boolean logStatus;
        boolean flag = customerService.verifyLogin(customer);
        if (flag) {
            System.out.println("Login Successfully!");
            logStatus = true;
        } else {
            System.out.println("**********************************\n"+
            "            Welcome to\n    Monash Community Exchange\n"+
            "              System\n              (MCES)");
            System.out.println("**********************************");
            System.out.println("      Login unsuccessfully!!\n");
            logStatus = false;
        }

        return logStatus;
    }

    // let user input their own email address and password 
    public Customer inputLoginInfo() {
        System.out.println("Please enter your Monash Email:");
        String email;
        while (true) {
            email = sc.nextLine();
            if (!email.endsWith("@student.monash.edu")) {
                System.out.println("Your Email is not Monash Email.Please enter your Monash Email:");
            } else {
                break;
            }
        }
        System.out.println("Please enter your password:");
        String password = sc.nextLine();
        Customer customer = new Customer(email, password);
        return customer;
    }

    // display the ui for Home Screen 
    public boolean MainMenu(Customer customer) {
        String choice;
        boolean flag;
        loop:
        while (true) {
            System.out.println("**********************************\n"+
            "         MCES Home screen\n"+
            "**********************************\n"+
            "    In here, you can navigate to\n     post/browse ads, view\n    exchange record, and check\n           watchlist.\n"+
            "**********************************\n"+
            "    Please enter the number for\n          wanted options\n\n 1.Post Advertisements\n"+
            " 2.Browse Advertisements\n"+
            " 3.Exchange Record\n"+
            " 4.Watchlist management\n 5.Log out");

            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    boolean f1 = postScreen(customer);
                    if (f1) {
                        break;
                    }
                    break;
                case "2":
                    boolean f2 = browseAd(customer);
                    if (f2) {
                        break;
                    }
                    break;
                case "3":
                    System.out.println("Sorry! This function is under development.");
                    break;
                case "4":
                    boolean f4 = watchListScreen();
                    if (f4) {
                        break;
                    }
                    break;
                case "5":
                    flag = true;
                    System.out.println("Log out successfully.");
                    break loop;

                default:
                    System.out.println("Error input! Please input again");
                    break;
            }
        }

        return flag;
    }

    // display the ui fo rencently viewed Screen 
    public boolean recentlyViewed() {
        System.out.println("**********************************");
        System.out.println("  MCES Recently Viewed screen ");
        System.out.println("**********************************");
        System.out.println("    In here, you can view the \n   recently viewed items/services  \n          with statistics.");
        System.out.println("**********************************");

        List<String> recentlyViewedData = customerService.findRecentlyViewed();
        Map<String, Integer> viewStats = customerService.showViewStats(recentlyViewedData);

//        System.out.println("Item/Service title" + "\t\t\t" + "Viewed times");
        for (Map.Entry<String, Integer> entry : viewStats.entrySet()) {
            System.out.println(" Item/Service title: " + entry.getKey()
                    + "    Viewed times: " + entry.getValue() + "\n");
        }

        String choice;
        boolean flag;
        while (true) {
            System.out.println(" 1.Go Back ");
            choice = sc.nextLine();

            if ("1".equals(choice)) {
                flag = true;
                break;
            } else {
                System.out.println("Please enter 1 to go Go !");
            }
        }
        return flag;
    }

    // display the ui for Watchlist Management screen
    public boolean watchListScreen() {
        String choice;
        boolean flag;

        loop:
        while (true) {
            System.out.println("**********************************");
            System.out.println("    MCES Watchlist Management screen ");
            System.out.println("**********************************");
            System.out.println("    In here, you can view recently \n   viewed items/services or check\n           watchlist.");
            System.out.println("**********************************");
            System.out.println("     Please enter the number got \n           wanted options\n");
            System.out.println(" 1.Recently Viewed");
            System.out.println(" 2.Watchlist");
            System.out.println(" 3.Go Back");

            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    boolean f1 = recentlyViewed();
                    if (f1) {
                        break;
                    }
                    break;
                case "2":
                    System.out.println("Sorry!This function is under development.");
                    break;
                case "3":
                    flag = true;
                    break loop;
                default:
                    System.out.println("Error input! Please enter correct number!");
                    break;
            }
        }
        return flag;
    }

    // display the ui for Browse screen
    public boolean browseAd(Customer customer) {
        String choice;
        boolean flag;

        loop:
        while (true) {

        System.out.println("**********************************\n"+
                "       MCES Browse screen \n"+
                "**********************************\n"+
                "     In here, you can choose \n     category, sub-category and top\n         10 newly.\n"+
                "**********************************\n"+
                "    Please enter the number for\n          wanted options\n\n"+
                " 1.Category\n"+
                " 2.Sub-category\n"+
                " 3.Top 10 newly\n"+
                " 4.Go Back\n"
);

        choice = sc.nextLine();
        switch (choice) {
            case "1":
                browseByCategory(customer);
                break;
            case "2":
                browseBySubcategory(customer);
                break;
            case "3":
                browseByTop10(customer);
                break;
            case "4":
                flag = true;
                break loop;
            default:
                System.out.println("Error input! Please input again");
                break;
            }
        }



        return flag;
    }

    // display the ui for Category Screen
    public void browseByCategory(Customer customer) {
        String categoryChoice;
        boolean flag;

        while (true) {
            System.out.println("**********************************");
            System.out.println("    MCES Category screen  ");
            System.out.println("**********************************");
            System.out.println("   In here, you can choose \n    category for items/services.");
            System.out.println("**********************************");
            System.out.println("    Please choose category");


            Map<String, List<String>> categoryInfo = customerService.getCategoryInfo();

            int categoryIndex = 1;
            List<String> categoryList = new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : categoryInfo.entrySet()) {
                String categoryName = entry.getKey();
                String one_category = categoryIndex + "." + categoryName;
                System.out.println(one_category); // 1.Textbook
                categoryList.add(categoryName);
                categoryIndex++;
            }

            System.out.println((categoryIndex) + "." + "Go Back");

            categoryChoice = sc.nextLine();

            int input = Integer.parseInt(categoryChoice);

            if (input == (categoryList.size() + 1)) {
                break;
            } else if (input > 0 && input <= categoryList.size()){
                List<AdForItem> adByCategory = customerService.findAdByCategory(categoryList.get(input - 1));
                adScreen(adByCategory);
            } else {
                System.out.println("Error input!");
            }

        }
    }

    // display the ui for SubCategory Screen
    public void browseBySubcategory(Customer customer) {
        String subcategoryChoice;
        boolean flag;

        while (true) {
            System.out.println("**********************************");
            System.out.println("    MCES Subcategory screen  ");
            System.out.println("**********************************");
            System.out.println("   In here, you can choose \n    subcategory for items/services.");
            System.out.println("**********************************");
            System.out.println("    Please choose subcategory");

            Map<String, List<String>> categoryInfo = customerService.getCategoryInfo();

            int subcategoryIndex = 1;
            List<String> subcategoryList = new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : categoryInfo.entrySet()) {
                List<String> subcategories = entry.getValue();
                for (String subcategory : subcategories) {
                    String[] subArr = subcategory.split(",");
                    for (int i = 0; i < subArr.length; i++) {
                        System.out.println(subcategoryIndex + "." + subArr[i]);
                        subcategoryList.add(subArr[i]);
                        subcategoryIndex++;
                    }
                }

            }

            System.out.println((subcategoryIndex) + "." + "Go Back");

            subcategoryChoice = sc.nextLine();

            int input = Integer.parseInt(subcategoryChoice);

            if (input == (subcategoryList.size() + 1)) {
                break;
            } else if (input > 0 && input <= subcategoryList.size()){
                List<AdForItem> adBySubcategory = customerService.findAdBySubcategory(subcategoryList.get(input - 1));
                adScreen(adBySubcategory);
            } else {
                System.out.println("Error input!");
            }

        }
    }

    // Display top 10 newly Advertisement list screen
    public void browseByTop10(Customer customer) {
        List<AdForItem> adByTop10 = customerService.findAdByTop10();
        adScreen(adByTop10);
    }

    // Display Advertisement list screen
    public void adScreen(List<AdForItem> adForItemList) {
        String choice;
        boolean flag;
        while (true) {
            System.out.println("**********************************");
            System.out.println("  MCES Advertisement list screen ");
            System.out.println("**********************************");
            System.out.println("    In here, you can view all\n      suitable advertisements.");
            System.out.println("**********************************");          
            System.out.println("Please enter the number to browse");

            for (int i = 0; i < adForItemList.size(); i++) {
                System.out.println((i + 1) + "." + adForItemList.get(i).getTitle());
            }
            System.out.println((adForItemList.size() + 1) + "." + "Go Back");

            choice = sc.nextLine();
            int input = Integer.parseInt(choice);
            if (choice.equals((adForItemList.size() + 1) + "")) {
                break;
            } else if (input > 0 && input <= adForItemList.size()){
                boolean b = showAdDetails(adForItemList.get(input - 1));
                if(b){
                    break;
                }
            } else {
                System.out.println("Error input!");
            }
        }

    }


        // Display Advertisement Detail screen
    public boolean showAdDetails(AdForItem ad) {
            String choice;
            boolean flag;
            while (true) {
                System.out.println("**********************************");
                System.out.println(" MCES Advertisement Detail screen");
                System.out.println("**********************************");
                System.out.println("   In here, you can view detail  \n    information of an advertisement");
                System.out.println("**********************************");
                String adInfo = " Title: " + ad.getTitle() + "\n" +
                        " CategoryName: " + ad.getCategoryName() + "\n" +
                        " SubCategoryName: " + ad.getSubCategoryName() + "\n" +
                        " Price:AUD " + ad.getPrice() + "\n" +
                        " Condition: " + ad.getCondition() + "\n" +
                        " Description: " + ad.getDescription() + "\n" +
                        " TradingWay: " + ad.getTradingWay() + "\n" +
                        " PostDateTime: " + ad.getPostDateTime()+"\n";
                System.out.println(adInfo);
                customerService.saveViewHistory(ad);
        
        
                System.out.println(" 1.Go Back");
                choice = sc.nextLine();
                if ("1".equals(choice)) {
                    flag = true;
                    break;
                } else {
                    System.out.println("Error input! Please enter correct number!");
                }
        
            }
            return flag;
    }


    // display Confirmation screen
    public void postItemAd(Customer customer) {
        AdForItem ad = inputAdInfo();

        System.out.println("**********************************");
        System.out.println("   MCES Confirmation screen ");
        System.out.println("**********************************");
        System.out.println("   In here, you can check and\n    edit entered information");
        System.out.println("**********************************");
        System.out.println("   Please enter the number to\n     edit the option\n");
        System.out.println(" Title: " + ad.getTitle());
        System.out.println(" CategoryName: " + ad.getCategoryName());
        System.out.println(" SubCategoryName: " + ad.getSubCategoryName());
        System.out.println(" Price:AUD " + ad.getPrice());
        System.out.println(" Condition: " + ad.getCondition());
        System.out.println(" Description: " + ad.getDescription());
        System.out.println(" TradingWay: " + ad.getTradingWay());
        System.out.println(" PostDateTime: " + ad.getPostDateTime()+"\n");
 
        System.out.println(" You can modify the categoryName and\n its relevant subCategoryName\n" +
                " Which one do you want to modify.\n");
        System.out.println("1.Modify categoryName   \n2.Modify subCategoryName    \n3.I don't want to modify anything,just post");
        String choice = sc.nextLine();
        if ("1".equals(choice)) {
            System.out.println("Your current category name is: " + ad.getCategoryName());
            System.out.println("Please enter the new category name, you can choose the following: ");

            Map<String, List<String>> categoryInfo = customerService.getCategoryInfo();
            categoryInfo.entrySet().removeIf(entry -> entry.getKey().equals(ad.getCategoryName()));
            List<String> categoryList = new ArrayList<>();
            int index = 1;
            for (String s : categoryInfo.keySet()) {
                System.out.println(index + "." + s);
                categoryList.add(s);
                index++;
            }

            String newCategoryNameChoice = sc.nextLine();
            int i = Integer.parseInt(newCategoryNameChoice);
            String newCategoryName = categoryList.get(i - 1);
            ad.setCategoryName(newCategoryName);

            System.out.println("CategoryName modified successfully!");
            System.out.println("CategoryName changed, you need to modify its relevant subCategoryName." +
                    "you can choose the following: ");
            List<String> subcategoryChoiceList = new ArrayList<>();
            int s_index = 1;
            for (String s : categoryInfo.keySet()) {
                if (newCategoryName.equals(s)) {
                    List<String> subcategoryList = categoryInfo.get(s);
                    for (String subcategory : subcategoryList) {
                        System.out.println(s_index + "." + subcategory);
                        subcategoryChoiceList.add(subcategory);
                        s_index++;
                    }
                }

            }
            String newSubCategoryNameChoice = sc.next();
            int i1 = Integer.parseInt(newSubCategoryNameChoice);
            String newSubCategoryName = subcategoryChoiceList.get(i1 - 1);
            ad.setSubCategoryName(newSubCategoryName);
            System.out.println("subCategoryName modified successfully!");
            System.out.println("Modification has been submitted. The system is posting your advertisement...");
            customerService.postItemAd(customer, ad);
            System.out.println("Advertisement post successfully!");
        } else if ("2".equals(choice)){
            System.out.println("Your current category name is: " + ad.getCategoryName());
            System.out.println("Your current subcategory name is: " + ad.getSubCategoryName());
            System.out.println("Please enter the new subcategory name, you can choose the following: ");
            Map<String, List<String>> categoryInfo = customerService.getCategoryInfo();
             List<String> subcategoryChoiceList = new ArrayList<>();
            int index = 1;
            for (String s : categoryInfo.keySet()) {
                if (ad.getCategoryName().equals(s)) {
                    List<String> subcategoryList = categoryInfo.get(s);
                    for (String subcategory : subcategoryList) {
                        if (!ad.getSubCategoryName().equals(subcategory)) {
                            System.out.println(index + "." + subcategory);
                            subcategoryChoiceList.add(subcategory);
                            index++;
                        }
                    }
                }
            }

            String newSubCategoryNameChoice = sc.nextLine();
            int i = Integer.parseInt(newSubCategoryNameChoice);
            String newSubCategoryName = subcategoryChoiceList.get(i - 1);
            ad.setSubCategoryName(newSubCategoryName);
            System.out.println("subCategoryName modified successfully!");
            System.out.println("Modification has been submitted. The system is posting your advertisement...");
            customerService.postItemAd(customer, ad);
            System.out.println("Advertisement post successfully!");
        } else {
            customerService.postItemAd(customer, ad);
            System.out.println("Advertisement post successfully!");
        }
    }

    // display posting screen
    private AdForItem inputAdInfo() {
        AdForItem ad = new AdForItem();

        System.out.println("**********************************");
        System.out.println("      MCES Posting screen ");
        System.out.println("**********************************");
        System.out.println("   In here, you can enter detail  \n       of posted items");
        System.out.println("**********************************");
        System.out.println("Please enter your advertisement title:");
        String title = sc.nextLine();
        
        System.out.println("Please enter your item category name:");
        Map<String, List<String>> categoryInfo = customerService.getCategoryInfo();
//        System.out.println(categoryInfo);
        Set<String> category = categoryInfo.keySet();
        int categoryIndex = 1;
        StringBuilder categoryStr = new StringBuilder();
        for (String s : category) {
            String output = categoryIndex + "." + s + " ";
            System.out.println(output);
            categoryStr.append(output);
            categoryIndex++;
        }
        String categoryChoice = categoryStr.toString();
        String[] choiceArr = categoryChoice.split(" ");
        String[] indexArr = new String[choiceArr.length];
        String[] categoryArr = new String[choiceArr.length];
        for (int i = 0; i < choiceArr.length; i++) {
            String index = choiceArr[i].split("\\.")[0];
            String cate = choiceArr[i].split("\\.")[1];
            indexArr[i] = index;
            categoryArr[i] = cate;
        }
        String categoryName = sc.nextLine();
        String categoryNameInput = "";
        for (int i = 0; i < indexArr.length; i++) {
            if (indexArr[i].equals(categoryName)) {
                categoryNameInput = categoryArr[i];
                ad.setCategoryName(categoryNameInput);
            }
        }

        List<String> subCategoryList = categoryInfo.get(categoryNameInput);

        if (!subCategoryList.get(0).equals("none")) {
            System.out.println("Please enter your item subcategory name:");

            for (int i = 0; i < subCategoryList.size(); i++) {
                System.out.println((i+1) + "." + subCategoryList.get(i));
            }

            String subCategoryName = sc.nextLine();

            for (int i = 0; i < subCategoryList.size(); i++) {
                if (subCategoryName.equals((i+1)+"")) {
                    ad.setSubCategoryName(subCategoryName);
                }
            }
        } else {
            ad.setSubCategoryName("");
        }

        System.out.println("Please enter your item price:");
        String priceStr = sc.nextLine();
        Double price = Double.parseDouble(priceStr);

        System.out.println("Please enter your item description:");
        String description = sc.nextLine();

        System.out.println("Please enter your item condition:");
        System.out.println("1.Used  2.New");
        String condition = sc.nextLine();
        if ("1".equals(condition)) {
            ad.setCondition("Used");
        } else if ("2".equals(condition)) {
            ad.setCondition("New");
        }

        System.out.println("Please enter your item trading way:");
        List<TradingWay> tradingWayInfo = customerService.getTradingWayInfo();
        for (int i = 0; i < tradingWayInfo.size(); i++) {
            System.out.println((i+1) + "." + tradingWayInfo.get(i).getTradingWayName());
        }
        String tradingWay = sc.nextLine();
        for (int i = 0; i < tradingWayInfo.size(); i++) {
            if (tradingWay.equals((i+1)+"")) {
                ad.setTradingWay(tradingWayInfo.get(i).getTradingWayName());
            }
        }

        Date date = new Date();
        String postDateTime = DateUtils.dateToString(date);
        String expiredDateTime = DateUtils.dateToString(DateUtils.calcExpiredDate(date));
        boolean isActive = true;
        Integer viewCount = 0;

        ad.setTitle(title);
//        ad.setCategoryName(categoryName);
        ad.setPrice(price);
//        ad.setCondition(condition);
        ad.setDescription(description);
//        ad.setTradingWay(tradingWay);
        ad.setPostDateTime(postDateTime);
        ad.setExpiredDateTime(expiredDateTime);
        ad.setActive(isActive);
        ad.setViewCount(viewCount);

        return ad;
    }

    // posting screen 
    public boolean postScreen(Customer customer) {
        String choice;
        boolean flag;

        loop:
        while (true) {
            System.out.println("**********************************");
            System.out.println("       MCES Posting screen ");
            System.out.println("**********************************");
            System.out.println("     In here, you can post \n   advertisemnets of items or \n            services.");
            System.out.println("**********************************");
            System.out.println("   Please enter the number for \n          wanted options\n");
            System.out.println("1.Post items");
            System.out.println("2.Post services");
            System.out.println("3.Go Back");

            choice = sc.nextLine();
            switch (choice) {
                case "1":
					postItemAd(customer);
                    break;
                case "2":
                    System.out.println("Sorry! This function is under development.");
                    break;
                case "3":
                    flag = true;
                    break loop;
                default:
                    System.out.println("Error input! Please enter the correct number.");
                    break;
            }
        }
        return flag;
    }
}
