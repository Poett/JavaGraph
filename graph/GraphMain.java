package graph;

import java.util.ArrayList;
import java.util.Collections;

public class GraphMain {

	public static void main(String[] args) {

		Loader l = new Loader("./graph/courses.txt");
		
		Graph G = l.getGraph();
		
		
		System.out.println(G);
		
		ArrayList<Vertex> topoG = G.topoLogicalOrder();

		System.out.print("{");
		for(Vertex v : topoG) 
		{
			System.out.print(v.getID() + ", ");
		}
		System.out.print("}");
	}

}
