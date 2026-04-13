import java.io.*;
import java.util.*;

class BuildingRoads{

    private static void dfs(int node, boolean[] vis, List<List<Integer>> edges){
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        vis[node] = true;

        while(!q.isEmpty()){
            int nod = q.poll();
            for(int nei : edges.get(nod)){
                if(!vis[nei]) {
                    q.add(nei);
                    vis[nei] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> edges = new ArrayList<>();
        for(int i=0;i<=n;i++) edges.add(new ArrayList<>());

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edges.get(u).add(v);
            edges.get(v).add(u);
        }

        boolean[] vis = new boolean[n+1];
        List<Integer> heads = new ArrayList<>();

        for(int i=1;i<=n;i++){
            if(!vis[i]){
                heads.add(i);
                dfs(i,vis,edges);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(heads.size()-1).append("\n");

        for(int i=0;i<heads.size()-1;i++){
            sb.append(heads.get(i)).append(" ").append(heads.get(i+1)).append("\n");
        }

        System.out.print(sb);
    }
}