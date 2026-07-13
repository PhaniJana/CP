//https://atcoder.jp/contests/abc294/tasks/abc294_g

import java.util.*;
public class DistanceQueriesOnATree {
    private static List<List<int[]>> tree;
    private static int[] depth;
    private static long[] dist;
    private static List<Long> euler;
    private static int[] t_in;
    private static int[] t_out;
    private static int time = 1;
    private static int[][] dp;
    private static int LOG = 20;
    
    
    //BINARY LIFTING
    private static void binaryLifting(int node,int par){
        dp[node][0] = par;
        for(int i=1;i<=LOG;i++){
            if(dp[node][i-1]!=-1) dp[node][i] = dp[dp[node][i-1]][i-1];
            else dp[node][i] = -1;
        }
        for(int[] child : tree.get(node)){
            if(child[0]==par) continue;
            binaryLifting(child[0],node);
        }
    }
    private static int lift(int node,int val){
        
        for(int i=LOG;i>=0;i--){
            if((val & (1<<i))!=0){
                if(node==-1) break;
                node = dp[node][i];
            }
        }
        return node;
    }
    private static int findLca(int u,int v){
        if(depth[u]>depth[v]){
            int temp = u;
            u = v;
            v = temp;
        }
        v = lift(v,depth[v]-depth[u]);
        int l = 0,r = depth[u];
        int ans = -1;
        while(l<=r){
            int mid = l + (r-l)/2;
            int liftU = lift(u,mid);
            int liftV = lift(v,mid);
            if(liftU==liftV){
                ans = liftV;
                r = mid - 1;
            }
            else l = mid + 1;
        }
        return ans;
    }
    
    //EULER TOUR
    private static void eulerTour(int node,int par){
        t_in[node] = time++;
        euler.add(dist[node]);
        for(int[] child : tree.get(node)){
            if(child[0] == par) continue;
            eulerTour(child[0],node);
        }
        t_out[node] = time++;
        euler.add(dist[node]);
    }  
    
    //DFS1
    private static void dfs1(int node,int par){
        
        for(int[] child : tree.get(node)){
            if(child[0] == par) continue;
            depth[child[0]] = 1 + depth[node];
            dist[child[0]] = dist[node] + child[1];
            dfs1(child[0],node);
        }
    }
    
    //MAIN FN
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] edges = new int[n][3];
        depth = new int[n+1];
        dist = new long[n+1];
        tree = new ArrayList<>();
        for(int i=0;i<=n;i++) tree.add(new ArrayList<>());
        for(int i=0;i<n-1;i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int wt = sc.nextInt();
            edges[i][0] = u;
            edges[i][1] = v;
            edges[i][2] = wt;
            tree.get(u).add(new int[]{v,wt});
            tree.get(v).add(new int[]{u,wt});
        }
        int q = sc.nextInt();
        int[][] queries = new int[q][3];
        for(int i=0;i<q;i++){
            int t = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            queries[i][0] = t;
            queries[i][1] = a;
            queries[i][2] = b;
        }
        
        //DFS FOR DIS FROM ROOT AND DEPTH
        dfs1(1,-1);
        
        //Euler Tour
        euler = new ArrayList<>();
        euler.add(-1L);
        t_in = new int[n+1];
        t_out = new int[n+1];
        eulerTour(1,-1);
        
        //LCA BINARY LIFTING
        dp = new int[n+1][LOG+1];
        for(int[] arr : dp) Arrays.fill(arr,-1);
        binaryLifting(1,-1);
        SegmentTree st = new SegmentTree(euler);
        StringBuilder ans = new StringBuilder(); 
        for(int[] query : queries){
            int type = query[0];
            if(type==1){
                int[] edge = edges[--query[1]];
                int u = edge[0];
                int v = edge[1];
                int delta = query[2] - edge[2];
                if(depth[u]>depth[v]){
                    st.update(t_in[u],t_out[u],1L*delta);
                }else{
                    st.update(t_in[v],t_out[v],1L*delta);
                }
                edge[2] = query[2];
            }
            else{
                int u = query[1];
                int v = query[2];
                int lca = findLca(u,v);
                // DIS FROM ROOT TO U 
                long dis1 = st.query(t_in[u]);
                // DIS FROM ROOT TO V
                long dis2 = st.query(t_in[v]);
                // DIS FROM ROOT TO LCA
                long dis3 = lca!=-1 ? st.query(t_in[lca]) : 0;
                
                long distance = (dis1 + dis2 - 2L*dis3);
                ans.append(distance).append("\n");
            }
        }
        System.out.print(ans);
    }
}

class SegmentTree{
    long[] tree;
    long[] lazy;
    int n;
    SegmentTree(List<Long> list){
        n = list.size();
        tree = new long[4*n];
        lazy = new long[4*n];
        build(0,1,n-1,list);
    }
    private void build(int idx,int l,int r,List<Long> list){
        if(l==r){
            tree[idx] = list.get(l);
            return;
        }
        int mid = l + (r-l)/2;
        build(2*idx+1,l,mid,list);
        build(2*idx+2,mid+1,r,list);
        tree[idx] = tree[2*idx+1] + tree[2*idx+2];
    }
    public void update(int l,int r,long val){
        update(0,1,n-1,l,r,val);
    }
    private void push(int idx,int l,int r){
        if(lazy[idx]!=0){
            tree[idx] += (r-l+1)*lazy[idx];
            if(l!=r){
                lazy[2*idx+1] += lazy[idx];
                lazy[2*idx+2] += lazy[idx];
            }
            lazy[idx] = 0;
        }
    }
    private void update(int idx,int l,int r,int p,int q,long val){
        push(idx,l,r);
        if(r<p || q<l) return;
        if(p<=l && r<=q){
            tree[idx] += (r-l+1)*val;
            if(l!=r){
                lazy[2*idx+1] += val;
                lazy[2*idx+2] += val;
            }
            return;
        } 
        int mid = l + (r-l)/2;
        update(2*idx+1,l,mid,p,q,val);
        update(2*idx+2,mid+1,r,p,q,val);
        tree[idx] = tree[2*idx+1] + tree[2*idx+2];
    }
    
    public long query(int idx) {return query(0,1,n-1,idx);}
    private long query(int idx,int l,int r,int index){
        push(idx,l,r);
        if(l==r) return tree[idx];
        int mid = l + (r-l)/2;
        if(index<=mid) return query(2*idx+1,l,mid,index);
        return query(2*idx+2,mid+1,r,index);
    }
}