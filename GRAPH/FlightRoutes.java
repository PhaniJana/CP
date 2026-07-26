import java.io.*;
import java.util.*;
public class FlightRoutes{
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
        int k = sc.nextInt();
        List<List<int[]>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++) graph.add(new ArrayList<>());
        for(int i=0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.get(u).add(new int[]{v,w});

        }
        int[] cnt = new int[n+1];
PriorityQueue<Pair> q = new PriorityQueue<>((a,b)->Long.compare(a.cost,b.cost));

        q.add(new Pair(1,0));
        int found = 0;

        while(!q.isEmpty()){
            Pair p = q.poll();
            if(cnt[p.u]==k) continue;
            if(p.u==n) {
                out.append(p.cost).append(" ");
                if (++found == k) return;
            }
            cnt[p.u]++;
            for(int[] v : graph.get(p.u)){
                int node = v[0];
                if(cnt[node] < k){
                    q.add(new Pair(node,v[1]+p.cost));
                }
            }
        }
    }

}
