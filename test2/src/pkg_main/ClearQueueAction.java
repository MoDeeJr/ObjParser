package pkg_main;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

/**
 * 
 * Simple action to clear an internally stored file list in EditorShared.my_files
 */
class ClearQueueAction extends AbstractAction
{
	EditorShared shared;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public ClearQueueAction(EditorShared in_shared)
	{
		super();
		this.shared = in_shared;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.shared.my_files.clear();
		this.shared.list_label.setText("file queue cleared. select additional files.");
		System.out.println("Queue cleared.");
	}
	
}