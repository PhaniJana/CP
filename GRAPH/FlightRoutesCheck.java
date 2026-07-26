import java.io.*;
import java.util.*;
public class FlightRoutesCheck{
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
    static class Pair{
        int u;
        long cost;
        Pair(int u,long cost){
            this.u = u;
            this.cost = cost;
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

    static void solve(FastReader sc, StringBuilder out) throws Exception {
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> graphRev = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
            graphRev.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graphRev.get(v).add(u);
        }
        boolean[] vis1 = new boolean[n+1];
        boolean[] vis2 = new boolean[n+1];
        dfs(1,graph,vis1);
        for(int i=1;i<=n;i++){
            if(!vis1[i]){
                out.append("NO").append("\n");
                out.append(1 + " " + i);
                return;
            }
        }
        dfs(1,graphRev,vis2);
        for(int i=1;i<=n;i++){
            if(!vis2[i]){
                out.append("NO").append("\n");
                out.append(i + " " + 1);
                return;
            }
        }
        out.append("YES");
    }
    static void dfs(int node,List<List<Integer>> graph,boolean[] vis){
        vis[node] = true;
        for(int v : graph.get(node)){
            if(!vis[v]) dfs(v,graph,vis);
        }
    }

}
