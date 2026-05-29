import java.io.*;
import java.util.*;
class SegmentTree{
    long[] tree;
    long[] lazy;
    long[] arr;
    int n;
    SegmentTree(int n,long[] arr){
        tree = new long[4*n];
        lazy = new long[4*n];
        this.arr = arr;
        this.n=n;
        build(0,0,n-1);
    }
    private void build(int idx,int l,int r){
        if(l==r){
            tree[idx] = arr[l];
            return;
        }
        int mid = l + (r-l)/2;
        build(2*idx+1,l,mid);
        build(2*idx+2,mid+1,r);
        tree[idx] = tree[2*idx+1] + tree[2*idx+2];
    }
    public void update(int l,int r,long val){
        update(0,0,n-1,l,r,val);
    }
    public long query(int k){
        return query(0,0,n-1,k);
    }
    private void push(int idx,int l,int r){
        if(lazy[idx]!=0){
            if(l!=r){
                lazy[2*idx+1] += lazy[idx];
                lazy[2*idx+2] += lazy[idx];
            }
            tree[idx] += lazy[idx]*(r-l+1);
            lazy[idx] = 0;
        }
    }
    private void update(int idx,int l,int r,int p,int q,long val){
        push(idx,l,r);
        if(q<l || r<p) return;
        else if(p<=l && r<=q){
            tree[idx] += (r-l+1)*val;
            if(l!=r){
                lazy[2*idx+1] += val;
                lazy[2*idx+2] += val;
            }
            return;
        }
        int mid = l + (r-l)/2;
        update(2*idx+1,l,mid,p,q,val);
        update(2*idx+2,mid+1,r,p,q,val);
        tree[idx] = tree[2*idx+1] + tree[2*idx+2];
    }
    
    private long query(int idx,int l,int r,int k){
        push(idx,l,r);
        if(l==r) return tree[idx];
        int mid = l + (r-l)/2;
        if(k<=mid) return query(2*idx+1,l,mid,k);
        return query(2*idx+2,mid+1,r,k);

    }
}

public class RangeUpdateQueries {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int q = sc.nextInt();
        long[] arr = new long[n];
        for(int i=0;i<n;i++) arr[i] = sc.nextLong();
        SegmentTree st = new SegmentTree(n,arr);
        StringBuilder ans = new StringBuilder();
        while(q-- >0){
            int type = sc.nextInt();
            if(type==1){
                int a = sc.nextInt();
                int b = sc.nextInt();
                long u = sc.nextLong();
                a--;
                b--;
                st.update(a,b,u);
            }
            else{
                int a = sc.nextInt();
                a--;
                ans.append(st.query(a)).append("\n");
            }
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

}