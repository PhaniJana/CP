import java.io.*;
import java.util.*;
public class BitInversions{
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
        int prefixNum;
        int suffixNum;
        int prefixLen;
        int suffixLen;
        int len;
        int ans;
        Node(int pn,int sn,int pl,int sl,int l,int a){
            prefixNum = pn;
            suffixNum = sn;
            prefixLen = pl;
            suffixLen = sl;
            len = l;
            ans = a;
        }
    }
    static class SegmentTree{
        int n;
        Node[] tree;
        int[] nums;
        SegmentTree(int[] nums,int n){
            this.n = n;
            this.nums = nums;
            tree = new Node[4*n];
            build(0,0,n-1);
        }
        private Node merge(Node a,Node b){
            int prefixNum = a.prefixNum;
            int suffixNum = b.suffixNum;
            int prefixLen = a.prefixLen;
            int suffixLen = b.suffixLen;
            int len = a.len + b.len;
            int ans = Math.max(a.ans,b.ans);

            if(a.suffixNum == b.prefixNum){
                ans = Math.max(ans,a.suffixLen + b.prefixLen);
                if(a.prefixLen==a.len) prefixLen = a.prefixLen + b.prefixLen;
                if(b.suffixLen == b.len) suffixLen = b.suffixLen + a.suffixLen;
            }
            return new Node(prefixNum,suffixNum,prefixLen,suffixLen,len,ans);
        }
        private void build(int idx,int l,int r){
            if(l==r){
                tree[idx] = new Node(nums[l],nums[l],1,1,1,1);
                return;
            }
            int mid = l + (r-l)/2;
            build(2*idx+1,l,mid);
            build(2*idx+2,mid+1,r);
            tree[idx] = merge(tree[2*idx+1],tree[2*idx+2]);
        }
        private void update(int idx,int l,int r,int index){
            if(l==r){
                nums[l] = 1 - nums[l];
                tree[idx] = new Node(nums[l],nums[l],1,1,1,1);
                return;
            }
            int mid = l + (r-l)/2;
            if(index<=mid) update(2*idx+1,l,mid,index);
            else update(2*idx+2,mid + 1,r,index);
            tree[idx] = merge(tree[2*idx+1],tree[2*idx+2]);
        }
        public int update(int idx){
            update(0,0,n-1,idx);
            return tree[0].ans;
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
        char[] temp = sc.next().toCharArray();
        int n = temp.length;
        int[] nums = new int[n];
        for(int i=0;i<n;i++) nums[i] = temp[i] - '0';
        SegmentTree sg = new SegmentTree(nums,n);
        int m = sc.nextInt();
        while(m-- > 0){
            int idx = sc.nextInt();
            idx--;
            out.append(sg.update(idx)).append(" ");
        }
    }

}


