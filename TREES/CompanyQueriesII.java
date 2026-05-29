import java.io.*;
import java.util.*;

public class CompanyQueriesII {
    private static int shift(int node,int k,int[][] dp){
        for(int i=17;i>=0;i--){
            if((k & (1<<i))!=0) {
                if(dp[node][i]!=-1) node = dp[node][i];
                else break;
            }
        }
        return node;
    } 
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int qry = sc.nextInt();
        int[] boss = new int[n+1];
        int[] depth = new int[n+1];
        for(int i=2;i<=n;i++) boss[i] = sc.nextInt();
        int[][] dp = new int[n+1][18];
        for(int[] arr : dp) Arrays.fill(arr,-1);
        for(int i=2;i<=n;i++){
            dp[i][0] = boss[i];
            for(int j=1;j<=17;j++){
                if(dp[i][j-1]!=-1) dp[i][j] = dp[dp[i][j-1]][j-1];
                else dp[i][j] = -1;
            }
        }
        @SuppressWarnings("unchecked")
        List<List<Integer>> tree = new ArrayList<>();
        for(int i=0;i<=n;i++) tree.add(new ArrayList<>());
        for(int i=2;i<=n;i++){
            tree.get(boss[i]).add(i);
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.addLast(1);
        while(!q.isEmpty()){
            int node = q.pollFirst();
            for(int child : tree.get(node)){
                depth[child] = 1 + depth[node];
                q.addLast(child);
            }
        }
        
        StringBuilder st = new StringBuilder();
        while(qry -- > 0){
            int u = sc.nextInt();
            int v = sc.nextInt();
            if(depth[u] > depth[v]){
                int temp = u;
                u = v;
                v = temp;
            }
            int reqJump = depth[v] - depth[u];
            v = shift(v,reqJump,dp);
            int l=0,h = depth[u];
            int ans=u;
            while(l<=h){
                int mid = (l+h)/2;
                int liftL = shift(u,mid,dp);
                int liftR = shift(v,mid,dp);
                if(liftL==liftR){
                    ans = liftL;
                    h = mid - 1;
                }else l = mid + 1;
            }
            st.append(ans).append("\n");
        }
        System.out.print(st);
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
