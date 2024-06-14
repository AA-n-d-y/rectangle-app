package com.render.ASN2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.render.ASN2.models.*;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
import org.springframework.ui.Model;

import java.util.InputMismatchException;
import java.util.List;



@Controller
public class RectanglesController {
    // Creating the repository object
    @Autowired
    private RectangleRepository repo;
    

    // Get request (displaying all the rectangles)
    @GetMapping("/display")
    public String showRectangles(Model model) {
        // Holding all the database's rectangles
        List<Rectangle> rectangleHolder = repo.findAll();

        // Making the rectangles accessible to the display file
        model.addAttribute("rectangles", rectangleHolder);

        // Rendering the display rectangles page
        return "displayRectangles";
    }


    // Get request (displaying the add page)
    @GetMapping("/add")
    public String getAddPage() {
        // Rendering the add rectangle page
        return "addRectangle";
    }    


    // Post request (adding a rectangle)
    @PostMapping("/submit")
    public String addRectangle(@RequestParam Map<String, String> rectangle, HttpServletResponse response) {
        // Processing the POST request
        
        // Getting the input values
        try {
            String name = rectangle.get("nameInput");
            int width = Integer.parseInt(rectangle.get("widthInput"));
            int height = Integer.parseInt(rectangle.get("heightInput"));
            String color = rectangle.get("colorInput");

            // Saving the rectangle into the database and setting the status
            repo.save(new Rectangle(name, width, height, color));
            response.setStatus(201);

            // Rendering the success page
            return "successPage";
        }
        // Catching invalid types for width and height, returning a fail page and setting the status
        catch (InputMismatchException obj) {
            response.setStatus(400);
            return "failPage";
        }
        // Catching all other exceptions, returning a fail page and setting the status
        catch (Exception obj) {
            response.setStatus(400);
            return "failPage";
        }
    }
    

    // Post request (deleting a rectangle from the database)
    @PostMapping("/delete")
    public String postMethodName(@RequestParam Map<String, String> rectangle, HttpServletResponse response) {
        // Deleting the rectangle
        repo.deleteById((Integer.parseInt(rectangle.get("toDelete"))));

        // Returning the successfully deleted page and setting the status
        response.setStatus(200);
        return "deletedRectangle";
    }
    

    // Get request (displaying an individual rectangle)
    @GetMapping("/rectangle")
    public String showRectangle(@RequestParam Map<String, String> rectangle, Model model) {
        // Holding the rectangle (setting it to null if it does not exist)
        Rectangle rectangleHolder = repo.findById((Integer.parseInt(rectangle.get("toDisplay")))).orElse(null);

        // Making the rectangles accessible to the display file
        model.addAttribute("rectangle", rectangleHolder);

        // Rendering the edit rectangle page
        return "editRectangle";
    }

    
    // Post request (updating an individual rectangle)
    @PostMapping("/rectangle")
    public String updateRectangle(@RequestParam Map<String, String> rectangle, HttpServletResponse response) {
        // Getting the input values and updating the rectangle
        try {
            // Holding the rectangle (setting it to null if it does not exist)
            Rectangle rectangleHolder = repo.findById((Integer.parseInt(rectangle.get("recID")))).orElse(null);
            String name = rectangle.get("Name");
            int width = Integer.parseInt(rectangle.get("Width"));
            int height = Integer.parseInt(rectangle.get("Height"));
            String color = rectangle.get("Color");

            // Setting the rectangle's attributes
            rectangleHolder.setName(name);
            rectangleHolder.setWidth(width);
            rectangleHolder.setHeight(height);
            rectangleHolder.setColor(color);

            // Updating the rectangle into the database and setting the status
            repo.save(rectangleHolder);
            response.setStatus(200);

            // Rendering the success page
            return "successPage";
        }
        // Catching invalid types for width and height, returning a fail page and setting the status
        catch (InputMismatchException obj) {
            response.setStatus(400);
            return "failPage";
        }
        // Catching all other exceptions, returning a fail page and setting the status
        catch (Exception obj) {
            response.setStatus(400);
            return "failPage";
        }
    }
}
