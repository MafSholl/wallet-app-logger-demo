package com.mafsholl.wallet.application;

public class HomePage extends PageNavigator {
    @Override
    public String title() {
        return "Welcome to Wallet Application";
    }

    @Override
    public String instruction() {
        return "Follow the menus to use our application";
    }

    @Override
    public void setNextPage(PageNavigator nextPage) {
        this.nextPage = nextPage;
    }
}
