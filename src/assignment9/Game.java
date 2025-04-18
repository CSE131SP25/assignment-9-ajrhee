package assignment9;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {

	private Snake snake;
	private Food food;
	private int pauseTime = 50; // Initial delay (in milliseconds)

	public Game() {
		StdDraw.enableDoubleBuffering();
		snake = new Snake();
		food = new Food(snake.getOccupiedGridPoints()); // Pass snake's occupied grid points to food
	}

	public void play() {
	    while (snake.isInbounds() && !snake.checkCollision()) {  // Stop if snake collides with itself or goes out of bounds
	        int dir = getKeypress();
	        if (dir != -1) snake.changeDirection(dir);

	        snake.move();

	        int result = snake.eatFood(food);
	        if (result == 1) {
	            food = new Food(snake.getOccupiedGridPoints()); // Respawn regular food
	        }

	        updateDrawing();
	        StdDraw.pause(pauseTime); // Adjust this if needed
	    }

	    gameOver();  // Show the game over screen when the game ends
	}

	private void gameOver() {
	    StdDraw.clear();

	    // Set font and color for the game over message
	    StdDraw.setPenColor(StdDraw.RED);
	    StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 30));

	    // Display "Game Over" message in the center of the screen
	    StdDraw.text(0.5, 0.5, "Game Over!");

	    // Display the final score
	    StdDraw.setPenColor(StdDraw.BLACK);
	    StdDraw.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));
	    StdDraw.text(0.5, 0.45, "Final Score: " + snake.getScore());

	    StdDraw.show();
	}

	private int getKeypress() {
		if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}

	private void updateDrawing() {
	    StdDraw.clear();

	    // Draw the score in the top-left corner
	    StdDraw.setPenColor(StdDraw.BLACK);
	    StdDraw.textLeft(0.02, 0.98, "Score: " + snake.getScore()); // Use snake's score

	    // Draw the snake and regular food
	    snake.draw();
	    food.draw();

	    StdDraw.show();
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}