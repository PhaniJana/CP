import java.io.*;
import java.util.StringTokenizer;

class ArrayDescription {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new  InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for(int i = 0; i < n; i++) a[i] =  Integer.parseInt(st.nextToken());
        int M = (int)1e9+7;
        int[][] dp = new int[n+1][m+1];
        // System.out.print(solve(0,0,a,n,m));

        for(int i=0;i<=m;i++) dp[n][i] = 1;

        for(int idx=n-1;idx>=0;idx--){
            for(int prevVal=m;prevVal>=0;prevVal--){
                int ways=0;
                if(a[idx]!=0){
                    if(prevVal==0 || Math.abs(a[idx]-prevVal)<=1) {
                        ways = (ways + dp[idx+1][a[idx]]) % M;
                    }
                }
                else{
                    int l = 1;
                    int r = m;
                    if(prevVal != 0){
                        l = Math.max(1, prevVal - 1);
                        r = Math.min(m, prevVal + 1);
                    }
                    for(int i=l;i<=r;i++){
                        ways = (ways + dp[idx+1][i]) % M;
                    }

                }
                dp[idx][prevVal] = ways;
            }
        }
        System.out.print(dp[0][0]);
    }
}