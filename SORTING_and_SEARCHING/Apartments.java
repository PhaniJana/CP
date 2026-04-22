
import java.io.*;
import java.util.*;

class FastReader{
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

public class Apartments {
    private static int BS(int[] arr,int st,int num){
        
        int n = arr.length;
        int ans = n;
        int end = n-1;
        while(st<=end){
            int mid = st + (end-st)/2;
            if(arr[mid]>=num){
                ans = mid;
                end = mid-1;
            }
            else{
                st = mid+1;
            }
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        int n = fr.nextInt();
        int m = fr.nextInt();
        int k = fr.nextInt();
        int[] applicant = fr.nextIntArray(n);
        int[] sizes = fr.nextIntArray(m);
        Arrays.sort(applicant);
        Arrays.sort(sizes);
        int st = 0;
        int count = 0;
        for(int i=0;i<n;i++) {
            int idx = BS(sizes,st,applicant[i] - k);
            if(idx>=m) break;
            if(Math.abs(applicant[i] - sizes[idx])<=k){
                count++;
                st = idx+1;
            }
        }
        System.out.print(count);
    }
}