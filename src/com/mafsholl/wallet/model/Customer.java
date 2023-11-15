package com.mafsholl.wallet.model;

import com.mafsholl.wallet.Utility;


public class Customer {

    private Integer id;
    private String name;
    private String phoneNumber;
    private String address;
    private String wallet;

    private Double balance;
    private String registeredDate;

    public Customer() {
        this.wallet = Utility.generateWalletID();
    }

    public Customer(String name, String phoneNumber, String address) {
        this();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Customer(Integer id, String name, String phoneNumber, String address, String wallet, Double balance, String registeredDate) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.wallet = wallet;
        this.balance = balance;
        this.registeredDate = registeredDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWallet() {
        return wallet;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", wallet='" + wallet + '\'' +
                ", balance=" + balance +
                ", registeredDate='" + registeredDate + '\'' +
                '}';
    }
}
