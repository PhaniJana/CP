import java.io.*;
import java.util.*;

public class TreeDistancesI {
    private static void dfs(int node, int par,
                        List<List<Integer>> edges, int dist,int[] dis){
    dis[node] = dist;

    for(int child : edges.get(node)){
        if(child == par) continue;
        dfs(child, node, edges,dist + 1, dis);
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
        int[] dis1 = new int[n+1];
        //----------DFS 1 FOR FINDING END POINT1-------------
        dfs(1,-1,edges,0,dis1);
        int max=0;
        int end1=1;
        for(int i=1;i<=n;i++){
            if(max<dis1[i]){
                max = dis1[i];
                end1 = i;
            }
        }
        
        int[] dis2 = new int[n+1]; //  DIS FROM END POINT 1 TO ALL
        //----------DFS FOR FINDING END POINT 2--------------
        dfs(end1,-1,edges,0,dis2);
        int end2=1;
        int max1=0;
        for(int i=1;i<=n;i++){
            if(max1<dis2[i]){
                max1 = dis2[i];
                end2 = i;
            }
        }
        int dis3[] = new int[n+1]; // DIS FROM END POINT 2 TO ALL
        dfs(end2,-1,edges,0,dis3);
        StringBuilder ans = new StringBuilder();
        for(int i=1;i<=n;i++){
            ans.append(Math.max(dis2[i],dis3[i])).append(" ");   
        }
        System.out.print(ans);
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
