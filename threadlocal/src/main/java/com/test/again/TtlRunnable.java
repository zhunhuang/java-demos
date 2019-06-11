package com.test.again;


import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public final class TtlRunnable implements Runnable {
    private final AtomicReference<Map<TransmittableThreadLocal<?>, Object>> copiedRef;
    private final Runnable runnable;
    private final boolean releaseTtlValueReferenceAfterRun;

    private TtlRunnable(Runnable runnable, boolean releaseTtlValueReferenceAfterRun) {
        this.copiedRef = new AtomicReference<Map<TransmittableThreadLocal<?>, Object>>(TransmittableThreadLocal.copy());
        this.runnable = runnable;
        this.releaseTtlValueReferenceAfterRun = releaseTtlValueReferenceAfterRun;
    }

    /**
     * wrap method {@link Runnable#run()}.
     */
    @Override
    public void run() {
        Map<TransmittableThreadLocal<?>, Object> copied = copiedRef.get();
        if (copied == null || releaseTtlValueReferenceAfterRun && !copiedRef.compareAndSet(copied, null)) {
            throw new IllegalStateException("TTL value reference is released after run!");
        }

        Map<TransmittableThreadLocal<?>, Object> backup = TransmittableThreadLocal.backupAndSetToCopied(copied);
        try {
            runnable.run();
        } finally {
            TransmittableThreadLocal.restoreBackup(backup);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " - " + runnable.toString();
    }

    public static TtlRunnable get(Runnable runnable, boolean releaseTtlValueReferenceAfterRun, boolean idempotent) {
        if (null == runnable) {
            return null;
        }

        if (runnable instanceof TtlRunnable) {
            if (idempotent) {
                // avoid redundant decoration, and ensure idempotency
                return (TtlRunnable) runnable;
            } else {
                throw new IllegalStateException("Already TtlRunnable!");
            }
        }
        return new TtlRunnable(runnable, releaseTtlValueReferenceAfterRun);
    }

    public static List<TtlRunnable> gets(Collection<? extends Runnable> tasks, boolean releaseTtlValueReferenceAfterRun, boolean idempotent) {
        if (null == tasks) {
            return Collections.emptyList();
        }
        List<TtlRunnable> copy = new ArrayList<TtlRunnable>();
        for (Runnable task : tasks) {
            copy.add(TtlRunnable.get(task, releaseTtlValueReferenceAfterRun, idempotent));
        }
        return copy;
    }
}
