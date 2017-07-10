package main;

import java.util.Random;

import main.objects.GameObjects;
import main.objects.ID;
import main.objects.MineCart;
import main.objects.Rock;


/**
 * Represents a level in the game
 * @author morsecode
 *
 */
public class Level implements Comparable<Level> {
    
    private int value;
    private MineCart player;
    private Handler handler;
    
    private static final int MAX_DISTANCE= 132;
    
    public Level(MineCart player, Handler handler) {
        this(1, player, handler); // the first level
    }
    
    private Level(int value, MineCart player, Handler handler) {
        this.value= Math.abs(value);    // don't allow levels < 0
        this.player= player;
        this.handler= handler;
        create();
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
        player.setX(0);
        
        if (player.getModel().isDead()) {
            return null;
        }
        
        return new Level(value + 1, player, handler);
    }
    
    
    private void create() {
        for(int xx=0; xx< MAX_DISTANCE; xx++){
            for(int yy=0; yy<4; yy++){
                
                Random rand = new Random();
                int s=rand.nextInt(5);
                if(s==0){
                    
                    int s2=rand.nextInt(100);
                    
                    GameObjects o = new Rock(xx*96+32, yy*96+232, ID.rock);
                    handler.addObject(o);
                    
                    if(s2==0){
                        o.setType(2);
                    }else{
                        o.setType(1);   
                    }
                    
                }else if(s==1){
    
                    int s2=rand.nextInt(200);
                    
                    GameObjects o = new Rock(xx*96+32, yy*96+232, ID.rock);
                    handler.addObject(o);
                    
                    if(s2==0){
                        o.setType(-2);
                    }else{
                        o.setType(-1);  
                    }
                }
            }
        }
        
    }

    public boolean keepGoing() {
        boolean endReached= player.getX() < MAX_DISTANCE;
        System.out.println("keep going: "+ endReached);
        return endReached;
    }
    
}
