import java.io.*;
import java.util.*;
public class InverseInversions{
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
        SegmentTree(int n){
            this.n = n;
            tree = new long[4*n];
            build(0,0,n-1);
        }
        private void build(int idx,int l,int r){
            if(l==r){
                tree[idx] = 1L;
                return;
            }
            int mid = l + (r-l)/2;
            build(2*idx+1,l,mid);
            build(2*idx+2,mid+1,r);
            tree[idx] = tree[2*idx+1] + tree[2*idx+2];
        }
        public int query(long key){
            return query(0,0,n-1,key);
        }
        private int query(int idx,int l,int r,long key){
            if(l==r){
                tree[idx]--;
                return l;
            }
            int mid = l + (r-l)/2;
            int ans;
            if(tree[2*idx+1] >= key) ans = query(2*idx+1,l,mid,key);
            else ans = query(2*idx+2,mid+1,r,key - tree[2*idx+1]);
            tree[idx] = tree[2*idx+1] + tree[2*idx+2];
            return ans;
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
        long k = sc.nextLong();
        SegmentTree sg = new SegmentTree(n);
        int[] ans = new int[n+1];
        for(int i=1;i<=n;i++){
            int rem = n - i;
            long max = (1L*rem*(rem-1))/2;
            long cnt = Math.max(0,k - max);
            ans[i] = sg.query(cnt+1) + 1;
            k -= cnt;
        }
        for(int i=1;i<=n;i++) out.append(ans[i]).append(" ");
    }

}
