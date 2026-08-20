import java.io.*;
import java.util.*;
public class FindingACentroid{
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

    }

    static final long M = 1_000_000_007L;
    static final long INF = Long.MAX_VALUE;
    static List<List<Integer>> tree;
    static List<Integer> ans;
    static int[] subtree;
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
    static void dfs1(int u,int p){

        subtree[u] = 1;
        for(int v : tree.get(u)){
            if(v==p) continue;
            dfs1(v,u);
            subtree[u] += subtree[v]; 
        }
    }
    static void dfs2(int u,int p,int n){

        boolean grater = false;
        if(p != -1){
            int sl = n - subtree[u];
            if(sl > n/2) grater = true;
        }
        for(int v : tree.get(u)){
            if(v==p) continue;
            if(subtree[v] > n/2) grater = true;
            dfs2(v,u,n);
        } 
        if(!grater) ans.add(u);
    }
    static void solve(FastReader sc, StringBuilder out) throws Exception {
        int n = sc.nextInt();
        tree = new ArrayList<>();
        for(int i=0;i<=n;i++) tree.add(new ArrayList<>());
        for(int i=0;i<n-1;i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
        subtree = new int[n+1];
        dfs1(1,-1);
        ans = new ArrayList<>();
        dfs2(1,-1,n);
        out.append(ans.get(0));
    }
}


/**
  V2
    static void dfs1(int u,int p){
        boolean grater = false;

        subtree[u] = 1;
        for(int v : tree.get(u)){
            if(v==p) continue;
            dfs1(v,u);
            subtree[u] += subtree[v]; 
            if(subtree[v] > n/2) grater = true;
        }
        if(p != -1){
            int sl = n - subtree[u];
            if(sl > n/2) grater = true;
        }
        if(!grater) ans.add(u);
    }
    static void solve(FastReader sc, StringBuilder out) throws Exception {
        n = sc.nextInt();
        tree = new ArrayList<>();
        for(int i=0;i<=n;i++) tree.add(new ArrayList<>());
        for(int i=0;i<n-1;i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
        subtree = new int[n+1];
        ans = new ArrayList<>();
        dfs1(1,-1);
        out.append(ans.get(0));
    }
 */
