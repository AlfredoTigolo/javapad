/**
	AlfsPad Class (EXTRA CREDIT DETAILS continues on MyToolBar Class)

	The AlfsPad class looks simple, but incorporates so many classes to get the
	added effect of a menu bar with tool bar below it.  Three layout managers where
	used to produce the desired effected.  First, the frame is set to border layout.  Then,
	the other 3 panels were created so the menubar and the toolbar can exist in one
	grid without overlapping each other.

	AlfsPad extends JFrame used BorderLayout

	FileMenu, EditMenu, and ToolsMenu which all extend from JMenu, used
	Panel 1 (p1) as a container, but first where placed on a Menubar container.
	Then added to p1.  The Layout was set to FlowLayout alignment left.

	Toolbar which extends from JToolBar used Panel 2 (p2) as a container.  The Layout
	was set to FlowLayout aligment left.

	Panel 3 (p3)  is a container for both p1 and p2.  It uses a GridLayout that has
	2 rows, and 1 column.  This is how it gives the effect of a menubar ontop of
	a toolbar.

	Once p3 adds p1 and p2, it is then later added on the AlfsPad frame.  The frame is
	set to BorderLayout.  p3 is added onto AlfsPad frame with an alignment North.
*/
//package wordprocessor;
package javapad;

//import wordprocessor.events.EditListener; //do we need this here?
//import wordprocessor.events.ToolsListener;
import javapad.events.FileListener;
import javapad.events.EditSubListener;
import javapad.events.EditListener; //do we need this here?
import javapad.events.ToolsListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
//import java.awt.Container;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener; //event listener
import java.awt.event.ActionEvent; //event type

/**
	This is the AlfsPad Class
*/
public class AlfsPad extends JFrame implements ActionListener {

	//creating the New LookAndFeel
	JMenu newLookAndFeel = new JMenu("New Look and Feel");
	JRadioButton windows, motif, metal;
	ButtonGroup newGroup = new ButtonGroup();

	//data members
	JMenuBar menuBar = new JMenuBar();
	public JTextArea jtaPAD = new JTextArea();
	//static public int counter = 0; // moved to NewFile method of EditListener

	//created Panel and used as containers
	JPanel p1 = new JPanel(); //menubar panel
	JPanel p2 = new JPanel(); //toolbar panel
	JPanel p3 = new JPanel();  //both p1 and p2 to add to ContentPane

	//created classes
	FileMenu fileMenu = new FileMenu();
	EditMenu editMenu = new EditMenu();
	ToolsMenu toolsMenu = new ToolsMenu();
	MyToolBar toolBar = new MyToolBar(); //extra credit
	//public EditSubMenu editSub = new EditSubMenu(jtaPAD); //the Find, Replace, Goto submenu

	//the listeners declared
	FileListener alfsPadListener = new FileListener(this);//passing the class
	EditListener editListener = new EditListener(jtaPAD); //listener for text
	ToolsListener toolsListener = new ToolsListener(jtaPAD);
	FileListener fileListener = new FileListener(jtaPAD);
	EditSubListener editSubListener = new EditSubListener(jtaPAD); //submenu for 3 hardest events


	/**
	the default constructor
	*/
	public AlfsPad() {
		//++counter; //increment counter when instance is created

		//setting a document listener for jtaPAD

		jtaPAD.getDocument().addDocumentListener(editListener);
		jtaPAD.getDocument().addDocumentListener(toolsListener);
		jtaPAD.getDocument().addDocumentListener(fileListener);
		jtaPAD.getDocument().addDocumentListener(editSubListener);

		editMenu.addActionListener(editListener);

		//wraps the sentence if it gets to the edge of the text area
		jtaPAD.setLineWrap(true);

		//setTitle("untitled - Alf's Pad "+counter);
		//counter used to determine number of instances, but counter was moved
		setTitle("Alf's Pad - untitled 1");

		//set setLayout
		getContentPane().setLayout(new BorderLayout(1,1));

		//add jtaPAD text area to Pane
		getContentPane().add(jtaPAD, BorderLayout.CENTER);

		//panels for tool bar
		p1.setLayout(new GridLayout(2,1,0,0));
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		p3.setLayout(new GridLayout(2,1,0,0));

		//getContentPane().add(toolBar, BorderLayout.SOUTH); //extra credit
		//getContentPane().add(p2, BorderLayout.NORTH); //extra credit

		//add menus to the frame
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(toolsMenu);
		p1.add(menuBar);
		p2.add(toolBar);//extra credit

		p3.add(p1);
		p3.add(p2);

		getContentPane().add(p3, BorderLayout.NORTH);

		this.createLookAndFeel();
		//calls method that adds a submenu of radio buttons to toolsMenu
	}

	/**
		Method that returns this frame object
	*/
	public JFrame getFrame() { return this; }

	/**
	Local listener for the LookAndFeel
	*/
	public void actionPerformed(ActionEvent ae) {
		String lafStr = new String();
		if (ae.getSource() == windows )
			lafStr= "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"; //windows
		else if (ae.getSource() == metal )
			lafStr = "javax.swing.plaf.metal.MetalLookAndFeel"; //metal
		else if (ae.getSource() == motif )
			lafStr = "com.sun.java.swing.plaf.motif.MotifLookAndFeel"; //motif

		//System.out.println("local listener invoked "+ae.getActionCommand()); //debug

		try {
				//System.out.println("Local Listener "+ae.getActionCommand()); //debug
				javax.swing.UIManager.setLookAndFeel(lafStr);
				javax.swing.SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) { }
	}//end actionPerformed

	/**
		Method that creates the new Look And Feel menu of radio buttons
	*/
	public void createLookAndFeel() {
		//creating radio buttons
		this.windows = ToolsMenu.createJRB("Windows");
		this.metal = ToolsMenu.createJRB("Metal");
		this.motif = ToolsMenu.createJRB("Motif");
		this.metal.setSelected(true);//selected first for default

		//grouping radio buttons
		newGroup.add(windows);
		newGroup.add(metal);
		newGroup.add(motif);

		//adding to submenu
		this.newLookAndFeel.add(windows);
		this.newLookAndFeel.add(metal);
		this.newLookAndFeel.add(motif);

		//adding to current toolsMenu
		this.toolsMenu.add(newLookAndFeel);

		//register listener
		windows.addActionListener(this);
		metal.addActionListener(this);
		motif.addActionListener(this);
	}
}