import java.util.*;
class EditDistance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        int n = a.length();
        int m = b.length();
        int dp[][] = new int[n+1][m+1];
        for(int i=0;i<=m;i++) dp[n][i] = m-i;
        for(int i=0;i<=n;i++) dp[i][m] = n-i;
        dp[n][m] = 0;
        for(int idx1=n-1;idx1>=0;idx1--){
            for(int idx2=m-1;idx2>=0;idx2--){
                if(a.charAt(idx1) == b.charAt(idx2)) {
                    dp[idx1][idx2] = dp[idx1+1][idx2+1];
                }
                else{
                    int delete = dp[idx1+1][idx2];
                    int add = dp[idx1][idx2+1];
                    int replace = dp[idx1+1][idx2+1];
                    dp[idx1][idx2] = 1+Math.min(delete,Math.min(add,replace));
                }
            }
        }
        System.out.print(dp[0][0]);
    }
}