/**
 * @author dinmo
 *
 */

package pkg_main;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.io.File;


class ClearQueueAction extends AbstractAction
{
	ArrayList<String> ref_data;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public ClearQueueAction(ArrayList<String> in_string)
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
	
	public void openDirectory() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setMultiSelectionEnabled(true);
		int result = fileChooser.showOpenDialog(this.ref_frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File[] files = fileChooser.getSelectedFiles();
			System.out.println(files.length);
		
			for(int i = 0; i < files.length; i++)
			{
				this.ref_data.add(files[i].getPath());
			}
		}
		
		System.out.println(this.ref_data);
	  }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.openDirectory();
	}
}

class Editor
{
	
	int canvas_width = 800;
	int canvas_height = 600;
	
	JFrame frame;
	
	ArrayList<String> files;
	
	public Editor() // constructor
	{
		this.frame = new JFrame();//creating instance of JFrame  
		
		this.files = new ArrayList<String>();
		
		// add files button
		
		JButton add_files_button = new JButton();//creating instance of JButton  
		OpenFilesAction add_files_action = new OpenFilesAction(this.files, this.frame);
		add_files_button.setAction(add_files_action);
		add_files_button.setText("add files");
		add_files_button.setBounds((int) (this.canvas_width * 0.25 - 50.0), (int) (this.canvas_height - this.canvas_height * 0.15) ,100, 25);//x axis, y axis, width, height  

		// generate button
		
		JButton b = new JButton();//creating instance of JButton  
		GenerateAction g_action = new GenerateAction(this.files, this.frame);
		
		b.setAction(g_action);
		b.setText("generate");
		b.setBounds(this.canvas_width / 2 - 50, (int) (this.canvas_height - this.canvas_height * 0.15) ,100, 25);//x axis, y axis, width, height  
		
		// clear items button
		
		JButton reset_queue_button = new JButton();//creating instance of JButton  
		ClearQueueAction clear_action = new ClearQueueAction(this.files);
		
		reset_queue_button.setAction(clear_action);
		reset_queue_button.setText("clear queue");
		reset_queue_button.setBounds((int) (this.canvas_width * 0.75 - 75.0), (int) (this.canvas_height - this.canvas_height * 0.15) ,150, 25);//x axis, y axis, width, height  
		
		// add content
		
		this.frame.add(b);
		this.frame.add(reset_queue_button);
		this.frame.add(add_files_button);

		// configure frame
		
		this.frame.setTitle("Vpos Generator");
		this.frame.setSize(this.canvas_width,this.canvas_height);//400 width and 500 height  
		this.frame.setLayout(null);//using no layout managers  
		this.frame.setVisible(true);//making the frame visible  
		this.frame.setLocationRelativeTo(null); // this method display the JFrame to center position of a screen

		this.frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	/**
		    	 * 		        
		    	  if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure you want to close this window?", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    	 */
		    	System.exit(0);
		    }
		});

	}
}
