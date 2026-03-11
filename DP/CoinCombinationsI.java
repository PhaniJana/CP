import java.io.*;
import java.util.*;

class CoinCombinationsI {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] coins = new int[n];
        for(int i=0;i<n;i++)
            coins[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(coins);

        int[] dp = new int[target+1];
        int mod = 1000000007;

        dp[0] = 1;

        for(int x=1;x<=target;x++){

            int ways = 0;

            for(int coin : coins){

                if(coin > x) break;

                ways += dp[x-coin];

                if(ways >= mod) ways -= mod;
            }

            dp[x] = ways;
        }

        System.out.println(dp[target]);
    }
}