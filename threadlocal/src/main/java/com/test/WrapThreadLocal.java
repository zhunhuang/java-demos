package com.test;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author: zhun.huang
 * @create: 2018-04-11 下午7:13
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class WrapThreadLocal<T> extends InheritableThreadLocal<T>{

    public static InheritableThreadLocal<Map<InheritableThreadLocal<?>, ?>> holder =
            new InheritableThreadLocal<Map<InheritableThreadLocal<?>, ?>>() {
                @Override
                protected Map<InheritableThreadLocal<?>, ?> initialValue() {
                    return new WeakHashMap<InheritableThreadLocal<?>, Object>();
                }

                @Override
                protected Map<InheritableThreadLocal<?>, ?> childValue(Map<InheritableThreadLocal<?>, ?> parentValue) {
                    return new WeakHashMap<InheritableThreadLocal<?>, Object>(parentValue);
                }
            };

    @Override
    public final T get() {
        T value = super.get();
        if (null != value) {
            addValue();
        }
        return value;
    }

    @Override
    public final void set(T value) {
        super.set(value);
        if (null == value) { // may set null to remove value
            removeValue();
        } else {
            addValue();
        }
    }

    @Override
    public final void remove() {
        removeValue();
        super.remove();
    }

    private void addValue() {
        if (!holder.get().containsKey(this)) {
            holder.get().put(this, null); // WeakHashMap supports null value.
        }
    }

    private void removeValue() {
        holder.get().remove(this);
    }

}
