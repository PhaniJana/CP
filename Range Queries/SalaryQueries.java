import java.io.*;
import java.util.*;
public class SalaryQueries{
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

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }


        char nextChar() throws IOException {
            return next().charAt(0);
        }

        // -------- Arrays --------
        int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = nextInt();
            return arr;
        }

    }

    static class CoordinateCompression{
        List<Integer> nums;
        List<Integer> unq;
        CoordinateCompression(List<Integer> _nums){
            nums = _nums;
            build();
        }
        private void build(){
            Collections.sort(nums);
            unq = new ArrayList<>();
            for(int num : nums){
                if(unq.isEmpty() || unq.get(unq.size()-1)!=num) unq.add(num);
            }
        }
        public int getSize(){
            return unq.size();
        }
        public int search(int num){
            int l = 0,r = unq.size()-1;
            while(l<=r){
                int mid = (l+r) / 2;
                if(unq.get(mid) == num) return mid;
                else if(unq.get(mid) > num) r = mid-1;
                else l = mid + 1;
            }
            return -1;
        }
    } 

    static class SegmentTree{
        int n;
        int[] tree;
        SegmentTree(int _n){
            n = _n;
            tree = new int[4*n];
        }
        private void update(int idx,int l,int r,int index,int val){
            if(l==r){
                tree[idx] += val;
                return;
            }
            int mid = (l+r)/2;
            if(index<=mid) update(2*idx+1,l,mid,index,val);
            else update(2*idx+2,mid+1,r,index,val);
            tree[idx] = tree[2*idx+1] + tree[2*idx+2];
        }
        private int query(int idx,int l,int r,int p,int q){
            if(r<p || q<l) return 0;
            else if(l>=p && r<=q) return tree[idx];
            int mid = (l+r)/2;
            return query(2*idx+1,l,mid,p,q) + query(2*idx+2,mid+1,r,p,q);
        }
        public int query(int l,int r){
            return query(0,0,n-1,l,r);
        }
        public void update(int idx,int val){
            update(0,0,n-1,idx,val);
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
        int[] nums = sc.nextIntArray(n);
        List<Integer> comp = new ArrayList<>();
        for(int i=0;i<n;i++) comp.add(nums[i]);
        int[][] queries = new int[m][3];
        for(int i=0;i<m;i++){
            int t = sc.nextChar() == '!' ? 1 : 2;
            int a = sc.nextInt();
            int b = sc.nextInt();
            queries[i][0] = t;
            queries[i][1] = a;
            queries[i][2] = b;
            comp.add(b);
            if(t==2) comp.add(a);
        }
        CoordinateCompression cc = new CoordinateCompression(comp);
        SegmentTree sg = new SegmentTree(cc.getSize()+2);
        for(int i=0;i<n;i++){
            nums[i] = cc.search(nums[i]);
            sg.update(nums[i],1);
        }
        for(int i=0;i<m;i++){
            queries[i][2] = cc.search(queries[i][2]);
            if(queries[i][0]==2) queries[i][1] = cc.search(queries[i][1]);
        }
        
        for(int[] q : queries){
            if(q[0]==1){
                q[1]--;
                sg.update(nums[q[1]],-1);
                sg.update(q[2],1);
                nums[q[1]] = q[2];
            }else{
                out.append(sg.query(q[1],q[2])).append("\n");
            }
        }
    }


}


