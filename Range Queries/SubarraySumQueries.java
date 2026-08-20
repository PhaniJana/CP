import java.io.*;
import java.util.*;
public class SubarraySumQueries{
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
    static class Node{
        long prefix;
        long suffix;
        long max;
        long sum;
        Node(){
            prefix = 0;
            suffix = 0;
            max = 0;
            sum = 0;
        }
        Node(long val){
            prefix = val;
            suffix = val;
            max = val;
            sum = val;
        }
    }
    static class SegmentTree{
        Node[] tree;
        int n;
        SegmentTree(long[] nums,int n){
            this.n = n;
            tree = new Node[4*n];
            build(0,0,n-1,nums);
        }
        private void build(int idx,int l,int r,long[] nums){
            if(l==r){
                tree[idx] = new Node(nums[l]);
                return;
            }
            int mid = (l+r)/2;
            build(2*idx+1,l,mid,nums);
            build(2*idx+2,mid+1,r,nums);
            tree[idx] = new Node();
            merge(tree[idx],tree[2*idx+1],tree[2*idx+2]);
        }
        private void merge(Node c,Node a,Node b){
            c.sum = a.sum + b.sum;
            c.prefix = Math.max(a.prefix,a.sum + b.prefix);
            c.suffix = Math.max(b.suffix,b.sum + a.suffix);
            c.max = Math.max(a.max,Math.max(b.max,a.suffix + b.prefix));
        }
        private void update(int idx,int l,int r,int index,int val){
            if(l==r){
                tree[idx] = new Node(val);
                return;
            }
            int mid = (l+r)/2;
            if(index<=mid) update(2*idx+1,l,mid,index,val);
            else update(2*idx+2,mid+1,r,index,val);
            merge(tree[idx],tree[2*idx+1],tree[2*idx+2]);
        }
        
        public long update(int idx,int val){
            update(0,0,n-1,idx,val);
            return Math.max(0L,tree[0].max); 
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
        long[] arr = sc.nextLongArray(n);
        SegmentTree sg = new SegmentTree(arr,n);
        for(int i=0;i<m;i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt();
            out.append(sg.update(a,b)).append("\n");
        }
    }

}


