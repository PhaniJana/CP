import java.io.*;
import java.util.*;

public class ExponentiationII {
    
    private static long power(long a,long b,long M){
        if(b==0) return 1;
        long half = power(a,b/2,M);
        long ans = (half*half) % M;
        if(b%2==1) ans = (ans*a) % M;
        return ans;
    }
    private static long solve(long a,long b,long c){
        long M = (long)1e9 + 7;
        long pow = power(b,c,M-1);
        return power(a,pow,M);
    }
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        
        StringBuilder ans = new StringBuilder();
        while(n-->0){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            ans.append(solve(a,b,c)).append("\n");
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

}
