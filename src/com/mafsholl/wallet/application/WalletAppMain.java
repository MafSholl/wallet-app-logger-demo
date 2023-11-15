package com.mafsholl.wallet.application;

import com.mafsholl.wallet.dto.Cart;
import com.mafsholl.wallet.model.Product;
import com.mafsholl.wallet.service.WalletService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class WalletAppMain {

    private static Scanner scanner;
    private static WalletService walletService;
    

    public static void main(String[] args) throws Exception {
        walletService = new WalletService();
        scanner = new Scanner(System.in);
        LinkedList<PageNavigator> pages = new LinkedList<>();
        HomePage home = new HomePage();
        RegistrationPage register = new RegistrationPage();
        home.setNextPage(register);
        ProductCatalogPage catalog = new ProductCatalogPage();
        register.setNextPage(catalog);
        CheckoutPage checkout = new CheckoutPage();
        checkout.setNextPage(home);
        ProductCartPage cartPage = new ProductCartPage();
        cartPage.setNextPage(checkout);

        pages.add(home);
        pages.add(register);
        pages.add(catalog);
        pages.add(checkout);
        pages.add(cartPage);
        PageNavigator page = home;
        onboardingMenu();
        while(page != null) {
            scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                userOnboarding(page);
            } else if (userInput == 2) {
                addProductsToCatalog();
            } else if (userInput == 3) {
                addProductsToCart();
            } else if (userInput == 4) {
                fundWallet();
            } else {
                customerCheckout();
            }
            page = page.getNextPage();
        }
    }

    public static void userOnboarding(PageNavigator page) throws Exception {
        System.out.println(page.title());
        System.out.println("==================");
        System.out.println(page.instruction());
        System.out.println();
        System.out.println("Please provide your name, phonenumber and address");
            System.out.print("Enter your name(s): ");
            String name =  scanner.next();
            System.out.print("Enter your phonenumber: ");
            String phonenumber =  scanner.next();
            System.out.print("Enter your address are: ");
            String address =  scanner.next();
            String customerwallet = walletService.registerCustomer(name, phonenumber, address);
            System.out.printf("Your wallet ID is %s\n", customerwallet);
            onboardingMenu();
    }

    public static void onboardingMenu() {
        System.out.println();
        System.out.println("What would you like to do? Enter number");
        System.out.println("Menu List\n" +
                "1 --> Register\n" +
                "2 --> Add to catalog\n" +
                "3 --> Add to cart\n" +
                "4 --> Fund wallet\n" +
                "5 --> Checkout\n"
        );
    }

    public static void addToCartMenu() {
        PageNavigator page = new ProductCartPage();
        System.out.printf("                 %s                \n", page.title());
        System.out.println("=================================================");
        System.out.println();
        System.out.println(page.instruction());
        System.out.println();
    }

    public static void addProductsToCart() {
        addToCartMenu();
        LinkedList<Product> catalog = walletService.getProductCatalog();
        for (Product product : catalog) {
            System.out.print(
                   "Product id: " + product.getId() + "\n   "
                   + "Product name: " + product.getName() + "\n   "
                   + "Product price: " + product.getUnitPrice() + "\n  "
           );
        }
        System.out.println();
        System.out.println("Add product using their ID; Enter number \"0\" when you're done");
        System.out.println();
        int userInput;
        do {
            System.out.println("Enter the id of the product you'd like to add: ");
            userInput = scanner.nextInt();
            System.out.print("Enter the quantity: ");
            int quantity = scanner.nextInt();
            if (userInput != 0) {
                walletService.addProductToCart(
                        walletService.getProductById(userInput).getId(),
                        quantity
                );
                System.out.println("Product added to cart successfully!");
            }
            System.out.println();
        } while (userInput != 0);
        onboardingMenu();
    }

    public static void addToCatalogMenu() {
        PageNavigator page = new ProductCatalogPage();
        System.out.printf("                 %s                \n", page.title());
        System.out.println("=================================================");
        System.out.println();
        System.out.println(page.instruction());
        System.out.println();
    }

    private static void addProductsToCatalog() {
        addToCatalogMenu();
        List<Product> products = new ArrayList<>();
        int check;
        do {
            System.out.println("Please fill in your product properties: ");
            System.out.print("Enter the product name: ");
            String name = scanner.next();
            System.out.print("Enter the product description: ");
            String description = scanner.next();
            System.out.print("Enter the product unit price: ");
            Double unitPrice =  scanner.nextDouble();
            System.out.print("Enter the product's quantity in stock: ");
            Integer quantityInStock = scanner.nextInt();
            products.add(new Product(name, description, unitPrice, quantityInStock));
            System.out.println("Products successfully added");
            System.out.println();
            System.out.print("Continue to add? 1 - Yes, 2 - No?");
            check = scanner.nextInt();
        } while (check != 2);
        walletService.populateProductCatalog(products);
        System.out.println("Products added to catalog successfull");
        addToCatalogMenu();
        LinkedList<Product> catalog = walletService.getProductCatalog();
        if (catalog.size() == 0) {
            System.out.println("You have no item in your catalog");
        } else {
            for (Product product : catalog) {
                System.out.print(
                        "Product id: " + product.getId() + "\n   "
                                + "Product name: " + product.getName() + "\n   "
                                + "Product price: " + product.getUnitPrice() + "\n  "
                );
            }
        }
        onboardingMenu();
    }

    public static void checkoutMenu() {
        PageNavigator page = new CheckoutPage();
        System.out.printf("                 %s                \n", page.title());
        System.out.println("=================================================");
        System.out.println();
        System.out.println(page.instruction());
        System.out.println();
    }

    public static void customerCheckout() throws Exception {
        //check customers  cart for products
        //if no product prompt no product
        //if product then ask for wallet id
        //Locate wallet, compare products amount with wallet amount
        //if wallet amount is more than product amount, reduce wallet amount
        //Then ensure that the product quantity reduced by exactly that amount
        checkoutMenu();
        System.out.print("Enter wallet ID: ");
        String walletID = scanner.next();
        List<Cart> checkedOutProducts = walletService.checkOutProduct(walletID);
        if (checkedOutProducts != null) System.out.println("Products successfully checkedout!");
        onboardingMenu();
    }

    public static void fundWallet() throws Exception {
        System.out.println("                 Fund Your Wallet                ");
        System.out.println("=================================================");
        System.out.println();
        System.out.print("Please enter your wallet ID: ");
        String walletID = scanner.next();
        System.out.print("Please the amount to fund: ");
        Double amount = scanner.nextDouble();
        if (walletService.getCustomerByWallet(walletID) != null)
            walletService.fundCustomerWallet(walletID, amount);
        onboardingMenu();
    }
}
