import java.io.*;
import java.util.*;
public class CreatingStrings{
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
    static List<String> ans;
    static int n;
    static void solve(int idx,int[] freq,StringBuilder curr){
        if(idx==n){
            ans.add(curr.toString());
            return;
        }
        for(int i=0;i<26;i++){
            if(freq[i]==0) continue;
            freq[i]--;
            curr.append((char)(i+'a'));
            solve(idx+1,freq,curr);
            curr.deleteCharAt(curr.length()-1);
            freq[i]++;
        }
    }
    static void solve(FastReader sc, StringBuilder out) throws Exception {
        String s = sc.next();
        ans = new ArrayList<>();
        int[] freq = new int[26];
        n = s.length();
        for(char c : s.toCharArray()){
            freq[c-'a']++;
        }
        ans = new ArrayList<>();
        solve(0,freq,new StringBuilder());
        out.append(ans.size()).append("\n");
        for(String t : ans) out.append(t).append("\n");
    }

}
