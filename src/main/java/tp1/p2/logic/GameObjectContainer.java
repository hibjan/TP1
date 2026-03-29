package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.Sun;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.view.Messages;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}
	
	public String positionToString(int col, int row) {
		StringBuilder buffer = new StringBuilder();
		boolean sunPainted = false;
		boolean sunAboutToPaint = false;

		for (GameObject g : gameObjects) {
			if(g.isAlive() && g.getCol() == col && g.getRow() == row) {
				String objectText = g.toString();
				sunAboutToPaint = objectText.indexOf(Messages.SUN_SYMBOL) >= 0;
				if (sunAboutToPaint) {
					if (!sunPainted) {
						buffer.append(objectText);
						sunPainted = true;
					}
				} else {
					buffer.append(objectText);
				}
			}
		}

		return buffer.toString();
	}
	
	public void update() {
		for(int i = 0; i < gameObjects.size(); i++) {
			GameObject g = gameObjects.get(i);
			if(g.isAlive()) {
				g.update();
			}
		}
	}
	
	public boolean takeDead() {
		List<GameObject> aux = new ArrayList<>();
		
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i).isAlive())
                aux.add(gameObjects.get(i));
			else 
				gameObjects.get(i).onExit();
		}
            
		gameObjects = aux;
	
		return false;
	}
	
	public GameItem getGameItemInPosition(int col, int row) {
		for(GameObject object : gameObjects)
			if(object.isInPosition(col,row) && object.fillPosition())
				return object;
		return null;
	}
	
	public boolean addItem(GameObject gameObject) {
		if(gameObjects.add(gameObject)) {
			gameObject.onEnter();
			return true;
		}
		return false;
	}
	
	public void checkValidObjectPosition(int col, int row) throws GameException{
		//There's an object in the coordinates
		if(isFullyOccupied(col, row))
			throw new InvalidPositionException(Messages.INVALID_POSITION.formatted(col, row));
	}
	
	public boolean isFullyOccupied(int col, int row) {
		int i=0;
		boolean fullyOcuppied = false;

		while (i<gameObjects.size() && !fullyOcuppied) {
			GameObject g = gameObjects.get(i);
			if (g.isAlive() && g.isInPosition(col, row)) {
				fullyOcuppied = g.fillPosition();
			}
			i++;
		}

		return fullyOcuppied;
	}
	
	public boolean tryToCatchObject(int col, int row) {
		boolean caught = false;
		
		for(GameObject object : gameObjects)
			if(object.isInPosition(col,row))
				if(object.catchObject())
					caught = true;
				
		return caught;
	}
}
