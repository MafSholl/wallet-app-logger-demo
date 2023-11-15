package com.mafsholl.wallet;

import java.util.UUID;

public class Utility {

    public static String generateWalletID() {
        String digits = String.valueOf(Math.abs(UUID.randomUUID().getMostSignificantBits()));
        String walletID = digits.substring(0, 10);
        return walletID;
    }

    public static String generateOrderCode() {
        String codePath = UUID.randomUUID().toString()
                .substring(0,6).replace("-","");
        String timePath = String.valueOf(System.currentTimeMillis());

        return new StringBuilder().append(codePath)
                .append(timePath).toString()
                .toUpperCase();
    }
    public static final String DB_URL
            = "jdbc:sqlserver://;servername=ISW-220610-709;" +
            "instanceName=SQLEXPRESS;databaseName=WalletDB;" +
            "encrypt=false;integratedSecurity=true;";

}
