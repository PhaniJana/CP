import java.util.*;
public class Labyrinth {
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static char[] dirC = {'D','R','U','L'};
    private static int[] FindA(String[] grid,int n,int m){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i].charAt(j)=='A'){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    private static String bfs(int sr,int sc,String[] grid,int n,int m){
        int endR=-1,endC=-1;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        char[][] parent = new char[n][m];
        vis[sr][sc] = true;
        q.add(new int[]{sr,sc});
        while(!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.peek()[1];
            q.poll();
            if(grid[r].charAt(c)=='B'){
                endR = r;
                endC = c;
                break;
            }
            for(int i=0;i<4;i++){
                int nr = r + dir[i][0];
                int nc = c + dir[i][1];
                if(nr>=0 && nr<n && nc>=0 && nc<m){
                    if(!vis[nr][nc] && grid[nr].charAt(nc)!='#'){
                        vis[nr][nc] = true;
                        parent[nr][nc] = dirC[i];
                        q.add(new int[]{nr,nc});
                    }
                }
            }
        }
        if(endR==-1) return "";
        StringBuilder sb = new StringBuilder();
        while(endR!=sr || endC!=sc){
            sb.append(parent[endR][endC]);
            char c = parent[endR][endC];
            if(c=='D') endR--;
            else if(c=='U') endR++;
            else if(c=='L') endC++;
            else endC--;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] grid = new String[n];
        for(int i=0;i<n;i++) grid[i] = sc.next();
        int[] st = FindA(grid,n,m);
        String ans = bfs(st[0],st[1],grid,n,m);
        if(ans.equals("")) System.out.print("NO");
        else{
            System.out.println("YES");
            System.out.println(ans.length());
            System.out.println(ans);
        }

    }
}

