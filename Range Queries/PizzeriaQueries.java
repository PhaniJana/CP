import java.io.*;
import java.util.*;
public class PizzeriaQueries{
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
        int n;
        SegmentTree(int n,long[] nums){
            this.n = n;
            tree = new long[4*n];
            build(0,0,n-1,nums);
        }
        private void build(int idx,int l,int r,long[] nums){
            if(l==r){
                tree[idx] = nums[l];
                return;
            }
            int mid = l + (r-l)/2;
            build(2*idx+1,l,mid,nums);
            build(2*idx+2,mid+1,r,nums);
            tree[idx] = Math.min(tree[2*idx+1],tree[2*idx+2]);
        }

        private long query(int idx,int l,int r,int p,int q){
            if(r<p || l>q) return Long.MAX_VALUE;
            if(l>=p && r<=q) return tree[idx];
            int mid = l + (r-l)/2;
            return Math.min(query(2*idx+1,l,mid,p,q),query(2*idx+2,mid+1,r,p,q));
        }

        private void update(int idx,int l,int r,int index,long val){
            if(l==r){
                tree[idx] += val;
                return;
            }
            int mid = l + (r-l)/2;
            if(index<=mid) update(2*idx+1,l,mid,index,val);
            else update(2*idx+2,mid+1,r,index,val);

            tree[idx] = Math.min(tree[2*idx+1],tree[2*idx+2]);
        }
        
        public long query(int l,int r){
            return query(0,0,n-1,l,r);
        }
        public void update(int idx,long val){
            update(0,0,n-1,idx,val);
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
        long[] nums = new long[n+1];
        long[] plus = new long[n+1];
        long[] sub = new long[n+1];
        for(int i=0;i<n;i++){
            nums[i] = sc.nextLong();
            plus[i] = nums[i] + (i+1);
            sub[i] = nums[i] - (i+1);
        }
        SegmentTree sg1 = new SegmentTree(n,plus);
        SegmentTree sg2 = new SegmentTree(n,sub);
        while(m-- > 0){
            int type = sc.nextInt();
            if(type==1){
                int idx = sc.nextInt()-1;
                long val = sc.nextLong();
                long delta = val - nums[idx];
                nums[idx] = val;
                sg1.update(idx,delta);
                sg2.update(idx,delta);
            }else{
                int idx = sc.nextInt();
                long ans = Math.min(sg2.query(0,idx-1) + idx,sg1.query(idx-1,n-1) - idx);
                out.append(ans).append("\n");
            }
        }
    }

}
