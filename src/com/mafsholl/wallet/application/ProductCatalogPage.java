package com.mafsholl.wallet.application;

public class ProductCatalogPage extends PageNavigator {
    @Override
    public String title() {
        return "Product Catalog";
    }

    @Override
    public String instruction() {
        return "Use the Product ID to select a product of your choice.";
    }

    @Override
    public void setNextPage(PageNavigator nextPage) {
        this.nextPage = nextPage;
    }
}
