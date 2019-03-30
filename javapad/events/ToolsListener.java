/**
	The ToolsListener Class

	The last of the 3 classes that I declared.  This listener deals with the ToolsMenu
	class which handles with Formating, Word Counts, and All Caps.  There should
	also be a method that handles the LookAndFeel of how the evniornment looks.

	Decided to update the method by outputing a message dialog box.  Must use
	JFrame and JOptionPane to out a message to the user.
*/
//package wordprocessor.events;
package javapad.events;

//import javapad.AlfsPad;
import java.util.StringTokenizer;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.JTextArea;
import javax.swing.JFrame; //used to output a message
import javax.swing.JOptionPane; //class that use a message display already
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ToolsListener implements ActionListener, ItemListener , DocumentListener{

	static JTextArea textArea = new JTextArea();
	//JFrame tempframe = new JFrame();
	static JFrame tempframe;
	//AlfsPad tempAlfsPad = new AlfsPad();
	JFrame note = new JFrame(); //used to display messages

	/**Default Constructor*/
	public ToolsListener() { }

	/**Constructor that accepts a frame argument*/
	public ToolsListener(JFrame aFrm) { this.tempframe = aFrm; }

	/**Constructor that accepts a JTextArea argument*/
	public ToolsListener(JTextArea aTextArea) { this.textArea = aTextArea; }

	//public ToolsListener (AlfsPad aFrm) { this.tempAlfsPad = aFrm; }

	/**
		Method that is override from the ActionListener interface
	*/
	public void actionPerformed(ActionEvent e) {
		String laftemp = new String(e.getActionCommand());

		if (laftemp.equals("Word Count"))
		{
			this.WordCount();
		}
		else if (laftemp.equals("Capitalize All") || laftemp.equals("Lowercase All"))
		{
			this.SetCase(laftemp);
		}
		else if (laftemp.equals("Motif") || laftemp.equals("Windows") || laftemp.equals("Metal"))
		{
			this.LookAndFeel(laftemp);
		}
		else if (e.getActionCommand().equals("About"))
		{
			this.About();
		}
		else
		{
			System.out.println("Tools -> " + e.getActionCommand());
			message(e.getActionCommand()+" Menu Item selected", note);
		}
	}

	/**
		three overrided method from the DocumentListener class
	*/
	public void insertUpdate(DocumentEvent de) {
		//System.out.println(textArea.getText()+" insertUpdate from ToolsListener");
	}

	public void removeUpdate(DocumentEvent de) {
		//System.out.println("remove");
	}

	public void changedUpdate(DocumentEvent de) {
		//System.out.println("changed");
	}

	/**
		This method was practice for the itemStateChanged method.
		Beause this class does not have access to the other declared
		menu items of the ToolsMenu Class, the ItemListener interface
		will be used in the ToolsMenu Class.
	*/
	public void itemStateChanged(ItemEvent ie) {
		String output = new String(ie.paramString());
		System.out.println("Length of string "+output.length());
		//System.out.println("Tools, Set Look and Feel -> "+ );
	}

	/**
		This Method Counts the number of words in a the JTextArea
	*/
	public void WordCount() {
		StringTokenizer st = new StringTokenizer(textArea.getText(), " \n");
		message("Word Count is "+st.countTokens(), note);
	}

	/**
		Method that changes all the textarea in capital or lowercase letters.
	*/
	public void SetCase(String aCase) {

		if (aCase.equals("Capitalize All"))
			textArea.setText(textArea.getText().toUpperCase());
		else if (aCase.equals("Lowercase All"))
			textArea.setText(textArea.getText().toLowerCase());
		/*
		//used to test string methods
		String lower = new String();
		String upper = new String();
		lower = laftemp.toLowerCase();
		upper = laftemp.toUpperCase();
		System.out.println (lower+ " " +upper);

		System.out.println(aCase);
		System.out.println(textArea.getText().toUpperCase()+ " debug from ToolsListener");
		*/
	}

	/**
		Method used to change the LookAndFeel
		problems: only changes the message frame, but not the AlfsPad frame.
	*/
	public void LookAndFeel(String ac) {
		System.out.println(ac);
		String lafStr = new String();

		//"javax.swing.plaf.metal.MetalLookAndFeel";
		lafStr = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		//"com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(lafStr);
			SwingUtilities.updateComponentTreeUI(tempframe);
		} catch (Exception e) { }
	}

	/**
		Method used to display the About menu item.
	*/
	public void About() {
		String credit = new String(
			"Programmer: Alfredo Tigolo III \n"+
			"Version: 0.25\n"+
			"Instructor: Jenny Chang\n"+
			"Alfredo's WordProcessor - Assignment #6");
		message(credit, note);
	}

	/**
		Method used to print out a dialog box to the user.
	*/
	public void message(String aStr, JFrame aFrm) {
		JOptionPane.showMessageDialog(aFrm, aStr,
			"Tools Menu", JOptionPane.INFORMATION_MESSAGE);
	}
}