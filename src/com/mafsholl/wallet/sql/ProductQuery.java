package com.mafsholl.wallet.sql;

import com.mafsholl.wallet.Utility;
import com.mafsholl.wallet.model.Product;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class ProductQuery {
    static final String INSERT_QUERY =
            "INSERT INTO Products (productname,description,unitprice," +
                    "quantityinstock,createddate) VALUES (?,?,?,?,?)";

    static final String UPDATE_INSTOCK_QUERY =
            "UPDATE Products SET quantityinstock=quantityinstock+? WHERE productid=?";


    static final String SELECT_ALL_QUERY =
            "SELECT productId,productname,description,unitprice," +
                    "quantityinstock,createddate,isAvailable FROM Products";

    static final String SELECT_BYID_QUERY =
            "SELECT productId,productname,description,unitprice," +
                    "quantityinstock,createddate,isAvailable FROM Products " +
                    "WHERE productId=?";

    public static String createProduct(Product product) {
        try(Connection conn = DriverManager
                .getConnection(Utility.DB_URL);
            PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY);
        ) {
            // Execute a query
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getUnitPrice());
            stmt.setInt(4, product.getQuantityInStock());
            stmt.setDate(5, Date.valueOf(LocalDate.now()));
            int result = stmt.executeUpdate();
            if (result == 1) return result + " product added successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Insertion not completed";
    }

    public static Product updateProductQuantity(Integer productId, Integer quantity) {
        try(Connection conn = DriverManager
                .getConnection(Utility.DB_URL);
            PreparedStatement stmt = conn.prepareStatement(UPDATE_INSTOCK_QUERY);
        ) {
            // Execute a query
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);
            int result = stmt.executeUpdate();
            if (result == 1) return getProductById(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Product getProductById(Integer productId) {
        try(Connection conn = DriverManager
                .getConnection(Utility.DB_URL);
            PreparedStatement stmt = conn.prepareStatement(SELECT_BYID_QUERY);
        ) {
            // Execute a query
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                Double unitprice = rs.getDouble(4);
                Integer quantity = rs.getInt(5);
                String date =
                        rs.getDate(6).toString();

                Boolean available = rs.getBoolean(7);
                Product product =
                        new Product(id,name,description,unitprice,quantity,date,available);
                return product;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LinkedList<Product> getProducts() {
        LinkedList<Product> products = null;
        try(Connection conn = DriverManager
                .getConnection(Utility.DB_URL);
            PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_QUERY);
            ResultSet rs = stmt.executeQuery()
        ) {
                products = new LinkedList<>();
                while (rs.next()) {
                    Integer id = rs.getInt(1);
                    String name = rs.getString(2);
                    String description = rs.getString(3);
                    Double unitprice = rs.getDouble(4);
                    Integer quantity = rs.getInt(5);
                    String date =
                            rs.getDate(6).toString();

                    Boolean available = rs.getBoolean(7);
                    Product product =
                            new Product(id,name,description,unitprice,quantity,date,available);
                    products.add(product);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

}
