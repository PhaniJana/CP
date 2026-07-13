import java.io.*;
import java.util.*;

public class GameRoutes {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        long M = (long)1e9 + 7;
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++) graph.add(new ArrayList<>());
        int m = sc.nextInt();
        int[] indegree = new int[n+1];
        while(m-- > 0){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            indegree[v]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        
        for(int i=1;i<=n;i++){
            if(indegree[i] == 0) q.add(i);
        }
        while(!q.isEmpty()){
            int node = q.poll();
            if(indegree[node]==0) list.add(node);
            for(int nei : graph.get(node)){
                if(--indegree[nei] == 0) q.add(nei);
            }
        }
        long[] ways = new long[n+1];
        ways[1] = 1;
        for(int node : list){
            for(int nei : graph.get(node)){
                ways[nei] = (ways[nei] + ways[node])%M;
            }
        }
        System.out.print(ways[n]);
    }
}

class FastReader {
    BufferedReader br;
    StringTokenizer st;

    FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
