import java.util.*;
class Permutations {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder s = new StringBuilder();
        if(n==2 || n==3) {
            System.out.println("NO SOLUTION");
            return;
        }
        for(int i=2;i<=n;i+=2) s.append(i).append(" ");
        
        for(int i=1;i<=n;i+=2) s.append(i).append(" ");
        System.out.println(s);
        
    }
}