/**
	STEPS TO INCOPORATE THIS CLASS INTO THE LARGER EVENT - HANDLING CODE

	1.  DATA MEMBERS, There must be no static data member variables of JTextArea

	2. The EXTRA TabbedPane, This must be no extra tabbed on this class

	3. DISABLE the main method towards the bottom of this class.

	4. Don't forget to comment out the EditListener Declaration where to include a aTextArea reference
	in ths constructor.
*/

package javapad;
import javapad.events.EditSubListener; //see if we can set a listener

/**
	The EditSubMenu Class houses a submenu here for Find, Replace, or Goto.
	I am using a JTabbedPane Class to produce the GUI event like the one they
	have in MS-Word.  Some example code were taken from the TabbedPaneDemo,
	TabWindow, TextComponent, and other fields that relate this with extra GUI.
*/
public class EditSubMenu extends javax.swing.JFrame implements
	java.awt.event.ActionListener {

//DATA MEMBERS
	//***** 1.
	//static javax.swing.JTextArea aTextArea = new javax.swing.JTextArea(); //the text area!
	//static javax.swing.JTextArea aTextArea; //the text area! take care of it in the listener class!
	javax.swing.JTabbedPane tabbedPane = new javax.swing.JTabbedPane();
	EditSubListener editSubListener = new EditSubListener(); //the editSubListener LISTENER
	//***** 4.
	javax.swing.JPanel Goto, Find, Replace, Extra;

	//for Find Buttons and textField
	javax.swing.JButton jbCancel = new javax.swing.JButton("Cancel");
	javax.swing.JButton jbFindNext = new javax.swing.JButton("Find Next");
	public javax.swing.JTextField findText = new javax.swing.JTextField(20);

	//for Replace Buttons, textField, and labels
	javax.swing.JButton jbCancel_R = new javax.swing.JButton("Cancel");
	javax.swing.JButton jbFindNext_R = new javax.swing.JButton("Find Next");
	javax.swing.JButton jbReplace_R = new javax.swing.JButton("Replace");
	javax.swing.JButton jbReplaceAll_R = new javax.swing.JButton("Replace All");
	public javax.swing.JTextField findText_R = new javax.swing.JTextField(10);
	public javax.swing.JTextField replaceText_R = new javax.swing.JTextField(10);
	javax.swing.JLabel lblfind = new javax.swing.JLabel ("Find What:");
	javax.swing.JLabel lblreplace = new javax.swing.JLabel ("Replace With:");

	//for Goto Buttons, textfield, and label
	javax.swing.JLabel linelbl_G = new javax.swing.JLabel ("Go to Line Number:");
	public javax.swing.JTextField findText_G = new javax.swing.JTextField(10);
	javax.swing.JButton jbGoto_G = new javax.swing.JButton("Goto");
	//javax.swing.JButton jbPrevious_G = new javax.swing.JButton("Previous");
	//javax.swing.JButton jbReplace_G = new javax.swing.JButton("Next");
	javax.swing.JButton jbCancel_G = new javax.swing.JButton("Cancel");

	//listener class that just listeners for the textfields.
	EditSubListener txtGotoListener = new EditSubListener(findText_G);

	EditSubListener threeTxtListeners = new EditSubListener(
		findText_R,
		replaceText_R,
		findText);

	//find, replace, and goto

//DELETED CODE BECAUSE EditSubListener

//DISABLED DEFAULT CONSTRUCTOR
		/**
			Constructor that accepts a JTextArea Argument
		*/
		//public EditSubMenu (javax.swing.JTextArea tempTextArea) {
			//EditSubMenu(); //calls default constructor? doesn't work
			//this.aTextArea = tempTextArea; //reference of tempTextArea, incorrect
		//}


//CONSTRUCTOR THAT ACCEPTS AN TEXTARE ARUGMENT
	/*
		might Modified now takes a JTextArea Argument Default constructor
	*/
	public EditSubMenu () {
	//public EditSubMenu (javax.swing.JTextArea tempTextArea) {
		//this.aTextArea = aTextArea; //needed in the event handling method
		//editSubListener = new EditSubListener(aTextArea);
		//super(); //Do I need the super constructor?

//DISABLED CODE TO BE USED TO DEBUG goto, find, replace ALGORITHM
		/******* 2.
		//Extra Tabbed Menu testing method
		Extra = createTabItem(Extra, "Extra"); //debugging
		Extra.setLayout(new java.awt.BorderLayout());
		Extra.add(aTextArea, java.awt.BorderLayout.CENTER);
		aTextArea.getDocument().addDocumentListener(editSubListener); //temp document listener
		//*/

		//adding document listeners to the textFields
		findText_G.getDocument().addDocumentListener(editSubListener);
		findText.getDocument().addDocumentListener(editSubListener);
		replaceText_R.getDocument().addDocumentListener(editSubListener);
		findText_R.getDocument().addDocumentListener(editSubListener);

		setTitle("Find and Replace");
		setSize(500,150);

//THE FIND MENU TAB AND ALL IT'S COMPONENTS
		Find = createTabItem(Find, "Find");
		Find.add(new javax.swing.JLabel("Find What:"));
		Find.add(findText);

		Find.add(jbCancel, java.awt.FlowLayout.RIGHT);
		addEditSubListener(jbCancel); //register listener
		jbCancel.addActionListener(this); //local listener to hide it

		Find.add(jbFindNext, java.awt.FlowLayout.RIGHT);
		addEditSubListener(jbFindNext); //register listener

//THE REPLACE MENU TAB AND ALL IT'S COMPONENTS
		Replace = createTabItem(Replace, "Replace");
		//Replace.setLayout(new java.awt.GridLayout(4,5,1,1)); //changed Layout
		Replace.setLayout(new java.awt.BorderLayout()); //changed Layout

		//creating a textPane Container for labels and text
		javax.swing.JPanel textPane = new javax.swing.JPanel(); //create pane for text and labs
		textPane.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		textPane.add(lblfind);
		textPane.add(findText_R);
		textPane.add(lblreplace);
		textPane.add(replaceText_R);
		Replace.add(textPane, java.awt.BorderLayout.NORTH);

		//creating a textPane Container for buttons
		javax.swing.JPanel buttonPane = new javax.swing.JPanel();
		buttonPane.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		buttonPane.add(jbReplace_R);
		addEditSubListener(jbReplace_R); //register listener

		buttonPane.add(jbReplaceAll_R);
		addEditSubListener(jbReplaceAll_R); //register listener

		buttonPane.add(jbFindNext_R);
		addEditSubListener(jbFindNext_R); //register listener

		buttonPane.add(jbCancel_R);
		addEditSubListener(jbCancel_R); //register listener
		jbCancel_R.addActionListener(this); //local listener to hide GUI.
		Replace.add(buttonPane, java.awt.BorderLayout.CENTER);

//THE GOTO MENU TAB AND ALL IT'S COMPONENTS
		Goto = createTabItem(Goto, "Goto");
		Goto.add(linelbl_G);
		Goto.add(findText_G);

		Goto.add(jbGoto_G);
		addEditSubListener(jbGoto_G); //register listener

		//Goto.add(jbPrevious_G);
		//addEditSubListener(jbPrevious_G); //register listener

		//Goto.add(jbReplace_G);
		//addEditSubListener(jbReplace_G); //register listener

		Goto.add(jbCancel_G);
		addEditSubListener(jbCancel_G); //register listener
		jbCancel_G.addActionListener(this); //local listener to hide GUI.

		/** //DISABLED CODE by place '/**' at the beginning of the line:
		//Enabled code my delete '/**' at the above line
		//Goto Tabbed Menu
		Goto = new javax.swing.JPanel(); //creating the panel
		//Goto.setMnemonicAt('G'); //only appears in JDK 1.4
		Goto.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		Goto.add(new javax.swing.JButton("test 1"));
		tabbedPane.addTab("Goto", Goto);

		//FindTabbed Menu
		Find = new javax.swing.JPanel();
		Find.add(new javax.swing.JButton("test 2"));
		tabbedPane.addTab("Find", Find);

		//Replace Tabbed Menu
		Replace = new javax.swing.JPanel();
		Replace.add(new javax.swing.JButton("test 3"));
		tabbedPane.addTab("Replace", Replace);
		/*/ //DISABLED CODE

		getContentPane().add(tabbedPane);//adding to the frame
	}

	/**
		Method that helps create a tabbed panel for EditSubMenu
	*/
	public javax.swing.JPanel createTabItem(javax.swing.JPanel aPanel, String tabStr) {

		//Goto.setMnemonicAt('G'); //only appears in JDK 1.4

		aPanel = new javax.swing.JPanel(); //creating the panel
		aPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT)); //easier to work with
		//aPanel.setLayout(new java.awt.BorderLayout(1,1));
		//aPanel.add(new javax.swing.JButton("From createTabItem Method"));
		tabbedPane.addTab(tabStr, aPanel);
		return aPanel;
	}

	//******* 3.
	/** //DISABLED TEST MAIN.  In order to get this to work, you need to
	delcare to change the constructor on this class so it deosn't except any arguments
		main method to test EditSubMenu
	*/
	//*
	public static void main(String[] args) {
		EditSubMenu editSubMenu = new EditSubMenu();
		MainPrj6.SetCenter(editSubMenu);
		//editSubMenu.setSize(500,150);
		editSubMenu.setSize(500,500);
	}
	/*

	/*
		Method that sets the tabbed pane depending on the integer passed
	*/
	public void setTab(int i) {
		tabbedPane.setSelectedIndex(i);
	}

	/**
		The method helps register the listeners for each button of this submenu
	*/
	public void addEditSubListener(javax.swing.JButton abjREF) {
		abjREF.addActionListener(editSubListener);
	}

	/**
		method is a local listener so we can hide or not make the GUI method
		of setVisible to false.
	*/
	public void actionPerformed(java.awt.event.ActionEvent ae) {
		if (ae.getActionCommand().equals("Cancel"))
			this.showGUI(false);
	}

	/**
		method used to setVisible method
	*/
	public void showGUI(boolean show) {
		this.setVisible(show);
	}
}