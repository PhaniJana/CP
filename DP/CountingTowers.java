import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class CountingTowers {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        long M =(long)1e9 + 7;
        StringBuilder ans = new StringBuilder();
        int[] q =  new int[t];
        int max=0;
        for (int i = 0; i < t; i++) {
            q[i] = sc.nextInt();
            max = Math.max(max,q[i]);
        }
        long[][] dp = new long[max+1][2];

        dp[1][0] = dp[1][1] = 1L;
        for(int i=2;i<=max;i++){
            dp[i][1] = (4L*dp[i-1][1]  + dp[i-1][0]) % M;
            dp[i][0] = (2L*dp[i-1][0]  + dp[i-1][1]) % M;
        }

        for(int n : q) ans.append((dp[n][0]+dp[n][1]) % M).append(" ");

        System.out.print(ans);
    }
}