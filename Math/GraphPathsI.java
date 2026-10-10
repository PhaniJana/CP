import java.io.*;
import java.util.*;
public class GraphPathsI{
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
        static long[][] I;
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
        static long[][] pow(long[][] base ,long n){
                if(n==0) return I;
         
                long[][] res = pow(base,n/2);
                res = mul(res,res);
                if(n%2==1) res = mul(res,base);
                return res;
        }
        static long[][] mul(long[][] A, long[][] B) {
                int n = A.length;
                int m = B[0].length;
                int k = B.length;
         
                long[][] C = new long[n][m];
         
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        for (int x = 0; x < k; x++) {
                            C[i][j] = (C[i][j] + A[i][x] * B[x][j]) % M;
                        }
                    }
                }
         
                return C;
        }
 
 

    static void solve(FastReader sc, StringBuilder out) throws Exception {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            long[][] T = new long[n][n];
            I = new long[n][n];
            for(int i=0;i<n;i++) I[i][i] = 1;
            while(m-- > 0){
                    int u = sc.nextInt()-1;
                    int v = sc.nextInt()-1;
                    T[u][v] = (T[u][v] + 1)%M;
            }
            // ways to reach node x using k edges
            long[][] mat = pow(T,k);
            out.append(mat[0][n-1]);
    }

}