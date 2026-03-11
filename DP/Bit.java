import java.io.*;
public class Bit{
    public static void main(String[] args) throws IOException{
        BufferedReader sc =new BufferedReader(new InputStreamReader(System.in));
        String x = sc.readLine();
        StringBuilder ans= new StringBuilder();
        int n =x.length();
        for(int i=0;i<n;i++){
            int num = x.charAt(i)-'0';
            if(i==0 && num==9) ans.append(9);
            else if(num>4) ans.append(9-num);
            else ans.append(num);
        }
        System.out.print(ans);
    }
}