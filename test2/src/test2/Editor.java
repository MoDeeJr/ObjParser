/**
 * 
 */
package test2;

/**
 * @author dinmo
 *
 */
// Java built-in GUI lib
import javax.swing.JButton;
import javax.swing.JFrame;

class Editor
{
	public Editor() // constructor
	{
		JFrame f = new JFrame();//creating instance of JFrame  
		
		JButton b = new JButton("click");//creating instance of JButton  

		b.setBounds(0,0,100, 40);//x axis, y axis, width, height  

		f.add(b);
		f.setSize(800,600);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
	}
}
