package CS415;

import java.io.File;
import java.util.LinkedList;

public class CAApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new SimControl(new Simulation());
		System.out.println("Your application is awesome, and runs perfectly!");
		String[] files = CAApplication.getAllLoadFiles();
		new CAMenuControl(getAllRules(), files);
		
	}
	
	public static RuleSet[] getAllRules() {
		
		return new RuleSet[]
						{
						new ConwaysGameOfLife(),
						};
		
	}
	
	public static String[] getAllLoadFiles() {
		
		File folder = new File("./src/SaveFiles/");
		File[] listOfFiles = folder.listFiles();
		LinkedList<String> returnList = new LinkedList<String>();
		String fileName;
		String[] fileNames;

	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile()) {
	    	     	  
		    	fileName = listOfFiles[i].getName();
		    	if(fileName.endsWith("xml")) {
		    		
		    		returnList.add(fileName);
		    	}
	    	}
	    }
	    
	    fileNames = new String[returnList.size()];
	    return returnList.toArray(fileNames);  
	}

}
