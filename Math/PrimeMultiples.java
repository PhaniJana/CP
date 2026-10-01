import java.io.*;
import java.util.*;
public class PrimeMultiples{
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
    static long gcd(long a,long b) {
        while(b!=0){
            long t = a%b;
            a = b;
            b = t;
        }
        return a;
    }
    static void solve(FastReader sc, StringBuilder out) throws Exception {
        long n = sc.nextLong();
        int k = sc.nextInt();
        long[] nums = sc.nextLongArray(k);
        int size = 1<<k;
        long[] lcm = new long[size];
        lcm[0] = 1;
        long total = 0;
        for(int m=1;m<size;m++){
            int prev = m & (m-1);
            if(lcm[prev]==n+1) {
                lcm[m] = n+1;
                continue;
            }
            int idx = Integer.numberOfTrailingZeros(m);
            long g = gcd(lcm[prev],nums[idx]);
            if(lcm[prev]/g > n/nums[idx]){
                lcm[m] = n + 1;
                continue;
            }
            lcm[m] = (lcm[prev]/g)*nums[idx];
            if(Integer.bitCount(m) % 2 == 1) total += n/lcm[m];
            else total -= n/lcm[m];
        }
        out.append(total);
    }

}



