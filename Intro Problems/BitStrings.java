import java.util.*;

class BitStrings {
    static long M = (long)1e9 + 7;
    static long pow(long a,long b){
        if(b==0) return 1L;

        long h = pow(a,b/2);
        h = (h*h) % M;
        if(b%2==1) h = (h*a) % M;
        return h;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long num = sc.nextLong();
        long ans = pow(2,num);
        System.out.print(ans);
    }
}