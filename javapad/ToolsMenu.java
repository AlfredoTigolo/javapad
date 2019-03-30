/**
	ToolsMenu Class

	Data members of this class are developed in the constructor which uses three methods
	to create the menuitem.  This class differs from FileMenu and EditMenu class because
	there is a grouped set of radio buttons.  The first method registers the action listener.
	The second method creates the menuitem and sets the mnemonic short cut.  The third
	creates the radio buttons for the Look and Feel Menu.
*/
//package wordprocessor;
package javapad;

//import wordprocessor.events.ToolsListener;
import javapad.events.ToolsListener;
import javax.swing.ImageIcon;
import java.awt.event.ItemListener; // local listener
import java.awt.event.ItemEvent; // local event type
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

//import wordprocessor.events.ToolsItemListener; //practice

/**
	The ToolsMenu Class
*/
public class ToolsMenu extends JMenu implements ItemListener {

	//data members
	JMenuItem jmiWordCount = new JMenuItem("Word Count", 'W'); //hard coded
	JMenu jmSetLookandFeel = new JMenu("Set Look and Feel");
	JMenuItem jmiCapitalizeAll, jmiLowerCaseAll, jmiAbout;
	JRadioButton jrbWindows, jrbMetal, jrbMotif;
	ToolsListener toolsListener = new ToolsListener(); //the listener close

	//ToolsItemListener toolsItemListener = new ToolsItemListener(); //practiced

	/**
		The default constructor
	*/
	public ToolsMenu() {

		super("Tools"); // calls superclass constructor
		this.setMnemonic('T'); // sets Mnemonic for this class
		add(jmiWordCount); // adds component to ToolsMenu
		addListener(jmiWordCount); //registers a listener.

		addSeparator();

		// creates the object using the createJMI method and assigns object to a reference
		jmiCapitalizeAll = createJMI("Capitalize All", 'C');
		add(jmiCapitalizeAll);
		addListener(jmiCapitalizeAll);

		jmiLowerCaseAll = createJMI("Lowercase All", 'L');
		add(jmiLowerCaseAll);
		addListener(jmiLowerCaseAll);

		addSeparator();

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
/** DISABLED CODE
//code delcaration or a version of this code was moved to the AlfsPad class

		// creates the sub menu for the 'Look and Feel'
		jrbWindows = createJRB("Windows");
		jrbWindows.setSelected(true);
		jrbMetal = createJRB("Metal");
		jrbMotif = createJRB("Motif");

// Special Note:  When you are using radio buttons, must use the ItemListener Interface

		//creating button group
		ButtonGroup group = new ButtonGroup();
		group.add(jrbWindows);
		group.add(jrbMetal);
		group.add(jrbMotif);
		jmSetLookandFeel.add(jrbWindows);
		jmSetLookandFeel.add(jrbMetal);
		jmSetLookandFeel.add(jrbMotif);
		//DISABLE SUBMENU
		add(jmSetLookandFeel);

 	//COMMENTS
 	3 statements assign register listener toolsListener, but because it is declared outside
 	the ToolMenu Class, the menuitems cannot be seen.  Instead of using the
 	'toolListener' class declared, it uses the 'this' local listener


 	// toolsListener <- outside listener
 	// this <- local listener

 	// If I use the local listener, it returns twice the value of the RadioButton selected

		jrbWindows.addActionListener(toolsListener);
		jrbMetal.addActionListener(toolsListener);
		jrbMotif.addActionListener(toolsListener);

		addSeparator();
*/ //DISABLED CODE
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		jmiAbout = createJMI("About", 'A');
		//MUST SPECIFY EXACT LOCATION OF IMAGE
		//jmiAbout.setIcon(new ImageIcon("e:/prj5/wordprocessor/images/camera.gif"));
		jmiAbout.setIcon(new ImageIcon("javapad/images/camera.gif"));
		add(jmiAbout);
		addListener(jmiAbout);

		addSeparator();
	}

	/**
		This method overrides the itemStateChanged Method of ItemListener
	*/
	public void itemStateChanged(ItemEvent ie) {

		if (jrbMetal.isSelected())
			System.out.println("Tools -> Set Look and Feel -> Metal");
		if (jrbMotif.isSelected())
			System.out.println("Tools -> Set Look and Feel -> Motif");
		if (jrbWindows.isSelected())
			System.out.println("Tools -> Set Look and Feel -> Windows");
	}

	/**
		This method 'addListener' helps register the component with the listener object
	*/
	public JMenuItem addListener(JMenuItem aJMI) {
		aJMI.addActionListener(toolsListener);
		return aJMI;
	}

	/**
		This method 'createJMI' creates an instance of JMenuItem and also helps set the
		Mnemonic key.
	*/
	public JMenuItem createJMI(String aMenuTitle, char aMnemonicChar) {
		return (new JMenuItem(aMenuTitle, aMnemonicChar));
	}

	/**
		This method 'createsJRB' create an instance of JRadioButton
	*/
	static public JRadioButton createJRB(String aTitle) {
		return (new JRadioButton(aTitle));
	}
}