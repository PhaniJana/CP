import java.io.*;
import java.util.*;

public class TreeDiameter {
    static int max = 0;
    private static int dfs(int node,int par,List<List<Integer>> edges){
        int max1=0,max2=0;
        for(int child : edges.get(node)){
            if(par==child) continue;
            int val = dfs(child,node,edges);
            if(val>max1){
                max2=max1;
                max1=val;
            }
            else if(val>max2){
                max2=val;
            }
        }
        max = Math.max(max,max1+max2);
        return 1 + max1;
    } 
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        List<List<Integer>> edges = new ArrayList<>();
        for(int i=0;i<=n;i++) edges.add(new ArrayList<>());
        for(int i=1;i<n;i++){
            int u = sc.nextInt();
            int v = sc.nextInt(); 
            edges.get(u).add(v);
            edges.get(v).add(u);
        }
        dfs(1,-1,edges);
        
        System.out.print(max);
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