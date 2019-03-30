package javapad.events;

/**
	FileIO is used for Input and Output of String from the JTextArea into a *.txt File.
	This code was taken from the Student Parking GUI example.  I had this code working,
	but it has something to do with the readline area.
*/
public class FileIO {

	static String line; //used to printout the text to the command-line
	static String returnStr; //used to return a String object
	//static public File outFile;
	static javax.swing.JTextArea tempTextArea = new javax.swing.JTextArea();

	/**
		main method used to test the class.  Advice from Junko, thanks!
	*/ //DISABLED CODE : Enable by adding asterik on the beginning of this line
	public static void main (String[] args) {

		try
		{

			readFromFile(args[0]);
			//writeToFile(args[1]);

		}
		catch (Exception e)
		{

			System.out.println("Doesn't exist "+e.getMessage());

		}
	}//end main method

	/**
		Method reads a text file and outputs it as a String object.  To use this method in
		a class for reading files, you must place the call to the method in a try and catch
		block

		try
		{
			FileIO.readFromFile("FileName")
		}
		catch (Exception e)
		{
			e.getMessage();
		}

		If it is not placed in a try / catch, compiler will warn that an exception is thrown.
	*/
	//static public void readFromFile(String aFile)
	static public String readFromFile(String aFile)
	throws java.io.IOException
	{

		System.out.println(aFile);
		//java.io.File inFile=new File(openDialog.getFile());
		java.io.File inFile=new java.io.File(aFile);

			if(inFile.exists() && inFile.canRead())
			{
				//JC: For more detail -
				//	http://java.sun.com/j2se/1.4/docs/api/java/io/BufferedReader.html
				// You create a buffer reader and pass the file to it. Then use the readLine method
				// to get each line back and append it to the textArea.

				java.io.BufferedReader fileInStream
				=new java.io.BufferedReader(new java.io.FileReader(inFile));

				line=fileInStream.readLine();
				returnStr=new String(); //clears the object

				while(line != null)
				{
					//informationArea.append(line + "\n")
					//System.out.println(line+"\n in the while loop"); //debugging
					returnStr = returnStr.concat(line+"\n");
					line=fileInStream.readLine();
				}

				fileInStream.close();

			}

		//System.out.println(returnStr +" at return");
		return new String(returnStr);
	}//end readFromFile method

	/**
	JC - USEFUL:

	This method reads the information in the JTextArea
	and writes it to a file.  For testing, right now it is reading from another textfile.
	The outfile
	*/
	static public void writeToFile(String aFile, javax.swing.JTextArea aTextArea)
	//public void writeToFile()
	throws java.io.IOException
	{
		tempTextArea = aTextArea;
		//java.io.File outFile=new File(saveDialog.getFile());
		java.io.File outFile = new java.io.File(aFile);
		//outFile = new java.io.File(aFile); //made it a static class variable

			if(!outFile.exists() || outFile.canWrite())
			{
				//this code writes the file!  Ah!
				java.io.BufferedWriter bufferedOutStream
					=new java.io.BufferedWriter(new java.io.FileWriter(outFile));

				//* The code is used to write to a a file.
				//javax.swing.JTextArea tempAreaText = new javax.swing.JTextArea();
				//bufferedOutStream.write(informationArea.getText()); //works from examle

				//what does this do?  It writes to a a file?  So, we must declare a File
				//bufferedOutStream.write(this.readFromFile(outFile.getName())); //doesn't work
				//bufferedOutStream.write(returnStr); //works?
				bufferedOutStream.write(tempTextArea.getText()); //works? reads
				//*/

				bufferedOutStream.close();
			}
	}//end writeToFile

}//endFileIO class
