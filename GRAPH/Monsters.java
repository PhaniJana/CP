import java.io.*;
import java.util.*;
class Monsters {
    private static String Path(char[][] parent,int endR,int endC,int sR,int sC){
    StringBuilder ans = new StringBuilder();
    while(endR!=sR || endC!=sC){
        char c = parent[endR][endC];
        ans.append(c);
        if(c=='D') endR--;
        else if(c=='U') endR++;
        else if(c=='R') endC--;
        else endC++;
    }
    return ans.reverse().toString();
}
    private static char GetDir(int d){
    switch(d){
        case 0 : return 'R'; // (0,1)
        case 1 : return 'L'; // (0,-1)
        case 2 : return 'D'; // (1,0)
        case 3 : return 'U'; // (-1,0)
    }
    return 'A';
}
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] grid = sc.nextStringArray(n);
        
        int INF = (int)1e9;
        int[][] monsterTime = new int[n][m];
        boolean[][] vis = new boolean[n][m];
        for(int[] arr : monsterTime) Arrays.fill(arr,INF);
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> q1 = new LinkedList<>();

        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        int startRow=0,startCol=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i].charAt(j)=='M') {
                    q.add(new int[]{i,j,0});
                    monsterTime[i][j] = 0;
                }
                if(grid[i].charAt(j)=='A') {
                    vis[i][j] = true;
                    startRow=i;
                    startCol=j;
                    q1.add(new int[]{i,j,0});
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
        
        
        char[][] parent = new char[n][m];
        while(!q1.isEmpty()){
            int[] cell = q1.poll();
            int row = cell[0];
            int col = cell[1];
            int dis = cell[2];
            if((row==n-1 || col==n-1 || row==0 || col==0) && monsterTime[row][col] > dis){
                System.out.println("YES");
                String ans = Path(parent,row,col,startRow,startCol);
                System.out.println(ans.length());
                System.out.println(ans);
                return;
            }
            for(int i=0;i<4;i++){
                int nextRow = row + dir[i][0];
                int nextCol = col + dir[i][1];
                if(nextRow >= 0 && nextRow<n && nextCol >= 0 && nextCol<m){
                    if(grid[nextRow].charAt(nextCol) !='#' && !vis[nextRow][nextCol]){
                        
                        if(monsterTime[nextRow][nextCol]>dis+1){
                            q1.add(new int[]{nextRow,nextCol,dis+1});
                            parent[nextRow][nextCol] = GetDir(i);
                            vis[nextRow][nextCol] = true;
                        }
                    }
                }
            }
        }
        System.out.print("NO");
        
    }
}