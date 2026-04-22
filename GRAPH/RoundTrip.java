import java.io.*;
import java.util.*;



class RoundTrip {
    static int[] parent;
    static int start;
    static boolean[] vis;
    static int end;
    static List<List<Integer>> edges;
    private static boolean dfs(int node,int par){
        vis[node] = true;
        parent[node] = par;
        for(int nei : edges.get(node)){
            if(nei==par) continue;
            if(vis[nei]){
                start = node;
                end=nei;
                return true;
            }
            if(dfs(nei,node)) return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        vis = new boolean[n+1];
        parent = new int[n+1];
        start=-1;
        end=-1;
        edges = new ArrayList<>();
        for(int i=0;i<=n;i++) edges.add(new ArrayList<>());
        for(int i=0;i<m;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            edges.get(u).add(v);
            edges.get(v).add(u);
        }
        for(int i=1;i<=n;i++){
            if(!vis[i] && dfs(i,i)){
                List<Integer> cycle = new ArrayList<>();
                int curr = start;
                cycle.add(end);
                while(curr != end){
                    cycle.add(curr);
                    curr = parent[curr];
                }
                cycle.add(end);
                Collections.reverse(cycle);
                StringBuilder s = new StringBuilder();
                s.append(cycle.size()).append("\n");
                for(int ele : cycle) s.append(ele).append(" ");
                System.out.print(s);
                return;
            }
        }
        System.out.print("IMPOSSIBLE");
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
