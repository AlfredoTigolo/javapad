//package wordprocessor.events;
package javapad.events;

// example probably not needed.
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ToolsItemListener implements ItemListener {
	public void itemStateChanged(ItemEvent ie) {
		System.out.println("Invoked");
	}
}