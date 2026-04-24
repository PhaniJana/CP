import java.io.*;
import java.util.*;

public class DynamicRangeSumQueries {
    public static void main(String[] args) throws Exception {
        FastReader sc = new FastReader();

        int n = sc.nextInt();
        int q = sc.nextInt();

        int[] arr = sc.nextIntArray(n);

        int base = (int)Math.sqrt(n) + 1;
        int blocks = (n + base - 1) / base;

        long[] nums = new long[blocks];

        // build blocks
        for (int i = 0; i < n; i++) {
            nums[i / base] += arr[i];
        }

        StringBuilder ans = new StringBuilder();

        while (q-- > 0) {
            int type = sc.nextInt();

            if (type == 1) {
                int idx = sc.nextInt() - 1;
                int val = sc.nextInt();

                int block = idx / base;

                nums[block] += (val - arr[idx]);
                arr[idx] = val;
            } 
            else {
                int l = sc.nextInt() - 1;
                int r = sc.nextInt() - 1;

                int Lb = l / base;
                int Rb = r / base;

                long sum = 0;

                if (Lb == Rb) {
                    for (int i = l; i <= r; i++) {
                        sum += arr[i];
                    }
                } 
                else {
                    int leftEnd = (Lb + 1) * base - 1;
                    if (leftEnd >= n) leftEnd = n - 1;

                    for (int i = l; i <= leftEnd; i++) {
                        sum += arr[i];
                    }

                    for (int b = Lb + 1; b < Rb; b++) {
                        sum += nums[b];
                    }

                    int rightStart = Rb * base;
                    for (int i = rightStart; i <= r; i++) {
                        sum += arr[i];
                    }
                }

                ans.append(sum).append('\n');
            }
        }

        System.out.print(ans);
    }
}

class FastReader {
    private final BufferedReader br;
    private StringTokenizer st;

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

    int[] nextIntArray(int n) throws IOException {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = nextInt();
        return arr;
    }
}