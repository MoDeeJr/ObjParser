package pkg_main;

//import test2.Editor; // don't need import, as classes exist in same package

public class Main {
	
	public static Editor e; 
	// Program Entry Point
	public static void main(String[] args) {
		
		e = new Editor();
		
		System.out.println("Editor constructed.");
		
							
	}
}
