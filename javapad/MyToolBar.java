/**
	MyToolBar Class (EXTRA CREDIT continued on AlfsPad Class)

	1 Constructor, 3 methods and data members consist of Strings and an
	array of JButtons.

	The constructor sets the toolbar float to false.  Then, sets the layout
	to gridlayout, 1 row, 5 columns.  Next, delcares an array of JButtons.
	Finally, the constructor then registers listeners and assigns the icon
	for each button.

	1st method creates a button and registers a listener.  2nd method is
	used for the local ActionListener interface with the actionPerformed method
	for the RadioButtons.  The 3rd prints out the command selected from the
	actionPerformed method.  It does not print to the command-line, instead it
	uses a JOptionPane class that shows a message to the user.

	The String data members tell the location of the icons for this class.  The icons
	are placed in a common location which is called the 'iconSource' String.  Then,
	depending on the icon name of the icon, creates a String for the location of the icon.

	The array of JButtons is very hard to keep track.  I decided to create an array because
	I did not want to specify the type of button it is by the name.  But, I used the name of the
	icon and picture to determine it's function.
*/

//package wordprocessor;
package javapad;

//import wordprocessor.events.FileListener; //must create local listener
import javapad.events.FileListener; //must create local listener
import javapad.events.EditListener; //must create local listener
import javapad.events.ToolsListener; //must create local listener

import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

/**
	The MyToolBar Class is delcared here.  EXTRA CREDIT
*/
public class MyToolBar extends JToolBar implements ActionListener {

	//data members
/**
	NOTE: Be sure to change the iconSource String to tell the compiler the
	exact location of the icons.
*/
	String iconSource = new String("javapad/images/");
	String construction = new String(iconSource+"construction.gif");
	String openIcon = new String(iconSource+"open.gif");
	String cameraIcon = new String(iconSource+"camera.gif");
	String exitIcon = new String(iconSource+"exit.gif");
	String smileIcon = new String(iconSource+"smile.gif");
	String sleepIcon = new String(iconSource+"sleep.gif");
	String saveIcon = new String(iconSource+"save.gif");
	String question = new String(iconSource+"question.gif");

	//JToolBar toolBar = new JToolBar(); //extra credit
/**
	Special NOTE: If an array of buttons is created, you must keep track of what
	each button does.
*/
	JButton[] myButton = new JButton[10];
	//FileListener listener = new FileListener(); //must create local listener

/**
	Default Constructor for MyToolBar Class
*/
	public MyToolBar() {

		super(); // calls superclass constructor
		setFloatable(false); //set false so toolbar will not float around the frame

		setLayout(new GridLayout(1,5,1,1)); //1 row, 5 columns

		//creating 6 buttons
		for (int i =1; i<7; i++)
		{
			myButton[i-1] = new JButton();
			myButton[i-1].setIcon(new ImageIcon(construction));
			add(myButton[i-1]);
		}

		createButton(myButton[0], openIcon, "Open");
		createButton(myButton[1], saveIcon, "Save");
		createButton(myButton[2], cameraIcon, "Copy"); //copy menuitem?
		//createButton(myButton[3], sleepIcon, "Sleeping"); //hours of retyping original code
		remove(myButton[3]);
		createButton(myButton[4], smileIcon, "About"); //about menuitem?
		createButton(myButton[5], exitIcon, "Exit");
	}

	/**
		method used to modify a button by taking the reference and string icon variable
		and also sets the action listener
	*/
	public void createButton(JButton ajbRef, String aStrIcon, String toolTipStr) {
		ajbRef.setText(toolTipStr);
		ajbRef.setIcon(new ImageIcon(aStrIcon));
		ajbRef.addActionListener(this);
		ajbRef.setToolTipText(toolTipStr);
		ajbRef.addActionListener(new EditListener());
		ajbRef.addActionListener(new ToolsListener());
		ajbRef.addActionListener(new FileListener());
	}

	/**
		method overrides the ActionListener interface method
		NESTED IF STATEMENTS
	*/
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());

			if (e.getSource() == myButton[0])
				//System.out.println("toolbar -> open");
				message("toolbar -> open");
				else if (e.getSource() == myButton[1])
					//System.out.println("toolbar -> save");
					message("toolbar -> save");
					else if (e.getSource() == myButton[2])
						//System.out.println("toolbar -> copy");
						message("toolbar -> copy");
						else if (e.getSource() == myButton[3])
							//System.out.println("toolbar -> sleeping");
							message("toolbar -> sleeping");
							else if (e.getSource() == myButton[4])
								//System.out.println("toolbar -> smile");
								message("toolbar -> smile");
								else if (e.getSource() == myButton[5])
								{
									message("You are now exiting the program");
									System.exit(0);
								}
	}

	/**
		Method prints out a message to the user.
	*/
	public void message(String aStr) {
		JOptionPane.showMessageDialog(this, aStr,
			"ToolBar Menu Option", JOptionPane.INFORMATION_MESSAGE);
	}

}//End MyToolBar class EXTRA CREDIT