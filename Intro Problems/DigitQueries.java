import java.io.*;
import java.util.*;
public class DigitQueries{
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
        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
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

    static int findRange(List<long[]> ranges,long num){
        int n = ranges.size();
        for(int i=1;i<n;i++){
            long l = ranges.get(i)[0];
            long r = ranges.get(i)[1];
            if(num>=l && num<=r) return i;
        }
        return 0;
    }
    static long pow(long a,long b){
        if(b==0) return 1L;
        long h = pow(a,b/2);
        h = h*h;
        if(b%2==1) h = h*a;
        return h;
    }
    static void solve(FastReader sc, StringBuilder out) throws Exception {
        List<long[]> ranges = new ArrayList<>();
        ranges.add(new long[]{0,0});
        long digits = 1;
        long prevEnd = 0;
        long currTotal = 9;
        while(digits<=18){
            long currSt = prevEnd + 1;
            long total = currTotal * digits;
            long currEnd = currSt + total - 1;
            ranges.add(new long[]{currSt,currEnd});
            currTotal *= 10;
            prevEnd = currEnd;
            digits++;
        }
        long q = sc.nextLong();
        while(q-- > 0){
            long idx = sc.nextLong();
            int digit = findRange(ranges,idx);
            long st = ranges.get(digit)[0];
            long add = (idx - st)/digit;
            long firstNum = pow(10,digit-1);
            long reqNum = firstNum + add;
            String s = String.valueOf(reqNum);
            int reqIdx = (int)((idx - st) % digit);
            out.append(s.charAt(reqIdx)).append("\n");
        }
    }
}


