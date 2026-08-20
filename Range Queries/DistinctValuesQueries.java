import java.io.*;
import java.util.*;
public class DistinctValuesQueries{
    static class CoordinateCompression{
        int[] nums;
        List<Integer> unq;
        CoordinateCompression(int[] _nums){
            nums = _nums.clone();
            build();
            getArray(_nums);
        }
        private void build(){
            Arrays.sort(nums);
            unq = new ArrayList<>();
            for(int num : nums){
                if(unq.isEmpty() || unq.get(unq.size()-1)!=num) unq.add(num);
            }
        }
        public int getSize(){
            return unq.size();
        }
        public void getArray(int[] arr){
            int n = arr.length;
            for(int i=0;i<n;i++){
                arr[i] = search(arr[i]);
            }
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
    static class Query{
        int i;
        int l;
        int r;
        Query(int i,int l,int r){
            this.i = i;
            this.l = l;
            this.r = r;
        }
    }
    static final long M = 1_000_000_007L;
    static final long INF = Long.MAX_VALUE;
    static int BLOCK = 450;
    static int st;
    static int end;
    static int[] freq;
    static int unq;
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

    static void add(int num){
        freq[num]++;
        if(freq[num]==1) unq++;
    }
    static void remove(int num){
        freq[num]--;
        if(freq[num]==0) unq--;
    }
    static void solve(FastReader sc, StringBuilder out) throws Exception {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] nums = sc.nextIntArray(n);
        CoordinateCompression cc = new CoordinateCompression(nums);
        Query[] queries = new Query[q];
        for(int i=0;i<q;i++){
            queries[i] = new Query(i,sc.nextInt(),sc.nextInt());
        }
        Arrays.sort(queries, (a, b) -> {
            int blockA = a.l / BLOCK;
            int blockB = b.l / BLOCK;

            if (blockA != blockB)
                return Integer.compare(blockA, blockB);

            if ((blockA & 1) == 0)
                return Integer.compare(a.r, b.r);
            else
                return Integer.compare(b.r, a.r);
        });
        st = 0;
        end = -1;
        freq = new int[cc.getSize()+2];
        unq = 0;
        int[] ans = new int[q];
        for(Query qry : queries){
            int l = --qry.l;
            int r = --qry.r;
            while(st < l) remove(nums[st++]);
            while(end > r) remove(nums[end--]);

            while(st > l) add(nums[--st]);
            while(end < r) add(nums[++end]);
            ans[qry.i] = unq;
        }
        for(int i=0;i<q;i++) out.append(ans[i]).append("\n");
         
        
    }

}
