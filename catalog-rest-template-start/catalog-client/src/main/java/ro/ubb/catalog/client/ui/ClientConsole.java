package ro.ubb.catalog.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.catalog.client.service.CategoryServiceClient;
import ro.ubb.catalog.client.service.CustomerServiceClient;
import ro.ubb.catalog.client.service.ProductServiceClient;
import ro.ubb.catalog.web.dto.CategoryDto;
import ro.ubb.catalog.web.dto.CustomerDto;
import ro.ubb.catalog.web.dto.ProductDto;

import java.util.Scanner;
import java.util.Set;


@Component
public class ClientConsole {
    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Autowired
    private CategoryServiceClient categoryServiceClient;

    private Scanner scanner = new Scanner(System.in);

    public void runConsole() {
        while (true) {
            showMenu();
            System.out.println("Choose an option");
            String option = scanner.next();
            if (option.equals("1")) { //1. Add a Product
                this.handleAddProduct();
            } else if (option.equals("2")) {//2. Update a Product
                this.handleUpdateProduct();
            } else if (option.equals("3")) {//3. Find a Product");
               this.handleFindProduct();
            } else if (option.equals("4")) {//4. Show all Products")
                this.handleShowAllProducts();
            } else if (option.equals("5")) {//5. Delete a Product");
                this.handleDeleteProduct();
            } else if (option.equals("6")) {
                this.handleAddCustomer();
            } else if (option.equals("7")) {
                this.handleUpdateCustomer();
            } else if (option.equals("8")) {
               this.handleFindCustomer();
            } else if (option.equals("9")) {
                this.handleShowAllCustomers();
            } else if (option.equals("10")) {
                this.handleDeleteCustomer();
            } else if (option.equals("11")) { //11. Add a Category
                this.handleAddCategory();
            } else if (option.equals("12")) {//12. Update a Category
                this.handleUpdateCategory();
            } else if (option.equals("13")) {//13. Find a Category");
                this.handleFindCategory();
            } else if (option.equals("14")) {//14. Show all Categories")
                this.handleShowAllCategories();
            } else if (option.equals("15")) {//15. Delete a Category");
                this.handleDeleteCategory();
            } else if (option.equals("x")) {
                break;
            } else {
                System.out.println("Incorrect option please retry");
            }
        }

    }


    private void showMenu() {
        System.out.println("1. Add a Product");
        System.out.println("2. Update a Product");
        System.out.println("3. Find a Product");
        System.out.println("4. Show all Products");
        System.out.println("5. Delete a Product");
        System.out.println("6. Add a Customer");
        System.out.println("7. Update a Customer");
        System.out.println("8. Find a Customer");
        System.out.println("9. Show all Customers");
        System.out.println("10. Delete a Customer");
        System.out.println("11. Add a Category");
        System.out.println("12. Update a Category");
        System.out.println("13. Find a Category");
        System.out.println("14. Show all Categories");
        System.out.println("15. Delete a Category");
        System.out.println("x. Exit");
    }


    private void handleAddCustomer() {
        try {
            System.out.println("Insert the first name of the customer: ");
            String firstName = this.scanner.next();
            System.out.println("Insert the last name of the customer: ");
            String lastName = this.scanner.next();
            System.out.println("Insert the address of the customer: ");
            String address = this.scanner.next();
            System.out.println("Insert the email of the customer (smth@smth): ");
            String email = this.scanner.next();
            System.out.println("Insert the phone number of the customer (10 digits): ");
            String phone = this.scanner.next();
            CustomerDto customerDto = new CustomerDto(firstName, lastName, address, email, phone);
            customerServiceClient.addCustomer(customerDto);
        } catch (Exception exception) {
            System.out.println("Error occurred");
            System.out.println(exception.getMessage());
        }
    }


    public void handleAddProduct() {
        try {
            System.out.println("Insert the Name of the product");
            String productName = this.scanner.next();
            System.out.println("Insert the Price of the product");
            float price = this.scanner.nextFloat();
            System.out.println("Insert (optional) comments for the product");
            String productComment = this.scanner.next();
            ProductDto prodToCreate = new ProductDto(productName, price, productComment);
            productServiceClient.addProduct(prodToCreate);
        } catch (Exception exception) {
            System.out.println("Error occurred");
            System.out.println(exception.getMessage());
        }
    }

    private void handleUpdateCustomer() {
        try {
            System.out.println("Insert the Id of the customer to update");
            int customerId = Integer.parseInt(this.scanner.next());
            System.out.println("Insert the first name of the customer: ");
            String firstName = this.scanner.next();
            System.out.println("Insert the last name of the customer: ");
            String lastName = this.scanner.next();
            System.out.println("Insert the address of the customer: ");
            String address = this.scanner.next();
            System.out.println("Insert the email of the customer (smth@smth): ");
            String email = this.scanner.next();
            System.out.println("Insert the phone number of the customer (10 digits): ");
            String phone = this.scanner.next();
            CustomerDto customerDto = new CustomerDto(firstName, lastName, address, email, phone);
            customerDto.setId(customerId);
            customerServiceClient.updateCustomer(customerId, customerDto);
        } catch (Exception exception) {
            System.out.println("Error occurred");
            System.out.println(exception.getMessage());
        }
    }

    private void handleUpdateProduct() {
        try {
            System.out.println("Insert the Id of the product to update");
            int productId = Integer.parseInt(this.scanner.next());
            System.out.println("Insert the new Name of the product");
            String productName = this.scanner.next();
            System.out.println("Insert the new Price of the product");
            float price = this.scanner.nextFloat();
            System.out.println("Insert (optional) new comments for the product");
            String productComment = this.scanner.next();
            ProductDto prodToCreate = new ProductDto(productName, price, productComment);
            prodToCreate.setId(productId);
            productServiceClient.updateProduct(productId, prodToCreate);
        } catch (Exception exception) {
            System.out.println("Error occurred");
            System.out.println(exception.getMessage());
        }
    }

    private void handleFindProduct() {
        System.out.println("Insert the Id of the product to find");
        int productId = Integer.parseInt(this.scanner.next());
        ProductDto foundProduct = productServiceClient.findOneProduct(productId);
        if(foundProduct!=null) {
            System.out.println("Product found: "+foundProduct);
        } else {
            System.out.println("Cannot find a product with that id!");
        }
    }


    private void handleFindCustomer() {
        System.out.println("Insert the Id of the customer to find");
        int customerId = Integer.parseInt(this.scanner.next());
        CustomerDto foundCustomer = customerServiceClient.findOneCustomer(customerId);
        if(foundCustomer!=null) {
            System.out.println("Customer found: "+foundCustomer);
        } else {
            System.out.println("Cannot find a customer with that id!");
        }
    }

    private void handleShowAllProducts() {
        Set<ProductDto> allProducts = productServiceClient.getAllProducts();
        for(ProductDto product : allProducts) {
            System.out.println("Product: " + product.toString());
        }
    }

    private void handleShowAllCustomers() {
        Set<CustomerDto> allCustomers = customerServiceClient.getAllCustomers();
        for(CustomerDto customerDto : allCustomers) {
            System.out.println("Customer: " + customerDto.toString());
        }
    }

    private void handleDeleteProduct() {
        System.out.println("Insert the Id of the product to delete");
        int productId = Integer.parseInt(this.scanner.next());
        productServiceClient.deleteProduct(productId);
    }

    private void handleDeleteCustomer() {
        System.out.println("Insert the Id of the customer to delete");
        int customerId = Integer.parseInt(this.scanner.next());
        customerServiceClient.deleteCustomer(customerId);
    }

    public void handleAddCategory() {
        System.out.println("Insert the Name of the category");
        String categoryName = this.scanner.next();
        System.out.println("Insert the Description for the category");
        String description = this.scanner.next();
        CategoryDto category = new CategoryDto(categoryName, description);
        categoryServiceClient.addCategory(category);
    }

    private void handleUpdateCategory() {
        System.out.println("Insert the Id of the category to update");
        int categoryId = this.scanner.nextInt();
        System.out.println("Insert the new Name of the category");
        String categoryName = this.scanner.next();
        System.out.println("Insert the new Descrption for the category");
        String description = this.scanner.next();
        CategoryDto category = new CategoryDto(categoryName, description);
        category.setId(categoryId);
        categoryServiceClient.updateCategory(categoryId, category);
    }

    private void handleFindCategory() {
        System.out.println("Insert the Id of the category to find");
        int categoryId = Integer.parseInt(this.scanner.next());
        var c = categoryServiceClient.findOneCategory(categoryId);

        if (c != null) {
            System.out.println("Category found: " + c);
        } else {
            System.out.println("Could not find category with id " + categoryId);
        }
    }

    private void handleShowAllCategories() {
        var c = categoryServiceClient.getAllCategories();
        for (CategoryDto category : c) {
            System.out.println(category);
        }
        if (c.size() == 0) {
            System.out.println("Could not find any categories");
        }
    }
    private void handleDeleteCategory() {
        System.out.println("Insert the Id of the category to delete");
        int categoryId = Integer.parseInt(this.scanner.next());
        categoryServiceClient.deleteCategory(categoryId);
    }
}
