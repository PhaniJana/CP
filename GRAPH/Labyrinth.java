import java.util.*;
public class Labyrinth {
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
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
        Queue<Pair> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        vis[sr][sc] = true;
        q.add(new Pair(sr,sc,""));
        while(!q.isEmpty()){
            int r = q.peek().r;
            int c = q.peek().c;
            String s = q.peek().s;
            q.poll();
            if(grid[r].charAt(c)=='B') return s;
            for(int i=0;i<4;i++){
                int nr = r + dir[i][0];
                int nc = c + dir[i][1];
                if(nr>=0 && nr<n && nc>=0 && nc<m){
                    if(!vis[nr][nc] && grid[nr].charAt(nc)!='#'){
                        String ns = s;
                        if(i == 0) ns +="D";
                        else if(i==1) ns += "R";
                        else if(i==2) ns += "U";
                        else ns += "L";
                        q.add(new Pair(nr,nc,ns));
                    }
                }
            }

        }
        return "";
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
class Pair{
    int r;
    int c;
    String s;
    Pair(int _r,int _c,String _s){
        r=_r;
        c=_c;
        s = _s;
    }
}
