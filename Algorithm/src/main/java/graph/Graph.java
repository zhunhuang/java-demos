package graph;

import java.util.LinkedList;

/**
 * description:
 * 使用邻接表 来表示图
 * 无权重的
 * @author zhun.huang 2019-08-03
 */
public class Graph<T> {

    /**
     * 顶点数量
     */
    public   int  num;

    /**
     * 邻接表，数组的每一个元素都是一个链表
     */
    public LinkedList<Node<T>> adj[];

    public Graph(int num) {
        this.num = num;
        adj = new LinkedList[num];
        for (int i = 0; i < num; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 添加一条有向边
     */
    public void addDirectionEdge(Node<T> from, Node<T> to) {
        if (from.id> num-1 || to.id> num-1) {
            throw new IndexOutOfBoundsException();
        }
        adj[from.id].add(to);
    }


    /**
     * 添加一条无向边
     */
    public void addEdge(Node<T> from, Node<T> to) {
        addDirectionEdge(from,to);

        addDirectionEdge(to,from);
    }


    public static class Node<T> {

        public Node(int id) {
            this.id = id;
        }

        /**
         * 顶点id
         */
        public int id;

        /**
         * 顶点存储的业务属性
         */
        public T value;

        public static Node getInstance(int id) {
            return new Node(id);
        }
    }
}
