package pkg_main;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

class OpenFilesAction extends AbstractAction
{
	ArrayList<File> ref_data;
	JFrame ref_frame;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OpenFilesAction(ArrayList<File> in_data, JFrame parent_frame)
	{
		this.ref_data = in_data;
		this.ref_frame = parent_frame;
	}

	
	public void openDirectory() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setMultiSelectionEnabled(true);
		int result = fileChooser.showOpenDialog(this.ref_frame);
		if (result != JFileChooser.APPROVE_OPTION) 
		{
			return;
		}
		
		File[] files = fileChooser.getSelectedFiles();
	
		for(int i = 0; i < files.length; i++)
		{
			this.ref_data.add(files[i]); // register our chosen file
			
			System.out.println("Selected file: " + files[i].getPath());

			//System.out.println(path.substring(files[i].getPath().lastIndexOf("\\")+1));
		}
		
		System.out.println("All Files (" + this.ref_data.size() + "): " + this.ref_data);
	  }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.openDirectory();
	}
}
