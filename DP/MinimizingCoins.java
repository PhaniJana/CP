import java.util.*;
class MinimizingCoins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int val = sc.nextInt();
        int[] c = new int[n];
        for(int i=0;i<n;i++) c[i] = sc.nextInt();
        
        int[][] dp = new int[n+1][val+1];
        int invalid =(int)1e9 + 7;
        for(int x = 1;x<=val;x++) dp[n][x] = invalid;
        
        for(int idx=n-1;idx>=0;idx--){
            for(int x=0;x<=val;x++){
                int pick = x-c[idx]>=0 ? 1 + dp[idx][x-c[idx]] : invalid;
                int notPick = dp[idx+1][x];
                dp[idx][x]=Math.min(pick, notPick);
            }
        }
        int ans = dp[0][val];
        ans = ans>= invalid ? -1 : ans;
        System.out.print(ans);
    }
}