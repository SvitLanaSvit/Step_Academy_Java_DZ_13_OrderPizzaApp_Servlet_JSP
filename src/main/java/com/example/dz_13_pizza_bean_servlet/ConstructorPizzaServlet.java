package com.example.dz_13_pizza_bean_servlet;

import com.example.dz_13_pizza_bean_servlet.bean.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "constructorPizzaServlet", value = "/constructor_pizza")
public class ConstructorPizzaServlet extends HttpServlet {
    private Connection connection;
    private Statement statement;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/pizzaApp", "bestuser", "bestuser");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/constructorPizza/pizzaOrderConstructor.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramFromForm = request.getParameter("param");
        if("makePizza".equals(paramFromForm)){
            putListFromDB(request);
            getRequestCategoryIngredientsRegion(request);
            getServletContext().getRequestDispatcher("/constructorPizza/pizzaOrderConstructor.jsp").forward(request,response);
        }else if("orderPizza".equals(paramFromForm)){
            List<Category> categories = getAllCategory();
            List<String> ingredientsByRadioButton = new ArrayList<>();
            for (var category : categories) {
                String ingredient = request.getParameter("ingredient_" + category.getName());
                if(ingredient != null){
                    ingredientsByRadioButton.add(ingredient);
                }
            }
            String[] ingredientsByCheckBox = request.getParameterValues("ingredients");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String region = request.getParameter("region");

            Person person = new Person();
            person.setFirstname(firstname);
            person.setLastname(lastname);
            person.setPhone(phone);
            person.setEmail(email);
            person.setRegion(region);

            if(ingredientsByRadioButton.contains("Thin Crust") || ingredientsByRadioButton.contains("Thick Crust")){
                request.setAttribute("ingredientsRadio", ingredientsByRadioButton);
                request.setAttribute("ingredients", ingredientsByCheckBox);
                request.setAttribute("delivery", person);
                getServletContext().getRequestDispatcher("/constructorPizza/showOrder.jsp")
                        .forward(request, response);
            }else{
                putListFromDB(request);
                getRequestCategoryIngredientsRegion(request);
                String message = "WARNING! Choose one base";
                request.setAttribute("message", message);
                getServletContext().getRequestDispatcher("/constructorPizza/pizzaOrderConstructor.jsp")
                        .forward(request, response);
            }
        }
    }

    public List<Category> getAllCategory(){
        String query = "SELECT * FROM categories";
        List<Category> categories = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name_category"));

                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }
    public List<ConstructorIngredient> getAllIngredients(){
        String query = "SELECT " +
                "mpi.id, mpi.category_id, c.name_category, mpi.name_ingredient, mpi.is_multi FROM mypizzaingredients mpi " +
                "LEFT JOIN categories c ON c.id = mpi.category_id";
        List<ConstructorIngredient> ingredients = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                ConstructorIngredient ingredient = new ConstructorIngredient();
                ingredient.setId(resultSet.getInt("id"));
                ingredient.setCategory_id(resultSet.getInt("category_id"));
                ingredient.setName_category(resultSet.getString("name_category"));
                ingredient.setName_ingredient(resultSet.getString("name_ingredient"));
                ingredient.setMulti(resultSet.getBoolean("is_multi"));

                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredients;
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
    private List<Region> getAllRegions(){
        String query = "SELECT * FROM regions;";
        List<Region> periods = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Region region = new Region();
                region.setId(resultSet.getInt("id"));
                region.setName_region(resultSet.getString("name_region"));

                periods.add(region);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return periods;
    }
    private void putListFromDB(HttpServletRequest request){
        List<Pizza> pizzas = getAllPizzas();
        int size = pizzas.size();
        request.setAttribute("pizzasSize", size);
    }
    private void getRequestCategoryIngredientsRegion(HttpServletRequest request){
        List<Category> categories = getAllCategory();
        request.setAttribute("categories", categories);
        List<ConstructorIngredient> ingredients = getAllIngredients();
        request.setAttribute("ingredients", ingredients);
        List<Region> regions = getAllRegions();
        request.setAttribute("regions", regions);
    }

}
