package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {

	private String fileName;
	
	Loader(String fileName)
	{
		this.fileName = fileName;
	}
	
	public Graph getGraph() 
	{
		Scanner scanner;
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		

		
		try {
		scanner = new Scanner(new File(fileName));
		
		//First loop to create each course vertex
		while(scanner.hasNext()) 
		{
			try {
			String line = scanner.nextLine();
			String record[] = line.split(" ", 3);
			String courseID = record[0] + " " + record[1];
			record = record[2].split("\\(");
			String name = record[0];
			vertices.add(new Vertex(courseID, name));
		
			}catch (Exception e) {
			}
		}
		
		Graph G = new Graph(vertices);
		
		
		scanner = new Scanner(new File(fileName));
		
		
		//Second loop to set prereqs as directed edges
		while(scanner.hasNext()) 
		{
			try {
			String line = scanner.nextLine();
			String record[] = line.split(" ", 3);
			String courseID = record[0] + " " + record[1];
			record = line.split("\\(");
			
			if(record.length == 2)
			{
				record = record[1].split("[,\\)]");
				for(String e : record) 
				{
					G.setDirectedEdge(G.getVertexByID(e.trim()), G.getVertexByID(courseID), 1);
				}
			}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		

		scanner.close();
		return G;
		}
		catch (FileNotFoundException e1) {

			return null;
		}
	}
}
