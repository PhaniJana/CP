import java.util.*;
class LCS {
    private static int[][] lcs(int[] text1, int[] text2,int n,int m) {

        int[][] dp = new int[n][m];
        for(int idx1=0;idx1<n;idx1++){
            for(int idx2=0;idx2<m;idx2++){
                if(text1[idx1]==text2[idx2]){
                    dp[idx1][idx2]= 1 + (idx1-1>=0 && idx2-1>=0 ? dp[idx1-1][idx2-1] : 0);
                }
                else{
                    int way1=idx1-1>=0 ? dp[idx1-1][idx2] : 0;
                    int way2=idx2-1>=0 ? dp[idx1][idx2-1] : 0;
                    dp[idx1][idx2]=Math.max(way1,way2);
                }

            }
        }
        return dp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];
        for(int i=0;i<n;i++) a[i] = sc.nextInt();
        for(int i=0;i<m;i++) b[i] = sc.nextInt();
        int[][] dp = lcs(a,b,n,m);
        System.out.println(dp[n-1][m-1]);
        List<Integer> l = new ArrayList<>();
        int i=n-1,j=m-1;
        while(i>=0 && j>=0){
            if(a[i]==b[j]) {
                l.add(a[i]);
                i--;
                j--;
            }
            else if(i > 0 && (j == 0 || dp[i-1][j] >= dp[i][j-1])){
                i--;
            }
            else{
                j--;
            }

        }
        Collections.reverse(l);
        for(int k:l){
            System.out.print(k+" ");
        }
    }
}