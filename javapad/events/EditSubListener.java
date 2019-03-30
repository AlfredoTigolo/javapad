package javapad.events;

import javapad.AlfsPad;
import javapad.EditSubMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.util.StringTokenizer;

/**
	EditSubListener that handles only the Find and Replace GUI popup.
*/
public class EditSubListener implements ActionListener, DocumentListener, CaretListener {

	//data members
	AlfsPad alfsPad;
	EditSubMenu anEditSub;
	static JTextArea searchTextArea = new JTextArea();
	//JTextField searchTextField = new JTextField(); //lets use a reference instead
	static JTextField searchTextField, txtFindReplace, txtReplace, txtFind;
	JFrame note = new JFrame(); //used to output messeges

	/**default constructor*/
	public EditSubListener () { }

	/**constructor that accepts an EditSubMenu item*/
	public EditSubListener(EditSubMenu aSubMenu) { this.anEditSub = aSubMenu; }

	/**constructor accepts an AlfsPad argument*/
	public EditSubListener (AlfsPad aAlfsPad) { this.alfsPad = aAlfsPad;}

	/**constructor accepts an JTextArea argument*/
	public EditSubListener (JTextArea aTextArea) { this.searchTextArea = aTextArea; }

	/**constructor accepts an JTextField arguement*/
	public EditSubListener (JTextField txtGoto){ this.searchTextField = txtGoto; }

	/**constructor accepts 3 JTextField arguments*/
	public EditSubListener (JTextField aTxtFind_R, JTextField aTxtReplace, JTextField aTxtFind)
	{
		this.txtFind = aTxtFind;
		this.txtFindReplace = aTxtFind_R;
		this.txtReplace = aTxtReplace;
	}

	/**actionPerformed method overrided from ActionListener interface*/
	public void actionPerformed(ActionEvent ae) {
		String aeStr = new String(ae.getActionCommand());
		System.out.println(ae.getActionCommand());
		System.out.println(this.searchTextArea.getText());
		//this.alfsPad.editSub.setVisible(true);

		if (aeStr.equals("Cancel"))
		{
			System.out.println("Uses local Listener");
		}
		//else if(aeStr.equals("Next") || aeStr.equals("Previous"))
		else if(aeStr.equals("Goto"))
		{
			try
			{
				int n = Integer.parseInt(searchTextField.getText() );
				System.out.println(searchTextField.getText() );
				System.out.println("Line Number to go to " +n); //debugging
				this.GotoLine(n); //passing value of n to method
			}
			catch (Exception e)
			{
				//System.out.println(e.getMessage()+" Error! Please enter a number");
				message(e.getMessage()+ " is what was entered.  Error!  Please Enter a number.",note);
				searchTextField.setText("");
			}
		}
		else if(aeStr.equals("Find Next"))
		{
			try
			{
				this.Find(searchTextField.getText().trim());
			}
			catch (Exception e)
			{
				message(e.getMessage()+"What did you do?", note);
			}
		}

	}

	/**method overrided from CaretListener*/
	public void caretUpdate(CaretEvent ce) {
		//this does not work?
		int x = ce.getDot();
		System.out.println(x);
	}

	/**1 or 3 methods overrided from the DocuementListener interface*/
	public void insertUpdate(DocumentEvent de) {
		System.out.println(this.searchTextArea.getText()+ " from editSubListener");
		System.out.println(this.searchTextArea.getCaretPosition()+" Position of Caret");
	}

	/**2 or 3 methods overrided from the DocuementListener interface*/
	public void removeUpdate(DocumentEvent de) {

	}

	/**3 or 3 methods overrided from the DocuementListener interface*/
	public void changedUpdate(DocumentEvent de) {
	}

	/**
		This stores the value of the the Caret position, then stores the value in an array.
		The for loop searches for the \n then records the next position of the caret as the
		beginning of the line.  Parameter is the line position, and the output is the caret
		position the line starts.  If you use StringTokenizer, the length plus one is the
		position of the the next line.

		StringTokenizer is not used hear.  Probably used in the Find and Replace methods.

		Once the loops are done, it takes the intLine taken in and moves the caret cursor to
		the appropriate line in the textArea.
	*/
	//public void NumOfLines() {
	public void GotoLine(int intLine) {

		//if statements checks if the intLine is greater then number of lines in the method
		System.out.println("intLine "+intLine);
		System.out.println("LineCount "+searchTextArea.getLineCount());
		if (intLine > searchTextArea.getLineCount() || intLine == 0)
		{
			message("Line Number Out of Range", note);
			//searchTextField(""); //can't be seen
			return; //the return statement exits the NumOfLine Method
		}//end if
		else if (intLine ==1)
		{
			searchTextArea.setCaretPosition(0);
			return; //exits method
		}

		System.out.println(searchTextArea.getText().length()+" length of string text?");

		String search = new String(searchTextArea.getText()); //reads the string
		int length = searchTextArea.getText().length(); //stores the length of the string
		int[] CaretArray = new int[100];
		int j = 2; //counter for arrary

/*
THE charAt() METHOD OF THE STRING CLASS
		1st for loop cycles through the string checking the character return.  i = 0 is line one
		This first loop stores the value of the Caret Position of the return
*/
		CaretArray[1] = 0; //line one
		for (int i = 2; i<length; i++)
		{
			//System.out.println(search.charAt(i)); //debug char printed out
			if (search.charAt(i) == '\n')
			{
				CaretArray[j] = i; //stores value of i or CaretPosition
				j++; //increment j
				//System.out.println("Line "+j+" is at Position "+i); //debug
				//System.out.println("Found return at postion "+i); //debug
			}
		}//end loop

/*
		2nd for loop prints out the value of j which are the Caret position the lines should appear
		Second loop just double checks the value of the CaretArray.
*/
		for (int i = 1; i<j; i++)
		{
			System.out.println(CaretArray[i]);
		}

		searchTextArea.setCaretPosition(CaretArray[intLine]+1); //sets the caret position in the text

	}//end Goto Method

/**
	The Find methods searches for a string the user inputs
*/
	public void Find (String search) {
//STRING TOKENIZER.  Will work for the find and replace methods.

		//if statement checks null is the search string, output message and exits methods
		//if (search == null || search == "")
		//{
		//	message("Please Enter a String to Search",note);
		//	return;
		//}

		//delimitor is '\n' a return and " " space
		StringTokenizer st = new StringTokenizer(searchTextArea.getText(), " \n");
		System.out.println("search string: "+search);

		int length = st.countTokens(); //sets the length

		for (int i=0; i<length; i++) //count is set to the number of tokens
		{
			while (st.hasMoreTokens())
			{
				String temp = new String(st.nextToken()); //the string in a token form
				System.out.println(temp); //the String
				System.out.println(temp.length()+ " length of string");
			}//end while loop
		}//end for loop

	}//end Find Method

	/**
				Method prints out a GUI message to the user.
	*/
	public void message(String aStr, JFrame aFrm) {
		JOptionPane.showMessageDialog(aFrm, aStr,
		"NOTE", JOptionPane.INFORMATION_MESSAGE);
	}
}