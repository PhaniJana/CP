import java.util.*;
class CoordinateCompression{
        long[] nums;
        List<Long> unq;
        CoordinateCompression(long[] _nums){
            nums = _nums.clone();
            build();
            getArray(_nums);
        }
        private void build(){
            Arrays.sort(nums);
            unq = new ArrayList<>();
            for(long num : nums){
                if(unq.isEmpty() || unq.get(unq.size()-1)!=num) unq.add(num);
            }
        }
        public int getSize(){
            return unq.size();
        }
        public void getArray(long[] arr){
            int n = arr.length;
            for(int i=0;i<n;i++){
                arr[i] = (long)search(arr[i]);
            }
        }
        public int search(long num){
            int l = 0,r = unq.size()-1;
            while(l<=r){
                int mid = (l+r) / 2;
                if(unq.get(mid) == num) return mid+1;
                else if(unq.get(mid) > num) r = mid-1;
                else l = mid + 1;
            }
            return -1;
        }
    } 
