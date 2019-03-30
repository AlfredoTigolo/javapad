//package wordprocessor.events;
/**page 558, Core Java 2*/
package javapad.events;

/**
	MyFileFilter is an subclass of FileFilter.
*/
public class MyFileFilter extends javax.swing.filechooser.FileFilter {
//public class MyFileFilter implements java.io.FileFilter {

	public MyFileFilter() {
		super();
	}

	public boolean accept(java.io.File f) {
		return f.getName().toLowerCase().endsWith(".txt") || f.isDirectory();
	}

	public String getDescription() {
		return "Text File"; //for now
	}
}