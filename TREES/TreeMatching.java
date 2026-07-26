import java.io.*;
import java.util.*;

public class TreeMatching {
    static List<List<Integer>> edges;
    static long M = (long)1e9 + 7;
    static long[][] dp;
    private static void dfs(int u,int p){
            
        for(int v : edges.get(u)){
            if(v==p) continue;
            dfs(v,u);
            dp[u][1] += dp[v][0];
        }
        for(int v : edges.get(u)){
            if(v==p) continue;
            dp[u][0] = Math.max(dp[u][0] , dp[v][1] + dp[u][1] - dp[v][0] + 1);
        }
        
    } 
    public static void solve() throws IOException{
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        edges = new ArrayList<>();
        for(int i=0;i<=n;i++) edges.add(new ArrayList<>());
        for(int i=0;i<n-1;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            edges.get(u).add(v);
            edges.get(v).add(u);
        }
        dp = new long[n+1][2];

        dfs(1,-1);
        System.out.print(dp[1][0]);
    }
    public static void main(String[] args) throws Exception {
        
        new Thread(null, () -> {
            try {
                solve();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "1", 1 << 26).start();

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
