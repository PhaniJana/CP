class Pow{
    long M = (long)1e9 + 7;
    private long exp(long base,long exp){
        if(exp==0) return 1L;
        long half = exp(base,exp/2);
        long ans = (half * half) % M;
        if((exp & 1)==1) ans = (ans*base)%M;
        return ans;
    }
}