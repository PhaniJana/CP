import java.io.*;
import java.util.*;
public class LongestFlightRoute{
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
    
    static void solve(FastReader sc, StringBuilder out) throws Exception {
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<Integer>> edges = new ArrayList<>();

        for(int i=0;i<=n;i++) {
            edges.add(new ArrayList<>());
        }
        int[] indegree = new int[n+1];
        int[] par = new int[n+1];
        int[] dis = new int[n+1];
        for(int i=0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            edges.get(v).add(u);
            indegree[u]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=n;i++) if(indegree[i]==0) q.add(i);
        List<Integer> order = new ArrayList<>();
        for(int i=1;i<=n;i++) par[i] = i;
        while(!q.isEmpty()){
            int u = q.poll();
            if(indegree[u]==0) order.add(u);
            for(int v : edges.get(u)){
                if(--indegree[v] == 0) q.add(v);
            }
        }
        Arrays.fill(dis, Integer.MIN_VALUE);
        dis[n] = 1;
        for(int u : order){
            if(dis[u] == Integer.MIN_VALUE) continue;
            for(int v : edges.get(u)){
                if(dis[v] < dis[u] + 1){
                    dis[v] = dis[u] + 1;
                    par[v] = u;
                }
            }
        }
        if(dis[1] == Integer.MIN_VALUE){
            out.append("IMPOSSIBLE");
        } else{
            out.append(dis[1]).append("\n");
            int curr = 1;
            List<Integer> ans = new ArrayList<>();
            while(curr!=n){
                ans.add(curr);
                curr = par[curr];
            }
            ans.add(n);
            for(int ele : ans) out.append(ele).append(" ");
        }
    }
}
