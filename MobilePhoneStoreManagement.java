package com.example.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Optional;



import java.util.ArrayList;
import java.util.List;

public class MobilePhoneStoreManagement extends Application {

    private List<Product> productList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Create Product Management UI
        VBox productBox = new VBox(10);
        productBox.setPadding(new Insets(10));
        Button addProductBtn = new Button("Add Product");
        Button editProductBtn = new Button("Edit Product");
        Button deleteProductBtn = new Button("Delete Product");
        Button listProductBtn = new Button("List Products");
        productBox.getChildren().addAll(addProductBtn, editProductBtn, deleteProductBtn, listProductBtn);

        // Create Customer Management UI
        VBox customerBox = new VBox(10);
        customerBox.setPadding(new Insets(10));
        Button addCustomerBtn = new Button("Add Customer");
        Button editCustomerBtn = new Button("Edit Customer");
        Button deleteCustomerBtn = new Button("Delete Customer");
        Button listCustomerBtn = new Button("List Customers");
        customerBox.getChildren().addAll(addCustomerBtn, editCustomerBtn, deleteCustomerBtn, listCustomerBtn);

        // Create Order Management UI
        VBox orderBox = new VBox(10);
        orderBox.setPadding(new Insets(10));
        Button addOrderBtn = new Button("Add Order");
        Button editOrderBtn = new Button("Edit Order");
        Button deleteOrderBtn = new Button("Delete Order");
        Button listOrderBtn = new Button("List Orders");
        orderBox.getChildren().addAll(addOrderBtn, editOrderBtn, deleteOrderBtn, listOrderBtn);

        // Create layout for the main screen
        HBox root = new HBox(20);
        root.getChildren().addAll(productBox, customerBox, orderBox);

        // Set actions for buttons
        addProductBtn.setOnAction(event -> addProduct());
        editProductBtn.setOnAction(event -> editProduct());
        deleteProductBtn.setOnAction(event -> deleteProduct());
        listProductBtn.setOnAction(event -> listProducts());

        addCustomerBtn.setOnAction(event -> addCustomer());
        editCustomerBtn.setOnAction(event -> editCustomer());
        deleteCustomerBtn.setOnAction(event -> deleteCustomer());
        listCustomerBtn.setOnAction(event -> listCustomers());

        addOrderBtn.setOnAction(event -> addOrder());
        editOrderBtn.setOnAction(event -> editOrder());
        deleteOrderBtn.setOnAction(event -> deleteOrder());
        listOrderBtn.setOnAction(event -> listOrders());

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setTitle("Mobile Phone Store Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addProduct() {
        // Add product functionality
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Add Product");
        dialog.setHeaderText("Enter Product Details");

        // Set the button types
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create and add input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Product Name");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");
        TextField priceField = new TextField();
        priceField.setPromptText("Price");
        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        GridPane grid = new GridPane();
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(new Label("Price:"), 0, 2);
        grid.add(priceField, 1, 2);
        grid.add(new Label("Quantity:"), 0, 3);
        grid.add(quantityField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a product object when the add button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());

                    // Create a new product
                    Product newProduct = new Product(name, description, price, quantity);
                    productList.add(newProduct);

                    // You can add further logic here like storing the product to a database

                    return newProduct;
                } catch (NumberFormatException e) {
                    // Handle invalid input
                    e.printStackTrace();
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void editProduct() {
        // Edit product functionality
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Edit Product");
        dialog.setHeaderText("Enter New Product Details");

        // Set the button types
        ButtonType editButtonType = new ButtonType("Edit", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(editButtonType, ButtonType.CANCEL);

        // Create and add input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Product Name");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");
        TextField priceField = new TextField();
        priceField.setPromptText("Price");
        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        GridPane grid = new GridPane();
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(new Label("Price:"), 0, 2);
        grid.add(priceField, 1, 2);
        grid.add(new Label("Quantity:"), 0, 3);
        grid.add(quantityField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a product object when the edit button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == editButtonType) {
                try {
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());

                    // Create a new product
                    Product editedProduct = new Product(name, description, price, quantity);

                    // You can further logic here like updating the product in the database

                    return editedProduct;
                } catch (NumberFormatException e) {
                    // Handle invalid input
                    e.printStackTrace();
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void deleteProduct() {
        // Delete product functionality
        ChoiceDialog<Product> dialog = new ChoiceDialog<>(null, productList);
        dialog.setTitle("Delete Product");
        dialog.setHeaderText("Select a product to delete:");
        dialog.setContentText("Product:");

        Optional<Product> result = dialog.showAndWait();
        result.ifPresent(product -> {
            // Remove the selected product from the list
            productList.remove(product);

            // You can further logic here like deleting the product from the database
        });
    }

    private void listProducts() {
        // List products functionality

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("List Products");
        dialog.setHeaderText("Product List");

        // Create a ListView to display products
        ListView<String> listView = new ListView<>();
        for (Product product : productList) {
            listView.getItems().add(product.getName() + " - $" + product.getPrice());
        }

        dialog.getDialogPane().setContent(listView);

        // Add OK button to close the dialog
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButton);

        dialog.showAndWait();
    }

    private void addCustomer() {
        // Add customer functionality

    }

    private void editCustomer() {
        // Edit customer functionality
    }

    private void deleteCustomer() {
        // Delete customer functionality
    }

    private void listCustomers() {
        // List customers functionality
    }

    private void addOrder() {
        // Add order functionality
    }

    private void editOrder() {
        // Edit order functionality
    }

    private void deleteOrder() {
        // Delete order functionality
    }

    private void listOrders() {
        // List orders functionality
    }

    public static void main(String[] args) {
        launch(args);
    }
}
