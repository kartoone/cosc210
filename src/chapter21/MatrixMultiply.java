package chapter21;

public class MatrixMultiply {

    int[][] multiply(int[][] a, int[][] b) {
        int rowsA = a.length;
        int rowsB = b.length;
        int colsA = a[0].length;
        int colsB = b[0].length;

        // the number of columns in A must equal the number of rows in B
        if (colsA != rowsB) {
            throw new IllegalArgumentException("Incompatible matrix dimensions");
        }

        // all positions in the result automatically initialized to 0
        int[][] result = new int[rowsA][colsB];

        // [i][j] is the position in result we are "working on" inside the k loop
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                // k iterates through columns of A and rows of B
                // note that "k" is used as the second index for A and the first index for B
                // which corresponds to the column of A and the row of B
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    int[][] parallel_multiply(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;

        // the number of columns in A must equal the number of rows in B
        if (colsA != b.length) {
            throw new IllegalArgumentException("Incompatible matrix dimensions");
        }

        // all positions automatically initialized to 0
        int[][] result = new int[rowsA][colsB];

        Thread[] threads = new Thread[rowsA];
        for (int i = 0; i < rowsA; i++) {
            final int row = i;
            // launch a new thread to tackle each row in a separate thread
            threads[i] = new Thread(() -> {
                for (int j = 0; j < colsB; j++) {
                    for (int k = 0; k < colsA; k++) {
                        result[row][j] += a[row][k] * b[k][j];
                    }
                }
            });
            threads[i].start();
        }

        // wait for all threads to finish
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return result;
    }
        
    public static void main(String[] args) {
        // Example usage
        MatrixMultiply mm = new MatrixMultiply();
        int[][] reallyLargeA = new int[1000][1000];
        int[][] reallyLargeB = new int[1000][1000];
        // Fill reallyLargeA and reallyLargeB with data as needed
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                reallyLargeA[i][j] = i + j;
                reallyLargeB[j][i] = i - j;
            }
        }
        System.out.println("Sequential multiplication starting");
        long startTime = System.currentTimeMillis();
        int[][] result = mm.multiply(reallyLargeA, reallyLargeB);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms");

        System.out.println("Parallel multiplication starting");
        startTime = System.currentTimeMillis();
        int[][] parallelResult = mm.parallel_multiply(reallyLargeA, reallyLargeB);
        endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms"); 

    }

}
