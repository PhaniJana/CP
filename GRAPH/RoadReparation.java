import java.io.*;
import java.util.*;
public class RoadReparation {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        long M = (long)1e9 + 7;
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++) graph.add(new ArrayList<>());
        int m = sc.nextInt();
        int[][] edges = new int[m][3];
        for(int i=0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges[i][0] = u;
            edges[i][1] = v;
            edges[i][2] = w;
        }
        Arrays.sort(edges,(a,b)->a[2]-b[2]);
        DSU dsu = new DSU(n+1);
        long cost = 0;
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            cost += dsu.union(u,v,w);
        }
        int par = dsu.find(1);
        for(int i=2;i<=n;i++){
            if(dsu.find(i)!=par){
                System.out.print("IMPOSSIBLE");
                return;
            }
        }
        System.out.print(cost);
        
    }
}

class DSU{
    int[] par;
    int[] size;
    DSU(int n){
        par = new int[n];
        size = new int[n];
        for(int i=0;i<n;i++) {
            par[i] = i;
            size[i] = 1;
        }
    }
    public int find(int u){
        if(par[u] == u) return u;
        return par[u] = find(par[u]);
    }
    
    public int union(int u,int v,int w){
        int pu = find(u);
        int pv = find(v);
        if(pu==pv) return 0;
        if(size[pu]>size[pv]){
            par[pv] = par[pu];
            size[pu]+=size[pv];
        }
        else{
            par[pu] = par[pv];
            size[pv]+=size[pu];
        }
        return w;
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
