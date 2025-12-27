package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import java.awt.Point;
import java.awt.Polygon;

public class Day9 {

    public static void main(String[] args) {
        File file = new File("day9.txt");
        try {
           Scanner scanner = new Scanner(file);
           //System.out.println(method1(scanner));
           System.out.println(method2(scanner));
           scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }

    private static boolean alreadythere(SortedMap<Double, Point[]> distances, Point point1, Point point2) {
        Iterator<Point[]> it = distances.values().iterator();
        while (it.hasNext()) {
            Point[] next = it.next();
            if (next[0]==point1 && next[1]==point2 || next[0]==point2 && next[1]==point1)
                return true;
        }
        return false;
    }
        
    // heuristics on hoping you don't get a weird edge case with a gazillion long distances in one direction but not the other
    @SuppressWarnings("unused")
	private static long method1(Scanner scanner) {
        ArrayList<Point> points = new ArrayList<>();

        // first read in the file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            points.add(new Point(Integer.parseInt(parts[0]),Integer.parseInt(parts[1])));
        }
        
        SortedMap<Double, Point[]> areas = new TreeMap<>();
        for (int i=0; i<points.size(); i++) {
            Point current = points.get(i);
            SortedMap<Double, Point[]> areascurr = new TreeMap<>();
            for (int j=0; j<points.size(); j++) {
                if (i==j)
                    continue; // never calc distance from point to itself
                Point other = points.get(j);
                if (!alreadythere(areascurr, current, other)) {
                    Point newpair[] = new Point[] {current,other};
                    Double d = Math.abs(1+current.getX()-other.getX())*Math.abs(1+current.getY()-other.getY());
                    areas.put(-d,newpair); // put in inverted distance so we don't have to mess with sort order which by default is ascending
                }
            }
            // only keep the top 100 areas
            int j = 0;
            Iterator<Double> it1 = areascurr.keySet().iterator();
            while (it1.hasNext()&&j<100) {
                Double key = it1.next();
                Point pair[] = areascurr.get(key);
                areas.put(key, pair);
                j++;
            }
        }
        return Math.abs(Math.round(areas.firstKey()));
    }

    private static long method2(Scanner scanner) {
        ArrayList<Point> redpoints = new ArrayList<>();
        Polygon red = new Polygon();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            Point pt = new Point(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
            redpoints.add(pt);
            red.addPoint((int)Math.round(pt.getX()),(int)Math.round(pt.getY()));
        }

        // ok now same algorithm as before but we need to check points of each "area" to make sure that ALL POINTS are contained inside the polygon
        SortedMap<Double, Point[]> areas = new TreeMap<>();
        for (int i=0; i<redpoints.size(); i++) {
            System.out.println(i);
            Point current = redpoints.get(i);
            SortedMap<Double, Point[]> areascurr = new TreeMap<>();
            for (int j=0; j<redpoints.size(); j++) {
                if (i==j)
                    continue; // never calc distance from point to itself
                Point other = redpoints.get(j);
                if (!alreadythere(areascurr, current, other)) {
                    Point newpair[] = new Point[] {current,other};
                    double diffx = 1+Math.abs(current.getX()-other.getX());
                    double diffy = 1+Math.abs(current.getY()-other.getY());
                    if (diffy < 20000 && diffx > 75000 || diffy < 20000 && diffx > 75000) {
                        Double d = diffx*diffy;
                        areascurr.put(-d,newpair); // put in inverted distance so we don't have to mess with sort order which by default is ascending
                    }
                }
            }
            // only keep the top 100 areas
            int j = 0;
            Iterator<Double> it1 = areascurr.keySet().iterator();
            while (it1.hasNext()&&j<1) {
                Double key = it1.next();
                Point pair[] = areascurr.get(key);
                if (redgreenOnly(red,redpoints,pair[0],pair[1])) {
                    areas.put(key, pair);
                    j++;
                }
            }
        }
         return Math.abs(Math.round(areas.firstKey())); 
    }

    private static boolean realcontains(Polygon red, ArrayList<Point> redpoints, double dx, double dy) {
        if (red.contains(dx, dy))
            return true;

        long x = Math.round(dx);
        long y = Math.round(dy);

        Iterator<Point> it = redpoints.iterator();
        Point prev = null;
        Point first = null;
        while (it.hasNext()) {
            Point curr = it.next();
            if (prev != null) {
                long prevx = Math.round(prev.getX());
                long prevy = Math.round(prev.getY());
                long currx = Math.round(curr.getX());
                long curry = Math.round(curr.getY());
                if (prevx==currx && prevx==x) {
                    long starty = Math.min(prevy,curry);
                    long endy = Math.max(prevy,curry);;
                    if (y>=starty && y<=endy)
                        return true;
                } else if (prevy==y) {
                    long startx = Math.min(prevx,currx);
                    long endx = Math.max(prevx,currx);;
                    if (x>=startx && x<=endx)
                        return true;
                }
            } else {
                first = curr;
            }
            prev = curr;
        }

        // wrap around
        long prevx = Math.round(prev.getX());
        long prevy = Math.round(prev.getY());
        long currx = Math.round(first.getX());
        long curry = Math.round(first.getY());
        if (prevx==currx && prevx==x) {
            long starty = Math.min(prevy,curry);
            long endy = Math.max(prevy,curry);;
            if (y>=starty && y<=endy)
                return true;
        } else if (prevy==y){
            long startx = Math.min(prevx,currx);
            long endx = Math.max(prevx,currx);;
            if (x>=startx && x<=endx)
                return true;
        }
        return false;
    }

    private static boolean redgreenOnly(Polygon red, ArrayList<Point> redpoints, Point prev, Point other) {
        long prevx = Math.round(prev.getX());
        long prevy = Math.round(prev.getY());
        long currx = Math.round(other.getX());
        long curry = Math.round(other.getY());
        // just check entire border four combinations of endpoints are inside red
        long startx = Math.min(prevx,currx);
        long endx = Math.max(prevx,currx);;
        for (long x=startx; x<=endx; x++) {
            if (!realcontains(red,redpoints,x,prevy)||!realcontains(red,redpoints,x,curry))
                return false;
        }
        long starty = Math.min(prevy,curry);
        long endy = Math.max(prevy,curry);;
        for (long y=starty; y<=endy; y++) {
            if (!realcontains(red,redpoints,prevx,y)||!realcontains(red,redpoints,currx,y))
                return false;
        }

        return true;
    }
 
}
