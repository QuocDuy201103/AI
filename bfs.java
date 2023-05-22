package TwoWaterJugProblem;

import java.util.*;

public class bfs {
    public static int MAX_JUG1 = 4, MAX_JUG2 = 3, GOAL = 2;
    // queue là một hàng đợi (queue) được sử dụng để lưu trữ các đỉnh (vertex) trong quá trình tìm kiếm đường đi. 
    public static Queue<Vertex> queue = new LinkedList<>();
    //visited để lưu trữ các đỉnh đã được thăm trong quá trình tìm kiếm
    //visited1 được sử dụng để lưu trữ các đỉnh đã được thêm vào hàng đợi ưu tiên 
    public static Set<Vertex> visited = new HashSet<>(), visited1 = new HashSet<>();
    
    public static void main(String[] args) {
        //đăt giá trị ban đầu cho bình 1, 2
        Vertex.setMaxJugsCapacity(MAX_JUG1, MAX_JUG2);
        Vertex initialVertex = new Vertex(new State(0, 0));
        //thêm đỉnh ban đầu vào hàng đợi và lưu trữ
        queue.add(initialVertex);      
        visited.add(initialVertex);
        visited1.add(initialVertex);
        int i = 0, k = 1;
        String s = visited.toString(), s1, s0 = "";
        //xuất đỉnh đầu
        System.out.println("Qua trinh thuc hien BFS:");
        System.out.print("-Lan: " + i + "; X: " + "; Open: " + queue + "; Closed: " + s + "\n");
        
        // vòng lặp while sẽ tiếp tục chạy cho đến khi hàng đợi queue rỗng. 
        //Với mỗi lần lặp, s1 sẽ lưu trữ giá trị trạng thái của đỉnh đầu tiên trong queue dưới dạng chuỗi. 
        //Sau đó, đỉnh này sẽ được lấy ra khỏi queue và gán vào biến currentVertex.
        while(!queue.isEmpty()) {
            s1 = "" + queue.peek().getState();
            Vertex currentVertex = queue.poll();
            //ktr bình 1 và 2 có phải goal không 
            //Nếu có, tức là đã tìm thấy đường đi tới đích, và tracePath() sẽ trả về đường đi từ trạng thái ban đầu đến trạng thái hiện tại của currentVertex. 
            //Đường đi này được in ra màn hình và lưu vào biến s0.				                                        
            if(currentVertex.getState().getJug1() == GOAL || currentVertex.getState().getJug2() == GOAL) {
                //Dòng này dùng để cập nhật chuỗi s0 để lưu trữ kết quả đường đi từ trạng thái ban đầu đến trạng thái đích. 
                s0 += k + "." + currentVertex.tracePath().toString() + "\n";
                k++;
                //cập nhật đỉnh dưới dạng chuỗi và lưu vào biến s
                s = visited1.toString();
                //kiểm tra tiếp nếu hàng đợi khác rỗng thì lấy đỉnh đầu trong hàng đợi lưu vào khi lưu trữ visited1 in ra màn hình
                if(!queue.isEmpty()) visited1.add(queue.peek());  
                i++;
                System.out.print("-Lan: " + i + "; X: " + s1 + "; Open: " + queue + "; Closed: " + s + "\n");
                // tiếp tục đến khi nào hàng đợi ưu tiên rỗng
                continue;
            }
            //khỏi tạo một danh sách các hàng động đổ nước
            ArrayList<Vertex> newVertices = new ArrayList<>();
            newVertices.add(currentVertex.full_jug1());
            newVertices.add(currentVertex.full_jug2());
            newVertices.add(currentVertex.empty_jug1()); 
            newVertices.add(currentVertex.empty_jug2());                        
            newVertices.add(currentVertex.pour_jug1_jug2());
            newVertices.add(currentVertex.pour_jug2_jug1());     
            //Nếu đỉnh mới đã được ghé thăm, không cần thêm nó vào hàng đợi và danh sách đã ghé thăm nữa. 
            //Nếu đỉnh mới đã được thăm nhưng lại không nằm trong đường đi của đỉnh hiện tại, 
            //thì nó cũng sẽ được thêm vào hàng đợi để xử lý tiếp.      
            for(Vertex newVertex : newVertices) {  
                if(!currentVertex.tracePath().getPath().contains(newVertex)) {
                    newVertex.setParent(currentVertex);
                    if(!visited.contains(newVertex)) {
                        queue.add(newVertex);                   
                        visited.add(newVertex);
                    }
                } 
            }
            //cập nhật đỉnh dưới dạng chuỗi và lưu vào biến s
            s = visited1.toString();
            //kiểm tra tiếp nếu hàng đợi khác rỗng thì lấy đỉnh đầu trong hàng đợi lưu vào khi lưu trữ visited1 in ra màn hình
            if(!queue.isEmpty()) visited1.add(queue.peek());  
            i++;
            System.out.print("-Lan: " + i + "; X: " + s1 + "; Open: " + queue + "; Closed: " + s + "\n");
        }
        
        System.out.println();
        System.out.println("ket qua tim duoc bang BFS:");
        System.out.print(s0);
    }        
}
