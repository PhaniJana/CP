class DoubleRollingHash {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;

    private static final long BASE1 = 911382323L;
    private static final long BASE2 = 972663749L;

    private final long[] hash1, hash2;
    private final long[] pow1, pow2;

    public DoubleRollingHash(int[] arr) {
        int n = arr.length;

        hash1 = new long[n + 1];
        hash2 = new long[n + 1];
        pow1 = new long[n + 1];
        pow2 = new long[n + 1];

        pow1[0] = pow2[0] = 1;

        for (int i = 0; i < n; i++) {
            pow1[i + 1] = (pow1[i] * BASE1) % MOD1;
            pow2[i + 1] = (pow2[i] * BASE2) % MOD2;

            // +1 so that 0 values also contribute
            long val = arr[i] + 1L;

            hash1[i + 1] = (hash1[i] * BASE1 + val) % MOD1;
            hash2[i + 1] = (hash2[i] * BASE2 + val) % MOD2;
        }
    }

    // Returns hash of subarray [l, r] (inclusive)
    public long getHash(int l, int r) {
        long x1 = (hash1[r + 1] - hash1[l] * pow1[r - l + 1]) % MOD1;
        if (x1 < 0) x1 += MOD1;

        long x2 = (hash2[r + 1] - hash2[l] * pow2[r - l + 1]) % MOD2;
        if (x2 < 0) x2 += MOD2;

        
        return ((x1<<32) | x2);
    }
}