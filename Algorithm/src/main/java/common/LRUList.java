package common;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * description:
 *
 * @author zhun.huang 2019-08-01
 */
public class LRUList<T> implements List<T> {

    private Node<T> head;

    private Node<T> tail;

    private int threshold = Integer.MAX_VALUE;

    private int size;

    public T get(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = findNodeByIndex(index);
        swapToTail(node);
        return node.getValue();
    }

    @Override
    public T set(int index, T element) {
        if (index> size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = findNodeByIndex(index);
        T oldValue = node.getValue();
        node.setValue(element);
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        if (index> size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = findNodeByIndex(index);


        Node<T> newNode = new Node<>();
        newNode.setValue(element);
        newNode.setPrev(node.getPrev());
        newNode.setNext(node);

        node.getPrev().setNext(newNode);

        node.setPrev(newNode);
        size++;
    }

    private Node<T> findNodeByIndex(int index) {
        Node<T> node = head;
        int tmp = 0;
        while (tmp!=index) {
            node = node.getNext();
            tmp++;
        }
        return node;
    }

    @Override
    public T remove(int index) {
        Node<T> node = findNodeByIndex(index);
        Node<T> prev = node.getPrev();
        Node<T> next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;
        return node.getValue();
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void swapToTail(Node<T> node) {
        Node<T> tailPrev = tail.getPrev();

        node.getPrev().setNext(tail);
        node.getNext().setPrev(tail);
        tail.setPrev(node.getPrev());
        tail.setNext(node.getNext());

        node.setPrev(tailPrev);
        node.setNext(null);
        tailPrev.setNext(node);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean add(T value) {
        Node<T> newNode = new Node<>();
        newNode.setPrev(tail);
        newNode.setValue(value);

        tail.setNext(newNode);
        tail = newNode;
        size++;
        if (size>threshold) {
            removeHead();
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head=null;
        tail=null;
        size=0;
    }

    private void removeHead() {
        head = head.getNext();
        head.setPrev(null);
        size--;
    }

    public static class Node<T> {
        private T value;

        private Node<T> next;

        private Node<T> prev;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }
    }
}
