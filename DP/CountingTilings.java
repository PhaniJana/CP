import java.io.*;
import java.util.*;
public class CountingTilings{
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
    static void generate_masks(int idx,int currMask,int mask,List<Integer> nextMasks,int n){
        if(idx==n){
            nextMasks.add(currMask);
            return;
        }
        int prev = (mask>>idx) & 1;
        if(prev==1){
            generate_masks(idx+1,currMask,mask,nextMasks,n);
        }
        else{
            if(idx+1<n && ((mask>>(idx+1)) & 1)!=1){
                generate_masks(idx+2,currMask,mask,nextMasks,n);
            }
            generate_masks(idx+1,currMask | (1<<idx),mask,nextMasks,n);
        }
    }
    static long solve(int col,int mask,long[][] dp,int n,int m){
        if(col==m){
            return mask==0 ? 1 : 0;
        }
        if(dp[col][mask]!=-1) return dp[col][mask];
        long ways = 0;
        List<Integer> nextMasks = new ArrayList<>();
        generate_masks(0,0,mask,nextMasks,n);
        for(int nextMask : nextMasks){
            ways = (ways + solve(col+1,nextMask,dp,n,m))%M;
        }
        return dp[col][mask] = ways;
    }
    static void solve(FastReader sc, StringBuilder out) throws Exception {
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[][] dp = new long[m][1<<n];
        for(long[] arr : dp) Arrays.fill(arr,-1);
        out.append(solve(0,0,dp,n,m));

    }
}
