import java.io.*;
public class MissingNumber{
    public static void main(String[] args) throws IOException{
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(sc.readLine());
        String[] arr = sc.readLine().split(" ");

        long sum=0;
        for(int i=0;i<n-1;i++){
            sum+=Integer.parseInt(arr[i]);
        }
        long sn =1L*n*(n+1)/2;
        System.out.print(sn-sum);
    }
}