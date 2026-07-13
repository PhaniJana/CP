import java.util.*;
import java.io.*;
class Monsters {
    private static void add(int r,int c,int nr,int nc,char[][] par){
        if(nr == r && nc == c+1) par[nr][nc] = 'R';
        else if(nr == r && nc == c-1) par[nr][nc] = 'L';
        else if(nr == r+1 && nc == c) par[nr][nc] = 'D';
        else par[nr][nc] = 'U';
    }
    private static int[] newRc(int r,int c,char ch){
        if(ch=='L') return new int[]{r,c+1};
        if(ch=='R') return new int[]{r,c-1};
        if(ch=='U') return new int[]{r+1,c};
        return new int[]{r-1,c}; // D
    }
    private static void getPath(int r,int c,char[][] par,int st,int end){
        StringBuilder ans = new StringBuilder();
        while(r!=st || c!=end){
            ans.append(par[r][c]);
            int[] a = newRc(r,c,par[r][c]);
            r = a[0];
            c = a[1];
        }
        System.out.println(ans.reverse());
    }
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] grid = sc.nextStringArray(n);
        
        int INF = (int)1e9;
        int[][] monsterTime = new int[n][m];
        boolean[][] vis = new boolean[n][m];
        char[][] par = new char[n][m];
        for(int[] arr : monsterTime) Arrays.fill(arr,INF);
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> q1 = new LinkedList<>();
        int ar = -1,ac = -1;
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i].charAt(j)=='M') {
                    q.add(new int[]{i,j,0});
                    monsterTime[i][j] = 0;
                }
                if(grid[i].charAt(j)=='A') {
                    vis[i][j] = true;
                    q1.add(new int[]{i,j,0});
                    ar = i;
                    ac = j;
                }
            }
        }
        while(!q.isEmpty()){
            int[] cell = q.poll();
            int row = cell[0];
            int col = cell[1];
            int dis = cell[2];
            for(int i=0;i<4;i++){
                int nextRow = row + dir[i][0];
                int nextCol = col + dir[i][1];
                if(nextRow >= 0 && nextRow<n && nextCol >= 0 && nextCol<m){
                    if(grid[nextRow].charAt(nextCol)!='#'){
                        if(monsterTime[nextRow][nextCol]>dis+1){
                            q.add(new int[]{nextRow,nextCol,dis+1});
                            monsterTime[nextRow][nextCol] = dis + 1;
                        }
                    }
                }
            }
        }
        
        while(!q1.isEmpty()){
            int[] cell = q1.poll();
            int row = cell[0];
            int col = cell[1];
            int dis = cell[2];
            if(row==n-1 || col==m-1 || row==0 || col==0) {
                System.out.println("YES");
                System.out.println(dis);
                getPath(row,col,par,ar,ac);
                return;
            }
            for(int i=0;i<4;i++){
                int nextRow = row + dir[i][0];
                int nextCol = col + dir[i][1];
                if(nextRow >= 0 && nextRow<n && nextCol >= 0 && nextCol<m){
                    if(grid[nextRow].charAt(nextCol) !='#' && !vis[nextRow][nextCol]){
                        
                        if(monsterTime[nextRow][nextCol]>dis+1){
                            q1.add(new int[]{nextRow,nextCol,dis+1});
                            vis[nextRow][nextCol] = true;
                            add(row,col,nextRow,nextCol,par);
                        }
                    }
                }
            }
        }
        System.out.print("NO");
        
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

    long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    String nextLine() throws IOException {
        return br.readLine();
    }

    char nextChar() throws IOException {
        return next().charAt(0);
    }

    // -------- Arrays --------
    int[] nextIntArray(int n) throws IOException {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = nextInt();
        return arr;
    }

    long[] nextLongArray(int n) throws IOException {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = nextLong();
        return arr;
    }

    double[] nextDoubleArray(int n) throws IOException {
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) arr[i] = nextDouble();
        return arr;
    }

    String[] nextStringArray(int n) throws IOException {
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) arr[i] = next();
        return arr;
    }

    char[] nextCharArray(int n) throws IOException {
        return next().toCharArray(); // assumes no spaces
    }
}
