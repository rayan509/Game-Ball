package gameball;

import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameBall extends JFrame implements ActionListener,MouseMotionListener,KeyListener{
    public final static int WIDTH=700;
    public final static int HIEGHT=700;
   public static GameBall game;
     int Xbat=350;
    int Ybat=670;
    int XBall=50;
    int YBall=50;
    int dx=10;
    int dy=15;
    int Score =0;
   // int speed=20;
    Rectangle bat = new Rectangle(Xbat,Ybat,100,10);
    Rectangle ball = new Rectangle(XBall,YBall,30,30);
    boolean start=true;
    boolean gameOver=false;
    int speed=35;
    Timer t;
   
        GameBall(){
            setSize(WIDTH, HIEGHT);
            setTitle("Game Ball");
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            addMouseMotionListener(this);
            addKeyListener(this);
            t = new Timer(speed,this);
            t.stop();
        }
         public void paint(Graphics g){
            super.paint(g);
            if(!start){
                 g.drawString("Press Space to Play ", getWidth()/2-60, getHeight()/2+40);
            }
            if(start){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 700, 700);
            g.setColor(Color.RED);
            g.drawString("Score :"+Score,20,50);
            g.fillOval(ball.x ,ball.y, ball.width,ball.height);
            g.fillRect(bat.x, bat.y, bat.width, bat.height);
            }
         
            if(gameOver){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 700, 700);
            g.setColor(Color.WHITE);
            g.drawString("Game Over ", getWidth()/2-20, getHeight()/2);
            g.drawString("Score :  "+Score, getWidth()/2-20, getHeight()/2+20);
            g.drawString("Press Space to Play Again", getWidth()/2-60, getHeight()/2+40);
              } 
         }
         
            public void BallMove(){
               
               new Thread(){
                  public void run(){
                              ball.x+=dx;
              ball.y+=dy;
              if(ball.x> getWidth()-50){
              dx=-dx;
              }
              if(ball.x<0){
              dx=-dx;
              }
                if(ball.y> getHeight()-50){
                gameOver=true;
                start=false;
                t.stop();
              }
              if(ball.y<50){
              dy=-dy;
              }
              if(ball.intersects(bat)){
              dy=-dy;
              Score+=10;
              }
                  }
            }.start();
            }
  
      
        
        
        
    public static void main(String[] args) {
       game= new GameBall();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
        BallMove();
       
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        bat.x=e.getX();
        bat.y=e.getY();
    
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        bat.x=e.getX();
        bat.y=e.getY();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int s=e.getKeyCode();
       if(s==KeyEvent.VK_SPACE&&gameOver){
       game.dispose();
       game = new GameBall();
         
       }
        if(s==KeyEvent.VK_SPACE){
         
         t.start();
       
    }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}