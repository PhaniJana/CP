import java.io.*;
import java.util.*;
public class CountingCoprimePairs {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        
        int[] nums = new int[n];
        int max = 0;
        for(int i=0;i<n;i++){
            nums[i] = sc.nextInt();
            max = Math.max(max,nums[i]);
        }
        int[] freq = new int[max+1];
        for(int num : nums) freq[num]++;
        long[] cnt2 = new long[max+1];
        for(int i=1;i<=max;i++){
            for(int j=i;j<=max;j+=i){
                cnt2[i]+=freq[j];
            }
            cnt2[i] = (cnt2[i]*(cnt2[i]-1))/2;
        }
        for(int i=max;i>=1;i--){
            for(int j=2*i;j<=max;j+=i){
                cnt2[i]-=cnt2[j];
            }
        }
        System.out.print(cnt2[1]);
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
}