import java.io.*;
import java.util.*;
class CoinCombinationsII {
    
    public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] coins = new int[n];
        for(int i=0;i<n;i++) coins[i] = Integer.parseInt(st.nextToken());
        int mod = (int)1e9+7;
        int[] next = new int[target+1];
        next[0] = 1;
        for(int idx=n-1;idx>=0;idx--){
            int[] curr = new int[target+1];
            curr[0] = 1;
            for(int x=0;x<=target;x++){
            int pick = x-coins[idx]>=0 ? curr[x-coins[idx]] : 0;
                int notPick = next[x];
                curr[x] = (pick + notPick)%mod;
            }
        }
        System.out.print(next[target]);
    }
}