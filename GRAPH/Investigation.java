import java.io.*;
import java.util.*;
public class Investigation{
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
    static final long NEG = Long.MIN_VALUE;
    static List<List<int[]>> graph;
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
        graph = new ArrayList<>();
        for(int i=0;i<=n;i++) graph.add(new ArrayList<>());
        while(m-- > 0){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.get(u).add(new int[]{v,w});
        }
        PriorityQueue<long[]> q = new PriorityQueue<>((a,b)->Long.compare(a[1],b[1]));
        q.add(new long[]{1,0});
        long[] dis = new long[n+1];
        long[] ways = new long[n+1];
        long[] minF = new long[n+1];
        long[] maxF = new long[n+1];
        Arrays.fill(dis,INF);
        Arrays.fill(minF,INF);
        Arrays.fill(maxF,NEG);
        dis[1] = 0;
        ways[1] = 1;
        minF[1] = maxF[1] = 0;
        while(!q.isEmpty()){
            long[] temp = q.poll();
            int u = (int)temp[0];
            long dist = temp[1];
            if(dis[u]<dist) continue;
            for(int[] child : graph.get(u)){
                int v = child[0];
                long d = child[1];
                if(dist + d < dis[v]){
                    dis[v] = dist + d;
                    ways[v] = ways[u];
                    minF[v] = 1 + minF[u];
                    maxF[v] = 1 + maxF[u];
                    q.add(new long[]{v,dis[v]});
                }
                else if(dist + d == dis[v]){
                    ways[v] = (ways[v] + ways[u]) % M;
                    maxF[v] = Math.max(maxF[v],1 + maxF[u]);
                    minF[v] = Math.min(minF[v],1 + minF[u]);
                }
            }
        }
        out.append(dis[n]).append(" ");
        out.append(ways[n]).append(" ");
        out.append(minF[n]).append(" ");
        out.append(maxF[n]);
    }

}



