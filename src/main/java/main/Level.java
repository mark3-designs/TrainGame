package main;

import main.objects.MineCart;


/**
 * Represents a level in the game
 * @author morsecode
 *
 */
public class Level implements Comparable<Level> {
    
    private int value;
    
    private EventHandler handler;
    private MineCart player;
    
    public Level(MineCart player) {
        this.value= 1;  // the first level
        this.player= player;
    }
    
    private Level(int value, MineCart player) {
        this.value= Math.abs(value);    // don't allow levels < 0
        this.player= player;
    }

    public int getValue() {
        return value;
    }
    
    /**
     * Returns <=-1 if this < otherLevel
     * Returns 0 if this == otherLevel
     * Returns >=1  if this > otherLevel 
     */
    @Override
    public int compareTo(Level otherLevel) {
        if (otherLevel == null) {
            return 1;   // something > null
        }
        
        // simple way to return < 0, 0, or > 0 with some math
        return value - otherLevel.value;
    }
    
    public Level getNextLevel() {
        return new Level(value + 1, player);
    }
    
    public EventHandler getHandler() {
        if (handler == null) {
            return (handler= handler= new EventHandler(player, this));
        } 
        
        return handler;
    }
}
