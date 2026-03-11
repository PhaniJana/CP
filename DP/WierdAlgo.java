import java.io.*;

public class WierdAlgo{
    public static void main(String[] args) throws IOException {
        BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        long n=Long.parseLong(sc.readLine());
        StringBuilder sb=new StringBuilder();
        while(n!=1){
            if(n%2==0){
                sb.append(n).append(" ");
                n=n/2;
            }
            else{
                sb.append(n).append(" ");
                n=3*n+1;
            }
        }
        sb.append(1);
        System.out.println(sb);
    }
}