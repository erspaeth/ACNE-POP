package CS415;
//
//public class FileManager {
//
//	private static FileManager instance = null;
//
//	public static FileManager getInstance() {
//		if (instance == null) {
//			instance = new FileManager();
//		}
//		return instance;
//	}
//
//	protected FileManager() {
//		// Exists only to defeat instantiation.
//	}
//	
//	
//	
//}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
	
public class FileManager {

	private int startArray[][];
	private int currentArray[][];
	private int generation;
	private String ruleset;
	
	private static FileManager instance = null;

	public static FileManager getInstance() {
		if (instance == null) {
			instance = new FileManager();
		}
		return instance;
	}

	protected FileManager() {
		// Exists only to defeat instantiation.
	}
	
	public String getRuleset(){
		return ruleset;
	}
	
	public int getGeneration(){
		return generation;
	}
	
	public int[][] getCurrentArray(){
		return currentArray;
	}
	
	public int[][] getStartArray(){
		return startArray;
	}
	
	
	public void saveXML(Simulation simulationToSave, String fileName){
		
		try {

			Element simulation = new Element("simulation");
			Document doc = new Document(simulation);
			//doc.setRootElement(simulation);

			Element options = new Element("options");
			options.addContent(new Element("ruleset").setText(simulationToSave.getRuleSet().getClass().getName()));

			doc.getRootElement().addContent(options);

			Element grid1 = new Element("grid");
			grid1.setAttribute(new Attribute("name", "start"));
			
			String rowContent = "";
			
			startArray = simulationToSave.getIntialStateAsArray();
			
			for(int i = 0; i < startArray.length; i++){
				rowContent = "";
				
			    for(int j = 0; j < startArray[i].length; j++){
			        rowContent += startArray[i][j];
			    }
			    
			    grid1.addContent(new Element("row").setText(rowContent));
			}

			doc.getRootElement().addContent(grid1);
			
			currentArray = simulationToSave.getCurrentStateAsArray();
			
			Element grid2 = new Element("grid");
			grid2.setAttribute(new Attribute("name", "current"));
			
			rowContent = "";
			
			for(int i = 0; i < currentArray.length; i++){
				rowContent = "";
				
			    for(int j = 0; j < currentArray[i].length; j++){
			        rowContent += currentArray[i][j];
			    }
			    
			    grid2.addContent(new Element("row").setText(rowContent));
			}

			doc.getRootElement().addContent(grid2);
			
			Element generationCount = new Element("Generation");
			generationCount.setText(""+simulationToSave.getGeneration());
			doc.getRootElement().addContent(generationCount);
			
			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("./src/SaveFiles/" + fileName));

			System.out.println("File Saved!");
		  } catch (IOException io) {
			System.out.println(io.getMessage());
		  }
	}
	
	public Simulation loadXML(String fileName){
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("./src/SaveFiles/" + fileName);
		startArray = new int[50][50];
		currentArray = new int[50][50];
  
		try {

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List mainElements = rootNode.getChildren("grid");

			generation = Integer.parseInt(rootNode.getChild("Generation").getValue());
			ruleset = rootNode.getChild("options").getChild("ruleset").getValue();

			for (int i = 0; i < mainElements.size(); i++) {

				Element node = (Element) mainElements.get(i);
				List subElements = node.getChildren("row");

				if(node.getAttributeValue("name").compareTo("start") == 0){
					for(int j = 0; j < subElements.size(); j++) {
						Element rowNode = (Element) subElements.get(j);
						String rowText = rowNode.getValue();
						for (int l = 0; l < rowText.length(); l++){
							startArray[j][l] = rowText.charAt(l) - '0';
						}
					}
	
				}
				else if(node.getAttributeValue("name").compareTo("current") == 0){
					for(int j = 0; j < subElements.size(); j++) {
						Element rowNode = (Element) subElements.get(j);
						String rowText = rowNode.getValue();
						for (int l = 0; l < rowText.length(); l++){
							currentArray[j][l] = rowText.charAt(l) - '0';
						}
					}
				}
				else{
					System.out.println("Unknown Grid Found -- Ignoring it.");
				}
			}
			
		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
		
		RuleSet rules = null;;
		try {
			Class c = Class.forName(ruleset);
			rules = (RuleSet)c.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Simulation(rules, startArray, currentArray, generation);
	}
	
	/*
	public static void main(String[] args) {
		
		XMLParser testParser = new XMLParser();
		testParser.loadXML("saved_data.xml");
		System.out.println(testParser.getRuleset());
		String className = testParser.getClass().getSimpleName();
		System.out.println(className);
	}
	*/
}

