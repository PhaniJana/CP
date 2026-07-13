import java.util.*;
public class RemovalGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] nums = new long[n];
        for(int i=0;i<n;i++) nums[i] = sc.nextLong();
        long[][] dp = new long[n][n];
        for(int g=0;g<n;g++){
            for(int i=0,j=g;j<n;j++,i++){
                if(g==0){
                    dp[i][j] = nums[i];
                }
                else if(g==1){
                    dp[i][j] = Math.max(nums[i],nums[j]);
                }
                else{
                    dp[i][j] = Math.max(nums[i] + Math.min(dp[i+2][j],dp[i+1][j-1]),nums[j] + Math.min(dp[i][j-2],dp[i+1][j-1]));
                }
            }
        }
        System.out.print(dp[0][n-1]);
    }
}