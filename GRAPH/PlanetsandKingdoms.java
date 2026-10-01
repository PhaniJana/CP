import java.io.*;
import java.util.*;
public class PlanetsandKingdoms{
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
    static Stack<Integer> s;
    static boolean[] vis;
    static int[] ans;
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
        s.push(u);
    }
    static void dfs2(int u,int scc){
        vis[u] = true;
        ans[u] = scc;
        for(int v : graph2.get(u)){
            if(!vis[v]) dfs2(v,scc);
        }
    } 
    static void solve(FastReader sc, StringBuilder out) throws Exception {
        int n = sc.nextInt();
        int m = sc.nextInt();
        graph1 = new ArrayList<>();
        graph2 = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            graph2.add(new ArrayList<>());
            graph1.add(new ArrayList<>());
        }
        while(m-- > 0){
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph1.get(a).add(b);
            graph2.get(b).add(a);
        }
        vis = new boolean[n+1];
        s = new Stack<>();
        for(int i=1;i<=n;i++){
            if(!vis[i]) dfs1(i);
        }
        ans = new int[n+1];
        vis = new boolean[n+1];
        int scc = 1;
        while(!s.isEmpty()){
            int u = s.pop();
            if(!vis[u]){
                dfs2(u,scc);
                scc++;
            }
        }
        out.append(scc-1).append("\n");
        for(int i=1;i<=n;i++) out.append(ans[i]).append(" ");
    }
}


