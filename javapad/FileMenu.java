/**
	FileMenu Class

	The FileListener is declared outside of this class and also in a different package.
	This class has data members, 1 constructor and 2 methods.  The constructor
	develops the data members by using the 2 methods to develop them.  1st
	method creates the menuitem and the 2nd method creates the short cut keys
	for the mnemonic commands.

*/
//package wordprocessor;
package javapad;

//import wordprocessor.events.FileListener; //declared in a different package
import javapad.events.FileListener;
import javapad.EditSubMenu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

/**
	The FileMenu Class
*/
public class FileMenu extends JMenu {

	//data members
	JMenuItem jmiOpen = new JMenuItem("Open");
	JMenuItem jmiNew, jmiPrint, jmiSave, jmiSaveAs, jmiExit;
	//EditSubMenu editSub = new EditSubMenu();
	FileListener fileListener = new FileListener(); //one listener

	/**
		default constructor
	*/
	public FileMenu() {

		//sets the title for the Menu
		super("File");
		this.setMnemonic('F');

		//new menu item
		jmiNew = createJMI("New", 'N');
		add(jmiNew);
		jmiNew.addActionListener(fileListener);

		//open menu item
		jmiOpen.setAccelerator(KeyStroke.getKeyStroke
			(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		add(jmiOpen).setMnemonic('O'); //set mnemonic
		jmiOpen.addActionListener(fileListener); //set listener

		//save menu item
		jmiSave = createJMI("Save", 'S');
		setShortCut(jmiSave, KeyEvent.VK_S);
		add(jmiSave);
		jmiSave.addActionListener(fileListener);

		//save as menu item
		jmiSaveAs = createJMI("Save As", 'A');
		add(jmiSaveAs);
		jmiSaveAs.addActionListener(fileListener);

		addSeparator();

		//print menu item
		jmiPrint = createJMI("Print", 'P');
		jmiPrint.setAccelerator(KeyStroke.getKeyStroke
			(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		add(jmiPrint);
		jmiPrint.addActionListener(fileListener);

		addSeparator();

		//exit menu item
		jmiExit = createJMI("Exit", 'x');
		add(jmiExit);
		jmiExit.addActionListener(fileListener);

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
	public void  setShortCut(JMenuItem aJMI, int aVK_Value) {
		aJMI.setAccelerator(KeyStroke.getKeyStroke
		(aVK_Value, ActionEvent.CTRL_MASK));
	}
}