package main.input;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game;
import main.EventHandler;
import main.GameController;
import main.GameView;
import main.PlayerModel;
import main.gfx.Camera;
import main.objects.ID;
import main.objects.GameItemBase;
import main.objects.MineCart;

public class MouseInput extends MouseAdapter{
	
	private EventHandler handler;
	private Camera camera;
	private GameController game;
	
	private int x,y;
	
	public MouseInput(EventHandler handler, Camera camera, GameController game){
		this.handler = handler;
		this.camera = camera;
		this.game = game;
	}
	
	public void mouseMoved(MouseEvent e){
		x = (int) (e.getX());
		y = (int) (e.getY());
	}
	
	public void mouseDragged(MouseEvent e){
		x = (int) (e.getX());
		y = (int) (e.getY());
	}
	
	public void mousePressed(MouseEvent e){
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());
		
		
		if(game.getPlayer().getType()<0) return;
		
		for (GameItemBase tempObject : handler.getObjects()) {
			
			Rectangle r = new Rectangle(mx-16, my-8, 32, 32);
			
			if(tempObject.getBounds().intersects(r) && tempObject.getId()==ID.rock){
				
				if(tempObject.getType()==-1){
					game.getPlayer().getModel().subtractHealth();
				}else if(tempObject.getType()==-2){
					if(game.getPlayer().getModel().getHealth() != PlayerModel.START_HEALTH){
                        game.getPlayer().getModel().addHealth();
					}else{
						game.getCoinSound().stop();
						game.getCoinSound().play();
						game.getPlayer().getModel().addMoney();
					}
				}else if(tempObject.getType()>0){
					
					game.getCoinSound().stop();
					game.getCoinSound().play();
					game.getPlayer().getModel().addMoney();
					
					
				}
				
				tempObject.setType(0);
			}
			
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
