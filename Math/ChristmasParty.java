import java.io.*;
import java.util.*;
public class ChristmasParty{
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
        static long add(long a,long b) {return (a+b)%M;}
        static long sub(long a,long b) {return (a-b+M)%M;}
        static long mul(long a,long b) {return (a*b)%M;}
        static long div(long a,long b) {return (a*pow(b,M-2))%M;}
        static long pow(long a,long b){
            if(b==0) return 1;
            long half = pow(a,b/2);
            long ans = (half*half)%M;
            if(b%2==1) ans = (ans * a)%M;
            return ans;
        }

    static void solve(FastReader sc, StringBuilder out) throws Exception {
        int n = sc.nextInt();
        long[] fact = new long[n+1];
            fact[0] = 1;
            for(int i=1;i<=n;i++) fact[i] = mul(fact[i-1],i);
            long inside = 0;
            for(int i=0;i<=n;i++){
                    long curr = div(1,fact[i]);
                    if(i % 2 == 0) inside = add(inside,curr);
                    else inside = sub(inside,curr);
            }
            inside = mul(fact[n],inside);
            out.append(inside);
    }

}