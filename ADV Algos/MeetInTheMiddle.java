import java.util.*;
public class MeetInTheMiddle {
    private static void GenerateSubSets(int idx,long sum,int n,int[] nums,List<Long> arr) {
        if(idx==n){
            arr.add(sum);
            return;
        }
        GenerateSubSets(idx+1,sum+nums[idx],n,nums,arr);
        GenerateSubSets(idx+1,sum,n,nums,arr);
    }
    
    private static int lowerBound(List<Long> list,long num){
        int low=0;
        int high = list.size()-1;
        while(low<=high){
            int mid = (low+high)/2;
            long ele = list.get(mid);
            if(ele<num) low = mid+1;
            else high = mid-1;
        }
        return low;
    }
    private static int upperBound(List<Long> list,long num){
        int low=0;
        int high = list.size()-1;
        while(low<=high){
            int mid = (low+high)/2;
            long ele = list.get(mid);
            if(ele<=num) low = mid+1;
            else high = mid-1;
        }
        return low;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        int n = sc.nextInt();
        long x = sc.nextLong();
        int[] nums = new int[n];
        for(int i=0;i<n;i++) nums[i] = sc.nextInt();
        List<Long> nums1 = new ArrayList<>();
        List<Long> nums2 = new ArrayList<>();
        int mid = n/2;
        GenerateSubSets(0,0,mid,nums,nums1);
        GenerateSubSets(mid,0,n,nums,nums2);
        Collections.sort(nums1);
        Collections.sort(nums2);
        long ans=0;
        for(long currSum : nums1){
            long req = x - currSum;
            int lb = lowerBound(nums2,req);
            int ub = upperBound(nums2,req);
            ans+= 1L*(ub-lb);
        }
        System.out.print(ans);
    }
}