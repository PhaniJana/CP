static long add(long a,long b) {return (a+b)%M;}
static long sub(long a,long b) {return (a-b+M)%M;}
static long mul(long a,long b) {return (a*b)%M;}
static long div(long a,long b) {return (a*pow(b,M-2))%M;}
static long pow(long a,long b){
    if(b==0) return 1;
    long half = pow(a,b/2);
    long ans = (half*half)%M;
    if(b%2==1) ans = (ans * a)%M;
    return ans;
}
static long ncr(long n,long r){
    long num = 1;
    long den = 1;
    for(long i=0;i<r;i++){
        num = mul(num,n-i);
        den = mul(den,i+1);
    }
    return div(num,den);
}