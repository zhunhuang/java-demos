package data.structure.loopArray.usage1;

/**
 * description:
 * 缺点：无法利用pop后的空间
 * @author zhun.huang 2019-03-08
 */
public class ArrayImpl<T> implements Queue<T> {

    private int size;
    private Object[] array;

    private int head = 0;
    private int tail = 0;

    ArrayImpl(int size) {
        this.size = size;
        this.array = new Object[size];
    }

    @Override
    public void push(T o) {
        if (tail == size - 1) {
            throw new RuntimeException("queue is full");
        }
        array[tail++] = o;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (head == tail) {
            throw new RuntimeException("queue is empty");
        }
        return (T) array[head++];
    }

    @Override
    public long length() {
        return tail - head;
    }
}
