import java.util.Scanner;

public class CodeForces {
    private static int CountOne(String s){
        int count=0;
        for(char c:s.toCharArray()){
            count+= (c-'0');
        }
        return count;
    }
    private static int NonConsecutiveZeros(String s,int n){
        int count=0;
        for(int i=0;i<n;i++){
            if(i-1>=0 && i+1<n && s.charAt(i)=='0' &&  s.charAt(i+1)=='1' && s.charAt(i-1)=='1'){
                count++;
            }
        }
        return count;
    }
    private static void solve(int n,String s){
        int ones =  CountOne(s);
        int zeros = NonConsecutiveZeros(s,n);
        System.out.print(ones-1+" ");
        System.out.println(ones+zeros);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n =  sc.nextInt();
            String s = sc.next();
            solve(n, s);
        }
    }
}
