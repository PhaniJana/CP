import java.io.*;
import java.util.*;

class MessageRoute {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read first line safely
        String line = br.readLine();
        while(line != null && line.trim().isEmpty()){
            line = br.readLine();
        }

        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> edges = new ArrayList<>();
        for(int i=0;i<=n;i++) edges.add(new ArrayList<>());

        DSU dsu = new DSU(n+1);

        // Read edges safely
        for(int i=0;i<m;i++){
            line = br.readLine();
            while(line != null && line.trim().isEmpty()){
                line = br.readLine();
            }

            st = new StringTokenizer(line);
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edges.get(u).add(v);
            edges.get(v).add(u);
            dsu.union(u,v);
        }

        // DSU connectivity check
        if(dsu.find(1) != dsu.find(n)){
            System.out.println("IMPOSSIBLE");
            return;
        }

        // BFS
        Queue<Integer> q = new LinkedList<>();
        int[] parent = new int[n+1];
        boolean[] vis = new boolean[n+1];

        vis[1] = true;
        parent[1] = 1;
        q.add(1);

        while(!q.isEmpty()){
            int node = q.poll();
            if(node == n) break;

            for(int nei : edges.get(node)){
                if(!vis[nei]){
                    vis[nei] = true;
                    parent[nei] = node;
                    q.add(nei);
                }
            }
        }

        // Extra safety (even though DSU checked)
        if(!vis[n]){
            System.out.println("IMPOSSIBLE");
            return;
        }

        // Build path
        List<Integer> path = new ArrayList<>();
        int node = n;

        while(node != parent[node]){
            path.add(node);
            node = parent[node];
        }
        path.add(1);

        Collections.reverse(path);

        System.out.println(path.size());
        for(int x : path){
            System.out.print(x + " ");
        }
    }
}

class DSU {
    int[] parent, size;

    DSU(int n){
        parent = new int[n];
        size = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
            size[i] = 1;
        }
    }

    int find(int u){
        if(parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    void union(int u, int v){
        int pu = find(u);
        int pv = find(v);
        if(pu == pv) return;

        if(size[pu] > size[pv]){
            parent[pv] = pu;
            size[pu] += size[pv];
        } else {
            parent[pu] = pv;
            size[pv] += size[pu];
        }
    }
}