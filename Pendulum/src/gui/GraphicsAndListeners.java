package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import logic.Pendulum;

public class GraphicsAndListeners extends JPanel implements KeyListener, MouseListener, ActionListener, MouseMotionListener{
	
	public Pendulum p;
	public Pendulum p2;
	public GraphicInterface graphics;
	public boolean playing;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private double scaleFactor;
	private LoopThread thread;
	public int pictureNumber; //only used for taking pictures
	public boolean take_pictures;
	
	
	public GraphicsAndListeners(GraphicInterface g){
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	
		p = new Pendulum(1);
		p2 = new Pendulum(2);
		
		this.graphics=g;
		
		playing=false;
		
		dx=500;
		dy=300;
		
		scaleFactor=1;
		
		pictureNumber=1;
		take_pictures=false;
		
		thread=new LoopThread(this);
		thread.setRunning(true);
		thread.start();
	}
	


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		g.drawLine((int)(0*scaleFactor+dx), (int)(0*scaleFactor+dy), (int)((p.l1*Math.sin(p.a1))*scaleFactor+dx), (int)((p.l1*Math.cos(p.a1))*scaleFactor+dy));
		g.drawLine((int)((p.l1*Math.sin(p.a1))*scaleFactor+dx), (int)((p.l1*Math.cos(p.a1))*scaleFactor+dy), (int)((p.l1*Math.sin(p.a1)+p.l2*Math.sin(p.a2))*scaleFactor+dx), (int)((p.l1*Math.cos(p.a1)+p.l2*Math.cos(p.a2))*scaleFactor+dy));

		g.setColor(Color.BLUE);
		g.fillOval((int)((p.l1*Math.sin(p.a1)-p.d1/2)*scaleFactor+dx), (int)((p.l1*Math.cos(p.a1)-p.d1/2)*scaleFactor+dy), (int)(p.d1*scaleFactor), (int)(p.d1*scaleFactor));
		
		g.setColor(Color.RED);
		g.fillOval((int)((p.l1*Math.sin(p.a1)+p.l2*Math.sin(p.a2)-p.d2/2)*scaleFactor+dx), (int)((p.l1*Math.cos(p.a1)+p.l2*Math.cos(p.a2)-p.d2/2)*scaleFactor+dy), (int)(p.d2*scaleFactor), (int)(p.d2*scaleFactor));

		dx+=400;
		
		g.setColor(Color.BLACK);
		g.drawLine((int)(0*scaleFactor+dx), (int)(0*scaleFactor+dy), (int)((p2.l1*Math.sin(p2.a1))*scaleFactor+dx), (int)((p2.l1*Math.cos(p2.a1))*scaleFactor+dy));
		g.drawLine((int)((p2.l1*Math.sin(p2.a1))*scaleFactor+dx), (int)((p2.l1*Math.cos(p2.a1))*scaleFactor+dy), (int)((p2.l1*Math.sin(p2.a1)+p2.l2*Math.sin(p2.a2))*scaleFactor+dx), (int)((p2.l1*Math.cos(p2.a1)+p2.l2*Math.cos(p2.a2))*scaleFactor+dy));

		g.setColor(Color.BLUE);
		g.fillOval((int)((p2.l1*Math.sin(p2.a1)-p2.d1/2)*scaleFactor+dx), (int)((p2.l1*Math.cos(p2.a1)-p2.d1/2)*scaleFactor+dy), (int)(p2.d1*scaleFactor), (int)(p2.d1*scaleFactor));
		
		g.setColor(Color.RED);
		g.fillOval((int)((p2.l1*Math.sin(p2.a1)+p2.l2*Math.sin(p2.a2)-p2.d2/2)*scaleFactor+dx), (int)((p2.l1*Math.cos(p2.a1)+p2.l2*Math.cos(p2.a2)-p2.d2/2)*scaleFactor+dy), (int)(p2.d2*scaleFactor), (int)(p2.d2*scaleFactor));
		
		dx-=400;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode()){ 	
		case KeyEvent.VK_RIGHT:
			if(playing){
				playing=false; 
			}
			else{
			playing=true; 
			}
			
			System.out.println("Playing: " + playing);
		break;  
		case KeyEvent.VK_LEFT:
			playing=false;
			p=new Pendulum(1);
			p2=new Pendulum(2);
			break;
		case KeyEvent.VK_UP:
			scaleFactor*=1.1;
			break;
		case KeyEvent.VK_DOWN:
			scaleFactor/=1.1;
			break;	
		case KeyEvent.VK_P:
			this.take_pictures=!this.take_pictures;
			
			if(this.take_pictures) {
				System.out.println("Taking Pictures");
			}
			else {
				System.out.println("Stopped Taking Pictures");
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		graphics.panel.requestFocus();
		x=arg0.getX();
		y=arg0.getY();
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void takePicture() {
	    BufferedImage img = new BufferedImage(graphics.panel.getWidth(), graphics.panel.getHeight(), BufferedImage.TYPE_INT_RGB);
	    graphics.panel.print(img.getGraphics()); // or: panel.printAll(...);
	    try {
	        ImageIO.write(img, "png", new File("images/"+pictureNumber+".png"));
	        pictureNumber++;
	    }
	    catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		dx=dx+(e.getX()-x);
		dy=dy+(e.getY()-y);
		
		x=e.getX();
		y=e.getY();
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
