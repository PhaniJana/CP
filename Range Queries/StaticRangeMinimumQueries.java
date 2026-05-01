import java.io.*;
import java.util.*;
class SegmentTree{
    int[] arr;
    int[] sg;
    int n;
    SegmentTree(int n,int[] arr){
        this.n = n;
        this.arr = arr;
        sg = new int[4*n];
    }
    public void buildTree(){
        build(0,0,n-1);
        //for(int ele : sg) System.out.print(ele+" ");
    }
    public int queryTree(int l,int r){
        return query(0,0,n-1,l,r);
    }
    private void build(int idx,int l,int r){
        if(l==r){
            sg[idx] = arr[l];
            return;
        }
        int mid = (l+r)/2;
        build(2*idx+1,l,mid);
        build(2*idx+2,mid+1,r);
        sg[idx] = Math.min(sg[2*idx+1],sg[2*idx+2]);   
    }
    private int query(int idx,int l,int r,int p,int q){
        if(r<p || q<l) return Integer.MAX_VALUE;
        if(l>=p && r<=q) return sg[idx];
        int mid = (l+r)/2;
        return Math.min(query(2*idx+1,l,mid,p,q),query(2*idx+2,mid+1,r,p,q));
    }
}
public class StaticRangeMinimumQueries {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = sc.nextIntArray(n);
        SegmentTree sgt = new SegmentTree(n,arr);
        sgt.buildTree();
        StringBuilder ans = new StringBuilder();
        for(int i=0;i<q;i++){
            int l = sc.nextInt();
            int r = sc.nextInt();
            ans.append(sgt.queryTree(l-1,r-1)).append("\n");
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

    double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    String nextLine() throws IOException {
        return br.readLine();
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

    long[] nextLongArray(int n) throws IOException {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = nextLong();
        return arr;
    }

    double[] nextDoubleArray(int n) throws IOException {
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) arr[i] = nextDouble();
        return arr;
    }

    String[] nextStringArray(int n) throws IOException {
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) arr[i] = next();
        return arr;
    }

    char[] nextCharArray(int n) throws IOException {
        return next().toCharArray(); // assumes no spaces
    }
}
