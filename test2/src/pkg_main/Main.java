package pkg_main;

//import pkg_main.Editor; // don't need import, as classes exist in same package

public class Main {
	
	public static Editor e; 
	
	// Program Entry Point
	public static void main(String[] args) {
		
		System.out.println("Editor constructed.");

		e = new Editor();
									
	}
}
