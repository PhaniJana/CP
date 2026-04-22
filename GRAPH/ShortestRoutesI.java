import java.io.*;
import java.util.*;


public class ShortestRoutesI {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<int[]>> edges = new ArrayList<>();
        for(int i=0;i<=n;i++) edges.add(new ArrayList<>());
        for(int i=0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges.get(u).add(new int[]{v,w});
        }
        PriorityQueue<long[]> q = new PriorityQueue<>((a,b)->Long.compare(a[1],b[1]));
        q.add(new long[]{1,0});
        long[] dis = new long[n+1];
        Arrays.fill(dis,(long)1e14);
        dis[1]=0;
        while(!q.isEmpty()){
            long[] curr = q.poll();
            int node = (int)curr[0];
            long wt = curr[1];
            if(wt>dis[(int)node]) continue;
            for(int[] nei : edges.get((int)node)){
                int neiNode = nei[0];
                if(dis[node] + nei[1] < dis[neiNode]){
                    dis[neiNode] = dis[node] + nei[1];
                    q.add(new long[]{neiNode,dis[neiNode]});
                }
            }
        }
        StringBuilder ans = new StringBuilder("");
        for(int i=1;i<=n;i++) ans.append(dis[i]).append(" ");
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
