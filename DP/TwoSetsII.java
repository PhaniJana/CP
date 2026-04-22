import java.util.*;
public class TwoSetsII {
    static long M = (long)1e9 + 7;
    private static long exp(long base,long exp){
        if(exp==0) return 1L;
        long half = exp(base,exp/2);
        long ans = (half * half) % M;
        if((exp & 1)==1) ans = (ans*base)%M;
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int sum = a*(a+1);
        sum = sum/2;
        if(sum%2==1) {
            System.out.print("0");
            return;
        }
        int target = sum/2;
        long[][] dp = new long[a+2][target+1];
        dp[a+1][0] = 1;
        long[] next = new long[target+1];
        next[0]=1;
        for(int num=a;num>=1;num--){
            long[] curr = new long[target+1];
            curr[0]=1;
            for(int currSum=1;currSum<=target;currSum++){
                long NotPick = next[currSum];
                long pick = 0;
                if(currSum-num>=0) pick = next[currSum-num];
                curr[currSum] = (pick + NotPick)%M;
            }
            next = curr;
        }
        System.out.print((next[target]*exp(2,M-2))%M);
    }
}
