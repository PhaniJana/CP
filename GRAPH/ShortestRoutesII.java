import java.io.*;
import java.util.*;
public class ShortestRoutesII {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
       long[][] dis = new long[n+1][n+1];
       long INF = (long)1e18;
       for(long[] arr : dis) Arrays.fill(arr,INF);
        for(int i=0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            long w = sc.nextLong();
            dis[u][v] = Math.min(dis[u][v], w);
            dis[v][u] = Math.min(dis[v][u], w);
        }
        for(int i=1;i<=n;i++) dis[i][i] = 0;
        int[][] queries = new int[q][2];
        for(int i=0;i<q;i++){
            int s = sc.nextInt();
            int d = sc.nextInt();
            queries[i][0] = s;
            queries[i][1] = d;
        }
        
        for(int via=1;via<=n;via++){ //TO PASS THE CSES TLE FOR ONE TEST CASE IN JAVA FOR N==500
            long[] rowVia = dis[via];

            for(int i=1;i<=n;i++){
                long[] rowI = dis[i];
                long dIV = rowI[via];

                if(dIV == INF) continue;

                for(int j=1;j<=n;j++){
                    if(rowVia[j] == INF) continue;

                    long newDist = dIV + rowVia[j];
                    if(newDist < rowI[j]){
                        rowI[j] = newDist;
                    }
                }
            }
        }
        /**
         * for(int via=1;via<=n;via++){
         *  for(int i=1;i<=n;i++){
         *      if(dis[i][via]==INF) continue;
         *      for(int j=1;j<=n;j++){
         *          if(dis[via][j]==INF) continue;
         *          dis[i][j] = Math.min(dis[i][j],dis[i][via]+dis[via][j]);
         *     } 
         *  }
         * }
         */
        StringBuilder ans = new StringBuilder("");
        for(int[] query : queries){
            int u = query[0];
            int v = query[1];
            long distance = dis[u][v] == INF ? -1 : dis[u][v]; 
            ans.append(distance).append("\n");
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
