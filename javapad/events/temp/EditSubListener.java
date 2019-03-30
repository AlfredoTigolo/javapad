//package javapad.events;

//import javapad.AlfsPad;
//import javapad.EditSubMenu;
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
	//AlfsPad alfsPad;
	static JTextArea searchTextArea = new JTextArea("Search through this test place.");
	//JTextField searchTextField = new JTextField(); //lets use a reference instead
	static JTextField searchTextField, txtFind;// txtFindReplace, txtReplace;
	JTextField txtFindReplace, txtReplace;
	JFrame note = new JFrame(); //used to output messeges

	/**default constructor*/
	public EditSubListener () { }

	/**constructor accepts an AlfsPad argument*/
	//public EditSubListener (AlfsPad aAlfsPad) { this.alfsPad = aAlfsPad;}

	/**constructor accepts an JTextArea argument*/
	public EditSubListener (JTextArea aTextArea) { this.searchTextArea = aTextArea; }

	/**constructor accepts an JTextField arguement*/
	public EditSubListener (JTextField txtGoto){ this.searchTextField = txtGoto; }

	/**constructor accepts 3 JTextField arguments*/
	public EditSubListener (JTextField aTxtFind_R, JTextField aTxtReplace, JTextField aTxtFind)
	{
		this.txtFind = aTxtFind;
		this.txtFindReplace = aTxtFind_R; //we are not dealing with variables. We are dealing with TextFields
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
				//this.Find(txtFind.getText().trim().toLowerCase());
                          this.Find2(txtFind.getText().trim().toLowerCase());
			}
			catch (Exception e)
			{
				message(e.getMessage()+"What did you do?\n I think you reached the end of the Document.", note);
			}
		}
		else if (aeStr.equals("Start Replace"))
		{
			try
			{
				String findStr = this.txtFindReplace.getText().trim().toLowerCase();
				String replaceStr = this.txtReplace.getText().trim().toLowerCase();
				this.Replace(findStr, replaceStr);
			}
			catch (Exception e)
			{
				message(e.getMessage()+"Please Enter a Find and Replace word", note);
			}
		}

	}//end actionPerformed Method

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
			message("Line Number Out of Range\nOr the value is blank", note);
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
 rewrite of the Find method
 */
public void Find2(String findStr) {

  String wholeStr = searchTextArea.getText(); //gets whole string from textArea
  StringTokenizer st = new StringTokenizer(wholeStr, " \n"); //break into words
  String temp, subStr; //used to search
  int intStart; //beginning index
  int intEnd = findStr.length(); //ending index

  searchTextArea.setCaretPosition(0);
  //for (int i = 0; i<st.countTokens(); i++) {
  while(st.hasMoreTokens())
  {
    temp = st.nextToken();
    intStart = temp.indexOf(findStr, 0);
    //used create or use exiting method to search the word
    System.out.println(intStart);
    System.out.println("findStr "+findStr);
    subStr = temp.substring(intStart, intEnd);
    System.out.println("subStr "+subStr);
    if (findStr == subStr)
    {
      message("Found a Match", note);
    }

  }//for loop or while loop
}//end Find2 method

/**
	The Find methods searches for a string the user inputs

	Do we need to keep track of the caret position?  Yes, it helps with selecting text.  I had a alot of trouble
	with this.  I broked down the text so well that I might be able to get the code in smaller doses.  The first
	time I tried to do this method is it gets only a word, but does not search through the word for a specific partern.
	The Find method breaks the textArea into tokenized words.  Error checking is done at the beginning of there is a
	value in the textField.  Tokenizer is used to cycle through the tokenized words.  While tokenizer cycles through
	the words, it checks for the pattern by calling another method called searchWord.  Once the the searchWord method
	is done, it doesn't return a value because it searches through the word for any possible parttern.  If a patter is found,
	it should high the word by taking the first occurence of the word to the length of the search word.  Then, it should
	move onto the next word.

*/
	public void Find (String search) {
//STRING TOKENIZER.  Will work for the find and replace methods.

//GET THE WORD
		//if statement checks null is the search string, output message and exits methods
		if ( search.equals("") || search.equals(null) )
		{
			message("Please Enter a String to Search",note);
			return;
		}

//TOKENIZED THE TEXT
		//delimitor is '\n' a return and " " space
		StringTokenizer st = new StringTokenizer(searchTextArea.getText().toLowerCase()); //tokenizer into words
		System.out.println("search string: "+search); //displays search string

		//setup of variables
		int length = st.countTokens(); //sets the length
		int index =0; //position of string pattern?
		int caretPos = 0; //cursor position
		String temp2 = null; //used to store word value
		searchTextArea.setCaretPosition(0); //set caret to top of the textArea, first line.
		boolean exit = false; //used to stop searching

		//for (int i=0; i<length; i++) //count is set to the number of tokens or words tokenized
		//for (int i=0; i<searchTextArea.getText().length(); i++) //count is set to the number of tokens
		//{
			//textArea
			//System.out.println(i+ " caret position");

//T0KENIZER IS USED TO CYCLE THROUGH EACH WORD
			while (st.hasMoreTokens())
			{
				temp2 = new String(st.nextToken()); //the token into string

				//String temp = new String(searchTextArea.getText());
				System.out.println(temp2+" & " +temp2.length()+ " length"); //the String and length of string


				//if (temp.equalsIgnoreCase(search)) //if not needed?
				//{
				//NEED AN INNER FOR LOOP TO CYCLE THROUGH THE CURRENT WORD and get the length of the word search word!?
				//index = temp2.indexOf(search); //returns the index of where the patter is found

//STRING COMPARISON WITH WORD AND TEXT DONE HERE
				searchWord ( temp2, search, searchTextArea.getCaretPosition());//inner loop

//JOPTIONPANE PROMPTS USER IF THEY WANT TO CONTINUE LOOKING FOR THE NEXT WORD
				int returnVal = JOptionPane.showConfirmDialog(new JFrame(),
					"Find the next word?");

				//switch statement to control the flow
				switch(returnVal)
				{
					case JOptionPane.OK_OPTION:
						//continue;
						break;

					case JOptionPane.NO_OPTION:
						//i = aWordStr.length() + 1;
						//return;
						exit = true;
						break;
						//continue;

					case JOptionPane.OK_CANCEL_OPTION:
						return;
						//break;
				}//end switch

				if (returnVal == JOptionPane.NO_OPTION){ return;}

				/* //commented out if statement because it only cycles through the first occurances of the text
				if (index != -1)
				{
					System.out.println("Starting index of current word "+index);
					message("Found match at index " +index, note);
				}
				*/

				//moving the caret+1
				caretPos += temp2.length()+1; //applies caret position to the length of the string.
				searchTextArea.setCaretPosition(caretPos); //changes caret position according to length of text

				//if (temp.equalsIgnoreCase(search))
				//{
				//	//System.out.println("found a match at "+index);
				//	message("Found match at index " +index, note);
				//}

				//}
			}//end while loop

		//}//end for loop

	}//end Find Method

	/**suppose to search through a word for other string patterns*/
	public void searchWord (String aWordStr, String aSearchStr, int caretLast) {

		int index = 0;
		int endLength = aSearchStr.length();


		for (int i = 0; i<aWordStr.length(); ++i)
		{
			 //returns the caret position in a string,
			index = aWordStr.indexOf(aSearchStr, i);
			if(index != -1)
			{
				System.out.println("letter index"+index);
				//select the word, and highlight, replace or goto the next one.
				//http://java.sun.com/j2se/1.3/docs/api/  select method of JTextComponent
				searchTextArea.select(caretLast + index, (caretLast + endLength));

			}//end if

			//message("Wait", note);

		}//end for loop

		//return index;

	}//end searchWord Method

	public void Replace(String findStr, String replaceStr) {
		if (findStr.equals(null) || replaceStr.equals(null))
		{
			message("Please enter a word or letter to search", note);
		}

		System.out.println("replace invoked");//debug
	}

	/**
				Method prints out a GUI message to the user.
	*/
	public void message(String aStr, JFrame aFrm) {
		JOptionPane.showMessageDialog(aFrm, aStr,
		"NOTE", JOptionPane.INFORMATION_MESSAGE);
	}
}