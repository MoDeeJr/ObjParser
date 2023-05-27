/**
 * @author dinmo
 *
 */

package pkg_main;

import java.util.ArrayList;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

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
		
		// use "modern", but default ui theme/appearance
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

		// init
		
		this.shared = new EditorShared();
		
		this.shared.frame = new JFrame();//creating instance of JFrame  
		this.shared.my_files = new ArrayList<File>();
		this.shared.list_label = new JLabel("no files selected."); // shared "multi-line" label
		this.shared.list_label.setBounds(0,0, this.canvas_width, this.canvas_height);
		this.shared.list_label.setVerticalAlignment(JLabel.TOP);
		this.shared.list_label.setFont(new Font("Serif", Font.TRUETYPE_FONT, 14));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Serif", Font.TRUETYPE_FONT, 14));
		menuBar.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		// add files button
		
		JButton btn_add_files = new JButton();//creating instance of JButton  
		OpenFilesAction add_files_action = new OpenFilesAction(this.shared); // associate action to this button
		btn_add_files.setAction(add_files_action);
		btn_add_files.setText("add files");
		btn_add_files.setBounds((int) (this.canvas_width * 0.25 - 50.0), (int) (this.canvas_height - this.canvas_height * 0.15) ,100, 25);//x axis, y axis, width, height  

		// generate button
		
		JButton btn_generate = new JButton();//creating instance of JButton  
		GenerateAction g_action = new GenerateAction(this.shared);
		btn_generate.setAction(g_action);
		btn_generate.setText("generate");
		btn_generate.setBounds(this.canvas_width / 2 - 50, (int) (this.canvas_height - this.canvas_height * 0.15) ,100, 25);//x axis, y axis, width, height  
		
		// clear items button
		
		JButton btn_reset = new JButton();//creating instance of JButton  
		ClearQueueAction clear_action = new ClearQueueAction(this.shared);
		btn_reset.setAction(clear_action);
		btn_reset.setText("clear queue");
		btn_reset.setBounds((int) (this.canvas_width * 0.75 - 75.0), (int) (this.canvas_height - this.canvas_height * 0.15) ,150, 25);//x axis, y axis, width, height  
				
		// add content to the canvas & menu bar
		
		//this.shared.frame.add(btn_generate);
		//this.shared.frame.add(btn_reset);
		//this.shared.frame.add(btn_add_files);
		this.shared.frame.add(this.shared.list_label);
		
		fileMenu.add(btn_add_files);
		fileMenu.add(btn_generate);
		fileMenu.add(btn_reset);

		// configure frame and display
		
		this.shared.frame.setJMenuBar(menuBar); // bind the menu bar to this canvas
		this.shared.frame.setTitle(".Obj to Bullet Physics .vpos Generator");
		this.shared.frame.setSize(this.canvas_width,this.canvas_height);
		this.shared.frame.setLayout(null);//using no layout managers  
		this.shared.frame.setVisible(true);//make the frame visible  
		this.shared.frame.setLocationRelativeTo(null); // c

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
