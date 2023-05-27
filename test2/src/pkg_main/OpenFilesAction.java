package pkg_main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

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

	public void openDirectory() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setMultiSelectionEnabled(true);
		int result = fileChooser.showOpenDialog(this.shared.frame);
		if (result != JFileChooser.APPROVE_OPTION) 
		{
			return;
		}
		
		// get chosen files using FileChooser (Windows browse)
		
		File[] files = fileChooser.getSelectedFiles();

		// display the files into a "multi-line" JLabel (uses <html> & <br> workaround to emulate "multi-line")
		
		this.shared.list_label.setText("");
		
		String new_list = "<html>";
		for(int i = 0; i < files.length; i++)
		{
			new_list += files[i].getName() + "<br/>";
		}
		new_list += "</html>";
		
		this.shared.list_label.setText(new_list);
		
		// register files internally (they will be processed later)
		for(int i = 0; i < files.length; i++)
		{
			this.shared.my_files.add(files[i]); // register our chosen file
			
			System.out.println("Selected file: " + files[i].getPath());
		}
		
		System.out.println("All Files (" + this.shared.my_files.size() + "): " + this.shared.my_files);
	  }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.openDirectory();
	}
}
