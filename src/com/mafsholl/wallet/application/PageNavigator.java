package com.mafsholl.wallet.application;

public abstract class PageNavigator {

    public abstract String title();

    public abstract String instruction();

    protected PageNavigator nextPage;

    public abstract void setNextPage(PageNavigator nextPage);

    public PageNavigator getNextPage() {
        return this.nextPage;
    }
}
