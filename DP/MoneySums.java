import java.util.*;
public class MoneySums {
    static int[] a;
    static boolean[] possible;
    static boolean[][] dp;
    static int n;
    private static void solve(int idx,int sum){
        if(idx==n){
            possible[sum] = true;
            return;
        }
        if(dp[idx][sum]) return;
        
        solve(idx+1,sum);
        solve(idx+1,sum + a[idx]);
        
        dp[idx][sum] = true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = new int[n];
        int sum=0;
        for(int i=0;i<n;i++) {
            int num = sc.nextInt();
            a[i] = num;
            sum+=num;
        }
        dp = new boolean[n][sum+1];
        possible = new boolean[sum+1];
        solve(0,0);
        int count=0;
        StringBuilder ans = new StringBuilder();
        for(int i=1;i<=sum;i++) if(possible[i]) {
            count++;
            ans.append(i).append(" ");
        }
        System.out.println(count);
        System.out.print(ans);
    }
}