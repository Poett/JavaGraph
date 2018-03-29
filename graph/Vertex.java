package graph;

public class Vertex implements Comparable<Vertex>{

	private String name;
	private String courseID;
	public boolean visited;
	public int pre;
	public int post;
	
	Vertex(String courseID, String name)
	{
		this.courseID = courseID;
		this.name = name;
		visited = false;
	}
	
	
	public String getName() {return name;}
	public String getID() {return courseID;}
	
	public int compareTo(Vertex rhs) {
		return this.courseID.compareTo(rhs.courseID);
	}
	
	public String toString() 
	{
		return courseID + " " + name;
	}
	


}
