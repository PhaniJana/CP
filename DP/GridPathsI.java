import java.util.*;
public class GridPathsI {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[][] a = new char[n][n];
        for(int i=0;i<n;i++){
            String s = sc.next();
            a[i] = s.toCharArray();
        }
        if(a[0][0]=='*' || a[n-1][n-1]=='*'){
            System.out.println(0);
            return;
        }
        int m = (int)1e9+7;
        int[][] dp = new int[n][n];
        dp[n-1][n-1]=1;
        for(int r=n-1;r>=0;r--){
            for(int c=n-1;c>=0;c--){
                if(r==n-1 && r==c) continue;
                int ways=0;
                if(r+1<n && a[r+1][c]!='*') ways = (ways + dp[r+1][c])%m;
                if(c+1<n && a[r][c+1]!='*') ways = (ways + dp[r][c+1])%m;
                dp[r][c]=ways;
            }
        }
        System.out.print(dp[0][0]);
    }
}