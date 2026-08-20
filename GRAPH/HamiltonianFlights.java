import java.io.*;
import java.util.*;
public class HamiltonianFlights{
    static class FastReader {
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

    static final long M = 1_000_000_007L;
    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        FastReader fs = new FastReader();
        StringBuilder out = new StringBuilder();

        int T = 1;
        //T = fs.nextInt();

        while (T-- > 0) {
            solve(fs, out);
        }

        System.out.print(out);
    }
    static List<List<Integer>> graph;
    static int n;
    static long[][] dp;
    static long dfs(int u,int mask){
        if(mask + 1 == (1<<n)) return u==n-1 ? 1L : 0; 
        if(dp[u][mask]!=-1) return dp[u][mask];
        long cnt = 0;
        for(int v : graph.get(u)){
            if (v == n - 1 && mask != (1 << (n - 1)) - 1) continue;

            if(((1<<v) & mask)==0){
                int nMask = mask | 1<<v;
                cnt = (cnt + dfs(v,nMask)) % M;
            }
        }
        return dp[u][mask] = cnt;
    }
    static void solve(FastReader sc, StringBuilder out) throws Exception {
        n = sc.nextInt();
        int m = sc.nextInt();
        graph = new ArrayList<>();
        for(int i=0;i<=n;i++) graph.add(new ArrayList<>());
        for(int i=0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            u--;
            v--;
            graph.get(u).add(v);
        }
        dp = new long[n][1<<n];
        for(long[] arr : dp) Arrays.fill(arr,-1);
        long ans = dfs(0,1);
        out.append(ans);
    }
}
