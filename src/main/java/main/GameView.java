package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.List;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import main.gfx.Assets;
import main.gfx.Camera;
import main.gfx.Font;
import main.input.Keyboard;
import main.input.MouseInput;
import main.objects.GameItemBase;
import main.objects.MineCart;

@EnableAutoConfiguration
@Component
public class GameView extends Canvas implements Runnable {
    
	private Camera camera;
	
	@Autowired
	private int windowWidth;
	
	@Autowired
	private int windowHeight;
	
	@Autowired
	private JFrame window;
	
	@Autowired
	private GameController game;
	
	private MouseInput mouseInput;
	//private Thread thread;
	
	private Level level;
	
	public GameView(){
        this.camera = new Camera(0, 100);
	    
	}
	
	public void init(Level level) {
	    this.level= level;
        window.add(this);
	    window.setVisible(true);

        this.addKeyListener(new Keyboard(level.getHandler()));
        mouseInput = new MouseInput(level.getHandler(), camera, game);
        this.addMouseListener(mouseInput);
        this.addMouseMotionListener(mouseInput);
	}
	
	@Override
	public void run() {
		this.requestFocus();
		while (game.isRunning()) {
			render();
		    // try { Thread.sleep(5); } catch (InterruptedException ignore) { }
		}
	}
	
	
	public void render(List<GameItemBase> object, Graphics g){
	    
	    for(int i = 0; i < object.size(); i++){
	        GameItemBase tempObject = object.get(i);

	        Rectangle screen = new Rectangle((int)camera.getX(), (int)camera.getY(), windowWidth, windowHeight);
	            
	        if(tempObject.getBounds().intersects(screen)){
	            tempObject.render(g);
	        }
	    }
	}
	
	
	// moved here from Game.java

    public void tick(MineCart player){
        
			
        if(player.getModel().isDead()){
            player.setType(-1);
            return;
        }
        
        // newer for loop structure (as of about Java6)
//        for (GameObjects obj : handler.getObjects()) {
//           if (obj.getId() == ID.mineCart) {
//               camera.tick(obj);
//           }
//        }
        camera.tick(player);    // this line replaces the above loop (i think) ... brad 2017-07-11
        
        // brad: i can't decide what to do with the below:
        for (GameItemBase obj : level.getHandler().getObjects()) {
		    Rectangle screen = new Rectangle((int)camera.getX(), (int)camera.getY(), windowHeight, windowWidth);
            level.getHandler().tick(obj, screen);
        }
        // it seems wrong that every piece of the app needs access to every other piece, so i'm trying 
        // to unwind that.  this may just be a revision.
        
    }
    
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        
        //clear screen
        g.clearRect(0, 0, windowWidth, windowHeight);
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, windowWidth, windowHeight);
        
        //draw here
        g2d.translate(-camera.getX(), -camera.getY());
        
        // render tracks
        renderTracks(g);
        
        // render the objects 
        render(level.getHandler().getObjects(), g);
        
        g2d.translate(camera.getX(), camera.getY());
        
        // render heads up display (HuD)
        renderHUD(g);
        
        //end draw  
        g.dispose();
        bs.show();  
    }
    
    // moved here from Game.java
    // i'm trying to consolidate areas of responsibility
    public void renderHUD(Graphics g){
        String msg = "Money "+ game.getPlayer().getModel().getMoney();
        
        g.setColor(Color.BLUE);
        g.fillRect(245, windowHeight-107, (msg.length()*32)+15, 48);
        Font.draw(g, msg, 250, windowHeight-100, 255, 255, 255, 2);
        
        g.setColor(Color.BLACK);
        g.fillRect(5, windowHeight-107, PlayerModel.START_HEALTH *2+4, 32);
        g.setColor(Color.RED);
        g.fillRect(7, windowHeight-105, game.getPlayer().getModel().getHealth()*2, 28);
        
        g.setColor(Color.GREEN);
        g.drawRect(mouseInput.getX()-16, mouseInput.getY()-8, 32, 32);
    }
    
    // moved here from Game.java
    public void renderTracks(Graphics g){
        for(int xx = 0; xx < 12800; xx+=512){
            g.drawImage(Assets.tracks, xx, 606, 512, 48, null);
        }
    }
    
    // accessor method for the camera so that other objects can get a reference to it
    public Camera getCamera() {
        return camera;
    }
}
