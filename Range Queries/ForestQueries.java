import java.io.*;
import java.util.*;

class ForestQueries {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int q = sc.nextInt();

        String[] s = new String[n];
        for(int i=0;i<n;i++) {
            s[i] = sc.next();
        }
        int[][] mat = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                mat[i][j] = s[i].charAt(j) == '.' ? 0 : 1;
            }
        }
        int[][] prefix = new int[n][n];
        prefix[0][0] = mat[0][0];
        for(int i=1;i<n;i++) {
            prefix[0][i] = prefix[0][i-1] + mat[0][i];
            prefix[i][0] = prefix[i-1][0] + mat[i][0];
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<n;j++){
                prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + mat[i][j];
            }
        }
        StringBuilder out = new StringBuilder();
        for(int i=0;i<q;i++){
            int r1 = sc.nextInt()-1;
            int c1 = sc.nextInt()-1;
            int r2 = sc.nextInt()-1;
            int c2 = sc.nextInt()-1;
            int ans = prefix[r2][c2];
            if(r1-1>=0) ans -= prefix[r1-1][c2];
            if(c1-1>=0) ans -= prefix[r2][c1-1];
            if(r1-1>=0 && c1-1>=0) ans+=prefix[r1-1][c1-1];
            out.append(ans).append("\n");
        }
        System.out.print(out);
        
    }
}

class FastReader {
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
