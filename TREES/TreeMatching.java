import java.io.*;
import java.util.*;
public class TreeMatching {
    private static void solve(int node,int par,int[][] dp,List<List<Integer>> edges) {
        
        for(int child : edges.get(node)){
            if(child == par) continue;
            solve(child,node,dp,edges);
        }
        int sum=0;
        for(int child : edges.get(node)){
            if(child == par) continue;
            sum+=Math.max(dp[child][0],dp[child][1]);
        }
        dp[node][0] = sum;
        for(int child : edges.get(node)){
            if(child == par) continue;
            dp[node][1] = Math.max(dp[node][1] , sum - Math.max(dp[child][0],dp[child][1])
            + dp[child][0] + 1);
        }
    }
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        List<List<Integer>> edges = new ArrayList<>();
        for(int i=0;i<=n;i++) edges.add(new ArrayList<>());
        for(int i=0;i<n-1;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            edges.get(u).add(v);
            edges.get(v).add(u);
        }
        int[][] dp = new int[n+1][2];
        solve(1,-1,dp,edges);
        System.out.print(Math.max(dp[1][0],dp[1][1]));
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
