package pkg_main;

//import test2.Editor; // don't need import, as classes exist in same package

public class Main {

	// Program Entry Point
	public static void main(String[] args) {
		
		System.out.println("Editor constructed.");
		
		Editor e = new Editor(); // spawns async lifetime GUI
		
		while(true)
		{
			if(e.files != null)
			{
				//System.out.println(e.files);
			}
		}
							
	}
}
