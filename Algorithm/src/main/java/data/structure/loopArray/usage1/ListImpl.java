package data.structure.loopArray.usage1;

/**
 * description:
 * 缺点，占用内存更多
 * @author zhun.huang 2019-03-08
 */
public class ListImpl<T> implements Queue<T> {

    private Node<T> head;

    private Node<T> tail;

    private int size;

    @Override
    public void push(T o) {
        if (head == null) {
            head = Node.newNode(o);
            tail = head;
            size = 1;
        } else {
            Node<T> newNode = Node.newNode(o);
            newNode.setPre(tail);
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }
    }

    @Override
    public T pop() {
        if (head == null) {
            throw new RuntimeException("queue is empty");
        }
        Node<T> poped = head;
        head = head.getNext();
        if (head !=null) {
            head.setPre(null);
        }
        size--;
        return poped.getData();
    }

    @Override
    public long length() {
        return size;
    }

    private static class Node<T> {

        private T data;
        private Node<T> next;

        private Node<T> pre;

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPre() {
            return pre;
        }

        public void setPre(Node<T> pre) {
            this.pre = pre;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public static <T> Node<T> newNode(T data) {
            Node<T> node = new Node<>();
            node.setData(data);
            return node;
        }
    }
}
