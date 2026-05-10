import java.io.*;
import java.util.*;
public class TreeDistancesI {
    private static int dfs1(int node,int par,List<List<Integer>> edges,int[] depths){
            int depth=0;
            for(int child : edges.get(node)){
                if(child==par) continue;
                depth = Math.max(depth,1 + dfs1(child,node,edges,depths));
            }
            return depths[node] = depth;
    }
    
    private static void dfs2(int node,int par,List<List<Integer>> edges,int[] depths,int par_ans,int[] ans){
        
        List<Integer> prefixMax = new ArrayList<>();
        List<Integer> suffixMax = new ArrayList<>();
        for(int child : edges.get(node)){
            if(child == par) continue;
            prefixMax.add(depths[child]);
            suffixMax.add(depths[child]);
        }
        int size = prefixMax.size();
        for(int i=1;i<size;i++) 
            prefixMax.set(i,Math.max(prefixMax.get(i-1),prefixMax.get(i)));
        for(int i=size-2;i>=0;i--)
            suffixMax.set(i,Math.max(suffixMax.get(i+1),suffixMax.get(i)));
        int c_no = 0; //child number
        
        for(int child : edges.get(node)){
            if(child == par) continue;
            int op1 = c_no==0 ? -1 : prefixMax.get(c_no-1);
            int op2 = c_no == size-1 ? -1 : suffixMax.get(c_no+1);
            int n_par_ans = 1 + Math.max(op1,Math.max(op2,par_ans));
            dfs2(child,node,edges,depths,n_par_ans,ans);
            c_no++;
        }
        ans[node] = 1 + Math.max(par_ans,size>0 ? prefixMax.get(size-1) : -1);
        
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
        int[] depths = new int[n+1];
        dfs1(1,-1,edges,depths);
        int[] ans = new int[n+1];
        dfs2(1,-1,edges,depths,0,ans);
        for(int i=1;i<=n;i++) System.out.print(ans[i] + " ");
        
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
