import java.io.*;
import java.util.*;
public class RoundTripII{
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
    static int start;
    static int end;
    static boolean dfs(int u,boolean[] vis,boolean[] path,
    List<List<Integer>> edges,int[] par){
        path[u] = true;
        vis[u] = true;
        for(int v : edges.get(u)){
            if(path[v]) {
                start = v;
                end = u;
                return true;
            }
            if(!vis[v]){
                par[v] = u;
                if(dfs(v,vis,path,edges,par)) {
                    return true; 
                }
            }
        }
        path[u] = false;
        return false;
    }

    static void solve(FastReader sc, StringBuilder out) throws Exception {
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<Integer>> edges = new ArrayList<>();
        for(int i=0;i<=n;i++) edges.add(new ArrayList<>());
        for(int i=0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            edges.get(u).add(v);
        }
        int[] par = new int[n+1];
        boolean[] vis = new boolean[n+1];
        boolean[] path = new boolean[n+1];
        start = -1;
        end = -1;
        for(int i=1;i<=n;i++){
            if(!vis[i]){
                if(dfs(i,vis,path,edges,par)){
                    List<Integer> ans = new ArrayList<>();
                    while(start != end){
                        ans.add(end);
                        end = par[end];
                    }
                    ans.add(start);
                    Collections.reverse(ans);
                    ans.add(start);
                    out.append(ans.size()).append("\n");
                    for(int ele : ans) out.append(ele).append(" ");
                    return;
                }
            }
        }
        out.append("IMPOSSIBLE");
    }

}
