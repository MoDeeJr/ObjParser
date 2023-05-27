/**
 * 
 */
package pkg_main;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import java.io.File; // Import the File class
import java.io.FileNotFoundException;
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * @author dinmo
 *
 */
class GenerateAction extends AbstractAction {

	ArrayList<File> ref_data;
	JFrame ref_frame;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenerateAction(ArrayList<File> in_data, JFrame parent_frame) {
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

	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.ref_data.size() == 0) {
			System.out.println("No files to process.");
			return;
		}

		for (int i = 0; i < this.ref_data.size(); i++) {
			File ref_file = this.ref_data.get(i);

			System.out.println("Processing file: " + ref_file.getPath());

			this.read_file(ref_file);
		}

	}

	private void read_file(File in_file) {
		
		File myObj = in_file;

		ArrayList<Vec3> src_vertex_positions = new ArrayList<Vec3>();
		ArrayList<Vec3> vertex_positions = new ArrayList<Vec3>();

		// read file, and generate source, unique vertex positions
		
		Scanner myReader;
		try {
			myReader = new Scanner(myObj);

			while (myReader.hasNextLine()) {

				String data = myReader.nextLine();

				String[] data1 = data.split(" "); // v x y z

				if (data1[0].equals("v")) {
					Vec3 new_vec = new Vec3();
					new_vec.x = Float.parseFloat(data1[1]);
					new_vec.y = Float.parseFloat(data1[2]);
					new_vec.z = Float.parseFloat(data1[3]);

					// System.out.println("Got vertex position: " + new_vec);
					src_vertex_positions.add(new_vec);

				}

				// System.out.println(my_line);

			}
			
			myReader.close();
			
			// read file again, but process "faces" to generate raw set of all vertex positions
			Scanner myReader2;

			myReader2 = new Scanner(myObj);

			while (myReader2.hasNextLine()) {

				String data = myReader2.nextLine();

				String[] data1 = data.split(" "); // f x/x/x x/x/x x/x/x

				if (data1[0].equals("f")) // f v/vt/n v/vt/n f/vt/n
				{
					String[] col1 = data1[1].split("/");
					String[] col2 = data1[2].split("/");
					String[] col3 = data1[3].split("/");

					int index = Integer.parseInt(col1[0]) - 1;
					Vec3 reference_vector = src_vertex_positions.get(index);
					vertex_positions.add(reference_vector);

					index = Integer.parseInt(col2[0]) - 1;
					Vec3 ref_vec_2 = src_vertex_positions.get(index);
					vertex_positions.add(ref_vec_2);

					index = Integer.parseInt(col3[0]) - 1;
					Vec3 ref_vec_3 = src_vertex_positions.get(index);
					vertex_positions.add(ref_vec_3);
				}
								
				// System.out.println(my_line);

				//System.out.println("vpos size: " + vertex_positions.size());

			}
			
			myReader2.close();
			
			// generate "out" file based on processed data
			
			String out_file_name = this.trim_file_extension(in_file.getName()) + ".vpos";
			System.out.println("Out file: " + out_file_name);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
