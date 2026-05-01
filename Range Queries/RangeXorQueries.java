import java.io.*;
import java.util.*;
public class RangeXorQueries {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = sc.nextIntArray(n);
        int[] prefixXor = new int[n+1];
        for(int i=1;i<=n;i++){
            prefixXor[i] = prefixXor[i-1] ^ arr[i-1];
        }
        StringBuilder ans = new StringBuilder();
        for(int i=0;i<q;i++){
            int l = sc.nextInt();
            int r = sc.nextInt();
            l--;
            r--;
            int xor = prefixXor[r+1] ^ prefixXor[l];
            ans.append(xor).append("\n");
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
    // -------- Arrays --------
    int[] nextIntArray(int n) throws IOException {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = nextInt();
        return arr;
    }

}
