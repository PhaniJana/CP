class SegmentTree{
        long[] tree;
        int n; 
        SegmentTree(int _n){
            n = _n;
            tree = new long[4*n];
        }
        public void update(int idx,long val){
            update(0,0,n-1,idx,val);
        }
        public long query(int idx){
            return query(0,0,n-1,0,idx);
        }
        private long query(int idx,int l,int r,int p,int q){
            if(r<p || q<l) return 0;
            if(p<=l && r<=q) return tree[idx];
            int mid = (l+r)/2;
            return (query(2*idx+1,l,mid,p,q) + query(2*idx+2,mid+1,r,p,q));
        }
        private void update(int idx,int l,int r,int index,long val){
            if(l==r) {
                tree[idx] = (tree[idx] + val);
                return;
            }
            int mid = (l+r)/2;
            if(index<=mid){
                update(2*idx+1,l,mid,index,val);
            }else update(2*idx+2,mid+1,r,index,val);
            tree[idx] = (tree[2*idx+1] + tree[2*idx+2]);
        }
    }
