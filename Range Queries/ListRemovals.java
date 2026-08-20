import java.io.*;
import java.util.*;
public class ListRemovals{
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

        // -------- Arrays --------
        int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = nextInt();
            return arr;
        }
    }
    static class SegmentTree{
        int n;
        int[] tree;
        SegmentTree(int n){
            this.n = n;
            tree = new int[4*n];
            build(0,0,n-1);
        }
        public void update(int idx){
            update(0,0,n-1,idx);
        }
        public int query(int k){
            return query(0,0,n-1,k);
        }
        private void build(int idx,int l,int r){
            if(l==r) {
                tree[idx] = 1;
                return;
            }
            int mid = (l+r)/2;
            build(2*idx+1,l,mid);
            build(2*idx+2,mid+1,r);
            tree[idx] = tree[2*idx+1] + tree[2*idx+2];
        }
        private int query(int idx,int l,int r,int k){
            if(l==r){
                return l;
            }
            int mid = (l+r)/2;
            if(tree[2*idx+1] >= k) return query(2*idx+1,l,mid,k);
            return query(2*idx+2,mid+1,r,k - tree[2*idx+1]);
        }
        private void update(int idx,int l,int r,int index){
            if(l==r) {
                tree[idx] = 0;
                return;
            }
            int mid = (l+r)/2;
            if(index<=mid) update(2*idx+1,l,mid,index);
            else update(2*idx+2,mid+1,r,index);
            tree[idx] = tree[2*idx+1] + tree[2*idx+2];
        }
    }
    static final long M = 1_000_000_007L;
    static final long INF = Long.MAX_VALUE;

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

    static void solve(FastReader sc, StringBuilder out) throws Exception {
        int n = sc.nextInt();
        int[] nums = sc.nextIntArray(n);
        SegmentTree sg = new SegmentTree(n);
        for(int i=0;i<n;i++){
            int pos = sc.nextInt();
            int idx = sg.query(pos);
            out.append(nums[idx]).append(" ");
            sg.update(idx);
        }
    }

}