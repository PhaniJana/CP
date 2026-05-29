import java.util.*;

class DistanceQueries {

    static List<List<Integer>> tree;
    static int LOG = 17;

    static void dfs(int node, int par, int[] dis, int[][] dp) {

        dp[node][0] = par;

        for (int i = 1; i <= LOG; i++) {
            if (dp[node][i - 1] != -1)
                dp[node][i] = dp[dp[node][i - 1]][i - 1];
            else
                dp[node][i] = -1;
        }

        for (int child : tree.get(node)) {
            if (child == par) continue;

            dis[child] = dis[node] + 1;

            dfs(child, node, dis, dp);
        }
    }

    static int lift(int node, int k, int[][] dp) {

        for (int i = LOG; i >= 0; i--) {
            if ((k & (1 << i)) != 0) {
                node = dp[node][i];

                if (node == -1) break;
            }
        }

        return node;
    }

    static int lca(int a, int b, int[][] dp, int[] dis) {

        if (dis[a] > dis[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        b = lift(b, dis[b] - dis[a], dp);

        if (a == b) return a;

        for (int i = LOG; i >= 0; i--) {

            if (dp[a][i] != dp[b][i]) {
                a = dp[a][i];
                b = dp[b][i];
            }
        }

        return dp[a][0];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();

        tree = new ArrayList<>();

        for (int i = 0; i <= n; i++)
            tree.add(new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        int[] dis = new int[n + 1];

        int[][] dp = new int[n + 1][LOG + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        dfs(1, -1, dis, dp);

        StringBuilder ans = new StringBuilder();

        while (q-- > 0) {

            int a = sc.nextInt();
            int b = sc.nextInt();

            int com = lca(a, b, dp, dis);

            int distance = dis[a] + dis[b] - 2 * dis[com];

            ans.append(distance).append("\n");
        }

        System.out.print(ans);
    }
}