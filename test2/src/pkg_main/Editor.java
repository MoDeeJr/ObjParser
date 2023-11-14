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

public class Editor
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
		menuBar.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JMenu fileMenu = new JMenu("FILE");
		fileMenu.setFont(new Font("Serif", Font.TRUETYPE_FONT, 14));
		menuBar.add(fileMenu);

		// add files button: allows user to choose files
		
		JButton btn_add_files = new JButton();//creating instance of JButton  
		OpenFilesAction add_files_action = new OpenFilesAction(this.shared); // associate action to this button
		btn_add_files.setAction(add_files_action);
		btn_add_files.setText("add files");

		// generate button: processes the user selection of files
		
		JButton btn_generate = new JButton();//creating instance of JButton  
		GenerateAction g_action = new GenerateAction(this.shared);
		btn_generate.setAction(g_action);
		btn_generate.setText("generate");
		
		// clear items button: removes any selected files
		
		JButton btn_reset = new JButton();//creating instance of JButton  
		ClearQueueAction clear_action = new ClearQueueAction(this.shared);
		btn_reset.setAction(clear_action);
		btn_reset.setText("clear queue");
				
		// add content to the canvas & menu bar

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
