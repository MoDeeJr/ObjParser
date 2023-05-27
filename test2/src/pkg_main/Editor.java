/**
 * @author dinmo
 *
 */

package pkg_main;

import java.util.ArrayList;
import javax.swing.*;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

class EditorShared
{
	JFrame frame; // master UI Canvas
	ArrayList<File> my_files; // list of files the user would like to process
	JLabel list_label; // shared "message" string
	
	public EditorShared()
	{
		
	}
}

class Editor
{
	
	int canvas_width = 800;
	int canvas_height = 600;
	
	EditorShared shared;
	
	public Editor() // constructor
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.shared = new EditorShared();
		
		this.shared.frame = new JFrame();//creating instance of JFrame  
		this.shared.my_files = new ArrayList<File>();
		this.shared.list_label = new JLabel("no files selected.");
		this.shared.list_label.setBounds(0,0, this.canvas_width, this.canvas_height);
		this.shared.list_label.setVerticalAlignment(JLabel.TOP);
		this.shared.list_label.setFont(new Font("Serif", Font.TRUETYPE_FONT, 14));
		
		// add files button
		
		JButton add_files_button = new JButton();//creating instance of JButton  
		OpenFilesAction add_files_action = new OpenFilesAction(this.shared);
		add_files_button.setAction(add_files_action);
		add_files_button.setText("add files");
		add_files_button.setBounds((int) (this.canvas_width * 0.25 - 50.0), (int) (this.canvas_height - this.canvas_height * 0.15) ,100, 25);//x axis, y axis, width, height  

		// generate button
		
		JButton b = new JButton();//creating instance of JButton  
		GenerateAction g_action = new GenerateAction(this.shared);
		b.setAction(g_action);
		b.setText("generate");
		b.setBounds(this.canvas_width / 2 - 50, (int) (this.canvas_height - this.canvas_height * 0.15) ,100, 25);//x axis, y axis, width, height  
		
		// clear items button
		
		JButton reset_queue_button = new JButton();//creating instance of JButton  
		ClearQueueAction clear_action = new ClearQueueAction(this.shared);
		reset_queue_button.setAction(clear_action);
		reset_queue_button.setText("clear queue");
		reset_queue_button.setBounds((int) (this.canvas_width * 0.75 - 75.0), (int) (this.canvas_height - this.canvas_height * 0.15) ,150, 25);//x axis, y axis, width, height  
				
		// add content
		
		this.shared.frame.add(b);
		this.shared.frame.add(reset_queue_button);
		this.shared.frame.add(add_files_button);
		this.shared.frame.add(this.shared.list_label);

		// configure frame
		
		this.shared.frame.setTitle("Vpos Generator");
		this.shared.frame.setSize(this.canvas_width,this.canvas_height);//400 width and 500 height  
		this.shared.frame.setLayout(null);//using no layout managers  
		this.shared.frame.setVisible(true);//making the frame visible  
		this.shared.frame.setLocationRelativeTo(null); // this method display the JFrame to center position of a screen

		this.shared.frame.addWindowListener(new java.awt.event.WindowAdapter() {
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
