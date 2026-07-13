import java.io.*;
import java.util.*;
public class SlidingWindowMedian {
    static TreeSet<Integer> set1;
    static TreeSet<Integer> set2;
    
    static void add(int idx) {
        set1.add(idx);
        set2.add(set1.pollFirst());

        if (set2.size() > set1.size()) {
            set1.add(set2.pollFirst());
        }
    }

    static void remove(int idx) {
        if (!set1.remove(idx))
            set2.remove(idx);

        if (set1.size() < set2.size())
            set1.add(set2.pollFirst());

        if (set1.size() > set2.size() + 1)
            set2.add(set1.pollFirst());
    }
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0;i<n;i++) nums[i] = sc.nextInt();
        StringBuilder ans = new StringBuilder();
        int i=0,j=0;
        
        set1 = new TreeSet<>((a,b) -> nums[a] == nums[b]
         ? Integer.compare(a,b)
         : Integer.compare(nums[b], nums[a]));

        set2 = new TreeSet<>((a,b) -> nums[a] == nums[b] 
        ? Integer.compare(a,b) : Integer.compare(nums[a], nums[b]));
        
        while(j<n){
            add(j);
            if(j-i+1==k){
                int med = set1.first();
                ans.append(nums[med]).append(" ");
                remove(i);
                i++;
            }
            j++;
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
}

