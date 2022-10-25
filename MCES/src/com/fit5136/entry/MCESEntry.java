package com.fit5136.entry;

import com.fit5136.controller.CustomerController;

// Startup class of system operation
public class MCESEntry {
    public static void main(String[] args) {
        System.out.println("**********************************\n"+
        "            Welcome to\n        Monash Community Exchange\n"+
        "              System\n              (MCES)");


        CustomerController customerController = new CustomerController();
        // start system1
        customerController.start();
    }
}
