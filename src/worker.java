import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class worker {
    private static Integer count = 0;
    //创建一个阻塞队列
    final BlockingQueue blockingQueue = new ArrayBlockingQueue<>(5);
    public static void main(String[] args) {
        worker test3 = new worker();
        new Thread(test3.new Worker()).start();
        new Thread(test3.new Consumer()).start();

    }
    static int arr[]=new int[100];
     class Worker implements Runnable {
     
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    blockingQueue.put(1);
                    arr[i]= 1;
                    count++;
                    System.out.println(Thread.currentThread().getName()
                            + " worker produce " + i + " state is new");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    
                    if(arr[i] == 1) {
                     blockingQueue.take();
                     System.out.println(Thread.currentThread().getName()
                             + " processed " + i + " it is FULL FILLED");
                     arr[i] = 0;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}