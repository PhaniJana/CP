import java.io.*;
import java.util.*;

// This won't pass the test case where  n=2*10^5  due to recurssion depth or stack over flow i submitted cpp solution of this code 
public class Subordinates {
    private static long dfs(int node,long[] sub,List<List<Integer>> edges){
        long count=0;
        for(int child : edges.get(node)){
            count += dfs(child,sub,edges);
        }
        sub[node] = count;
        return 1 + count;
    } 
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        List<List<Integer>> edges = new ArrayList<>();
        for(int i=0;i<=n;i++) edges.add(new ArrayList<>());
        for(int i=2;i<=n;i++){
            int v = i;
            int u = sc.nextInt(); 
            edges.get(u).add(v);
        }
        long[] sub = new long[n+1];
        dfs(1,sub,edges);
        StringBuilder s = new StringBuilder();
        for(int i=1;i<=n;i++) s.append(sub[i]).append(" ");
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

    
}