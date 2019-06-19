package learn.forkjoin;

import com.sun.deploy.Environment;

import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * description:
 * fork/join pool framework 完成文件夹文件数量计算
 *
 * @author zhun.huang 2018-11-13
 */
public class DirFileCounter {
    static ForkJoinPool forkJoinPool = new ForkJoinPool();

    public static void main(String[] args) {
        CountingTask countingTask = new CountingTask(Environment.getDeploymentHome());
        Integer invoke = forkJoinPool.invoke(countingTask);
        System.out.println(invoke);

        CountingTask countingTask2 = new CountingTask(new File("/home/zhunhuang/q/IdeaProject/campusTraining/java_demos"));
        invoke = forkJoinPool.invoke(countingTask2);
        System.out.println(invoke);
    }

    static class CountingTask extends RecursiveTask<Integer> {
        private File dir;

        public CountingTask(File dir) {
            System.out.println(dir.getPath());
            this.dir = dir;
        }

        @Override
        protected Integer compute() {
            int count = 0;
            File files[] = dir.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        // 对每个子目录都新建一个子任务。
                        CountingTask countingTask = new CountingTask(f);
                        // 类似于 executorService.execute(); 开始异步执行的意思
                        countingTask.fork();
                        count += countingTask.join();
                    } else {
                        count++;
                    }
                    System.out.println(f.getName());
                }
            }

            return count;
        }
    }
}
