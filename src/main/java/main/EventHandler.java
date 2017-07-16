package main;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import main.objects.GameItemBase;
import main.objects.ID;
import main.objects.MineCart;

public class EventHandler {
	//tick and render all objects

    // member variables should generally all be private to promote encapsulation
	private LinkedList<GameItemBase> object = new LinkedList<GameItemBase>();
	private boolean move = false, brake = false;
	
	private boolean levelComplete= false;
    private MineCart player;
    private Level level;
	
		
	public EventHandler(MineCart player, Level level){
	    this.player= player;
	    this.level= level;
	}
		
	public void tick(GameItemBase tempObject, Rectangle screen){
			
			if(tempObject.getBounds().intersects(screen)){
			    if (tempObject == player && !brake) {
			        tempObject.tick();
			    }
			}else if (tempObject == player) {
					tempObject.setType(-1);    // brad: figure out why the type is getting set to -1 and what that means.
					tempObject.tick();
					// see if we've past the end of the level.
					if (tempObject.getX() > screen.x + screen.width) {
					    levelComplete= true;
					}
			}
	}
	
	/**
	 * Returns a read-only copy of the internal list of objects being tracked by this handler
	 * @return
	 */
	public List<GameItemBase> getObjects() {
        return Collections.unmodifiableList(object);
    }
		

	
	public void addObject(GameItemBase tempObject){
		object.add(tempObject);
	}
	
	public void removeObject(GameItemBase tempObject){
		object.remove(tempObject);
	}

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}
		
	public boolean isBrake() {
		return brake;
	}

	public void setBrake(boolean brake) {
		this.brake = brake;
	}

	public boolean isComplete() { return levelComplete; }
	public MineCart getPlayer() { return player; }
	public Level getLevel() { return level; }
	
}
