//package wordprocessor;
package javapad;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.Dimension; //used to center from
import java.awt.Toolkit; //used to center frame
import java.awt.BorderLayout; //layout
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
	The MainPrj6 Class
*/
public class MainPrj6 {

	/**
		the Main Method
	*/
	public static void main(String[] args) {
		AlfsPad frame = new AlfsPad();
		//frame.setSize(400, 300); //later will be automatical enlarger
		SetCenter(frame);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setVisible(true);
		addWinListener(frame);
	}

	/**
		SetCenter Method that Centers a frame
	*/
	public static void SetCenter(JFrame aFrame) {
	   //aFrame.setSize(500,400);

	   // New in JDK 1.3 to exit the program upon closing
	   aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	   // Get the dimension of the screen
	   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	   int screenWidth = screenSize.width;
	   int screenHeight = screenSize.height;

	   aFrame.setSize(screenWidth, screenHeight - 50);

	   // Get the dimension of the frame
	   Dimension frameSize = aFrame.getSize();
	   int x = (screenWidth - frameSize.width)/2;
	   int y = (screenHeight - frameSize.height)/2;

	   aFrame.setLocation(x,y);
	   aFrame.setVisible(true);

  }//end setCenter method

  /**
  	Method that adds a listener Window Adaptor
  	page 367 of Core Java 2 Volume I
  */
  public static void addWinListener(JFrame aFrame) {
	  aFrame.addWindowListener(new
	  		WindowAdapter()
	  		{
				public void windowClosing(WindowEvent e)
				{
					JFrame note = new JFrame();
					JOptionPane.showMessageDialog(note, "You are now exiting the program.",
						"Exit", JOptionPane.INFORMATION_MESSAGE);
			/*
				JOptionPane.showMessageDialog(note, "Closing",
					"Close", JOptionPane.INFORMATION_MESSAGE);
			*/
					System.exit(0);
				}
			} );
  }

}