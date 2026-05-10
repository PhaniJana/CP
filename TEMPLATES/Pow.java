import java.io.*;
import java.util.*;

public class Pow{
    static long M = (int)1e9 + 7;
    private static long solve(long num,long pow){
        if(pow==0) return 1;
        long half = solve(num,pow/2);
        long ans = (half * half) % M;
        if(pow%2==1) ans = (ans * num) % M;
        return ans % M;
    } 
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        StringBuilder ans = new StringBuilder();
        int n = sc.nextInt();
        while(n-->0){
            long a = sc.nextLong();
            long b = sc.nextLong();
            ans.append(solve(a,b)).append("\n");
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

}
