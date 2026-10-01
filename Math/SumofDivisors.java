import java.util.*;

public class SumofDivisors {
  static long M = (long)1e9 + 7;
  static long inv = 500000004L;
  private static long cal(long a,long b){
    long num = (((b - a + 1) % M) * ((a + b) % M)) % M;
    return (num * inv) % M;
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long n = sc.nextLong();
    long l = 1;
    long ans = 0;
    while(l<=n){
      long q = n / l;
      long r = n / q;
      ans = (ans + (q*cal(l,r))%M) % M;
      l = r + 1;
    }
    System.out.print(ans);
    
  }
}