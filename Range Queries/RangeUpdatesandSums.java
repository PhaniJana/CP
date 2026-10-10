import java.io.*;
public class RangeUpdatesandSums{
    
    static class FastReader {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;

                if (len <= 0) {
                    return -1;
                }
            }

            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;

            do {
                c = read();
            } while (c <= ' ' && c != -1);

            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }

        long nextLong() throws IOException {
            int c;

            do {
                c = read();
            } while (c <= ' ' && c != -1);

            long sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            long value = 0;

            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value * sign;
        }

        long[] nextLongArray(int n) throws IOException {
            long[] arr = new long[n];

            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }

            return arr;
        }
    }


    static class SegmentTree{
        long[] tree;
        long[] lazy1;
        long[] lazy2;
        int n;
        SegmentTree(int n,long[] nums){
            this.n = n;
            tree = new long[4*n];
            lazy1 = new long[4*n];
            lazy2 = new long[4*n];
            build(0,0,n-1,nums);
        }
        private void build(int idx,int l,int r,long[] nums){
            if(l==r){
                tree[idx] = nums[l];
                return;
            }
            int mid = l + (r-l)/2;
            build(2*idx+1,l,mid,nums);
            build(2*idx+2,mid+1,r,nums);
            tree[idx] = tree[2*idx+1] + tree[2*idx+2];
        }

        private long query(int idx,int l,int r,int p,int q){
            
            if(r<p || l>q) return 0L;
            push(idx,l,r);
            if(l>=p && r<=q) return tree[idx];
            int mid = l + (r-l)/2;
            return query(2*idx+1,l,mid,p,q) + query(2*idx+2,mid+1,r,p,q);
        }
        
        private void applySet(int idx, int l, int r, long val) {
            tree[idx] = (r - l + 1L) * val;
            lazy2[idx] = val;
            lazy1[idx] = 0;
        }

        private void applyAdd(int idx, int l, int r, long val) {
            tree[idx] += (r - l + 1L) * val;

            if (lazy2[idx] != 0) {
                lazy2[idx] += val;
            } else {
                lazy1[idx] += val;
            }
        }

        private void push(int idx, int l, int r) {
            if (lazy1[idx] == 0 && lazy2[idx] == 0) return;
            if (l == r) return;

            int mid = l + (r - l) / 2;
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;

            if (lazy2[idx] != 0) {
                long val = lazy2[idx];

                applySet(left, l, mid, val);
                applySet(right, mid + 1, r, val);

                lazy2[idx] = 0;
            }

            if (lazy1[idx] != 0) {
                long val = lazy1[idx];

                applyAdd(left, l, mid, val);
                applyAdd(right, mid + 1, r, val);

                lazy1[idx] = 0;
            }
        }

        private void update(int idx,int l,int r,int p,int q,long val,int type){
            
            if(r<p || l>q) return;
            push(idx,l,r);
            if(l>=p && r<=q){
                if(type==1) applyAdd(idx, l, r, val);
                else applySet(idx, l, r, val);
                return;
            }
            int mid = l + (r-l)/2;
            update(2*idx+1,l,mid,p,q,val,type);
            update(2*idx+2,mid+1,r,p,q,val,type);
            tree[idx] = tree[2*idx+1] + tree[2*idx+2];
        }
        
        public long query(int l,int r){
            return query(0,0,n-1,l,r);
        }
        public void update(int l,int r,long val,int type){
            update(0,0,n-1,l,r,val,type);
        }
    }
    static final long M = 1_000_000_007L;
    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        FastReader fs = new FastReader();
        StringBuilder out = new StringBuilder();

        int T = 1;
        //T = fs.nextInt();
        
        while (T-- > 0) {
            solve(fs, out);
        }
        
        System.out.print(out);

    }

    
    static void solve(FastReader sc, StringBuilder out) throws Exception {
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] nums = sc.nextLongArray(n);
        SegmentTree sg = new SegmentTree(n,nums);
                                        
        while(m-- > 0){
            int type = sc.nextInt();
            if(type==1 || type==2){
                int l = sc.nextInt() - 1;
                int r = sc.nextInt() - 1;
                long val = sc.nextLong();
                sg.update(l,r,val,type);
            }else{
                int l = sc.nextInt() - 1;
                int r = sc.nextInt() - 1;
                long ans = sg.query(l,r);
                out.append(ans).append("\n");
            }
        }
    }
}
