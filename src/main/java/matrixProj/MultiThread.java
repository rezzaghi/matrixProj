package matrixProj;

import java.util.ArrayList;
import java.util.List;

public class MultiThread {

    public static void multiply(float[][] matrix1, float[][] matrix2, float[][] result) {
     List<Thread> threads = new ArrayList<>();
     int rows1 = matrix1.length;
     for (int i = 0; i < rows1; i++) {
      WorkerThread task = new WorkerThread(result, matrix1, matrix2, i);
      Thread thread = new Thread(task);
      thread.start();
      threads.add(thread);
      if (threads.size() % 2 == 0) {
       waitForThreads(threads);
      }
     }
    }

    private static void waitForThreads(List<Thread> threads) {
     for (Thread thread : threads) {
      try {
       thread.join();
      } catch (InterruptedException e) {
       e.printStackTrace();
      }
     }
     threads.clear();
    }
}
