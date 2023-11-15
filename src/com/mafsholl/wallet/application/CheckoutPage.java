package com.mafsholl.wallet.application;

public class CheckoutPage extends PageNavigator{
    @Override
    public String title() {
        return "Check Out";
    }

    @Override
    public String instruction() {
        return "Provide your wallet ID to checkout.";
    }

    @Override
    public void setNextPage(PageNavigator nextPage) {
        this.nextPage = nextPage;
    }
}
