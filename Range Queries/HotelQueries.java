import java.io.*;
import java.util.*;
public class HotelQueries{
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
        int n;
        long[] tree;
        SegmentTree(int n,int[] nums){
            this.n = n;
            tree = new long[4*n];
            build(0,0,n-1,nums);
        }
        private void build(int idx,int l,int r,int[] nums){
            if(l==r){
                tree[idx] = nums[l];
                return;
            }
            int mid = l + (r-l)/2;
            build(2*idx+1,l,mid,nums);
            build(2*idx+2,mid+1,r,nums);
            tree[idx] = Math.max(tree[2*idx+1],tree[2*idx+2]);
        }
        private int walk(int idx,int l,int r,int val){
            if(l==r){
                if(tree[idx]>=val){
                    tree[idx] -= val;
                    return l;
                }
                return -1;
            }
            int ans = -1;
            int mid = l + (r-l)/2;
            if(tree[2*idx+1]>=val) ans = walk(2*idx+1,l,mid,val);
            else if(tree[2*idx+2]>=val) ans = walk(2*idx+2,mid+1,r,val);
            tree[idx] = Math.max(tree[2*idx+1],tree[2*idx+2]);
            return ans;
        }
        public int walk(int val){
            return walk(0,0,n-1,val);
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
        int q = sc.nextInt();
        int[] nums = sc.nextIntArray(n);
        SegmentTree sg = new SegmentTree(n,nums);
        while(q-- > 0){
            int val = sc.nextInt();
            int ans = sg.walk(val)+1;
            out.append(ans).append(" ");
        }
    }

}
