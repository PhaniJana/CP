import java.io.*;
import java.util.*;
class DSU{
    int[] parent;
    int[] size;
    DSU(int n){
        parent = new int[n];
        size = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
    public int find(int node){
        if(parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }
    public void union(int u,int v){
        int parU = find(u);
        int parV = find(v);
        if(parU == parV) return;
        if(size[parU] > size[parV]){
            size[parU]+=size[parV];
            parent[parV] = parU;
        }else{
            size[parV]+=size[parU];
            parent[parU] = parV;
        }
    }
}
public class RoadConstruction {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int totalComps = n;
        int maxSize = 1;
        StringBuilder ans = new StringBuilder();
        DSU dsu = new DSU(n+1); 
        while(m-- >0){
            int u = sc.nextInt();
            int v = sc.nextInt();
            if(dsu.find(u) == dsu.find(v)){
                ans.append(totalComps + " " + maxSize).append("\n");
            } else{
                totalComps--;
                dsu.union(u,v);
                int par = dsu.find(u);
                maxSize = Math.max(maxSize,dsu.size[par]);
                ans.append(totalComps + " " + maxSize).append("\n");
            }
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

}
