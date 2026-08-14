import java.io.*;
import java.util.*;
public class PrefixSumQueries{
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

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        String nextLine() throws IOException {
            return br.readLine();
        }

        char nextChar() throws IOException {
            return next().charAt(0);
        }

        // -------- Arrays --------
        int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = nextInt();
            return arr;
        }

        long[] nextLongArray(int n) throws IOException {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) arr[i] = nextLong();
            return arr;
        }

        double[] nextDoubleArray(int n) throws IOException {
            double[] arr = new double[n];
            for (int i = 0; i < n; i++) arr[i] = nextDouble();
            return arr;
        }

        String[] nextStringArray(int n) throws IOException {
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) arr[i] = next();
            return arr;
        }

        char[] nextCharArray(int n) throws IOException {
            return next().toCharArray(); // assumes no spaces
        }
    }
    static class SegmentTree{
        long[] tree;
        long[] lazy;
        int n;
        SegmentTree(long[] nums){
            n = nums.length;
            tree = new long[4*n];
            lazy = new long[4*n];
            build(0,0,n-1,nums);
        }
        private void build(int idx,int l,int r,long[] nums){
            if(l==r){
                tree[idx] = nums[l];
                return;
            }
            int mid = (l+r)/2;
            build(2*idx+1,l,mid,nums);
            build(2*idx+2,mid+1,r,nums);
            tree[idx] = Math.max(tree[2*idx+1] , tree[2*idx+2]);
        }
        private void push(int idx,int l,int r){
            if(lazy[idx]!=0){
                tree[idx] += lazy[idx];
                if(l!=r){
                    lazy[2*idx+1] += lazy[idx];
                    lazy[2*idx+2] += lazy[idx];
                }
                lazy[idx] = 0;
            }
        }

        private void update(int idx,int l,int r,int p,int q,long val){
            push(idx,l,r);
            if(r<p || l>q) return;
            else if(l>=p && r<=q){
                tree[idx] += val;
                if(l!=r){
                    lazy[2*idx+1] += val;
                    lazy[2*idx+2] += val;
                }
                return; 
            }
            int mid = (l+r)/2;
            update(2*idx+1,l,mid,p,q,val);
            update(2*idx+2,mid+1,r,p,q,val);
            tree[idx] = Math.max(tree[2*idx+1],tree[2*idx+2]);
        }

        private long query(int idx,int l,int r,int p,int q){
            push(idx,l,r);
            if(r<p || l>q) return Long.MIN_VALUE;
            else if(l>=p && r<=q){
                return tree[idx]; 
            }
            int mid = (l+r)/2;
            return Math.max(query(2*idx+1,l,mid,p,q),query(2*idx+2,mid+1,r,p,q));
        }

        public void update(int l,int r,long val){
            update(0,0,n-1,l,r,val);
        }
        public long query(int l,int r){
            return query(0,0,n-1,l,r);
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
        int m = sc.nextInt();
        long[] nums = sc.nextLongArray(n);
        long[] prefix = new long[n];
        prefix[0] = nums[0];
        for(int i=1;i<n;i++) prefix[i] = prefix[i-1] + nums[i];
        SegmentTree st = new SegmentTree(prefix);
        while(m -- >0){
            int type = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            a--;
            if(type==1){
                long delta = b - nums[a];
                st.update(a,n-1,delta);
                nums[a] = b;
            }else{
                b--;
                long prev = a-1 >=0 ? st.query(a-1,a-1) : 0;
                long curr = st.query(a,b);
                curr = Math.max(0,curr - prev);
                out.append(curr).append("\n");
            }
        }
    }

}
