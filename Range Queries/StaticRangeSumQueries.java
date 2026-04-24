import java.io.*;
import java.util.*;
public class StaticRangeSumQueries {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = sc.nextIntArray(n);
        long[] prefix = new long[n+1];
        prefix[1] = arr[0];
        for(int i=2;i<=n;i++){
            prefix[i] = 1L * prefix[i-1] + arr[i-1];
        }
        int[][] queries = new int[q][2];
        for(int i=0;i<q;i++){
            queries[i] = sc.nextIntArray(2);
        }
        StringBuilder ans = new StringBuilder();
        for(int[] query : queries){
            int l = query[0];
            int r = query[1];
            ans.append(prefix[r] - prefix[l-1]).append("\n");
        }
        System.out.print(ans);
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