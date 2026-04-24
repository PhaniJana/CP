import java.io.*;
import java.util.*;
public class FlightDiscount {

    //GREEDY FAILS HERE
    /**
     *  4 4

        1 2 100

        2 4 100

        1 3 10

        3 4 200
        
        EXPECTED =>110
     */
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<int[]>> edges = new ArrayList<>();
        for(int i=0;i<=n;i++) edges.add(new ArrayList<>());
        while(m-- > 0){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges.get(u).add(new int[]{v,w});
        }
        PriorityQueue<long[]> q = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        q.add(new long[]{1,0,0});
        long[][] dis = new long[n+1][2];
        for(long[] arr : dis) Arrays.fill(arr,Long.MAX_VALUE);
        dis[1][0] = 0;
        
        while(!q.isEmpty()){
            long[] arr = q.poll();
            int node = (int)arr[0];
            long TotalDis = arr[1];
            int used = (int)arr[2];
            
            if(TotalDis > dis[node][used]) continue;
            
            for(int[] e : edges.get(node)){
                int v = e[0];
                int wt = e[1];
                if(dis[v][used] > TotalDis + wt){
                    dis[v][used] = TotalDis + wt;
                    q.add(new long[]{v,dis[v][used],used});
                }
                if(used==0){
                    if(dis[v][1] > TotalDis + wt/2){
                        dis[v][1] = TotalDis + wt/2;
                        q.add(new long[]{v,dis[v][1],1});
                    }
                }
            }
        }
        System.out.print(dis[n][1]);
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
    // -------- Arrays --------
    int[] nextIntArray(int n) throws IOException {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = nextInt();
        return arr;
    }
}