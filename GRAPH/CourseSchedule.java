import java.io.*;
import java.util.*;
public class CourseSchedule {
    private static boolean dfs(int node,boolean[] vis,Stack<Integer> s,List<List<Integer>> edges,boolean[] path){
        vis[node] = true;
        path[node] = true;
        for(int nei : edges.get(node)){
            if(path[nei]) return true;
            if(!vis[nei]) if(dfs(nei,vis,s,edges,path)) return true;
        }
        s.push(node);
        path[node] = false;
        return false;
    }
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        List<List<Integer>> edges = new ArrayList<>();
        for(int i=0;i<=n;i++) edges.add(new ArrayList<>());
        while(m-- > 0){
            int u = sc.nextInt();
            int v = sc.nextInt();
            edges.get(v).add(u);
        }
        Stack<Integer> s = new Stack<>();
        boolean[] vis = new boolean[n+1];
        boolean[] path = new boolean[n+1];
        for(int i=1;i<=n;i++){
            if(!vis[i]) if(dfs(i,vis,s,edges,path)){
                System.out.print("IMPOSSIBLE");
                return;
            }
        }
        StringBuilder ans = new StringBuilder("");
        for(int ele : s) ans.append(ele).append(" ");
        
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
