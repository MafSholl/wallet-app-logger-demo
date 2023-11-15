package com.mafsholl.wallet.application;

public class ProductCartPage extends PageNavigator{
    @Override
    public String title() {
        return "Product Cart";
    }

    @Override
    public String instruction() {
        return "Follow the prompts to add or remove products from cart.";
    }

    @Override
    public void setNextPage(PageNavigator nextPage) {
        this.nextPage = nextPage;
    }
}
