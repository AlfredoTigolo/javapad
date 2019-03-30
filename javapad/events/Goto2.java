package javapad.events;

public class Goto2 {

	/**
		This method is try not an array to store the string position
	*/
	public void GotoLine2(int intLine) {

		//if statements checks if the intLine is greater then number of lines in the method
		System.out.println("intLine "+intLine);
		//System.out.println("LineCount "+searchTextArea.getLineCount());
		/*
		if (intLine > searchTextArea.getLineCount())
		{
			message("Line Number Out of Range", note);
			//searchTextField(""); //can't be seen
			return; //the return statement exits the NumOfLine Method
		}
		*/

		//System.out.println(searchTextArea.getText().length()+" length of string text?");

		//String search = new String(searchTextArea.getText()); //reads the string
		//int length = searchTextArea.getText().length(); //stores the length of the string
		int[] CaretArray = new int[100];
		int j = 2; //counter for arrary

	/*
	THE charAt() METHOD OF THE STRING CLASS
		1st for loop cycles through the string checking the character return.  i = 0 is line one
		This first loop stores the value of the Caret Position of the return
	*/
		CaretArray[1] = 0; //line one
		//for (int i = 2; i<length; i++)
		for (int i = 2; i<10; i++)
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

		//searchTextArea.setCaretPosition(CaretArray[intLine]+1); //sets the caret position in the text

	}//end Goto Method

	/*
	Oleg G <hardcore_champ32@hotmail.com> wrote:

	So, as I am cycling through the String, the Caret number is moving up also.  Once I get the return character, I store the cycle number into an array.  SO, for every return value I find, I store the cycle number in an arrary.


	What exactly do you add to the array, and how do you declare the array? If there is one thing I was bad at in cs201 it was arrays :-(



	Here's my go to method as it is (don't copy it :-))
	*/


	 public void OLEGgoTo ()
	  {

	/*

	place this in a try and catch block if user inputs a letter instead of a number, the try and catch will take care of it and will not crash you program.

	*/
	    String input = JOptionPane.showInputDialog(
	     Notepad.this,
	     "Enter The Line You Wish To Go To",
	     "Find", JOptionPane.INFORMATION_MESSAGE);
	    Integer someInteger = null;
	    int lineNumber = someInteger.parseInt(input);

	/*

	getLineCount is useful for error check the value you get from textField.  If the getLineCount > lineNumber, return.  The return statement will exit the method.  I placed this error checking "IF" ontop of the gotoLine method

	*/
	    //output.getLineCount();
	    //output.setCaretPosition(lineNumber);
	    String text=output.getText();

	/*

	No need to store values into an array

	*/
	    int [] mylist;
	    int linecount, charcount;
	    charcount = text.length();

	/*

	For loop should cycle through string and find the position of the Line Number - 1 = return position.  From this for loop, you need I think 2 counters. i is the caret position when it cycles through the string. lineNumber is the terminating factor.

	STRING VALUES:
	trest <- LINE 1 'special case'
	crest
	best
	test <- LINE 4 = 3rd return


	Your if and for loop statement can have another comparison like this:

	...

	int returnPosition = LineNumber -1;

	int numOFreturns = 0; //number of times for loop found a return

	if (text.charAt(i) == '\n' && returnPosition==numOFreturns ) //see your updated code below

	...

	*/
	    for (int i = 0; i < charcount; i++)
	      {
	        if (text.charAt(i) == '\n')
	        {
	          ++numOFreturn; //updated by some guy

	if(numOFreturn == returnposition)

	//used the setCaretPosition method here.  Be sure to add one for new line. i is the caret number you use plus 1.  Oleg, I think I did your Goto.  Handle the special cases ONTOP of your Goto Method


	        }
	      }

	  }






	So what I am missing is the implementation of teh for loop, and the actual going to the line, which is the easy part...


}