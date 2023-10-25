package com.example.dz_13_pizza_bean_servlet;

import com.example.dz_13_pizza_bean_servlet.bean.Ingredient;
import com.example.dz_13_pizza_bean_servlet.bean.Pizza;
import com.example.dz_13_pizza_bean_servlet.bean.PizzaOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "pizzaServlet", value = "/pizza")
public class PizzaServlet extends HttpServlet {
    private Connection connection;
    private Statement statement;

    @Override
    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/pizzaApp", "bestuser", "bestuser");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pizza> pizzas = getAllPizzas();
        int size = pizzas.size();
        request.setAttribute("pizzasSize", size);
        getServletContext().getRequestDispatcher("/pizza.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramFromForm = request.getParameter("param");

        if("connect".equals(paramFromForm)){
            connect();
            String message = "The connection was successful!";
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/pizza.jsp").forward(request, response);
        }else if("insert".equals(paramFromForm)){
            insert();
            String message = "The insertion was successful!";
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/pizza.jsp").forward(request, response);
        }else if("readyMadePizza".equals(paramFromForm)){
            List<Pizza> pizzas = getAllPizzas();
            List<Ingredient> ingredients = getAllIngredients();
            request.setAttribute("pizzas", pizzas);
            request.setAttribute("ingredients", ingredients);
            getServletContext().getRequestDispatcher("/pizzaOrder.jsp").forward(request, response);
        }else if("orderPizza".equals(paramFromForm)){
            String[] pizzas = request.getParameterValues("pizzaName");
            String[] ingredients = request.getParameterValues("ingredient");
            List<PizzaOrder> pizzaOrderList = new ArrayList<>();
            for (String pizza : pizzas) {
                PizzaOrder pizzaOrder = new PizzaOrder();
                String count = request.getParameter(pizza + "_Quantity");
                int countInt = Integer.parseInt(count);
                pizzaOrder.setName(pizza);
                pizzaOrder.setCount(countInt);
                pizzaOrderList.add(pizzaOrder);
            }
            request.setAttribute("pizzaOrderList", pizzaOrderList);
            request.setAttribute("ingredients", ingredients);
            getServletContext().getRequestDispatcher("/showOrder.jsp").forward(request,response);
        }
    }

    private void connect(){
        String pizzas = "CREATE TABLE pizzas(" +
                "id int auto_increment primary key not null, " +
                "name_pizza nvarchar(250) not null unique);";

        String ingredients = "CREATE TABLE ingredients(" +
                "id int auto_increment primary key not null, " +
                "name_ingredient nvarchar(250) not null unique);";

        String categories = "CREATE TABLE categories(" +
                "id int auto_increment primary key not null," +
                "name_category nvarchar(250) not null unique);";

        String myPizzaIngredients = "CREATE TABLE mypizzaingredients(" +
                "id int auto_increment primary key not null, " +
                "category_id int not null, " +
                "name_ingredient nvarchar(250) not null unique, " +
                "is_multi bit not null, FOREIGN KEY (category_id) references categories(id));";

        try {
            statement.executeUpdate(pizzas);
            statement.executeUpdate(ingredients);
            statement.executeUpdate(categories);
            statement.executeUpdate(myPizzaIngredients);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void insert(){
        String pizzas = "INSERT INTO pizzas (name_pizza) " +
                "VALUES('Margarita'), " +
                "('Four cheeses'), " +
                "('Caprichoza'), " +
                "('Hawaiian');";

        String ingredients = "INSERT INTO ingredients (name_ingredient) " +
                "VALUES('Tomato Sauce'), " +
                "('Mozzarella Cheese'), " +
                "('Parmesan Cheese'), " +
                "('Cheddar Cheese'), " +
                "('Gorgonzola Cheese'), " +
                "('Feta Cheese'), " +
                "('Pepperoni'), " +
                "('Sausage'), " +
                "('Bacon'), " +
                "('Ham'), " +
                "('Mushrooms'), " +
                "('Green Peppers'), " +
                "('Onions'), " +
                "('Black Olives'), " +
                "('Green Olives'), " +
                "('Spinach'), " +
                "('Tomatoes'), " +
                "('Pineapple'), " +
                "('Jalapeños'), " +
                "('Garlic');";

        String categories = "INSERT INTO categories (name_category) " +
                "VALUES('Pizza Base'), " +
                "('Meat'), " +
                "('Vegetables'), " +
                "('Cheese'), " +
                "('Sauces'), " +
                "('Herbs and Spices'), " +
                "('Seafood');";

        String myPizzaIngredients = "INSERT INTO mypizzaingredients (category_id, name_ingredient, is_multi) " +
                "VALUES(1, 'Thin Crust', 0), " +
                "(1, 'Thick Crust', 0), " +
                "(2, 'Pepperoni', 1), " +
                "(2, 'Sausage', 1), " +
                "(2, 'Bacon', 1), " +
                "(2, 'Ham', 1), " +
                "(3, 'Mushrooms', 1), " +
                "(3, 'Green Peppers', 1), " +
                "(3, 'Onions', 1), " +
                "(3, 'Black Olives', 1), " +
                "(4, 'Mozzarella', 1), " +
                "(4, 'Parmesan', 1), " +
                "(4, 'Cheddar', 1), " +
                "(4, 'Gorgonzola', 1), " +
                "(4, 'Feta', 1), " +
                "(5, 'Tomato Sauce', 0), " +
                "(5, 'Barbecue Sauce', 0), " +
                "(6, 'Basil', 0), " +
                "(6, 'Oregano', 0), " +
                "(6, 'Garlic Powder', 0), " +
                "(7, 'Shrimp', 1), " +
                "(7, 'Clams', 1);";

        try {
            statement.executeUpdate(pizzas);
            statement.executeUpdate(ingredients);
            statement.executeUpdate(categories);
            statement.executeUpdate(myPizzaIngredients);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Pizza> getAllPizzas(){
        String query = "SELECT * FROM pizzas";
        List<Pizza> pizzas = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Pizza pizza = new Pizza();
                pizza.setId(resultSet.getInt("id"));
                pizza.setName(resultSet.getString("name_pizza"));

                pizzas.add(pizza);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pizzas;
    }
    private List<Ingredient> getAllIngredients(){
        String query = "SELECT * FROM ingredients";
        List<Ingredient> ingredients = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Ingredient ingredient = new Ingredient();
                ingredient.setId(resultSet.getInt("id"));
                ingredient.setName(resultSet.getString("name_ingredient"));

                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredients;
    }
}
