import java.io.*;
import java.util.*;

public class SumofTwoValues {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] nums = sc.nextIntArray(n);
        Map<Integer,Integer> mpp = new HashMap<>();
        for(int i=0;i<n;i++){
            int req = x - nums[i];
            if(mpp.containsKey(req)){
                int first = mpp.get(req) + 1;
                int last = i+1;
                System.out.print(first +" " +last);
                return;
            }
            mpp.put(nums[i],i);
        }
        System.out.print("IMPOSSIBLE");
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
    // -------- Arrays --------
    int[] nextIntArray(int n) throws IOException {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = nextInt();
        return arr;
    }
}