package assignment9;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;

public class Food {

    public static final int GRID_SIZE = 50; // 50x50 grid
    public static final double FOOD_SIZE = 1.0 / GRID_SIZE;

    private int gridX, gridY;

    /**
     * Creates a new Food at a random grid location, not on the snake
     * @param snakeBody list of Points occupied by the snake
     */
    public Food(List<Point> snakeBody) {
        Random rand = new Random();
        boolean valid = false;

        if (snakeBody == null) {
            snakeBody = new ArrayList<>(); // Initialize to empty list if null
        }
        
        while (!valid) {
            gridX = rand.nextInt(GRID_SIZE);
            gridY = rand.nextInt(GRID_SIZE);
            Point foodPoint = new Point(gridX, gridY);

            if (!snakeBody.contains(foodPoint)) {
                valid = true;
            }
        }
    }

    /**
     * Draws the Food
     */
    public void draw() {
        double x = (gridX + 0.5) * FOOD_SIZE;
        double y = (gridY + 0.5) * FOOD_SIZE;

        StdDraw.setPenColor(Color.RED);
        StdDraw.filledCircle(x, y, FOOD_SIZE / 2);
    }

    // Getters for collision checks
    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public Point getPosition() {
        return new Point(gridX, gridY);
    }
}