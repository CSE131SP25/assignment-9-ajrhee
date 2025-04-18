package assignment9;

import java.awt.Point;
import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = Food.FOOD_SIZE;
	private int score = 0;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private boolean shouldGrow = false;

	public Snake() {
		segments = new LinkedList<>();

		// Place in middle of grid
		int gridX = 25; // center of 50x50 grid
		int gridY = 25;

		double startX = (25 + 0.5) * Food.FOOD_SIZE;
		double startY = (25 + 0.5) * Food.FOOD_SIZE;
		segments.add(new BodySegment(startX, startY, SEGMENT_SIZE));

		deltaX = 0;
		deltaY = 0;
	}

	public void changeDirection(int direction) {
		if (direction == 1) { // up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { // down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { // left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { // right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}

	public void move() {
		if (deltaX == 0 && deltaY == 0) return;

		// Get current head position
		BodySegment head = segments.getFirst();
		double newX = head.getX() + deltaX;
		double newY = head.getY() + deltaY;

		// Add new head
		segments.addFirst(new BodySegment(newX, newY, SEGMENT_SIZE));

		if (!shouldGrow) {
			segments.removeLast(); // normal move
		} else {
			shouldGrow = false; // grow once
		}
	}

	public void draw() {
		for (BodySegment segment : segments) {
			segment.draw();
		}
	}

	public int eatFood(Food f) {
	    BodySegment head = segments.getFirst();

	    int headGridX = (int) (head.getX() / Food.FOOD_SIZE);
	    int headGridY = (int) (head.getY() / Food.FOOD_SIZE);

	    if (headGridX == f.getGridX() && headGridY == f.getGridY()) {
	        shouldGrow = true;
	        score++; // increment score when eating regular food
	        return 1; // regular food eaten
	    }
	    return 0; // no food eaten
	}

	public boolean isInbounds() {
		BodySegment head = segments.getFirst();
		double x = head.getX();
		double y = head.getY();
		return x >= 0 && x <= 1 && y >= 0 && y <= 1;
	}
	
	public boolean checkCollision() {
	    BodySegment head = segments.getFirst();
	    for (int i = 1; i < segments.size(); i++) {
	        if (head.getX() == segments.get(i).getX() && head.getY() == segments.get(i).getY()) {
	            return true; // Collision detected
	        }
	    }
	    return false; // No collision
	}
	
	public int getScore() {
	    return score;
	}

	// To provide the body as grid points to avoid food spawn collisions
	public LinkedList<Point> getOccupiedGridPoints() {
		LinkedList<Point> bodyPoints = new LinkedList<>();
		for (BodySegment seg : segments) {
			int gx = (int) (seg.getX() / Food.FOOD_SIZE);
			int gy = (int) (seg.getY() / Food.FOOD_SIZE);
			bodyPoints.add(new Point(gx, gy));
		}
		return bodyPoints;
	}
}