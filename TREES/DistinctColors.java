import java.io.*;
import java.util.*;

class DistinctColors {
    static int[] inTime;
    static int[] outTime;
    static List<Integer> euler;
    static int timer;
    static List<List<Integer>> tree;
    static int[] cols;
    private static void dfs1(int node,int par){
        inTime[node] = timer++;
        euler.add(cols[node]);
        for(int child : tree.get(node)){
            if(child == par) continue;
            dfs1(child,node);
        }
        outTime[node] = timer++;
        euler.add(cols[node]);
    }
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        cols = new int[n+1];
        for(int i=1;i<=n;i++) cols[i] = sc.nextInt();
        tree = new ArrayList<>();
        for(int i=0;i<=n;i++){
            tree.add(new ArrayList<>());
        }
        for(int i=0;i<n-1;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
        inTime = new int[n+1];
        outTime = new int[n+1];
        euler = new ArrayList<>();
        timer = 0;
        dfs1(1,-1);
        int[][] query = new int[n][2];
        for(int i=1;i<=n;i++){
            query[i-1][0] = inTime[i];
            query[i-1][1] = outTime[i];
        }
        MoS_Algo(query,euler,n);
    }
    private static void MoS_Algo(int[][] query,List<Integer> nums,int n){
        Map<Integer,Integer> mpp = new HashMap<>();
        int block = (int)Math.ceil(Math.sqrt(2*n));
        Query[] qry = new Query[n];
        for(int i=0;i<n;i++){
            qry[i] = new Query(query[i][0],query[i][1],i);
        }
        Arrays.sort(qry,(a,b)->{
            int b1 = a.l / block;
            int b2 = b.l / block;
            if (b1 != b2) return b1 - b2;
            if ((b1 & 1) == 0) return a.r - b.r;
            return b.r - a.r;
        });
        int l = 0,r = -1;
        int[] ans = new int[n];
        for(int i=0;i<n;i++){
            int cl = qry[i].l;
            int cr = qry[i].r;
            while(l > cl) add(--l,mpp);
            while(r < cr) add(++r,mpp);
            
            while(l < cl) remove(l++,mpp);
            while(r > cr) remove(r--,mpp);
            ans[qry[i].i] = mpp.size();
        }
        StringBuilder sol = new StringBuilder();
        for(int i=0;i<n;i++){
            sol.append(ans[i]).append(" ");
        }
        System.out.print(sol);
    }
    private static void add(int idx,Map<Integer,Integer> mpp){
        int ele = euler.get(idx);
        mpp.put(ele,mpp.getOrDefault(ele,0)+1);
    }
    private static  void remove(int idx,Map<Integer,Integer> mpp){
        int ele = euler.get(idx);
        int freq = mpp.getOrDefault(ele,0);
        if(freq<=1) mpp.remove(ele);
        else mpp.put(ele,freq-1);
    }
    static class Query{
        int l;
        int r;
        int i;
        Query(int _l,int _r,int _i){
            l = _l;
            r = _r;
            i = _i;
        }
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


    
}
