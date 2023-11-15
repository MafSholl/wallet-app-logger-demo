package com.mafsholl.wallet.service;

import com.mafsholl.wallet.dto.Cart;
import com.mafsholl.wallet.dto.CustomerProduct;
import com.mafsholl.wallet.exception.InvalidWalletException;
import com.mafsholl.wallet.model.Customer;
import com.mafsholl.wallet.model.Product;
import com.mafsholl.wallet.sql.CustomerQuery;
import com.mafsholl.wallet.sql.ProductQuery;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WalletService {
    private List<Cart> cart = null;
    private List<Cart> checkedOut = null;

    public WalletService() {
        if (cart == null) {
            cart = new ArrayList<>();
            checkedOut = new ArrayList<>();
        }
    }

    // Add a product to cart
    public void addProductToCart(Integer productId, Integer quantity) {
        Product product = ProductQuery.getProductById(productId);
        if (product == null) return;
        if (quantity <= 0 || quantity > product.getQuantityInStock()) return;

        Cart item = new Cart(productId, product.getName(),
                product.getDescription(), product.getUnitPrice(), quantity);
        if (!cart.contains(item)) {
            cart.add(item);
        } else {
            if (cart.remove(item)){
                cart.add(item);
            }
        }
    }

    // Checkout
    public List<Cart> checkOutProduct(String walletId) throws InvalidWalletException, Exception{
        Customer customer = CustomerQuery.getCustomerByWallet(walletId);
        if (customer == null) {
            throw new InvalidWalletException(walletId);
        }
        if (cart.size() == 0){
            throw new Exception("No item in your cart");
        }
        for (Cart item:cart) {
            if (item.getSubTotal() > customer.getBalance()) {
                continue;
            }
            CustomerProduct product = new CustomerProduct(customer.getId(), item.getProductId(),
                    item.getUnitPrice(), item.getQuantity());
            System.out.println("Creating customer order using the cart...");
            int result = CustomerQuery.createCustomerOrder(product);
            if (result == 1) {
                System.out.println("Debiting customer's wallet with " + item.getSubTotal());
                CustomerQuery.updateCustomerBalance(walletId, -item.getSubTotal());
                checkedOut.add(item);
                System.out.println("Cart successfully added to customer's " +
                        "order with order code: " + product.getOrderCode());
            }
        }
        return checkedOut;
    }

    // Display products in cart
    public List<Cart> getMyProductCart() {
        return cart;
    }

    // Displays all products
    public LinkedList<Product> getProductCatalog() {
        return ProductQuery.getProducts();
    }

    // Add products to product catalog
    public void populateProductCatalog(List<Product> products) {
        for (Product p: products) {
            ProductQuery.createProduct(p);
        }
    }

    // Product restock
    public void restockProduct(Integer productId, Integer quantity) {
        if (quantity <= 0) return;
        ProductQuery.updateProductQuantity(productId, +quantity);
    }

    // Retrieve product by id
    public Product getProductById(Integer productId) {
        return ProductQuery.getProductById(productId);
    }

    // Create a customer
    public String registerCustomer(String name, String phoneNumber, String address)
            throws Exception {
        if(name.isBlank() || phoneNumber.isBlank() || address.isBlank()) {
            throw new Exception("name or phoneNumber or address must be provided");
        }
        Customer customer = new Customer(name, phoneNumber, address);
        return CustomerQuery.createCustomer(customer);
    }
    // Retrieve a customer by Wallet Id
    public Customer getCustomerByWallet(String walletID) {
        return CustomerQuery.getCustomerByWallet(walletID);
    }

    // Fund a customer wallet
    public Customer fundCustomerWallet(String walletID, double amount)
        throws Exception{
        if (amount <= 0) {
            throw new Exception("amount must be greater than zero");
        }
        return CustomerQuery.updateCustomerBalance(walletID, amount);
    }

}
