import java.io.*;
import java.util.*;

public class TreeDistancesII {
    static List<List<Integer>> tree;
    private static  void dfs(int node,int par,int[] ans,int[] count){
        count[node] = 1;
        for(int child : tree.get(node)){
            if(child == par) continue;

            dfs(child,node,ans,count);
            count[node] += count[child]; 
            ans[node] += ans[child] + count[child];
        }
    }
    
    private static void dfs2(int node,int par,int[] ans,int[] count,int n){
        
        for(int child : tree.get(node)){
            if(child==par) continue;
            ans[child] = ans[node] - count[child] + (n - count[child]);
            dfs2(child,node,ans,count,n);
        }
    }
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        tree = new ArrayList<>();
        for(int i=0;i<=n;i++) tree.add(new ArrayList<>());
        for(int i=0;i<n-1;i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
        int[] ans = new int[n+1];
        int[] count = new int[n+1];
        
        dfs(1,-1,ans,count);
        dfs2(1,-1,ans,count,n);
        StringBuilder sol = new StringBuilder();
        for(int i=1;i<=n;i++) sol.append(ans[i]).append(" ");
        System.out.println(sol);
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