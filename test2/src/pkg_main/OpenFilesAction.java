package pkg_main;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

class OpenFilesAction extends AbstractAction
{
	ArrayList<String> ref_data;
	JFrame ref_frame;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OpenFilesAction(ArrayList<String> in_data, JFrame parent_frame)
	{
		this.ref_data = in_data;
		this.ref_frame = parent_frame;
	}
	
	private String trim_file_extension(String filename)
	{
		String result = "";
		
		int[] c_result = filename.chars().toArray();
		
		for(int i = 0; i < filename.length(); i++)
		{
			if((char) c_result[i] != '.') 
			{
				result += (char) c_result[i]; 
			}
			else
			{
				break;
			}
		}
		
		return result;

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
		System.out.println(files.length);
	
		for(int i = 0; i < files.length; i++)
		{
			this.ref_data.add(files[i].getPath());
			
			String path = files[i].getPath();
			String filename = files[i].getName();
			
			System.out.println(path);
			System.out.println(filename);
			System.out.println(this.trim_file_extension(filename));

			//System.out.println(path.substring(files[i].getPath().lastIndexOf("\\")+1));
		}
		
		System.out.println(this.ref_data);
	  }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.openDirectory();
	}
}
