class DSU {
    int[] parent, size;

    DSU(int n){
        parent = new int[n];
        size = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
            size[i] = 1;
        }
    }

    int find(int u){
        if(parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    void union(int u, int v){
        int pu = find(u);
        int pv = find(v);
        if(pu == pv) return;

        if(size[pu] > size[pv]){
            parent[pv] = pu;
            size[pu] += size[pv];
        } else {
            parent[pu] = pv;
            size[pv] += size[pu];
        }
    }
}