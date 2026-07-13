import java.io.*;
import java.util.*;

class SegmentTree{
    long[] tree;
    long[] pow;
    long M = (long)1e9 + 7;
    int n;
    char[] s;
    SegmentTree(int n,long[] pow,char[] s){
        this.pow = pow;
        this.n = n;
        this.s = s;
        tree = new long[4*n];
        build(0,0,n-1);
    }
    private void build(int idx,int l,int r){
        if(l==r){
            tree[idx] = (s[l] * pow[l]) % M;
            return;
        }
        int mid = (l+r)/2;
        build(2*idx+1,l,mid);
        build(2*idx+2,mid+1,r);
        tree[idx] = (tree[2*idx+1] + tree[2*idx+2]) % M;
    }
    private void update(int idx,int l,int r,int index,char c){
        if(l==r){
            tree[idx] = (c * pow[l]) % M;
            return;
        }
        int m = (l+r) / 2;
        if(index <= m) update(2*idx+1,l,m,index,c);
        else update(2*idx+2,m+1,r,index,c);
        tree[idx] = (tree[2*idx+1] + tree[2*idx+2]) % M;
    }
    
    private long query(int idx,int l,int r,int p,int q){
        if(r<p || q<l) return 0L;
        if(p<=l && q>=r) return tree[idx];
        int mid = (l+r)/2;
        return (query(2*idx+1,l,mid,p,q) + query(2*idx+2,mid+1,r,p,q)) % M;
    }
    public void update(int idx,char c){
        update(0,0,n-1,idx,c);
    }
    public long query(int l,int r){
        return query(0,0,n-1,l,r);
    }
}

public class PalindromeQueries {
    private static long power(long num,long pow,long M){
        if(pow==0) return 1;
        long half = power(num,pow/2,M);
        long ans = (half * half) % M;
        if(pow%2==1) ans = (ans * num) % M;
        return ans % M;
    } 

    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int q = sc.nextInt();
        StringBuilder s = new StringBuilder(sc.next());
        long M = (long)1e9 +7;
        long B = 911382323;
        long[] pow = new long[n];
        long[] inverse = new long[n];
        long inv  = power(B,M-2,M);
        inverse[0] = 1;
        pow[0] = 1;
        for(int i=1;i<n;i++){
            pow[i] = (pow[i-1] * B) % M;
            inverse[i] = (inverse[i-1] * inv) % M;
        }
        StringBuilder ans = new StringBuilder();
        SegmentTree st = new SegmentTree(n,pow,s.toString().toCharArray());
        SegmentTree rev = new SegmentTree(n,pow,s.reverse().toString().toCharArray());
        while(q-- > 0){
            int type = sc.nextInt();
            if(type==1){
                int i = sc.nextInt();
                i--;
                char b = sc.nextChar();
                st.update(i,b);
                rev.update(n-1-i,b);
            } else{
                int a = sc.nextInt();
                int b = sc.nextInt();
                a--;
                b--;
                long x = (st.query(a,b) * inverse[a]) % M;
                long y = (rev.query(n-1-b,n-1-a) * inverse[n-1-b]) % M;
                ans.append(x==y ? "YES" : "NO").append('\n');
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

    char nextChar() throws IOException {
        return next().charAt(0);
    }
}
