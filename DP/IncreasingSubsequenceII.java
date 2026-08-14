import java.io.*;
import java.util.*;
public class IncreasingSubsequenceII{
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
        SegmentTree(int _n){
            n = _n;
            tree = new long[4*n];
        }
        public void update(int idx,long val){
            update(0,0,n-1,idx,val);
        }
        public long query(int idx){
            return query(0,0,n-1,0,idx);
        }
        private long query(int idx,int l,int r,int p,int q){
            if(r<p || q<l) return 0;
            if(p<=l && r<=q) return tree[idx];
            int mid = (l+r)/2;
            return (query(2*idx+1,l,mid,p,q) + query(2*idx+2,mid+1,r,p,q))%M;
        }
        private void update(int idx,int l,int r,int index,long val){
            if(l==r) {
                tree[idx] = (tree[idx] + val)%M;
                return;
            }
            int mid = (l+r)/2;
            if(index<=mid){
                update(2*idx+1,l,mid,index,val);
            }else update(2*idx+2,mid+1,r,index,val);
            tree[idx] = (tree[2*idx+1] + tree[2*idx+2])%M;
        }
    }
    
    static class coordinateCompression{
        long[] nums;
        List<Long> unq;
        coordinateCompression(long[] _nums){
            nums = _nums.clone();
            build();
        }
        private void build(){
            Arrays.sort(nums);
            unq = new ArrayList<>();
            for(long num : nums){
                if(unq.isEmpty() || unq.get(unq.size()-1)!=num) unq.add(num);
            }
        }
        public int getSize(){
            return unq.size();
        }
        public int search(long num){
            int l = 0,r = unq.size()-1;
            while(l<=r){
                int mid = (l+r) / 2;
                if(unq.get(mid) == num) return mid+1;
                else if(unq.get(mid) > num) r = mid-1;
                else l = mid + 1;
            }
            return -1;
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
        long[] nums = sc.nextLongArray(n);
        coordinateCompression obj = new coordinateCompression(nums);
        SegmentTree st = new SegmentTree(obj.getSize()+1);
        st.update(0,1);
        long ways = 0;
        
        for(long num : nums){
            int idx = obj.search(num);
            long curr = st.query(idx-1);
            ways = (ways + curr) % M;
            st.update(idx,curr);
        }
        out.append(ways);
    }
}
