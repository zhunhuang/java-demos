package graph;

/**
 * description:
 * 广度优先搜索遍历
 * @author zhun.huang 2019-08-03
 */
public class DFS {

    /**
     * 无向图
     */
    private Graph graph;

    public DFS() {
        graph = new Graph(8);

        graph.addEdge(Graph.Node.getInstance(0), Graph.Node.getInstance(1));
        graph.addEdge(Graph.Node.getInstance(0), Graph.Node.getInstance(3));
        graph.addEdge(Graph.Node.getInstance(1), Graph.Node.getInstance(2));
        graph.addEdge(Graph.Node.getInstance(1), Graph.Node.getInstance(4));
        graph.addEdge(Graph.Node.getInstance(2), Graph.Node.getInstance(5));
        graph.addEdge(Graph.Node.getInstance(3), Graph.Node.getInstance(4));
        graph.addEdge(Graph.Node.getInstance(4), Graph.Node.getInstance(5));
        graph.addEdge(Graph.Node.getInstance(4), Graph.Node.getInstance(6));
        graph.addEdge(Graph.Node.getInstance(5), Graph.Node.getInstance(7));
        graph.addEdge(Graph.Node.getInstance(6), Graph.Node.getInstance(7));
    }

    private boolean founded = false;

    public void dfs(int from, int to) {
        // 初始化
        boolean[] visited = new boolean[graph.num];
        Graph.Node[] prev = new Graph.Node[graph.num];
        for (int i = 0; i < graph.num; i++) {
            prev[i] = Graph.Node.getInstance(-1);
        }
        // 开始递归查找
        recursiveDfs(Graph.Node.getInstance(from),Graph.Node.getInstance(to),visited,prev);

        print(prev,from,to);
    }

    private void recursiveDfs(Graph.Node from, Graph.Node to, boolean[] visited, Graph.Node[] prev) {
        if (founded) {
            return;
        }
        visited[from.id] = true;
        if (from == to) {
            founded = true;
            return;
        }
        for (int i = 0; i < graph.adj[from.id].size(); i++) {
            Graph.Node current = (Graph.Node)graph.adj[from.id].get(i);
            if (visited[current.id]) {
                continue;
            }
            prev[current.id] = from;
            recursiveDfs(current,to,visited,prev);
        }
    }

    public void print(Graph.Node[] prev, int from, int to) {
        if (prev[to].id != -1 && to != from) {
            // 打印该节点之前，先打印该节点的上一个节点。
            print(prev, from, prev[to].id);
        }
        System.out.print(to + " ");
    }

    public static void main(String[] args) {
        new BFS().bfs(0, 6);
    }
}
