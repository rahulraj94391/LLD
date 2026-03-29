package com.org.striver;

import java.util.*;

public class GraphSolutions {
    public static void main(String[] args) {
        System.out.println("Graph");
    }
}

// G8
class Solution_Number_of_Islands {
    private final int[] dx = {-1, -1, -1, +0, +0, +1, +1, +1};
    private final int[] dy = {-1, +0, +1, -1, +1, -1, +0, +1};
    private int rowLen, colLen;
    private boolean[][] vis;
    private char[][] grid;

    public int numIslands(char[][] grid) {
        rowLen = grid.length;
        colLen = grid[0].length;
        vis = new boolean[rowLen][colLen];
        this.grid = grid;
        int islandCount = 0;

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (isLandUnvisited(i, j)) {
                    islandCount++;
                    dfs(i, j);
                }
            }
        }

        return islandCount;
    }

    private void dfs(int i, int j) {
        for (int surrounding = 0; surrounding < 8; surrounding++) {
            vis[i][j] = true;
            int new_i = i + dx[surrounding];
            int new_j = j + dy[surrounding];
            if (isIndexInBound(new_i, new_j) && isLandUnvisited(new_i, new_j)) {
                dfs(new_i, new_j);
            }
        }
    }

    private boolean isIndexInBound(int i, int j) {
        return (i >= 0 && i < rowLen) && (j >= 0 && j < colLen);
    }

    private boolean isLandUnvisited(int i, int j) {
        return grid[i][j] == '1' && !vis[i][j];
    }
}

// G9
class Solution_Flood_Fill {
    private final int[] dx = {-1, +0, +0, +1};
    private final int[] dy = {+0, -1, +1, +0};
    private int[][] ans;
    private int iniColor;
    private int newColor;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        this.iniColor = image[sr][sc];
        this.newColor = newColor;
        this.ans = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            System.arraycopy(image[i], 0, ans[i], 0, image[0].length);
        }
        dfs(sr, sc);
        return ans;
    }

    private void dfs(int row, int col) {
        if (ans[row][col] == newColor) return;
        for (int i = 0; i < 4; i++) {
            ans[row][col] = newColor;
            int new_row = row + dx[i];
            int new_col = col + dy[i];
            if (isIndexInBound(new_row, new_col) && ans[new_row][new_col] == iniColor) {
                dfs(new_row, new_col);
            }
        }
    }

    private boolean isIndexInBound(int i, int j) {
        return (i >= 0 && i < ans.length) && (j >= 0 && j < ans[0].length);
    }
}

// G10
class Solution_Rotting_Orange {
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        int[][] vis = new int[n][m];
        int cntFresh = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.add(new Pair(i, j, 0));
                    vis[i][j] = 2;
                } else {
                    vis[i][j] = 0;
                }
                if (grid[i][j] == 1) {
                    cntFresh++;
                }
            }
        }

        int tm = 0;
        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};
        int cnt = 0;
        while (!q.isEmpty()) {
            int r = q.peek().row;
            int c = q.peek().col;
            int t = q.peek().time;
            tm = Math.max(tm, t);
            q.remove();

            for (int i = 0; i < 4; i++) {
                int nrow = r + drow[i];
                int ncol = c + dcol[i];
                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {
                    q.add(new Pair(nrow, ncol, t + 1));
                    vis[nrow][ncol] = 2;
                    cnt++;
                }
            }
        }
        if (cnt != cntFresh) return -1;
        return tm;
    }

    private static class Pair {
        int row, col, time;

        Pair(int r, int c, int t) {
            row = r;
            col = c;
            time = t;
        }
    }
}

// G11
class Solution_Detect_Cycle_Undirected_Graph {
    private boolean[] vis;
    private ArrayList<ArrayList<Integer>> adj;

    public boolean bfsCycleDetect(int src) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src, -1));
        vis[src] = true;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int node = pair.node;
            int parent = pair.parent;

            for (int nei : adj.get(node)) {
                if (!vis[nei]) {
                    q.add(new Pair(nei, node));
                    vis[nei] = true;
                } else if (nei != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        vis = new boolean[V];
        this.adj = adj;
        for (int i = 0; i < V; i++) {
            System.out.println("for");
            if (!vis[i]) {
                System.out.println("if - one");
                if (bfsCycleDetect(i)) {
                    System.out.println("if - two");
                    return true;
                }
            }
        }
        return false;
    }

    private static class Pair {
        int node;
        int parent;

        public Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }
}

// G14
class Solution_Replace_O_with_X {
    private static final int[] dx = {-1, +0, +0, +1};
    private static final int[] dy = {+0, -1, +1, +0};
    private static final char CHAR_O = 'O';
    private static final char CHAR_X = 'X';
    private static boolean allowMarking;
    private static boolean[][] vis;
    private static char[][] ans;

    static char[][] fill(int rows, int cols, char[][] a) {
        vis = new boolean[rows][cols];
        ans = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(a[i], 0, ans[i], 0, cols);
        }

        // traverse only boundary without changing O's to X's
        allowMarking = false;

        // top side (left to right)
        for (int c = 0; c < cols; c++) {
            if (!vis[0][c] && ans[0][c] == CHAR_O) {
                dfs(0, c);
            }
        }

        // bottom side (left to right)
        for (int c = 0; c < cols; c++) {
            if (!vis[rows - 1][c] && ans[rows - 1][c] == CHAR_O) {
                dfs(rows - 1, c);
            }
        }

        // left side (top to bottom)
        for (int r = 0; r < rows; r++) {
            if (!vis[r][0] && ans[r][0] == CHAR_O) {
                dfs(r, 0);
            }
        }

        // right side (top to bottom)
        for (int r = 0; r < rows; r++) {
            if (!vis[r][cols - 1] && ans[r][cols - 1] == CHAR_O) {
                dfs(r, cols - 1);
            }
        }

        // traverse the entire matrix changing O's to X's
        allowMarking = true;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!vis[i][j] && ans[i][j] == CHAR_O) {
                    dfs(i, j);
                }
            }
        }

        return ans;
    }

    private static void dfs(int r, int c) {
        vis[r][c] = true;
        if (allowMarking) ans[r][c] = CHAR_X;
        for (int i = 0; i < 4; i++) {
            int new_r = r + dx[i];
            int new_c = c + dy[i];
            if (isIndexInBound(new_r, new_c) && !vis[new_r][new_c] && ans[new_r][new_c] == CHAR_O) {
                dfs(new_r, new_c);
            }
        }
    }

    private static boolean isIndexInBound(int i, int j) {
        return (i >= 0 && i < ans.length) && (j >= 0 && j < ans[0].length);
    }
}

// G16
class Solution_Number_of_Distinct_Islands {
    private final int[] dx = {-1, +0, +0, +1};
    private final int[] dy = {+0, -1, +1, +0};
    private final HashSet<ArrayList<String>> set = new HashSet<>();
    private int rowCount, colCount;
    private boolean[][] vis;
    private int[][] grid;

    int countDistinctIslands(int[][] matrix) {
        grid = matrix;
        rowCount = grid.length;
        colCount = grid[0].length;
        vis = new boolean[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (!vis[i][j] && grid[i][j] == 1) {
                    ArrayList<String> list = new ArrayList<>();
                    dfs(i, j, list, i, j);
                    set.add(list);
                }
            }
        }

        return set.size();
    }

    private void dfs(int row, int col, ArrayList<String> shapeList, int row0, int col0) {
        vis[row][col] = true;
        shapeList.add((row - row0) + " " + (col - col0));
        for (int i = 0; i < 4; i++) {
            int new_r = row + dx[i];
            int new_c = col + dy[i];
            if (isIndexInBound(new_r, new_c) && !vis[new_r][new_c] && grid[new_r][new_c] == 1) {
                dfs(new_r, new_c, shapeList, row0, col0);
            }
        }
    }

    private boolean isIndexInBound(int i, int j) {
        return (i >= 0 && i < rowCount) && (j >= 0 && j < colCount);
    }

    private static class Pair {
        int xx;
        int yy;

        public Pair(int xx, int yy) {
            this.xx = xx;
            this.yy = yy;
        }
    }
}

// G17
class Solution_isBipartite_BFS {
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!check(i, V, adj, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean check(int start, int V, ArrayList<ArrayList<Integer>> adj, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int nei : adj.get(node)) {
                if (color[nei] == -1) {
                    color[nei] = 1 - color[node];
                    q.add(nei);
                } else if (color[nei] == color[node]) {
                    return false;
                }
            }
        }
        return true;
    }
}

// G18
class Solution_isBipartite_DFS {
    private int[] color;
    private ArrayList<ArrayList<Integer>> adj;

    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj) {
        this.adj = adj;
        color = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!dfs(i, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int node, int col) {
        color[node] = col;

        for (int nei : adj.get(node)) {
            if (color[nei] == -1) {
                if (!dfs(nei, 1 - col)) {
                    return false;
                }
            } else if (color[nei] == col) {
                return false;
            }
        }
        return true;
    }
}

// G19
class Solution_Detect_Cycle_Directed_Graph_DFS {
    private ArrayList<ArrayList<Integer>> adj;
    private boolean[] vis;
    private boolean[] pathVis;

    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adjList) {
        adj = adjList;
        vis = new boolean[V];
        pathVis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (dfs(i)) {
                    return true;
                }
            }
        }


        return false;
    }

    private boolean dfs(int node) {
        vis[node] = true;
        pathVis[node] = true;
        for (int nei : adj.get(node)) {
            if (!vis[nei]) {
                if (dfs(nei)) {
                    return true;
                }
            } else if (pathVis[nei]) {
                return true;
            }
        }
        pathVis[node] = false;
        return false;
    }
}

// G20
class Solution_Eventual_Safe_Nodes_DFS {
    private List<List<Integer>> adj;
    private boolean[] vis;
    private boolean[] pathVis;
    private boolean[] safeNode;

    List<Integer> eventualSafeNodes(int V, List<List<Integer>> adjList) {
        adj = adjList;
        vis = new boolean[V];
        pathVis = new boolean[V];
        safeNode = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (safeNode[i]) ans.add(i);
        }

        return ans;
    }

    // return true if cycle found, else false
    private boolean dfs(int node) {
        vis[node] = true;
        pathVis[node] = true;
        safeNode[node] = false;
        for (int nei : adj.get(node)) {
            if (!vis[nei]) {
                if (dfs(nei)) {
                    safeNode[node] = false; // If there is a cycle, it
                    // cannot be the part of safeNode.

                    return true;
                }
            } else if (pathVis[nei]) {
                safeNode[node] = false; // Cycle found on 'nei' so 'node'
                // cannot be the part of safeNode,
                // since node leads to a cycle.

                return true;
            }
        }
        safeNode[node] = true; // if 'node' completes the dfs call
        pathVis[node] = false;
        return false;
    }
}

// G21
class Solution_TopoSort_DFS {
    private Stack<Integer> st = new Stack<>();
    private ArrayList<ArrayList<Integer>> adj;
    private boolean[] vis;

    int[] topoSort(int V, ArrayList<ArrayList<Integer>> adjList) {
        adj = adjList;
        vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i);
            }
        }

        int[] topoSort = new int[V];
        for (int i = 0; i < V; i++) {
            topoSort[i] = st.pop();
        }
        return topoSort;
    }

    private void dfs(int node) {
        vis[node] = true;
        for (int nei : adj.get(node)) {
            if (!vis[nei]) {
                dfs(nei);
            }
        }
        st.push(node);
    }
}

// G22
class Solution_TopoSort_BFS {
    public int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> q = new LinkedList<>();
        int[] indegree = new int[V];
        int[] topoSort = new int[V];


        // store in-degree in an array
        for (int node = 0; node < V; node++) {
            for (int nei : adj.get(node)) {
                indegree[nei]++;
            }
        }


        // add all the elements from the in-degree array to queue
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int currentAnsPointer = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            topoSort[currentAnsPointer++] = node;
            for (int nei : adj.get(node)) {
                if (--indegree[nei] == 0) {
                    q.add(nei);
                }
            }
        }

        return topoSort;
    }
}

// G24
class Solution_Course_Scheduler_I_and_II {
    static int[] findOrder(int N, int P, ArrayList<ArrayList<Integer>> pre) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < P; i++) {
            adj.get(pre.get(i).get(1)).add(pre.get(i).get(0));
        }

        int[] indegree = new int[N];
        for (int node = 0; node < N; node++) {
            for (int nei : adj.get(node)) {
                indegree[nei]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int[] topoSort = new int[N];
        int idx = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            topoSort[idx++] = node;
            for (int nei : adj.get(node)) {
                if (--indegree[nei] == 0) {
                    q.add(nei);
                }
            }
        }

        return (idx == N) ? topoSort : new int[]{};
    }
}

// G25
class Solution_Eventual_Safe_Nodes_BFS {
    List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        List<List<Integer>> adjRev = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjRev.add(new ArrayList<>());
        }

        int[] indegree = new int[V];
        for (int node = 0; node < V; node++) {
            for (int nei : adj.get(node)) {
                adjRev.get(nei).add(node);
                indegree[node]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        List<Integer> safeNodes = new ArrayList<>();
        for (int node = 0; node < V; node++) {
            if (indegree[node] == 0) q.add(node);
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            safeNodes.add(node);
            for (int nei : adjRev.get(node)) {
                if (--indegree[nei] == 0) {
                    q.add(nei);
                }
            }
        }

        Collections.sort(safeNodes);
        return safeNodes;
    }
}

// G26
class Solution_Alien_Dictionary {
    private ArrayList<Integer> topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> q = new LinkedList<>();
        int[] indegree = new int[V];
        ArrayList<Integer> topoSortList = new ArrayList<>();


        // store in-degree in an array
        for (int node = 0; node < V; node++) {
            for (int nei : adj.get(node)) {
                indegree[nei]++;
            }
        }


        // add all the elements from the in-degree array to queue
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            topoSortList.add(node);
            for (int nei : adj.get(node)) {
                if (--indegree[nei] == 0) {
                    q.add(nei);
                }
            }
        }

        return topoSortList;
    }

    public String findOrder(String[] dict, int N, int K) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            adj.add(new ArrayList<>());
        }


        for (int i = 0; i < N - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i + 1];

            int minLen = Math.min(s1.length(), s2.length());
            for (int j = 0; j < minLen; j++) {
                char cc1 = s1.charAt(j);
                char cc2 = s2.charAt(j);
                if (cc1 != cc2) {
                    adj.get(cc1 - 'a').add(cc2 - 'a');
                    break;
                }
            }
        }

        ArrayList<Integer> topoSort = topoSort(K, adj);
        StringBuilder sb = new StringBuilder();
        for (int integer : topoSort) {
            sb.append((char) (integer + 'a'));
        }
        return sb.toString();
    }
}

// G27
class Solution_Shortest_Path_DAG {
    public int[] shortestPath(int N, int M, int[][] edges) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        // fill ArrayList with empty arraylists
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }


        // fill the adj list
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int wt = edge[2];
            adj.get(from).add(new Pair(to, wt));
        }

        boolean[] vis = new boolean[N];
        Stack<Integer> st = new Stack<>();

        /*
         Why TOPO SORT?
         Finding the shortest path to a vertex is easy if you already know the shortest paths to all the vertices
         that can precede it. Finding the longest path to a vertex in DAG is straightforward if you already know
         the longest path to all the vertices that can precede it.
         Processing the vertices in topological order ensures that by the time you get to a vertex, you've already
         processed all the vertices that can precede it.
         Dijkstra's algorithm is necessary for graphs that can contain cycles because they can't be
         topologically sorted.
        */
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                topoSort(i, adj, vis, st);
            }
        }

        // declare a distance array with arr[source] = 0
        int[] dist = new int[N];
        Arrays.fill(dist, (int) 1e9);
        dist[0] = 0;


        while (!st.isEmpty()) {
            int node = st.pop();
            for (int i = 0; i < adj.get(node).size(); i++) {
                int v = adj.get(node).get(i).node;
                int wt = adj.get(node).get(i).wt;
                dist[v] = Math.min(dist[v], dist[node] + wt);
            }
        }

        for (int i = 0; i < N; i++) {
            if (dist[i] == 1e9) dist[i] = -1;
        }

        return dist;
    }

    private void topoSort(int node, ArrayList<ArrayList<Pair>> adj, boolean[] vis, Stack<Integer> st) {
        vis[node] = true;
        for (Pair nei : adj.get(node)) {
            if (!vis[nei.node]) {
                topoSort(nei.node, adj, vis, st);
            }
        }
        st.push(node);
    }

    private static class Pair {
        int node;
        int wt;

        public Pair(int node, int wt) {
            this.node = node;
            this.wt = wt;
        }
    }
}

// G28
class Solution_Shortest_Path_Undirected_Graph_With_Unit_Weight {
    public int[] shortestPath(int[][] edges, int n, int m, int src) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] dist = new int[n];
        Arrays.fill(dist, (int) 1e9);
        dist[src] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int nei : adj.get(node)) {
                if (dist[node] + 1 < dist[nei]) {
                    dist[nei] = dist[node] + 1;
                    q.add(nei);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (dist[i] == 1e9) {
                dist[i] = -1;
            }
        }
        return dist;
    }
}

// G29, 30, 31
class Solution_Word_Ladder_I {
    public int ladderLength(String startWord, String targetWord, List<String> wordList) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(startWord, 0));

        Set<String> st = new HashSet<>(wordList);
        st.remove(startWord);

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            String word = pair.word;
            int steps = pair.step;

            if (word.equals(targetWord)) return steps + 1;

            // Poll word from queue and start replacing each character from i: 0-> length,
            // if the new word is in the word list, then add it to queue with an incremented
            // step. And remove the word from wordlist, to prevent going back to that specific
            // word in the future iteration which will unnecessarily increase the step count.
            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    String replaced = word.substring(0, i) + ch + word.substring(i + 1);
                    if (st.contains(replaced)) {
                        st.remove(replaced);
                        q.add(new Pair(replaced, steps + 1));
                    }
                }
            }
        }
        return 0;
    }

    private record Pair(String word, int step) {
    }
}

// G32, 33, 34
class Solution_Dijkstra {
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.distance - y.distance);
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e9);

        dist[S] = 0;
        pq.add(new Pair(0, S));

        while (!pq.isEmpty()) {
            int dis = pq.peek().distance;
            int node = pq.peek().node;
            pq.poll();

            for (ArrayList<Integer> nei_with_wt : adj.get(node)) {
                int adjNode = nei_with_wt.get(0);
                int edgeWt = nei_with_wt.get(1);

                if (dis + edgeWt < dist[adjNode]) {
                    dist[adjNode] = dis + edgeWt;
                    pq.add(new Pair(dist[adjNode], adjNode));
                }
            }
        }
        return dist;
    }

    private static class Pair {
        int distance;
        int node;

        public Pair(int distance, int node) {
            this.distance = distance;
            this.node = node;
        }
    }
}


/*------------------------------------------------------------------------------------------------------*/
class Solution_isPossible {
    public boolean isPossible(int N, int P, int[][] pre) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < P; i++) {
            adj.get(pre[i][1]).add(pre[i][0]);
        }

        int[] indegree = new int[N];

        for (int node = 0; node < N; node++) {
            for (int nei : adj.get(node)) {
                indegree[nei]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        ArrayList<Integer> topoSort = new ArrayList<>();

        while (!q.isEmpty()) {
            int node = q.poll();
            topoSort.add(node);
            for (int nei : adj.get(node)) {
                if (--indegree[nei] == 0) {
                    q.add(nei);
                }
            }
        }

        return topoSort.size() == N;
    }
}

class Solution_minTimeToReachBottomRight {
    // Define directions for moving in the grid (right, left, down, up)
    private static final int[] dX = {0, 0, 1, -1};
    private static final int[] dY = {1, -1, 0, 0};

    public static int minTimeToReachBottomRight(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;


        // Create a queue for BFS and add the starting point
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});

        // Create a 2D array to store distances and initialize the start point
        int[][] distance = new int[rows][cols];
        distance[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // Check if we have reached the bottom-right corner
            if (x == rows - 1 && y == cols - 1) {
                return distance[x][y] - 1; // Subtract 1 to account for initial 1
            }

            // Explore all possible directions
            for (int i = 0; i < 4; i++) {
                int newX = x + dX[i];
                int newY = y + dY[i];

                // Check if the new position is within bounds and not blocked
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == '.' && distance[newX][newY] == 0) {
                    queue.add(new int[]{newX, newY});
                    distance[newX][newY] = distance[x][y] + 1;
                }
            }
        }

        return -1; // No path found
    }

    public static String reachTheEnd(List<String> grid, int maxTime) {
        int result = minTimeToReachBottomRight(listToArr(grid));
        if (result <= maxTime && result != -1) return "Yes";
        else return "No";
    }

    private static char[][] listToArr(List<String> grid) {
        int rows = grid.size();
        int cols = grid.getFirst().length();

        char[][] grid_A = new char[rows][cols];
        for (int i = 0; i < grid.size(); i++) {
            grid_A[i] = grid.get(i).toCharArray();
        }

        return grid_A;
    }
}

class Solution_minimumEffortPath {
    private final int[] dr = {-1, +0, +0, +1};
    private final int[] dc = {+0, -1, +1, +0};

    public int minimumEffortPath(int[][] heights) {
        PriorityQueue<Triplet> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.d));
        int n = heights.length;
        int m = heights[0].length;

        int[][] dist = new int[n][m];
        for (int[] row : dist) {
            Arrays.fill(row, (int) 1e9);
        }

        dist[0][0] = 0;
        pq.add(new Triplet(0, 0, 0));

        while (!pq.isEmpty()) {
            Triplet it = pq.poll();
            int diff = it.d;
            int row = it.r;
            int col = it.c;

            if (row == n - 1 && col == m - 1) return diff;

            for (int i = 0; i < 4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];
                if (!isInRange(nr, nc, heights)) continue;
                int newEffort = Math.max(Math.abs(heights[row][col] - heights[nr][nc]), diff);
                if (newEffort < dist[nr][nc]) {
                    dist[nr][nc] = newEffort;
                    pq.add(new Triplet(newEffort, nr, nc));
                }
            }
        }
        return 0;
    }

    private boolean isInRange(int nr, int nc, int[][] heights) {
        return (nr >= 0 && nr < heights.length) && (nc >= 0 && nc < heights[0].length) && (heights[nr][nc] != 0);
    }

    private static class Triplet {
        int d, r, c;

        public Triplet(int d, int r, int c) {
            this.d = d;
            this.r = r;
            this.c = c;
        }
    }
}

class Solution_shortestPath_in_binary_maze {
    private final int[] dr = {-1, +0, +0, +1};
    private final int[] dc = {+0, -1, +1, +0};

    int shortestPath(int[][] grid, int[] src, int[] dest) {
        int INFINITY = (int) 1e9;
        int srcR = src[0], srcC = src[1];
        int destR = dest[0], destC = dest[1];
        int r = grid.length, c = grid[0].length;

        // make dist array and fill will infinity
        int[][] dist = new int[r][c];
        for (int[] row : dist) Arrays.fill(row, INFINITY);

        // queue for dijkstra's algo
        Queue<Triplet> q = new LinkedList<>();

        // initial config
        dist[srcR][srcC] = 0;
        q.add(new Triplet(0, srcR, srcC));


        // start popping out elements from queue
        // check and add adj cells to queue
        // if new dist is less than prev dist.
        outer:
        while (!q.isEmpty()) {
            Triplet t = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = t.r + dr[i];
                int nc = t.c + dc[i];
                if (!isInRange(nr, nc, grid)) continue;
                if (t.d + 1 < dist[nr][nc]) {
                    dist[nr][nc] = t.d + 1;
                    q.add(new Triplet(t.d + 1, nr, nc));
                }
                if (nr == dest[0] && nc == dest[1]) break outer;
            }
        }

        return (dist[destR][destC] == INFINITY) ? (-1) : (dist[destR][destC]);
    }

    private boolean isInRange(int nr, int nc, int[][] grid) {
        return (nr >= 0 && nr < grid.length) && (nc >= 0 && nc < grid[0].length) && (grid[nr][nc] != 0);
    }

    private static class Triplet {
        int d, r, c;

        public Triplet(int d, int r, int c) {
            this.d = d;
            this.r = r;
            this.c = c;
        }
    }
}

class Solution_shortestPath_in_weighted_undirected_graph {
    // link: https://www.geeksforgeeks.org/problems/shortest-path-in-weighted-undirected-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=shortest-path-in-weighted-undirected-graph
    public List<Integer> shortestPath(int n, int m, int[][] edges) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            adj.get(u).add(new Pair(w, v));
            adj.get(v).add(new Pair(w, u));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(pair -> pair.distance));
        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = (int) 1e9;
            parent[i] = i;
        }

        dist[1] = 0;
        pq.add(new Pair(0, 1));

        while (!pq.isEmpty()) {
            Pair it = pq.poll();
            int node = it.node;
            int dis = it.distance;

            for (Pair iter : adj.get(node)) {
                int adjNode = iter.node;
                int edgeWt = iter.distance;
                if (dis + edgeWt < dist[adjNode]) {
                    dist[adjNode] = dis + edgeWt;
                    pq.add(new Pair(dis + edgeWt, adjNode));
                    parent[adjNode] = node;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        if (dist[n] == (int) 1e9) {
            ans.add(-1);
            return ans;
        }

        int node = n;
        while (node != parent[node]) {
            ans.add(node);
            node = parent[node];
        }
        ans.add(1);
        Collections.reverse(ans);
        return ans;
    }

    private static class Pair {
        int distance;
        int node;

        public Pair(int distance, int node) {
            this.distance = distance;
            this.node = node;
        }
    }
}

class Solution_DFS_Of_Graph {
    private final ArrayList<Integer> ans = new ArrayList<>();
    private int[] vis;

    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        vis = new int[V];
        helper(0, adj);
        return ans;
    }

    private void helper(int node, ArrayList<ArrayList<Integer>> adj) {
        vis[node] = 1;
        ans.add(node);

        for (int nei : adj.get(node)) {
            if (vis[nei] == 0) {
                helper(nei, adj);
            }
        }
    }
}