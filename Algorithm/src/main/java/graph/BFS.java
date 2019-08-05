package graph;

import java.util.LinkedList;

/**
 * description:
 * 广度优先搜索
 *
 * @author zhun.huang 2019-08-03
 */
public class BFS {

    /**
     * 无向图
     */
    private Graph graph;

    public BFS() {
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

    public void bfs(int from, int to) {
        if (from == to) {
            return;
        }
        /**
         * 存储已经访问过的节点
         */
        boolean[] visited = new boolean[graph.num];

        /**
         * 存储访问过，但相邻的顶点没有被全部访问完的
         */
        LinkedList<Graph.Node> queue = new LinkedList<>();

        /**
         * 记录访问路径
         */
        Graph.Node[] prev = new Graph.Node[graph.num];

        visited[from] = true;

        queue.add(Graph.Node.getInstance(from));

        for (int i = 0; i < graph.num; i++) {
            prev[i] = Graph.Node.getInstance(-1);
        }
        while (!queue.isEmpty()) {
            // 从队列中拿出一个节点
            Graph.Node pop = queue.pop();
            /**
             * 将这个节点的相邻节点全部遍历一遍
             */
            for (Graph.Node currentNode : (LinkedList<Graph.Node>)graph.adj[pop.id]) {
                int currentId = currentNode.id;
                if (visited[currentId]) {
                    continue;
                }
                // 没有访问过,放到待访问队列中去
                queue.add(currentNode);
                // 数组的第 i个位置中的i代表第i个顶点，存储的值，是他的上一个节点。
                prev[currentId] = pop;
                // 判断该节点是否匹配
                if (currentId == to) {
                    print(prev, from, to);
                }
                // 设置该节点访问过为true。
                visited[currentId] = true;

            }

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
