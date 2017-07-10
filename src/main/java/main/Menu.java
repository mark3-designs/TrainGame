package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import main.gfx.Assets;
import main.gfx.Font;

public class Menu extends Canvas {

    private JFrame window;
    
    // in-line enumeration declaration
    public enum MenuItem {
        PLAY("Play")
        , QUIT("Quit");
        
        private String text;
        
        private MenuItem(String text) {
            this.text= text;
        }
        
        public String toString() {
            return text;
        }
    }
    
    
    private MenuItem selection;
    
    
    public Menu(int width, int height, String title) {
        this.window= new JFrame(title);
        
        window.setPreferredSize(new Dimension(width, height));
        window.setMaximumSize(new Dimension(width, height));
        window.setMinimumSize(new Dimension(width, height));
        
        window.add(this);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        this.addKeyListener(new MenuKeyListener(this));
        //mouseInput = new MouseInput(handler, camera, this);
        //this.addMouseListener(mouseInput);
        //this.addMouseMotionListener(mouseInput);
        
        render();
    }

    public void select() {
        // todo
        selection= MenuItem.QUIT;
    }

    public MenuItem getSelection() {
        
        while (selection == null) {
            
        }
        return selection;
    }

    
    private void render() {
        /*
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        */
        
        Graphics g = this.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        
        //clear screen
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        //draw here
        String msg = "Hello World.";
        
        g.setColor(Color.BLUE);
        g.fillRect(0, HEIGHT-107, (msg.length()*32)+15, 48);
        Font.draw(g, msg, 250, HEIGHT-100, 255, 255, 255, 2);
        
        g.setColor(Color.GREEN);
        //g.drawRect(mouseInput.getX()-16, mouseInput.getY()-8, 32, 32);
        
        //end draw  
        //g.dispose();
    }

}
