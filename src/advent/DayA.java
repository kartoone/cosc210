package advent;

import java.io.File;
import java.io.FileNotFoundException;

// linear algebra library
import com.google.ortools.Loader;
import com.google.ortools.sat.*;

import java.util.*;

public class DayA {

    public static class MinSumIntegerSolver {

        /**
         * Solve: minimize sum(x) subject to A x = b
         * where x are non-negative integers.
         */
        public static Result solve(int[][] A, int[] b, int maxVarValue) {
            Loader.loadNativeLibraries();

            int m = A.length;
            int n = A[0].length;

            CpModel model = new CpModel();

            IntVar[] x = new IntVar[n];
            for (int i = 0; i < n; i++) {
                x[i] = model.newIntVar(0, maxVarValue, "x" + i);
            }

            // Ax = b constraints
            for (int r = 0; r < m; r++) {
                LinearExprBuilder expr = LinearExpr.newBuilder();
                for (int j = 0; j < n; j++) {
                    if (A[r][j] != 0) {
                        expr.addTerm(x[j], A[r][j]);
                    }
                }
                model.addEquality(expr, b[r]);
            }

            // minimize sum(x)
            LinearExprBuilder obj = LinearExpr.newBuilder();
            for (IntVar v : x) obj.addTerm(v, 1);
            model.minimize(obj);

            CpSolver solver = new CpSolver();
            CpSolverStatus status = solver.solve(model);

            if (status != CpSolverStatus.OPTIMAL && status != CpSolverStatus.FEASIBLE) {
                return new Result(status, null, Long.MAX_VALUE);
            }

            long[] sol = new long[n];
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sol[i] = Math.round(solver.value(x[i]));
                sum += sol[i];
            }

            return new Result(status, sol, sum);
        }

        public static class Result {
            public final CpSolverStatus status;
            public final long[] x;
            public final long sum;

            Result(CpSolverStatus status, long[] x, long sum) {
                this.status = status;
                this.x = x;
                this.sum = sum;
            }
        }
    }

    static class Board {
        private int[] state;
        private int[] target;
        private int[] joltages;
        private ArrayList<int[]> presses = new ArrayList<>();
        public Board(String line) {
            String[] parts = line.split(" ");
            String targetstr = parts[0].substring(1,parts[0].length()-1);
            target = new int[targetstr.length()];
            state = new int[target.length];
            for (int i=0; i<target.length; i++) {
                char c = targetstr.charAt(i);
                target[i] = c=='#' ? 1 : 0;
            }
            // turn the presses into a list of lights to toggle
            for (int i=1; i<parts.length-1; i++) {
                String lightstr = parts[i].substring(1,parts[i].length()-1);
                String lightstrparts[] = lightstr.split(",");
                int pressarr[] = new int[lightstrparts.length];
                for (int j = 0; j < lightstrparts.length; j++) {
                   pressarr[j] = Integer.parseInt(lightstrparts[j]); 
                }
                presses.add(pressarr);
            }
            // handle the joltages similar to presses but without a loop
            String joltstr = parts[parts.length-1].substring(1,parts[parts.length-1].length()-1);
            String joltparts[] = joltstr.split(",");
            joltages = new int[joltparts.length];
            for (int j = 0; j < joltparts.length; j++) {
               joltages[j] = Integer.parseInt(joltparts[j]); 
            }
        }

        private void resetState() {
            state = new int[state.length];
        }

        private void applyPress(int[] press) {
            for (int i : press) {
               state[i] = (state[i] + 1)%2; 
            }
        }
        
        // try every combo of "n" button presses and see if it leads to target state
        public boolean canSolve(int n) {
            ArrayList<int[]> combos = new ArrayList<>();

            int combo[] = new int[n];
            while (true) {
                combos.add(combo);
                combo = combo.clone();
                // work right to left incrementing each index up to p
                int pos = n-1;
                while (pos >= 0) {
                    combo[pos]++;
                    if (combo[pos] < presses.size())    
                        break;
                    combo[pos] = 0; // rollover the index
                    pos--;
                }
                if (pos < 0) 
                    break; // we rolled over on most significant digit meaning we have made it through all the combos
            }

            Iterator<int[]> it = combos.iterator();
            while (it.hasNext()) {
                int[] nextCombo = it.next();
                this.resetState();
                for (int i : nextCombo) {
                   this.applyPress(presses.get(i));
                }
                if (java.util.Arrays.equals(state,target))
                    return true;
            }

            return false;
        }

        // so basic idea is to try all the possible combinations of button presses
        // but the total number of combos is in the trillions ... so no way to do that
        // and even just processing them all without storing them would take too long

        // one thing you can do is look at the joltages and figure out a "minimum" and maximum number of presses
        // each press can only increment a joltage value by 1 
        // we need to have a MINIMUM of the max joltage presses ... in other words "n" needs to be at least the max joltage!

        // can solve this as linear equation!
        // let x0 be number of times press0 is made
        // let x1 be number of times press1 is made
        // etc...

        // for example from my input:
        // [#.#..#] (1,3) (3,4) (0,3,5) (1,2,3,4) (0,2,5) (0,1) (2,5) {44,35,48,43,24,44}
        //   press#   0     1      2        3        4       5    6 
        //   x2 + x4 + x5 = 44
        //   x0 + x3 + x5 = 35
        //   x3 + x4 + x6 = 48
        //   x0 + x1 + x2 + x3 = 43
        //   x1 + x3 = 24
        //   x2 + x4 + x6 = 44
        //   x0 + x1 + x2 + x3 + x4 + x5 + x6 = n

        static boolean contains(int[] arr, int value) {
            for (int x : arr) {
                if (x == value) return true;
            }
            return false;
        }

        public int canSolve2() {
            int[][] A = new int[joltages.length][]; 
            for (int j=0; j<joltages.length; j++) {
                A[j] = new int[presses.size()];
                for (int p=0; p<presses.size(); p++) {
                    int jolts[] = presses.get(p);
                    A[j][p] = contains(jolts, j) ? 1 : 0;
                }
            }

            MinSumIntegerSolver.Result res =
                MinSumIntegerSolver.solve(A, joltages, 200);

            System.out.println("Status: " + res.status);
            if (res.x != null) {
                for (int i = 0; i < res.x.length; i++) {
                    System.out.println("x" + i + " = " + res.x[i]);
                }
                System.out.println("min n = " + res.sum);
            }
            return (int) res.sum;
        }

    }

    public static void main(String[] args) {
        File file = new File("daya.txt");
        try {
           Scanner scanner = new Scanner(file);
           //System.out.println(method1(scanner));
           System.out.println(method2(scanner));
           scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }

    // breadth first search of trying all the different combos of all the buttons
    @SuppressWarnings("unused")
	private static long method1(Scanner scanner) {
        ArrayList<Board> boards = new ArrayList<>();

        // first read in the file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            boards.add(new Board(line));
        }

        // bread first search
        long total = 0;
        Iterator<Board> it = boards.iterator();
        while (it.hasNext()) {
            Board b = it.next();
            int n=1;
            while (n<10000) {
                if (n>=8)
                    System.out.println(n);
                if (b.canSolve(n)) {
                    total += n;
                    break;
                }
                n++;
            }
        }
        
        return total;
    }

    private static long method2(Scanner scanner) {
        ArrayList<Board> boards = new ArrayList<>();

        // first read in the file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            boards.add(new Board(line));
        }

        // bread first search
        long total = 0;
        Iterator<Board> it = boards.iterator();
        while (it.hasNext()) {
            Board b = it.next();
            total += b.canSolve2();
        }
        return total;
    }

}
