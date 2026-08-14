import java.io.*;
import java.util.*;

public class LongestPalindrome {

    static class FastReader {
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
    }

    static class RollingHash {

        char[] nums;

        long[] pow;
        long[] inv;
        long[] hash;

        int n;

        static final long MOD = 1_000_000_007L;
        static final long BASE = 911_382_323L;

        RollingHash(char[] nums) {
            this.nums = nums;
            this.n = nums.length;

            pow = new long[n];
            inv = new long[n];
            hash = new long[n];

            buildHash();
        }

        private void buildHash() {

            pow[0] = 1;
            inv[0] = 1;

            hash[0] = nums[0];

            long invBase = modPow(BASE, MOD - 2);

            for (int i = 1; i < n; i++) {

                pow[i] = (pow[i - 1] * BASE) % MOD;

                inv[i] = (inv[i - 1] * invBase) % MOD;

                hash[i] = (hash[i - 1]
                        + (nums[i] * pow[i]) % MOD) % MOD;
            }
        }

        long getHash(int l, int r) {

            long h = hash[r];

            if (l > 0) {
                h = (h - hash[l - 1] + MOD) % MOD;
            }

            // Normalize the hash so that
            // nums[l] has BASE^0
            h = (h * inv[l]) % MOD;

            return h;
        }

        private long modPow(long a, long b) {

            long ans = 1;

            while (b > 0) {

                if ((b & 1) == 1) {
                    ans = (ans * a) % MOD;
                }

                a = (a * a) % MOD;

                b >>= 1;
            }

            return ans;
        }
    }

    static int binarySearch(
            int i,
            int j,
            RollingHash org,
            RollingHash rev,
            int n) {

        int l = 0;
        int r = Math.min(i, n - 1 - j);

        while (l <= r) {

            int mid = (l + r) / 2;

            // Original string:
            // [i-mid ... j+mid]
            long h1 = org.getHash(
                    i - mid,
                    j + mid
            );

            /*
             * Corresponding substring in reversed string.
             */
            long h2 = rev.getHash(
                    n - j - 1 - mid,
                    n - i - 1 + mid
            );

            if (h1 == h2) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }

    static void solve(
            FastReader sc,
            StringBuilder out) throws Exception {

        String s = sc.next();

        int n = s.length();

        RollingHash org =
                new RollingHash(s.toCharArray());

        String reversed =
                new StringBuilder(s)
                        .reverse()
                        .toString();

        RollingHash rev =
                new RollingHash(reversed.toCharArray());

        int maxLen = 1;
        int start = 0;

        for (int i = 0; i < n - 1; i++) {

            // --------------------------------
            // Odd length palindrome
            // --------------------------------

            int oddRadius =
                    binarySearch(
                            i,
                            i,
                            org,
                            rev,
                            n
                    );

            int oddLen =
                    2 * oddRadius + 1;

            if (oddLen > maxLen) {

                maxLen = oddLen;

                start = i - oddRadius;
            }


            // --------------------------------
            // Even length palindrome
            // --------------------------------

            int evenRadius =
                    binarySearch(
                            i,
                            i + 1,
                            org,
                            rev,
                            n
                    );

            int evenLen =
                    2 * evenRadius + 2;

            if (evenLen > maxLen) {

                maxLen = evenLen;

                start = i - evenRadius;
            }
        }

        out.append(
                s.substring(
                        start,
                        start + maxLen
                )
        );
    }

    public static void main(String[] args)
            throws Exception {

        FastReader sc = new FastReader();

        StringBuilder out =
                new StringBuilder();

        solve(sc, out);

        System.out.print(out);
    }
}