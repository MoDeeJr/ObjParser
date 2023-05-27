package pkg_main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * 
 * OpenFilesAction uses FileChooser to display a browse feature, for users to select files.
 * Selected files are stored into EditorShared.my_files for processing.
 */
class OpenFilesAction extends AbstractAction
{
	EditorShared shared;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OpenFilesAction(EditorShared in_shared)
	{
		this.shared = in_shared;
	}
	
	private String get_file_type(String filename)
	{
		String type = "";
		
		int[] c_result = filename.chars().toArray();

		boolean capture = false; // only capture characters after '.'
		for(int i = 0; i < c_result.length; i++)
		{
			if(capture) {
				type += (char) c_result[i];
			}
			
			if(c_result[i] == '.')
			{
				capture = true;
			}
		}
		
		return type;
	}

	public void openDirectory() {
		
		// get selected files using FileChooser (Windows browse)

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setMultiSelectionEnabled(true);
		int result = fileChooser.showOpenDialog(this.shared.frame);
		if (result != JFileChooser.APPROVE_OPTION) 
		{
			return;
		}
				
		File[] files = fileChooser.getSelectedFiles();
		
		// copy our files and store internally; needs to be processed later; ignore non .obj files
		
		for(int i = 0; i < files.length; i++)
		{
			String fn = files[i].getName();
			
			if(this.get_file_type(fn).equals("obj")) {
				this.shared.my_files.add(files[i]);
			}	
		}		
		System.out.println("All Files (" + this.shared.my_files.size() + "): " + this.shared.my_files);
	
		this.update_ui_listing();
		
	  }
	
/**
 * pack the files into a "multi-line" JLabel (uses html & br workaround to emulate "multi-line")
 */
	private void update_ui_listing()
	{		
		this.shared.list_label.setText("");
		
		String list_of_files = "<html>";
		for(int i = 0; i < this.shared.my_files.size(); i++)
		{
			File my_file = this.shared.my_files.get(i);
			list_of_files += my_file.getName() + "<br/>";
		}
		list_of_files += "</html>";
		
		this.shared.list_label.setText(list_of_files);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.openDirectory();
	}
}
