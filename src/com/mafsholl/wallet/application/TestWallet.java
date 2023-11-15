package com.mafsholl.wallet.application;

import java.util.LinkedList;

public class TestWallet {

    public static void main(String[] args) {

        LinkedList<PageNavigator> pages = new LinkedList<>();
        HomePage home = new HomePage();
        RegistrationPage register = new RegistrationPage();
        home.setNextPage(register);
        ProductCatalogPage catalog = new ProductCatalogPage();
        register.setNextPage(catalog);

        pages.add(home);
        pages.add(register);
        pages.add(catalog);

        PageNavigator nextPage = home;
        while(nextPage != null) {
            System.out.println(nextPage.title());
            System.out.println("==================");
            System.out.println(nextPage.instruction());
            System.out.println();
            nextPage = nextPage.getNextPage();
        }

    }

}
