package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

	private ArrayList<Vertex> vertices;
	private Integer[][] edges;
	private int clock;

	Graph(ArrayList<Vertex> userGraph)
	{
		clock = 0;
		
		vertices = userGraph;
		
		for(Vertex v : vertices) 
		{
			v.visited = false;
		}
		
		edges = new Integer[userGraph.size()][userGraph.size()];
	}
	
	
	
	
	
	
	
	
	public ArrayList<Vertex> getVertices(){return vertices;}
	
	public Vertex getVertexByID(String courseID) 
	{
		Vertex x = null;
		for(Vertex v : vertices) 
		{
			if(v.getID().equals(courseID)) {x = v; break;}
		}
		
		return x;
	}
	
	public void setDirectedEdge(Vertex start, Vertex end, int weight) 
	{
		//Get the vertices index or catch if could not get index
		try {
			int vI = vertices.indexOf(start);
			int uI = vertices.indexOf(end);
			edges[vI][uI] = weight;
		} 
		catch (Exception e) {
			return;
		}
		

	}
	
	public ArrayList<Vertex> topoLogicalOrder()
	{
		this.dfs();
		
		return topoMergeSort(vertices);
	}
	
	private ArrayList<Vertex> merge(ArrayList<Vertex> left, ArrayList<Vertex> right) 
	{
		ArrayList<Vertex> toReturn = new ArrayList<Vertex>();
		
		int i = 0;
		int j = 0;
		
		//Merge in descending order (greatest gets put inside)
		while(i < left.size() && j < right.size()) 
		{
			//Case - left index is greater
			if(left.get(i).post > right.get(j).post) {toReturn.add(left.get(i)); i++;}
			//Case - left is equal or less than right
			else {toReturn.add(right.get(j)); j++;}
		}
		while(i < left.size()) {toReturn.add(left.get(i)); i++;}
		while(j < right.size()) {toReturn.add(right.get(j)); j++;}
		
		return toReturn;
	}
	private ArrayList<Vertex> topoMergeSort(ArrayList<Vertex> vertexArray) 
	{
		if(vertexArray.size() <= 1) {return vertexArray;}
		
		ArrayList<Vertex> left = new ArrayList<Vertex>(vertexArray.subList(0, (vertexArray.size()/2)));
		ArrayList<Vertex> right = new ArrayList<Vertex>(vertexArray.subList((vertexArray.size()/2),vertexArray.size()));
		
		left = topoMergeSort(left);
		right = topoMergeSort(right);
		
		return(merge(left, right));
	}
	
	public void setUndirectedEdge(Vertex v, Vertex u, int weight) 
	{
		setDirectedEdge(v, u, weight);
		setDirectedEdge(u, v, weight);
	}
	
//	public void removeVertex(Vertex x) 
//	{
//		if(!vertices.contains(x))
//		{
//			return;
//		}
//		
//		
//		
//		Integer newEdges = 
//		
//	}

	
	
	public ArrayList<Vertex> getEdges(Vertex v)
	{
		try {
			ArrayList<Vertex> edgesOf = new ArrayList<Vertex>();
			int i = vertices.indexOf(v);
			for(int j = 0; j < vertices.size(); j++) 
			{
				if(edges[i][j] != null) { edgesOf.add(vertices.get(j)); }//if there's a set weight for the edge i->j, then send j to edgesOf}
			}
			
			
			return edgesOf;
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	
	public ArrayList<Vertex> getBackEdges(Vertex v)
	{
		try {
			ArrayList<Vertex> edgesOf = new ArrayList<Vertex>();
			int i = vertices.indexOf(v);
			for(int j = 0; j < vertices.size(); j++) 
			{
				if(edges[j][i] != null) { edgesOf.add(vertices.get(j)); }//if there's a set weight for the edge i->j, then send j to edgesOf}
			}
			
			
			return edgesOf;
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	
	
	
	
	public void dfs() 
	{
		clock = 0; 
		
		for(Vertex v : vertices) 
		{
			v.visited = false;
		}
		
		for(Vertex v : vertices) 
		{
			if(!v.visited) {explore(v);}
		}
	}
	
	public void bfs(Vertex s) 
	{
		HashMap<Vertex, Integer> dist = new HashMap<Vertex, Integer>();
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		for(Vertex v : vertices) 
		{
			dist.put(v, null);
		}
		
		//Sets distance to itself as 0
		dist.put(s, 0);
		
		queue.add(s);
		
		//While the queue isn't empty
		while(!queue.isEmpty()) 
		{
			Vertex u = queue.removeFirst(); //Eject from queue
			for (Vertex e : getEdges(u))  //For each edge in the queue
			{
				if(dist.get(e) == null) //if the edge's distance has not been set
				{
					queue.add(e); //add it to queue
					dist.put(e, dist.get(u) + 1); //set distance to a level below U
				}
			}
		}
		
	}
	
	private void explore(Vertex v) 
	{
		v.visited = true;
		v.pre = clock++;
		for(Vertex u : getEdges(v)) 
		{
			if(!u.visited) {explore(u);}
		}
		v.post = clock++;
	}
	
	
	public Graph reverseGraph() 
	{
		
		Graph R = new Graph(vertices);
		
		//For each of the vertices
		for(Vertex v : vertices) 
		{
			//For each of v's edges
			for(Vertex e : this.getEdges(v)) 
			{
				//Add an edge to reversed of e -> v
				R.setDirectedEdge(e, v, 1);
			}
		}
		return R;
	}
	
	
	
	
	public String toString() 
	{
		//Go through each vertices and show its edges
		String toReturn = "";
		
		
		for(Vertex v : vertices) 
		{
			toReturn = toReturn + v + "(";
			for(Vertex edge : this.getBackEdges(v)) 
			{
				toReturn = toReturn + edge.getID() + ", ";
			}
			toReturn = toReturn + ")\n";
			
		}
		
		
		
		
		
		return toReturn;
	}
	
	
	
}
