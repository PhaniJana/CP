import java.io.*;
import java.util.*;
public class CoinCollector{
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
 
        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
 
        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
 
        String nextLine() throws IOException {
            return br.readLine();
        }
 
        char nextChar() throws IOException {
            return next().charAt(0);
        }
 
        // -------- Arrays --------
        int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = nextInt();
            return arr;
        }
 
        long[] nextLongArray(int n) throws IOException {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) arr[i] = nextLong();
            return arr;
        }
 
        double[] nextDoubleArray(int n) throws IOException {
            double[] arr = new double[n];
            for (int i = 0; i < n; i++) arr[i] = nextDouble();
            return arr;
        }
 
        String[] nextStringArray(int n) throws IOException {
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) arr[i] = next();
            return arr;
        }
 
        char[] nextCharArray(int n) throws IOException {
            return next().toCharArray(); // assumes no spaces
        }
    }
 
    static final long M = 1_000_000_007L;
    static final long INF = Long.MAX_VALUE;
    static List<List<Integer>> graph1;
    static List<List<Integer>> graph2;
    static List<List<Integer>> graph3;
    static boolean vis[];
    static int[] comps;
    static long[] coins;
    static Deque<Integer> s;
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
 
    static void dfs1(int u){
        vis[u] = true;
        for(int v : graph1.get(u)){
            if(!vis[v]) dfs1(v);
        }
        s.addLast(u);
    }
    static long dfs2(int u,int num){
        comps[u] = num;
        vis[u] = true;
        long scr = coins[u];
        for(int v : graph2.get(u)){
            if(!vis[v]) scr += dfs2(v,num);
        }
        return scr;
    }
    static void solve(FastReader sc, StringBuilder out) throws Exception {
        int n = sc.nextInt();
        int m = sc.nextInt();
        graph1 = new ArrayList<>();
        graph2 = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            graph1.add(new ArrayList<>());
            graph2.add(new ArrayList<>());
        }
        coins = new long[n+1];
        for(int i=1;i<=n;i++) coins[i] = sc.nextLong();
        while(m-- > 0){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph1.get(u).add(v);
            graph2.get(v).add(u);
        }
        vis = new boolean[n+1];
        // ----------------- KOSARAJU START------------------//
        //------------------FIND ORDER FOR KOSARAJU--------------------
        s = new ArrayDeque<>(); 
        vis = new boolean[n+1];
        for(int i=1;i<=n;i++) if(!vis[i]) dfs1(i);
        comps = new int[n+1];
        int comp = 1;
        List<Long> score = new ArrayList<>();
        score.add(0L);
        vis = new boolean[n+1];
        while(!s.isEmpty()){
            int u = s.pollLast();
            if(!vis[u]){
                score.add(dfs2(u,comp));
                comp++;
            }
        }
        comp--;
        //-----------------------KOSARAJU END---------------------
 
        // ----------------BUILD NEW GRAPH WITH SCC COMPRESSION---------------
        graph3 = new ArrayList<>();
        for(int i=0;i<=comp;i++) graph3.add(new ArrayList<>());
        
        for(int u=1;u<=n;u++){
            for(int v : graph1.get(u)){
                if(comps[u]!=comps[v]) graph3.get(comps[u]).add(comps[v]);
            }
        }
 
        //---------------- APPLY DP ON NEW GRAPH----------------
        
        long[] dp = new long[comp + 1];
        for(int i=1;i<=comp;i++) dp[i] = score.get(i);
        for(int u=1;u<=comp;u++){
            for(int v : graph3.get(u)){
                dp[v] = Math.max(dp[v],dp[u] + score.get(v));
            }
        }
        long max=0;
        for(int i=1;i<=comp;i++) max = Math.max(dp[i],max);
        out.append(max);
    }
}
 