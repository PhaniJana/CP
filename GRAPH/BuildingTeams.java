import java.io.*;
import java.util.*;
public class BuildingTeams {
    static boolean bfs(int nod,int[] group,List<List<Integer>> edges,int n){
        Queue<Integer> q = new LinkedList<>();
        q.add(nod);
        group[nod] = 0;
        while(!q.isEmpty()){
           int node = q.poll();
           int nG = 1 - group[node];
           for(int nei : edges.get(node)){
               if(group[nei]==-1) {
                group[nei] = nG;
                q.add(nei);
               }
               else if(group[nei]!=nG) return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
       FastReader fs = new FastReader();
       int n = fs.nextInt();
       int m = fs.nextInt();
       List<List<Integer>> edges = new ArrayList<>();
       for(int i=0;i<=n;i++) edges.add(new ArrayList<>());
       for(int i=0;i<m;i++){
           int u = fs.nextInt();
           int v = fs.nextInt();
           edges.get(u).add(v);
           edges.get(v).add(u);
       }
       int[] group = new int[n+1];
       Arrays.fill(group,-1);
        for(int i=1;i<=n;i++){
            if(group[i]==-1) if(!bfs(i,group,edges,n)){
               System.out.print("IMPOSSIBLE");
               return;
            }
        }
       StringBuilder s = new StringBuilder();
       for(int i=1;i<=n;i++) s.append(group[i]+1).append(" ");
       System.out.print(s);
       
       
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