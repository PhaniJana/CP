import java.util.*;
class RectangleCutting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int[][] dp = new int[h+1][w+1];
        for(int i=1;i<=h;i++) dp[i][1] = i-1;
        for(int i=1;i<=w;i++) dp[1][i] = i-1;
        for(int a=2;a<=h;a++){
            for(int b=2;b<=w;b++){
                if(a==b) continue;
                int min = Integer.MAX_VALUE;
                for(int i=1;i<b;i++){
                    min = Math.min(min,1+dp[a][i]+dp[a][b-i]);
                }
                for(int i=1;i<a;i++){
                    min = Math.min(min,1+dp[i][b]+dp[a-i][b]);
                }
                dp[a][b]=min;
            }
        }
        System.out.print(dp[h][w]);
    }
}