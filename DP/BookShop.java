import java.util.*;
class BookShop {
    static int[] price;
    static int n;
    static int[] pages;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int x = sc.nextInt();
        price = new int[n];
        pages = new int[n];
        for(int i=0;i<n;i++) price[i] = sc.nextInt();
        for(int i=0;i<n;i++) pages[i] = sc.nextInt();
        dp = new int[n+1][x+1];

        for(int idx=n-1;idx>=0;idx--){
            for(int p=0;p<=x;p++){
                int notPick = dp[idx+1][p];
                int pick = 0;
                if(p-price[idx]>=0) pick=pages[idx] + dp[idx+1][p-price[idx]];
                dp[idx][p] = Math.max(pick,notPick);
            }
        }
        System.out.print(dp[0][x]);

    }
}
