import java.io.*;
import java.util.*;

public class Projects {
    private static int search(long[][] nums,long num,int n){
        int l=0,r=n;
        int ans = -1;
        while(l<=r){
            int mid = (l+r)/2;
            if(nums[mid][1]<num){
                ans = mid;
                l = mid + 1;
            }
            else r = mid - 1;
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        long[][] nums = new long[n][3];
        for(int i=0;i<n;i++){
            long a = sc.nextLong();
            long b = sc.nextLong();
            long val = sc.nextLong();
            nums[i][0] = a;
            nums[i][1] = b;
            nums[i][2] = val;
        }
        Arrays.sort(nums,(a,b)->Long.compare(a[1],b[1]));
        
        long[] dp = new long[n];
        for(int idx=0;idx<n;idx++){
            long notPick = idx==0 ? 0 : dp[idx-1];
            int n_idx = search(nums,nums[idx][0],idx-1);
            long pick = nums[idx][2] + (n_idx == -1 ? 0 : dp[n_idx]);
            dp[idx] = Math.max(pick,notPick);
        }
        System.out.print(dp[n-1]);
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
}
