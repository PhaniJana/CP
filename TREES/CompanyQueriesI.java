import java.io.*;
import java.util.*;

public class CompanyQueriesI {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] boss = new int[n+1];
        boss[1] = -1;
        for(int i=2;i<=n;i++) boss[i] = sc.nextInt();
        int[][] dp = new int[n+1][18];
        for(int[] arr : dp) Arrays.fill(arr,-1);
        for(int i=2;i<=n;i++){
            dp[i][0] = boss[i];
            for(int j=1;j<=17;j++){
                if(dp[i][j-1]!=-1){
                    dp[i][j] = dp[dp[i][j-1]][j-1];
                }
                else dp[i][j] = 1;
            }
        }
        StringBuilder ans = new StringBuilder();
        for(int i=0;i<q;i++){
            int node = sc.nextInt();
            int k = sc.nextInt();
            for(int bit=17;bit>=0;bit--){
                if((k&(1<<bit))!=0){
                    if(node!=-1){
                        node = dp[node][bit];
                    }else break;
                }
            }
            ans.append(node).append("\n");
        }
        System.out.print(ans);
    }
}

class FastReader {
    BufferedReader br;
    StringTokenizer st;
    FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}