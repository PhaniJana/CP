import java.io.*;
import java.util.*;

class ArrayDivision {
    private static boolean check(long max,long[] arr,int k,int n) {
        int cnt = 1;
        long curr=0;
        for(int i=0;i<n;i++){
            if(curr + arr[i] > max){
                cnt++;
                curr = 0;
            }
            curr+=arr[i];
        }
        return cnt<=k;
    }
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[] nums = sc.nextLongArray(n);
        long max = Long.MIN_VALUE;
        long sum=0L;
        for(long num : nums) {
            max = Math.max(max,num);
            sum += num;
        }
        long l = max , r = sum;
        long ans = -1;
        while(l<=r){
            long mid = (l+r)/2L;
            if(check(mid,nums,k,n)){
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        System.out.print(ans);
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
    long[] nextLongArray(int n) throws IOException {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = nextLong();
        return arr;
    }
}
