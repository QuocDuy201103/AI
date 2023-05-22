package TwoWaterJugProblem;

import java.util.List;
import java.util.ArrayList;

public class Path<T> {
    private List<T> path;
    
    public Path(){
        path = new ArrayList<>();
    }
    
    public void addVertex(T vertex) {
        path.add(vertex);
    }
    
    public List<T> getPath(){
        return path;
    }
    
    public void setPath(List<T> path){
        this.path.addAll(path);
    }

    public void printPath(){
        for (int i = 0; i < path.size(); i++){
            System.out.print(path.get(i).toString() + " ");
        }
        
        System.out.println();
    }    
    
    public String toString ()
    {
    	String s = "";
    	for (int i = 0 ; i<path.size(); i++)
    	{
    		s += path.get(i).toString() + " ";
    	}
    	return s;
    }
}
