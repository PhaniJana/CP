import java.util.*;
class DiceCombinations {
    public static void main(String[] args) {
        int mod = (int)1e9 + 7;
        Scanner sc =new  Scanner(System.in);
        int N= sc.nextInt();
        int[] dp = new int[N+1];
        dp[0] = 1;
        for(int n=1;n<=N;n++){
            int ways=0;
            for(int i=1;i<=6;i++){
                if(n-i>=0) ways = (ways + dp[n-i] % mod) % mod; 
            }
            dp[n]=ways;
        }
        System.out.print(dp[N]);
    }
}