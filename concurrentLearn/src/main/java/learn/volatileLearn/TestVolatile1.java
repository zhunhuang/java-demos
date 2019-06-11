package learn.volatileLearn;

/**
 * @author: zhun.huang
 * @create: 2018-03-07 下午9:05
 * @email: nolan.zhun@gmail.com
 * @description: 测试
 */
public class TestVolatile1 {

    public static class Car {

        /**
         * 发动机, 可独立生产
         */
        private volatile boolean engineFinished;

        /**
         * 外壳, 必须发动机生产完毕后才能做
         */
        private volatile boolean shellFinished;

        /**
         * 内饰, 必须外壳做好后才能做
         */
        private volatile boolean trimFinished;

        public boolean isEngineFinished() {
            return engineFinished;
        }

        public void setEngineFinished(boolean engineFinished) {
            this.engineFinished = engineFinished;
        }

        public boolean isShellFinished() {
            return shellFinished;
        }

        public void setShellFinished(boolean shellFinished) {
            this.shellFinished = shellFinished;
        }

        public boolean isTrimFinished() {
            return trimFinished;
        }

        public void setTrimFinished(boolean trimFinished) {
            this.trimFinished = trimFinished;
        }
    }

    public static class WorkerA implements Runnable {

        private Car car;

        public WorkerA(Car car) {
            this.car = car;
        }

        @Override
        public void run() {
            if (!car.isEngineFinished()) {
                System.out.println("workerA开始生产发动机....");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("workerA 发动机生产完毕....");
                car.setEngineFinished(true);
            } else {
                System.out.println("发动机已经生产, workerA 继续进行下一步....");
            }

            while (!car.isShellFinished()) {
                System.out.println("workerA 等待外壳生产完毕....");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("外壳生产完毕, workerA开始生产内饰....");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("workerA 内饰生产完毕....");
            car.setTrimFinished(true);
        }
    }

    public static class WorkerB implements Runnable {

        private Car car;

        public WorkerB(Car car) {
            this.car = car;
        }

        @Override
        public void run() {
            while (!car.isEngineFinished()) {
                System.out.println("workerB 等待发动机生产完毕....");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!car.isShellFinished()) {
                System.out.println("workerB开始生产外壳....");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("workerB 外壳生产完毕....");
                car.setShellFinished(true);
            } else {
                System.out.println("外壳已经生产, workerB 继续进行下一步....");
            }
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
        long time = System.currentTimeMillis();
        new Thread(new WorkerB(car)).start();
        new Thread(new WorkerA(car)).start();
        while (!(car.engineFinished && car.shellFinished && car.trimFinished)) {
        }
        System.out.println("总计耗时:"+ (System.currentTimeMillis() - time));
    }
}
