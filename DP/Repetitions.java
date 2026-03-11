import java.io.*;
class Repetitions {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int max=1;
        int curr=1;
        int n = s.length();
        for(int i=1;i<n;i++){
            if(s.charAt(i-1)==s.charAt(i)) curr++;
            else curr=1;
            max = Math.max(max,curr);
        }
        System.out.print(max);
    }
}