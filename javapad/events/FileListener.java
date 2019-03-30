/**
	The FileListener Class

	I decided to create 3 listener classes for each of the following menu items.
	The FileMenu Class deals more with file management.  Seperating what
	the listeners 'encapsulates' their functionality.  For now, this class listeners
	for the Exit menu item selected.  The others just print out a message they
	they have been invoked.

	Decided to update this class by create a method that outputs a message
	box instead of outputing to the command line.  Importing the JOptionPane
	Class to have a GUI popup with a message.  Must also import a JFrame class
	to output the message

	For JFileChooser, be sure to change the soureFile String to determine where
	files are being saved.

	The New, Save, SaveAs, and Open have a seperate class that has to deal with
	reading and writing to a file.  If it is included in the event handling code, then
	the code will get lost in the event.

	Need to check the boolean value of update at each file menu option.

	Look at the parking meter example to get the read to and save to a file.  Great!

	Must double check the error checking values for File, SaveAs, Save, and Open.
	This code can now opens text files from other directories.  There are so many
	error checking code to do.  So, need to do those later.  Right now, it is time to
	move onto the other methods.
*/

//package wordprocessor.events;
package javapad.events;

import javax.swing.JOptionPane; //for messaging
import javax.swing.JFrame; //for messaging

//import wordprocessor.FileMenu;
//import javapad.Filemenu;
import javapad.MainPrj6;
import javapad.AlfsPad;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.JFileChooser;
//import javax.swing.filechooser.FileFilter;
//FileFilter is an absbstract class and I need to declared another class
import java.io.File; //used for changing the current directory

/**
	The FileListener Class
*/
public class FileListener implements ActionListener, ItemListener,  DocumentListener {
//public class FileListener implements ItemListener {

	JFileChooser fc = new JFileChooser(); //used for the file open and file save
	String fileSource = new String("javapad/txt/*.txt");//used File class
	MyFileFilter fileFilter = new MyFileFilter(); //used to filter files
	String printOut = new String(); //print out the textArea to a JOptionPane
	static AlfsPad tempAlfsPad; //place holder for AlfsPad reference
	//AlfsPad test;
	static JTextArea textArea = new JTextArea();
	static boolean update = false; //used for checking modified text area
	JFrame note = new JFrame(); //used to output a message
	static int counter = 1; //used to count how many files have been created

	/**
		Default constructor
	*/
	public FileListener() { }

	/**
		Constructor that accepts a JTextArea
	*/
	public FileListener(JTextArea aTextArea) { this.textArea = aTextArea; }

	/**
		constructor that excepts AlfsPad reference
	*/
	public FileListener(AlfsPad alfsPad) { this.tempAlfsPad = alfsPad; }

	/**
		Constructor that accepts a FileMenu option
	*/
	//public FileListener(FileMenu aMenu) { }

	/**
		method overrided from ActionListener Interface
		This actionPerformed uses other methods within the classs to prefrom
		the action
	*/
	public void actionPerformed(ActionEvent e) {

		//String test = new String(e.getActionCommand());//used to check if Exit is selected

		//if (test.equals("Exit")) //program shuts down when exit is selected
		 //program shuts down when exit is selected
		if (e.getActionCommand().equals("New"))
		{
			this.NewFile();
		}
		if (e.getActionCommand().equals("Print"))
		{
			this.Printing();
		}
		else if (e.getActionCommand().equals("Save As") || e.getActionCommand().equals("Save"))
		{
			this.SaveAsFile(); //calls a method and passes a note frame
		}
		else if (e.getActionCommand().equals("Open"))
		{
			this.OpenFile(); //calls a method and passes a note frame
		}
		else if (e.getActionCommand().equals("Exit"))
		{
			booleanValue();
			if (update)
				this.NewFile();
			message("You are now Exiting Alf'sPad", note);
			System.exit(0);
			//test.dispose();
		}
		else
		{
			System.out.println("File -> "+e.getActionCommand());
			//message(e.getActionCommand()+" Menu Item selected.", note);
		}
	}

	/**
		method overrided from the ItemListener Interface
	*/
	public void itemStateChanged(ItemEvent e) {
		System.out.println(e.getSource());
	}

	/**
		three overrided method from the DocumentListener class
	*/
	public void insertUpdate(DocumentEvent de) {
		//System.out.println(tempAlfsPad.getTitle()); //debug
		update = true; //changes when text is typed in
		System.out.println("JTextArea change is "+update); //debug
		//System.out.println(textArea.getText()+" insert method from FileListener"); //debugging
		//System.out.println(printOut+" insert method from FileListener");
	}

	public void removeUpdate(DocumentEvent de) {
		update = true; //changes when text is deleted
		//System.out.println(update);
		//System.out.println("remove");
	}

	public void changedUpdate(DocumentEvent de) {
		//System.out.println("changed");
	}

	/**
		Method creates another instance of AlfsPad.  Commented out code that
		created an instance of AlfsPad.  Now, it checks for text in the area and
		should write to a file.  Three possible choices can be made.  OK, calls the
		SaveAsFile method, NO, doesnt save the file, but makes the textarea go
		blank, and cancel doesn't do anything.
	*/
	public void NewFile() {
		++counter; //increated counter when NewFile invoked
		tempAlfsPad.setTitle("Alf's Pad - untitled " + counter); //change title
		System.out.println("NewFile Invoked");

		if (update) //value changes when JTextArea is modified
		{
			int returnVal = JOptionPane.showConfirmDialog(new JFrame(),
				"Save Changes to current File?");
				System.out.println(returnVal);

			//switch statement inside the if updates have been made to textarea
			switch(returnVal)
			{
				case JOptionPane.OK_OPTION:
					this.SaveAsFile(); //class save menu
					this.textArea.setText("");
					update = false; //changes back to false
					this.booleanValue(); //debug
					break;

				case JOptionPane.NO_OPTION:
					this.textArea.setText("");
					update = false; //changes back to false
					this.booleanValue(); //debug
					break;

				case JOptionPane.OK_CANCEL_OPTION:
					this.booleanValue(); //debug
					break;
			}//end switch
		}//end if
		else
		{
			update = false;
			textArea.setText(""); //blanks the area because the TextArea has been saved already
		}//end else

		/*
		//must create an array of AlfsPad.  But, depending on the index count, then
		it should be deleted.
		 //code that does multicasting
		test = new AlfsPad();
		MainPrj6.SetCenter(test);
		MainPrj6.addWinListener(test);
		*/
	}

	/**
		Method used to SaveAs or Save a file by using a dialog box
		SaveAs and Save are both different methods.  One problem is checking
		the getSelected method of JFileChooser is checking.  Must create a seperate
		class that does reading and writing.
	*/
	public void SaveAsFile() {
		int returnVal=0; //used to check if Save or Cancel Button was choosen

		fc.setDialogTitle("Save As");
		fc.setFileFilter(fileFilter);
		fc.setSelectedFile(new File(fileSource));
		returnVal = fc.showSaveDialog(note);
		File file = fc.getSelectedFile(); //used to save a file
		//tempAlfsPad.setText(""); //should be blanked if saved?

		switch(returnVal)
		{
			/**
				Must check if file name is entered
			*/
			case JFileChooser.APPROVE_OPTION:
			    System.out.println("File -> Save? -> JFileChooser "+file.getName()); //debug

			    try
			    {
			    	//FileIO.writeToFile("javapad/txt/"+file.getName(), textArea);
			    	FileIO.writeToFile(fc.getCurrentDirectory()+file.getName(), textArea);
				}
				catch (Exception e)
				{
					System.out.println("File is not exist "+e.getMessage());
				}

				 //this is where a real application would open the file.
                //log.append("Opening: " + file.getName() + "." + newline); //

		    	//this.textArea.setText("Saving "+file.getName()); //debugging
			    //this.textArea.setText(""); //set blank when debugging finished
			    this.tempAlfsPad.setTitle("Alf's Pad "+fc.getCurrentDirectory()+"\\"+file.getName());
			    update = false;
			    this.booleanValue(); //debug
				break;

			case JFileChooser.CANCEL_OPTION:
				System.out.println("File -> Canceled -> JFileChooser"); //debug
				break;
		}//end switch block
	}

	/**
		Method used to Open a file by using a dialog box.
		This method, you must specify the source of the text it is reading from.
	*/
	public void OpenFile() {

		fc.setDialogTitle("Open");
		//fileFilter.addExtension("txt"); //creating a filter, but method must be declared
		fc.setFileFilter(fileFilter); //adding the filter
		//fc.getCurrentDirectory(); //need this to specify the location of the text files.
		fc.setSelectedFile(new File(fileSource));
		//File file = fc.getSelectedFile();

		if (update) //value changes when JTextArea is modified
		{
			this.NewFile();
			/*
			int returnVal = JOptionPane.showConfirmDialog(new JFrame(),
				"Save Changes to current File?");
			*/
		}

		fc.setDialogTitle("Open");
		int returnVal = fc.showOpenDialog(note);

		switch(returnVal)
		{
			case JFileChooser.OPEN_DIALOG:
					//?
					//this.textArea.setText("Opening "+file.getName()); //debugging
					//System.out.println("Opening "+file.getName()); //debugging

					try
					{
						File file = fc.getSelectedFile();
						System.out.println(file);
						System.out.println(FileIO.readFromFile(fc.getCurrentDirectory()+"\\"+file.getName())); //code throws exception
						//String ok = FileIO.readFromFile("javapad/txt/"+file.getName()); //from FileIO class
						String ok = FileIO.readFromFile(fc.getCurrentDirectory()+file.getName()); //from FileIO class
						this.textArea.setText(ok); //code throws exception
						this.tempAlfsPad.setTitle("Alf's Pad "+fc.getCurrentDirectory()+"\\"+file.getName()); //changes Title name and gives location.
						update = false;
						booleanValue();

					}
					catch (Exception e)
					{

						System.out.println("Doesn't work "+e.getMessage());

					}

				break;

			case JFileChooser.CANCEL_OPTION:
					System.out.println("File -> Canceled -> JFileChooser"); //debug
				break;
		}//end switch block
	}

	/**
			Method prints out a GUI message to the user.
	*/
	public void message(String aStr, JFrame aFrm) {
		JOptionPane.showMessageDialog(aFrm, aStr,
			"File Menu", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
		Method that is used to printOut the current text.
		Method should check if there is a blank textArea.
	*/
	public void Printing() {
		try
		{
			printOut = textArea.getText();
			message(printOut, note);
		}
		catch (Exception e) { }
	}

	/**
		debugging method that checks value of boolean update for JTextAra
	*/
	public void booleanValue() {
		System.out.println("JTextArea change is "+update); //debug
	}
}