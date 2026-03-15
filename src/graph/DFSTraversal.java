package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DFSTraversal {
    public static void main(String[] args) {
        int V = 5;
        List<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
        adj[0].addAll(Arrays.asList(1, 2));
        adj[1].addAll(Arrays.asList(0, 3));
        adj[2].addAll(Arrays.asList(0, 4));
        adj[3].add(1);
        adj[4].add(2);

        boolean[] visited = new boolean[V];
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < V; i++){ // this will ensure disconnect component as well
            if(!visited[i]){
                dfsTraversal(i, adj, visited, result);
            }
        }
        System.out.print("DFSTraversal-->" + result);
    }

    private static void dfsTraversal(int v, List<Integer>[] adj, boolean[] visited, List<Integer> res) {
        visited[v] = true;
        res.add(v);
        for (int node : adj[v]) {
            if (!visited[node]) {
                dfsTraversal(node, adj, visited, res);
            }
        }
    }
}
