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
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
/**
 * @author dinmo
 *
 */
class GenerateAction extends AbstractAction {
	EditorShared shared;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenerateAction(EditorShared in_shared) {
		this.shared = in_shared;
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

		// if no files
		
		if (this.shared.my_files.size() == 0) {
			
			this.shared.list_label.setText("no files to process. select files first.");
			
			System.out.println("No files to process.");
			
			return;
		}

		// for each file, process the file and generate required output
		
		for (int i = 0; i < this.shared.my_files.size(); i++) {
			File ref_file = this.shared.my_files.get(i);

			System.out.println("Processing file: " + ref_file.getPath());

			this.process_file(ref_file);
		}
		
		// files processed
		
		String result = "";
		result += "<html>";
		
		for(int i = 0; i < this.shared.my_files.size(); i++)
		{
			File my_file = this.shared.my_files.get(i);
			
			result += "Processed " + my_file.getName() + ", output generated at " + this.trim_file_extension(my_file.getName()) + ".vpos";
			result += "<br/>";
		}
		
		result += "</html>";
		
		this.shared.list_label.setText(result);

	}

	/**
	 * 
	 * @param in_file FileChooser must generate the input files from user browse and selection
	 */
	private void process_file(File in_file) {
		
		File myObj = in_file;

		ArrayList<Vec3> src_vertex_positions = new ArrayList<Vec3>();
		ArrayList<Vec3> vertex_positions = new ArrayList<Vec3>();

		// read file, and generate source, unique vertex positions
		
		try {
			Scanner myReader;

			myReader = new Scanner(myObj);

			while (myReader.hasNextLine()) {

				String data = myReader.nextLine();

				String[] data1 = data.split(" "); // v x y z

				if (data1[0].equals("v")) {
					Vec3 new_vec = new Vec3();
					new_vec.x = Float.parseFloat(data1[1]);
					new_vec.y = Float.parseFloat(data1[2]);
					new_vec.z = Float.parseFloat(data1[3]);

					src_vertex_positions.add(new_vec);
				}
			}
			
			myReader.close();
			
			// read file again, but process "faces" to generate raw set of all vertex positions
			
			Scanner myReader2;

			myReader2 = new Scanner(myObj);

			while (myReader2.hasNextLine()) {

				String data = myReader2.nextLine();

				String[] data1 = data.split(" "); // original str:  f v/vt/n v/vt/n v/vt/n

				if (data1[0].equals("f"))
				{
					String[] col1 = data1[1].split("/"); // v/vt/n
					String[] col2 = data1[2].split("/");
					String[] col3 = data1[3].split("/");

					// note: subtract 1, as .obj stores index reference starting from 1, not 0
					
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
			}
			
			myReader2.close();
			
			// generate "out" file based on processed data
			
			String out_file_name = this.trim_file_extension(in_file.getName()) + ".vpos";
			System.out.println("Out file: " + out_file_name);
			
			PrintWriter writer = new PrintWriter(out_file_name, "UTF-8");
			
			for(int i = 0; i < vertex_positions.size(); i++)
			{
				Vec3 my_value = vertex_positions.get(i);
				
				writer.write(Float.toString(my_value.x) + " " + Float.toString(my_value.y) + " " + Float.toString(my_value.z));
				writer.write("\n");
			}
			
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
