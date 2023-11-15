package com.mafsholl.wallet.exception;

public class InvalidWalletException extends Exception {

    private String walletId;

    public InvalidWalletException(String walletId) {
        this.walletId = walletId;
    }

    @Override
    public String getMessage() {
        return "Invalid walletID: " + walletId + ". Please check your walletID and retry.";
    }
}
