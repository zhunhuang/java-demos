package jdbc;

import java.sql.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * description:
 *
 * @author zhunhuang, 2019/11/13
 */
public class ConcurrentUpdate {

    public static final int threadCount = 40;

    public static ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

    public static volatile AtomicLong atomicInteger = new AtomicLong();

    public static volatile CountDownLatch countDownLatch = new CountDownLatch(threadCount);

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        Class.forName("com.mysql.jdbc.Driver");
        Thread countThread = new Thread(new CountRunnable());
        countThread.start();
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(new AddRunnable("jdbc:mysql://localhost:3306/test", "root", "123456"));
        }
        countDownLatch.await();
        countThread.interrupt();
        System.out.println("结束主线程");

    }

    public static class AddRunnable implements Runnable {

        private Connection connection;

        AddRunnable(String url, String user, String pwd) {
            try {
                connection = DriverManager.getConnection(url, user, pwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                connection.setAutoCommit(false);
                PreparedStatement queryStat = connection.prepareStatement("select count from bill_storage  where id=1 for update");
                PreparedStatement updateStat = connection.prepareStatement("update bill_storage set count=count+1.0 where id=1");
                for (int i = 0; i < 10000; i++) {
                    ResultSet resultSet = queryStat.executeQuery();
                    resultSet.next();
                    updateStat.execute();
//                    preparedStatement.setInt(1,2);
//                    preparedStatement.execute();
//                    preparedStatement.setInt(1,3);
//                    preparedStatement.execute();
//                    preparedStatement.setInt(1,4);
//                    preparedStatement.execute();
//                    preparedStatement.setInt(1,5);
//                    preparedStatement.execute();
                    connection.commit();
                    atomicInteger.incrementAndGet();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }
    }

    public static class CountRunnable implements Runnable {

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    long qps = atomicInteger.get() * 1000 / (System.currentTimeMillis() - startTime);
                    System.out.println("当前qps：" + qps);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("结束统计线程");
        }
    }


}
