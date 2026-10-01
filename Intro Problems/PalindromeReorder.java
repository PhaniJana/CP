import java.io.*;
import java.util.*;
public class PalindromeReorder{
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
        String s = sc.next();
        int[] freq = new int[26];
        int pal = 0;
        for(char c : s.toCharArray()){
            int x = c - 'A';
            pal ^= (1<<x);
            freq[x]++;
        }
        if((pal & (pal-1))!=0) {
            out.append("NO SOLUTION");
            return;
        }
        char mid = 0;
        for(int i=0;i<26;i++) if(freq[i]%2==1) mid = (char)(i+'A');
        StringBuilder half = new StringBuilder();
        StringBuilder ans = new StringBuilder();
        for(int i=0;i<26;i++){
            if(freq[i]==0) continue;
            String t = (char)(i+'A') + "";
            half.append(t.repeat(freq[i]/2));
        }
        ans.append(half.toString());
        if(pal!=0) ans.append(mid);
        ans.append(half.reverse().toString());
        out.append(ans.toString());
    }

}
