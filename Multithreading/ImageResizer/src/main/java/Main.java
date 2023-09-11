import java.io.File;



public class Main {

    public static void main(String[] args) {
        String srcFolder = "ImageResizer\\src";
        String dstFolder = "ImageResizer\\dst";


        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        int numThreads = Runtime.getRuntime().availableProcessors();

        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[numThreads]; // Объявляем массив потоков

        if (files != null) {
            int filesPerThread = files.length / numThreads;
            int startIndex = 0;

            for (int i = 0; i < numThreads; i++) {
                int endIndex = (i == numThreads - 1) ? files.length : startIndex + filesPerThread;

                File[] subArray = new File[endIndex - startIndex];
                System.arraycopy(files, startIndex, subArray, 0, endIndex - startIndex);

                threads[i] = new Thread(new ImageResizer(subArray, new File(dstFolder), 300, 300));
                threads[i].start();

                startIndex = endIndex;
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
