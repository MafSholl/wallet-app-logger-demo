package com.mafsholl.wallet.sql;

import com.mafsholl.wallet.Utility;
import com.mafsholl.wallet.dto.CustomerProduct;
import com.mafsholl.wallet.model.Customer;

import java.sql.*;
import java.time.LocalDate;

public class CustomerQuery {
    static final String INSERT_QUERY =
            "INSERT INTO Customers (name,phonenumber,address," +
                    "walletid,createddate) VALUES (?,?,?,?,?)";

    static final String UPDATE_BALANCE_QUERY =
            "UPDATE Customers SET balance=balance+? WHERE walletid=?";

    static final String SELECT_QUERY_BY_WALLET =
            "SELECT customerId,name,phonenumber,address," +
                    "walletid,cast(balance as decimal) as my_balance,createddate" +
                    " FROM Customers WHERE walletid=?";

    static final String INSERT_CART =
            "INSERT INTO CustomerProduct (customerId,productId,unitprice," +
                    "quantity,ordercode,createddate) VALUES (?,?,?,?,?,?)";

    public static String createCustomer(Customer customer) {
        try(Connection conn = DriverManager
                .getConnection(Utility.DB_URL);
            PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY);
        ) {
            // Execute a query
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhoneNumber());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getWallet());
            stmt.setDate(5, Date.valueOf(LocalDate.now()));
            int result = stmt.executeUpdate();
            if (result == 1) return customer.getWallet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Customer updateCustomerBalance(String walletId, double amount) {
        try(Connection conn = DriverManager
                .getConnection(Utility.DB_URL);
            PreparedStatement stmt = conn.prepareStatement(UPDATE_BALANCE_QUERY);
        ) {
            // Execute a query
            stmt.setDouble(1, amount);
            stmt.setString(2, walletId);
            int result = stmt.executeUpdate();
            if (result == 1) return getCustomerByWallet(walletId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Customer getCustomerByWallet(String wallet) {
        try(Connection conn = DriverManager
                .getConnection(Utility.DB_URL);
            PreparedStatement stmt = conn.prepareStatement(SELECT_QUERY_BY_WALLET);
        ) {
            // Execute a query
            stmt.setString(1, wallet);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String name = rs.getString(2);
                String phone = rs.getString(3);
                String address = rs.getString(4);
                String walletId = rs.getString(5);
                Double balance = rs.getDouble(6);
                String date =
                        rs.getDate(7).toString();

                Customer customer =
                        new Customer(id,name,phone,address,walletId,balance,date);
                return customer;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int createCustomerOrder(CustomerProduct customerProduct) {
        try(Connection conn = DriverManager
                .getConnection(Utility.DB_URL);
            PreparedStatement stmt = conn.prepareStatement(INSERT_CART);
        ) {
            // Execute a query
            stmt.setInt(1, customerProduct.getCustomerId());
            stmt.setInt(2, customerProduct.getProductId());
            stmt.setDouble(3, customerProduct.getUnitPrice());
            stmt.setInt(4, customerProduct.getQuantity());
            stmt.setString(5, customerProduct.getOrderCode());
            stmt.setDate(6, Date.valueOf(LocalDate.now()));
            int result = stmt.executeUpdate();
            if (result == 1) {
                System.out.println("Reducing product's quantity in stock by " + customerProduct.getQuantity());
                ProductQuery.updateProductQuantity(customerProduct.getProductId(),
                        -customerProduct.getQuantity());
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
