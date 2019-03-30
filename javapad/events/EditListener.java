/**
	The EditListener Class

	Silimar to the FileMenu Class, this is a listener class that deals with the EditMenu
	Class.  This class deals with the actual text in the text area.  Some methods or
	event handling needs to be done where text is stored, copy, cut or replaced.

	Decided to update this class by adding a JDailog box that prompts the user what
	command was used.  Remember, you need to import the JOption Panel and must
	declare a frame to print out your message.
*/
//package wordprocessor.events;
package javapad.events;

import javax.swing.JOptionPane; //used for messages
import javax.swing.JFrame; //used to print a message on a frame

import javapad.AlfsPad;
import javapad.EditSubMenu;
import javapad.EditMenu;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.event.DocumentListener; //listener for documents
import javax.swing.event.DocumentEvent; //listener for documents

/**
	The EditListener Class
*/
public class EditListener implements ActionListener, ItemListener, DocumentListener {
//public class EditListener implements ItemListener , ActionListener {

	JFrame note = new JFrame(); //used to print the message
	AlfsPad alfsPAD; //temporary holder reference
	static JTextArea textArea = new JTextArea(); //made this text part static
	EditSubMenu anEditSub;// = new EditSubMenu();//the submenu item

	//trying to declare an inner class

	/**
		Default Constructor
	*/
	public EditListener() { }

	/**
		Constructor that accepts an AlfsPad frame, also declares an inner class of SubMenu
	*/
	public EditListener( AlfsPad tempAlfsPad ) {
		this.alfsPAD = tempAlfsPad;
		this.textArea = tempAlfsPad.jtaPAD;
		//anEditSub = new EditSubMenu(textArea);//the submenu item
		}

		/**
		Constructor the accepts a EditSubMenu
		*/
		public EditListener(EditSubMenu aEditSub) { this.anEditSub = aEditSub; }


	/**
		Constructor that accepts an EditMenu Class
	*/
	public EditListener(EditMenu aMenu) { }

	/**
		Constructor that accepts JTextArea Argument
	*/
	public EditListener(JTextArea jta) { this.textArea = jta; }

	/**
		overried from the ActionListener interface
	*/
	public void actionPerformed(ActionEvent e) {
		String acStr = new String(e.getActionCommand());
		//System.out.println(textArea.getText()); //debug
		//DateAndTime.BOTH(); //debug
		//System.out.println("test this " + alfsPAD.jtaPAD.getText());//code doesn't work

		if (e.getActionCommand().equals("Cut"))
			this.CutText();
		else if (e.getActionCommand().equals("Copy"))
			this.CopyText();
		else if (e.getActionCommand().equals("Paste"))
			this.PasteText();
		else if (e.getActionCommand().equals("Select All"))
			this.SelectAllText();
		else if (e.getActionCommand().equals("Date / Time"))
			this.DateTimeText();
		else if (acStr.equals("Goto") || acStr.equals("Find") || acStr.equals("Replace"))
			this.showEditSub(acStr);//created another listener for this event

		System.out.println("Edit -> "+e.getActionCommand());
		//message(e.getActionCommand() +" Menu Item selected", note);
	}

	/**
		Method calls another GUI and makes it visible.  The other GUI has events
		listeners on it.  Also, the constructor it has needs to be delcared in other listeners.
		Confusing?  Look at the code.  But, this method must makes it visible and selects
		the correct index depending on the item choose
	*/
	public void showEditSub(String aStrIndex) {
		//this.alfsPAD.editSub.setVisible(true); //created another listener for this event
		anEditSub.setVisible(true);

		if (aStrIndex.equals("Find"))
			anEditSub.setTab(0);
		else if (aStrIndex.equals("Replace"))
			anEditSub.setTab(1);
		else if (aStrIndex.equals("Goto"))
			anEditSub.setTab(2);
	}

	/**
		Method that appends the date and time to the text area
		Method now uses the replaceSelection to insert text in where the cursor is
	*/
	public void DateTimeText() {
		//textArea.append(DateAndTime.BOTH());
		textArea.replaceSelection(DateAndTime.BOTH()); //thanks Eugene!
		//textArea.insert(DateAndTime.BOTH(), need an integer?);
	}

	/**
		Method used to select all the text
	*/
	public void SelectAllText() { textArea.selectAll(); }

	/**
		Method used to paste text
	*/
	public void PasteText() { textArea.paste(); 	}

	/**
		Method used to copy text
	*/
	public void CopyText() { textArea.copy(); }

	/**
		Method used to cut text
	*/
	public void CutText() {
		//try {
			//System.out.println("CutText invoked");
			//System.out.println(textArea.getSelectedText());
			textArea.cut();
		//}catch (Exception e) { System.out.println("no text selected"); }
	}

	/**
		overrided from the ItemListener interface
	*/
	public void itemStateChanged(ItemEvent e) {
		System.out.println(e.getSource());
	}

	/**
		method used to print out a message to the user what command was
		choosen
	*/
	public void message(String aStr, JFrame aFrm) {
		JOptionPane.showMessageDialog(aFrm, aStr,
			"Edit Menu", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
		three overrided method from the DocumentListener class
	*/
	public void insertUpdate(DocumentEvent de) {
		//alfsPAD.jtaPAD.getText();
		//System.out.println(textArea.getText()+" insert method from EditListener");
	}

	public void removeUpdate(DocumentEvent de) {
		//System.out.println("remove");
	}

	public void changedUpdate(DocumentEvent de) {
		//System.out.println("changed");
	}
}