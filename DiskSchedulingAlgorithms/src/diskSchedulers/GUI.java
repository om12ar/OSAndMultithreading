package diskSchedulers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUI frame = new GUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GUI(String title,int initPos, int diskSize, ArrayList<Integer> result, ArrayList<String> sequence) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setTitle(title);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		MyCanvas myCanvas = new MyCanvas(initPos, diskSize, result, sequence);
		myCanvas.setBackground(Color.BLACK);
		contentPane.add(myCanvas);
		setContentPane(contentPane);
		
	}
	


}


class MyCanvas extends JComponent
{

	int initPos;
	int diskSize;
	ArrayList<Integer> result;
	ArrayList<String> sequence;
	
	public MyCanvas(int initPos, int diskSize, ArrayList<Integer> result, ArrayList<String> sequence){
		
		this.initPos = initPos;
		this.diskSize = diskSize;
		this.result = new ArrayList<>(result);
		this.sequence = new ArrayList<>(sequence);
		
	}
	public void paint(Graphics g) {

		 	
		  	ArrayList<Integer> points = new ArrayList<>(result);
		  	points.add(initPos);
		  	points.add(diskSize);
		  	
		  	Collections.sort(points);
		  	
		  	double scaleFactor = 460.0 / diskSize;
		  	
			Dimension d = this.getPreferredSize();
		    int fontSize = 20;
		   
		    g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
		     
		    g.setColor(Color.red);
		    g.drawString("0", 0, 15);
		    for(int i = 0 ; i < points.size(); i++){
		    	 
		    	g.drawString("" + points.get(i), (int)(points.get(i) * scaleFactor), 15);
		    	
		    }
		    
		    int YDisp;
		    if(scaleFactor < 1){
		    	YDisp = (int)((1 / scaleFactor) * points.size()) * 2;
		    }
		    else{
		    	YDisp = (int)(scaleFactor * points.size());
		    }
		    
		    int YCurrent = YDisp;
		    
		    for(int i = 0; i < sequence.size(); i++){
		    	
		    	String[] tempStrings = sequence.get(i).split("\\s* -> \\s*");
		    	int x1 = (int)(Integer.parseInt(tempStrings[0]) * scaleFactor);
		    	int x2 = (int)(Integer.parseInt(tempStrings[1]) * scaleFactor);
		    	
		    	
		    	g.drawLine(x1, YCurrent, x2, YCurrent += YDisp );

		    }
		    

	  
	  }
	
	
}
