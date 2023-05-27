package pkg_main;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;

class ClearQueueAction extends AbstractAction
{
	ArrayList<File> ref_data;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public ClearQueueAction(ArrayList<File> in_string)
	{
		super();
		//System.out.println(in_string);
		this.ref_data = in_string;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.ref_data.clear();
		System.out.println("Queue cleared.");
	}
	
}