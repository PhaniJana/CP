import java.util.Arrays;
import java.util.Scanner;

public class RemovingDigits {
    /*private static int solve(int n){
        if(n<10) return 1;
        if(dp[n]!=-1)  return dp[n];
        int k =n;
        int min = Integer.MAX_VALUE;
        while(k>0){
            int rem = k%10;
            if(rem!=0) min=Math.min(min,1+solve(n-rem));
            k/=10;
        }
        return dp[n]=min;
    }*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n<10) {
            System.out.println(1);
            return;
        }
        int[] dp = new int[n+1];

        for(int i=0;i<10;i++) dp[i]=1;
        for(int i=10;i<=n;i++){
            int k = i;
            int min = Integer.MAX_VALUE;
            while(k>0){
                int rem = k%10;
                if(rem!=0) min=Math.min(min,1 + dp[i-rem]);
                k/=10;
            }
            dp[i]=min;
        }
        System.out.println(dp[n]);
    }
}
