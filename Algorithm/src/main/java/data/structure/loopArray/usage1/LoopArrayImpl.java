package data.structure.loopArray.usage1;

/**
 * description:
 *
 * @author zhun.huang 2019-03-08
 */
public class LoopArrayImpl<T> implements Queue<T> {

    private  int size;
    private Object[] array;

    private int head = 0;
    private int tail = 0;

    LoopArrayImpl(int size) {
        this.size = size;
        this.array = new Object[size];
    }

    @Override
    public void push(T o) {
        if ((tail+1)% size ==head) {
            throw new RuntimeException("queue is full");
        }
        array[tail] = o;
        tail = (tail+1)% size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (head == tail) {
            throw new RuntimeException("queue is empty");
        }
        T value = (T) array[head];
        head = (head+1)% size;
        return value;
    }

    @Override
    public long length() {
        return (tail+ size -head)% size;
    }
}
