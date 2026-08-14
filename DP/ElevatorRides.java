import java.io.*;
import java.util.*;
public class ElevatorRides{
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
    static class Pair{
        long rides;
        long wt;
        Pair(long _rides,long _wt){
            rides = _rides;
            wt = _wt;
        }
        Pair(){
            rides = Long.MAX_VALUE;
            wt = Long.MAX_VALUE;
        }
    }
    static final long M = 1_000_000_007L;
    static final long INF = Long.MAX_VALUE;
    static long w;
    static int n;
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
        n = sc.nextInt();
        w = sc.nextInt();
        long[] nums = sc.nextLongArray(n);
        Pair[] dp = new Pair[1<<n];
        dp[0] = new Pair(1,0);
        
        for(int mask = 1;mask<(1<<n);mask++){
            Pair curr = new Pair();
            for(int i=0;i<n;i++){
                if(((mask>>i)&1)==0) continue;
                Pair prev = dp[mask ^ 1<<i];
                
                if(prev.wt + nums[i] <= w){
                    if(curr.rides==prev.rides){
                        curr.wt = Math.min(curr.wt,prev.wt+nums[i]);
                    }
                    else if(curr.rides > prev.rides){
                        curr.rides = prev.rides;
                        curr.wt = prev.wt + nums[i];
                    }    
                }else{
                    if(curr.rides == prev.rides + 1){
                        curr.wt = Math.min(curr.wt,prev.wt);
                    } 
                    else if(curr.rides > prev.rides + 1){
                        curr.rides = prev.rides +1;
                        curr.wt = nums[i];
                    }
                }
            }
            dp[mask] = curr;
        }
        out.append(dp[(1<<n)-1].rides);
    }
}