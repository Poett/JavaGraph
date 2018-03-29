package graph;

import java.util.ArrayList;
import java.util.Collections;

public class GraphMain {

	public static void main(String[] args) {

		Loader l = new Loader("./graph/courses.txt");
		
		Graph G = l.getGraph();
		
		
		System.out.println(G);
		
		ArrayList<Vertex> topoG = G.reverseGraph().topoLogicalOrder();
		Collections.reverse(topoG);
		
		System.out.println(topoG);
	}

}
