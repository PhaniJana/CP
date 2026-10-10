import java.io.*;
import java.util.*;
public class RepeatingSubstring{
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
    static class DoubleRollingHash {
        private static final long MOD1 = 1_000_000_007L;
        private static final long MOD2 = 1_000_000_009L;
    
        private static final long BASE1 = 911382323L;
        private static final long BASE2 = 972663749L;
    
        private final long[] hash1, hash2;
        private final long[] pow1, pow2;
    
        public DoubleRollingHash(char[] arr) {
            int n = arr.length;
    
            hash1 = new long[n + 1];
            hash2 = new long[n + 1];
            pow1 = new long[n + 1];
            pow2 = new long[n + 1];
    
            pow1[0] = pow2[0] = 1;
    
            for (int i = 0; i < n; i++) {
                pow1[i + 1] = (pow1[i] * BASE1) % MOD1;
                pow2[i + 1] = (pow2[i] * BASE2) % MOD2;
    
                // +1 so that 0 values also contribute
                long val = arr[i] + 1L;
    
                hash1[i + 1] = (hash1[i] * BASE1 + val) % MOD1;
                hash2[i + 1] = (hash2[i] * BASE2 + val) % MOD2;
            }
        }
    
        // Returns hash of subarray [l, r] (inclusive)
        public long getHash(int l, int r) {
            long x1 = (hash1[r + 1] - hash1[l] * pow1[r - l + 1]) % MOD1;
            if (x1 < 0) x1 += MOD1;
    
            long x2 = (hash2[r + 1] - hash2[l] * pow2[r - l + 1]) % MOD2;
            if (x2 < 0) x2 += MOD2;
    
            
            return ((x1<<32) | x2);
        }
    }

    static final long M = 1_000_000_007L;
    static final long INF = Long.MAX_VALUE;
    static DoubleRollingHash h;
    static int n;
    static char[] arr;
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
        String s = sc.next();
        n = s.length();
        arr = s.toCharArray();
        h = new DoubleRollingHash(arr);
        int len = 0;
        int st = 0;
        int l = 1,r = n-1;
        while(l<=r){
            int mid = l + (r-l)/2;
            int idx = check(mid);
            if(idx!=-1){
                len = mid;
                st = idx;
                l = mid + 1;
            }
            else r = mid-1;
        }
        if(len==0) out.append(-1);
        else{
            out.append(s.substring(st,st+len));
        }
    }
    static int check(int len){
        Set<Long> set = new HashSet<>();
        for(int i=0;i<=n-len;i++) if(!set.add(h.getHash(i,i+len-1))) return i;
        return -1;
    }

}
