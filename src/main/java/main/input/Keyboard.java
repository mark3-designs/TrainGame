package main.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.EventHandler;

public class Keyboard extends KeyAdapter {

	EventHandler handler;
	
	public Keyboard(EventHandler handler){
		this.handler = handler;
	}
	
	public void keyTyped(KeyEvent e){
		int key = e.getKeyChar();
		
		
		switch (key) {
		case KeyEvent.VK_UP:
		case 'f': case 'F':
			handler.setMove(!handler.isMove());
		System.out.println("keypress event: "+ e);
		    break;
		case KeyEvent.VK_DOWN:
		case 's': case 'S':
			handler.setBrake(!handler.isComplete());
		System.out.println("keypress event: "+ e);
			break;
		    
		}
		
	}
	
}
