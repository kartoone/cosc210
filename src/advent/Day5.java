package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) {
        File file = new File("day5.txt");
        try {
           Scanner scanner = new Scanner(file);
           //System.out.println(method1(scanner));
           System.out.println(method2(scanner));
           scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }

    @SuppressWarnings("unused")
	private static long method1(Scanner scanner) {
        long total = 0;
        ArrayList<Long[]> ranges = new ArrayList<>();
        boolean rangeFinder = true;
        while (scanner.hasNextLine()) {
            if (rangeFinder) {
                String range = scanner.nextLine();
                if (range.length()==0) {
                    rangeFinder = false;
                    continue;
                }
                String parts[] = range.split("-");
                ranges.add(new Long[] {Long.parseLong(parts[0]), Long.parseLong(parts[1])});
            } else {
                // found all the ranges ... now we are looking for spoiled
                long id = Long.parseLong(scanner.nextLine());
                for (Long[] longs : ranges) {
                    if (id>=longs[0]&&id<=longs[1]) {
                        total++;
                        break;
                    }
                }
            }
        }
        return total;
    }

    private static long method2(Scanner scanner) {
        long total = 0;
        ArrayList<Long[]> ranges = new ArrayList<>();

        // first just read in all the ranges ... no processing / checking at all
        while (scanner.hasNextLine()) {
            String range = scanner.nextLine();
            if (range.length()==0) {
                break; // done with the ranges so exit loop
            }
            String parts[] = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            ranges.add(new Long[] {start, end}); // add the first range no matter what
        }

        // now sort 
        ranges.sort((a,b) -> {return a[0].compareTo(b[0]); });

        // finally adjust to remove overlaps
        for (Long[] longs : ranges) {
           System.out.println(longs[0] + " " + longs[1]); 
        }

        // use iterator so we can safely delete in place any range that must be removed
        // note that we can also update the total as we adjust ranges ... theoretically we should NEVER have to adjust an end range b/c we are only adjusting starting ranges and potentially removing completely subsumed ranges
        Iterator<Long[]> it = ranges.iterator();
        Long currentEnd = null;
        ArrayList<Long[]> finalranges  = new ArrayList<>();
        while (it.hasNext()) {
            Long[] range = it.next();
            if (currentEnd == null) {
                // special case, first itearation
                // that range gets included no matter what
                // just set currentEnd and skip to next iteration
                currentEnd = range[1];
                finalranges.add(range);
                total += range[1] - range[0] + 1;
                continue;
            }

            // normal case, adjust or delete (by not adding to final ranges)
            if (range[0] <= currentEnd && range[1]>currentEnd) {
                range[0] = currentEnd+1;
                currentEnd = range[1];
            } else if (range[0] > currentEnd) {
                currentEnd = range[1];
            } else if (range[0] <= currentEnd && range[1]<=currentEnd) {
                continue;
            }
            total += range[1] - range[0] + 1;
            finalranges.add(new Long[]{range[0], range[1]});
        }
        return total;
    }
}
