package pkg_main;

public class EditorUtility {

	public EditorUtility() {
	}

	public static String get_file_type(String filename)
	{
		String type = "";
		
		int[] c_result = filename.chars().toArray();

		boolean capture = false;
		for(int i = 0; i < c_result.length; i++)
		{
			if(capture) {
				type += (char) c_result[i];
			}
			
			if(c_result[i] == '.')
			{
				capture = true;
			}
		}
		
		return type;
	}
}
