import java.util.*;
public class CountingNumbers {
    static Long dp[][][][];
    private static long solve(int idx,int prev,int tight,int lz,String s){
        if(idx==s.length()) return 1;
        if(dp[idx][prev+1][tight][lz]!=null) return dp[idx][prev+1][tight][lz];
        int low=0;
        int high = tight==1 ? s.charAt(idx)-'0' : 9;
        long count=0;
        for(int i=low;i<=high;i++){
            int nt = tight==1 && s.charAt(idx)-'0'==i ? 1 : 0;
            if(lz==1 && i==0) count+=solve(idx+1,prev,nt,1,s);
            else{
                if(i==prev) continue;
                count+= solve(idx+1,i,nt,0,s);
            }
        }
        return dp[idx][prev+1][tight][lz] = count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        String sa = String.valueOf(a-1);
        String sb = String.valueOf(b);
        long ans_a=0;
        if(a>0){
            dp = new Long[sa.length()][11][2][2];
            ans_a = solve(0,-1,1,1,sa);
        }
        long ans_b = 0;
        dp = new Long[sb.length()][11][2][2];
        ans_b = solve(0,-1,1,1,sb);
        System.out.print(ans_b-ans_a);
    }
}
