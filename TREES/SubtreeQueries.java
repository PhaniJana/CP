import java.io.*;
import java.util.*;
public class SubtreeQueries {
    private static List<List<Integer>> tree;
    private static long time = 1;
    private static void euler_dfs(int node,int par,long[] in_time,
    long[] out_time,List<Long> euler,long[] val){
        in_time[node] = time++;
        euler.add(val[node]);
        for(int child : tree.get(node)){
            if(par==child) continue;
            euler_dfs(child,node,in_time,out_time,euler,val);
        }
        out_time[node] = time++;
        euler.add(val[node]);
    }
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int q = sc.nextInt();
        long[] val = new long[n+1];
        for(int i=1;i<=n;i++) val[i] = sc.nextLong();
        tree = new ArrayList<>();
        for(int i=0;i<=n;i++) tree.add(new ArrayList<>());
        for(int i=0;i<n-1;i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
        List<Long> euler = new ArrayList<>();
        euler.add(-1L);
        long[] in_time = new long[n+1];
        long[] out_time = new long[n+1];
        euler_dfs(1,-1,in_time,out_time,euler,val);
        SegmentTree st = new SegmentTree(euler); 
        StringBuilder ans = new StringBuilder();
        while(q-- > 0){
            int type = sc.nextInt();
            if(type==1){
                int node = sc.nextInt();
                int value = sc.nextInt();
                st.update((int)in_time[node],value);
                st.update((int)out_time[node],value);
            }else{
                int node = sc.nextInt();
                long res = st.query((int)in_time[node],(int)out_time[node])/2L;
                ans.append(res).append("\n");
            }
        }
        System.out.print(ans);
    }
}
class SegmentTree{
    int n;
    long[] tree;
    List<Long> arr;
    SegmentTree(List<Long> arr){
        n = arr.size();
        tree = new long[4*n];
        this.arr = arr;
        build(0,1,n-1);
    }
    private void build(int idx,int l,int r){
        if(l==r){
            tree[idx] = arr.get(l);
            return;
        }
        int mid = l + (r-l)/2;
        build(2*idx+1,l,mid);
        build(2*idx+2,mid+1,r);
        tree[idx] = tree[2*idx+1] + tree[2*idx+2];
    }
    public void update(int idx,int val){
        update(0,1,n-1,idx,val);
    }
    private void update(int idx,int l,int r,int index,int val){
        if(l==r){
            tree[idx] = val;
            return;
        }
        int mid = l + (r-l)/2;
        if(index<=mid) update(2*idx+1,l,mid,index,val);
        else update(2*idx+2,mid+1,r,index,val);
        tree[idx] = tree[2*idx+1] + tree[2*idx+2];
    }
    public long query(int l,int r){
        return query(0,1,n-1,l,r);
    }
    private long query(int idx,int l,int r,int p,int q){
        if(r<p || q<l) return 0;
        if(p<=l && r<=q) return tree[idx];
        int mid = l + (r-l)/2;
        return query(2*idx+1,l,mid,p,q) + query(2*idx+2,mid+1,r,p,q);
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