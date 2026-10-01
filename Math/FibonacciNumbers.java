import java.util.*;
class FibonacciNumbers {
    static long[][] I;
    static long M = (long)1e9 + 7;
    static long[][] pow(long[][] base ,long n){
        if(n==0) return I;

        long[][] res = pow(base,n/2);
        res = mul(res,res);
        if(n%2==1) res = mul(res,base);
        return res;
    }
    static long[][] mul(long[][] A, long[][] B) {
        int n = A.length;
        int m = B[0].length;
        int k = B.length;

        long[][] C = new long[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int x = 0; x < k; x++) {
                    C[i][j] =
                        (C[i][j] + A[i][x] * B[x][j]) % M;
                }
            }
        }

        return C;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        if(n==0L){
            System.out.print(0);
            return;
        }
        I = new long[2][2];
        I[0][0] = I[1][1] = 1;
        long[][] T = new long[2][2];
        T[0][0] = T[0][1] = T[1][0] = 1;
        long[][] base = new long[2][1];
        base[0][0] = 1;
        long[][] ans = mul(pow(T,n-1),base);
        System.out.print(ans[0][0]);
    }
}




