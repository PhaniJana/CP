import java.util.*;
//https://codeforces.com/contest/61/problem/E
public class EnemyIsWeak {
    private static long solve(int[] arr,int max,int n){
        SegmentTree sg1 = new SegmentTree(max+1);
        SegmentTree sg2 = new SegmentTree(max+1);
        for(int i=0;i<n;i++){
            sg2.update(arr[i],1);
        }
        long ans=0;
        for(int i=0;i<n;i++){
            long left = sg1.query(arr[i]+1, max);
            long right = sg2.query(0, arr[i]-1);
            ans += left*right;

            sg1.update(arr[i], 1);
            sg2.update(arr[i], -1);
        }
        return ans;

    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] arr = new int[n];
        int max=0;
        for(int i=0;i<n;i++) {
            arr[i] = sc.nextInt();
            max = Math.max(arr[i],max);
        }
        int[] nums = arr.clone();
        Arrays.sort(nums);
        List<Integer> l = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(l.size()==0 || l.get(l.size()-1)!=nums[i]) l.add(nums[i]);
        }
        for(int i=0;i<n;i++){
            arr[i] = Collections.binarySearch(l,arr[i]);
        }
        System.out.println(solve(arr,l.size()-1,n));
    
    }
}
class SegmentTree{
    long[] tree;
    int n;
    SegmentTree(int max) {
        n = max;
        tree = new long[4*n];
    }
    public long query(int l,int r){
        return query(0,0,n-1,l,r);
    }

    public void update(int index,long val){
        update(0,0,n-1,index,val);
    }


    public long query(int idx,int l,int r,int p,int q){
        if(q<l || r<p || p>q) return 0;
        if(p<=l && r<=q) return tree[idx];
        int mid = l + (r-l)/2;
        return query(2*idx+1,l,mid,p,q) + query(2*idx+2, mid+1, r, p, q);
    }

    public void update(int idx,int l,int r,int index,long val){
        if(l==r){
            tree[idx] += val;
            return;
        } 
        int mid = l + (r-l)/2;
        if(index<=mid) update(2*idx+1,l,mid,index,val);
        else update(2*idx+2,mid+1,r,index,val);

        tree[idx] = tree[2*idx+1] + tree[2*idx+2];
    }
    
}
