/**
	EditMenu Class

	Similar to the FileMenu class, this deals more with the input placed onto the text area
	of AlfsPad Class.  The only difference here is the Ctrl-C, Ctrl-X, and Ctrl-X simulate
	the operating system of copy, cut and paste.  When the menu item is selected, then
	it just prints the message out of what menuitem was selected.  The constructor
	develops the data members of the class and uses two methods o constructor them.
	Similar to the FileMenu class methods.
*/
//package wordprocessor;
package javapad;

//import wordprocessor.events.EditListener;
import javapad.EditSubMenu;
import javapad.events.EditSubListener;
import javapad.events.EditListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
//the needs to be a submenu here for Find,Replace, or Goto

/**
	The EditMenu Class
*/
public class EditMenu extends JMenu{

	//data members
	JMenuItem jmiCut = new JMenuItem("Cut", 't');
	JMenuItem jmiCopy, jmiPaste, jmiSelectAll, jmiFind, jmiReplace, jmiGoto, jmiDateTime;
	EditSubMenu editSub = new EditSubMenu();
	EditListener editListener = new EditListener(editSub);//EditListener regular
	EditSubListener editSubListener = new EditSubListener(); //editSub Menu Listener


	/**
		Default Constructor
	*/
	public EditMenu () {
		super("Edit"); //calls the superclass constructor
		this.setMnemonic('E'); //sets the Mnemonic of this class

/**
	Special Note:  When the program is running, it simulates the Ctrl-C, Ctrl-X and
	Ctrl-V commands for windows, Copy, Cut, and Paste respectively.  The menu
	doesn't work when menuitem is selected.  Only, prints out the type of menuitem
	selected.
*/

		//cut menu item
		jmiCut = setShortCut(jmiCut, KeyEvent.VK_X); //set mnemonic
		add(jmiCut); //add menuitem to menu
		jmiCut.addActionListener(editListener); //register the listener

		//copy menu item
		jmiCopy = createJMI("Copy", 'C');
		setShortCut(jmiCopy, KeyEvent.VK_C);
		add(jmiCopy);
		jmiCopy.addActionListener(editListener);

		//paste menu item
		jmiPaste = createJMI("Paste", 'P');
		setShortCut(jmiPaste, KeyEvent.VK_V);
		add(jmiPaste);
		jmiPaste.addActionListener(editListener);

		addSeparator();

		//Go to Menu item
		jmiGoto = createJMI("Goto", 'G');
		setShortCut(jmiGoto, KeyEvent.VK_G);
		add(jmiGoto);
		jmiGoto.addActionListener(editListener);
		jmiGoto.addActionListener(editSubListener);

		//find menu item
		jmiFind = createJMI("Find", 'F');
		setShortCut(jmiFind, KeyEvent.VK_F);
		add(jmiFind);
		jmiFind.addActionListener(editListener);

		//replace menu item
		jmiReplace = createJMI("Replace", 'R');
		setShortCut(jmiReplace, KeyEvent.VK_H);
		add(jmiReplace);
		jmiReplace.addActionListener(editListener);

		addSeparator();

		//select All menu item
		jmiSelectAll = createJMI("Select All", 'A');
		setShortCut(jmiSelectAll, KeyEvent.VK_A);
		add(jmiSelectAll);
		jmiSelectAll.addActionListener(editListener);

		//DataTime menu item
		jmiDateTime = createJMI("Date / Time", 'T');
		//setShortCut(jmiDateTime, KeyEvent.VK_F5);
		jmiDateTime.setAccelerator(KeyStroke.getKeyStroke(
			KeyEvent.VK_F5, 0)); //0 is used so it will only be F5
		add(jmiDateTime);
		jmiDateTime.addActionListener(editListener);
	}

	/**
		This method 'createJMI' creates an instance of JMenuItem and also helps set the
		Mnemonic key.
	*/
	public JMenuItem createJMI(String aMenuTitle, char aMnemonicChar) {
		return (new JMenuItem(aMenuTitle, aMnemonicChar));
	}

	/**
		This method 'setShortCut' uses the setAccelerator method of the KeyStroke to
		set a Short Cut to the MenuItem
	*/
	public JMenuItem  setShortCut(JMenuItem aJMI, int aVK_Value) {
		aJMI.setAccelerator(KeyStroke.getKeyStroke
		(aVK_Value, ActionEvent.CTRL_MASK));
		return aJMI;
	}
}