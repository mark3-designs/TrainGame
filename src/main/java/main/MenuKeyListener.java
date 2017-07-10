package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuKeyListener implements KeyListener {

    private Menu menu;
    
    public MenuKeyListener(Menu menu) {
        this.menu= menu;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.isActionKey()) {
            menu.select();
        }
    
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

}
