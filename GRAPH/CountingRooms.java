import java.util.*;
class CountingRooms {
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    private static void bfs(int row,int col,boolean[][] vis,String[] grid,int n,int m){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row,col});

        while(!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.peek()[1];
            q.poll();
            for(int i=0;i<4;i++){
                int nrow = r + dir[i][0];
                int ncol = c + dir[i][1];
                if(nrow>=0 && ncol>=0 && nrow<n && ncol<m){
                    if(!vis[nrow][ncol] && grid[nrow].charAt(ncol)=='.'){
                        vis[nrow][ncol] = true;
                        q.add(new int[]{nrow,ncol});
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] grid = new String[n];
        for(int i=0;i<n;i++) grid[i] = sc.next();
        int cnt=0;
        boolean[][] vis = new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i].charAt(j)=='.' && !vis[i][j]){
                    cnt++;
                    bfs(i,j,vis,grid,n,m);
                }
            }
        }
        System.out.print(cnt);
    }
}