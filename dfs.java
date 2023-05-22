package TwoWaterJugProblem;

import java.util.*;

public class dfs {
    public static int MAX_JUG1 = 4, MAX_JUG2 = 3, GOAL = 2;
    public static Stack<Vertex> stack = new Stack<>();
    public static Set<Vertex> visited = new HashSet<>(), visited1 = new HashSet<>();
    
    public static void main(String[] args) {
        Vertex.setMaxJugsCapacity(MAX_JUG1, MAX_JUG2);
        Vertex initialVertex = new Vertex(new State(0, 0));
        stack.push(initialVertex);      
        visited.add(initialVertex);
        visited1.add(initialVertex);
        int i = 0, k = 1;
        String s = visited.toString(), s1, s0 = "";
        System.out.println("Qua trinh thuc hien giai thuat DFS:");
        System.out.print("-Lan lap: " + i + "; X: " + "; Open: " + stack + "; Closed: " + s + "\n");
        
        while(!stack.isEmpty()) {
            s1 = "" + stack.peek().getState();
            Vertex currentVertex = stack.pop();				                                        
            if(currentVertex.getState().getJug1() == GOAL || currentVertex.getState().getJug2() == GOAL) {
                s0 += k + "." + currentVertex.tracePath().toString() + "\n";
                k++;
                s = visited1.toString();
                if(!stack.isEmpty()) visited1.add(stack.peek());  
                i++;
                System.out.print("-Lan lap: " + i + "; X: " + s1 + "; Open: " + stack + "; Closed: " + s + "\n");
                continue;
            }                        
            ArrayList<Vertex> newVertices = new ArrayList<>();
            newVertices.add(currentVertex.full_jug1());
            newVertices.add(currentVertex.full_jug2());
            newVertices.add(currentVertex.empty_jug1()); 
            newVertices.add(currentVertex.empty_jug2());                        
            newVertices.add(currentVertex.pour_jug1_jug2());
            newVertices.add(currentVertex.pour_jug2_jug1());        
            for(Vertex newVertex : newVertices) {  
                if(!currentVertex.tracePath().getPath().contains(newVertex)) {
                    newVertex.setParent(currentVertex);
                    if(!visited.contains(newVertex)) {
                        stack.push(newVertex);                   
                        visited.add(newVertex);
                    }
                } 
            }
            s = visited1.toString();
            if(!stack.isEmpty()) visited1.add(stack.peek());  
            i++;
            System.out.print("-Lan lap: " + i + "; X: " + s1 + "; Open: " + stack + "; Closed: " + s + "\n");
        }
        
        System.out.println();
        System.out.println("Nhung duong di tim duoc bang giai thuat DFS:");
        System.out.print(s0);
    }        
}
