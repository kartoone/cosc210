package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class Day8 {
    static class Point3D {
        private int x;
        private int y;
        private int z;
        private Point3D other1; // a single point can be directly connected to at most two other points
        private Point3D other2;

        Point3D(int x, int y, int z) {
            this.x=x;
            this.y=y;
            this.z=z;
        }
        double distance(Point3D other) {
            long diffx = x-other.x;
            long diffy = y-other.y;
            long diffz = z-other.z;
            Double d = Math.sqrt(diffx*diffx + diffy*diffy + diffz*diffz);
            return d;
        }
        void connect(Point3D other) {
            if (other1 == null) {
                this.other1 = other;
            } else if (other2 == null) {
                this.other2 = other;
            } else {
                System.err.println(this + "already connected to two other points: " + this.other1 + " and " + this.other2);
                System.exit(-1);
            }
        }
        public boolean isConnected(Point3D other) {
            return other1==other || other2==other;
        }
        public boolean fullyConnected() {
            return other1!=null && other2!=null;
        }
        @Override
        public String toString() {
            return this.x + "," + this.y + "," + this.z;
        }
    }

    public static void main(String[] args) {
        File file = new File("day8.txt");
        try {
           Scanner scanner = new Scanner(file);
           //System.out.println(method1(scanner));
           System.out.println(method2(scanner));
           scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }
        
    // helper just to keep us from measuring the distance from point2 back to point1 when we've already measured point1 to point2 and vice versa
    private static boolean alreadythere(SortedMap<Double, Point3D[]> distances, Point3D point1, Point3D point2) {
        Iterator<Point3D[]> it = distances.values().iterator();
        while (it.hasNext()) {
            Point3D[] next = it.next();
            if (next[0]==point1 && next[1]==point2 || next[0]==point2 && next[1]==point1)
                return true;
        }
        return false;
    }

    // works great, but infeasible for 
    @SuppressWarnings("unused")
	private static long method1(Scanner scanner) {
        long total = 1;

        // first read in all the coords
        ArrayList<Point3D> coords = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] nums = line.split(",");
            coords.add(new Point3D(Integer.parseInt(nums[0]),Integer.parseInt(nums[1]),Integer.parseInt(nums[2])));
        }

        // now find all the distances between all the coords
        SortedMap<Double, Point3D[]> distances = new TreeMap<>();

        for (int i=0; i<coords.size(); i++) {
            Point3D current = coords.get(i);
            for (int j=0; j<coords.size(); j++) {
                if (i==j)
                    continue; // never calc distance from point to itself
                Point3D other = coords.get(j);
                if (!alreadythere(distances, current, other)) {
                    Point3D newpair[] = new Point3D[] {current,other};
                    Double d = current.distance(other);
                    distances.put(d,newpair);
                    if (distances.size()>1000) {
                        distances.remove(distances.lastKey());
                    }
                }
            }
        }

        ArrayList<ArrayList<Point3D>> circuits = new ArrayList<>();
        Iterator<Double> it = distances.keySet().iterator();
        int count = 0;
        while(it.hasNext() && count < 1000) {
            Double dist = it.next();
            Point3D[] pair = distances.get(dist);
            if (circuits.isEmpty()) {
                ArrayList<Point3D> circuit = new ArrayList<>();
                circuit.add(pair[0]);
                circuit.add(pair[1]);
                circuits.add(circuit);
            } else {
                // see if this is something we can work with by searching for which circuits each side of the pair is in
                Iterator<ArrayList<Point3D>> it2 = circuits.iterator();
                ArrayList<Point3D> circuit1 = null;
                ArrayList<Point3D> circuit2 = null;
                while (it2.hasNext()) {
                    ArrayList<Point3D> circuit = it2.next();
                    if (circuit.contains(pair[0]))
                        circuit1 = circuit;
                    if (circuit.contains(pair[1]))
                        circuit2 = circuit;
                }
                if (circuit1 == null && circuit2 == null) {
                    // create a new circuit
                    ArrayList<Point3D> circuit = new ArrayList<>();
                    circuit.add(pair[0]);
                    circuit.add(pair[1]);
                    circuits.add(circuit);
                } else if (circuit1 == circuit2) {
                    // already in same circuit ... nothing to do
                    continue;
                } else if (circuit1 != null && circuit2 != null) {
                    circuit1.addAll(circuit2);
                    circuits.remove(circuit2);
                } else if (circuit1 != null) {
                    circuit1.add(pair[1]);
                } else if (circuit2 != null) {
                    circuit2.add(pair[0]);
                }
            }
            count++;
        }
        
        circuits.sort((a,b)->{return b.size()-a.size();});

        for (int ci=0; ci<circuits.size() && ci<3; ci++) {
            total *= circuits.get(ci).size();
        }
        
        return total;
    }

    // we need to different strategy to continue all the way out to full connection
    // otherwise need to calculate 1,000,000 distances and inserting them into sorted treemap 
    // takes way too long ... as it turns out, if we only keep the top 100 (instead of 1000) shortest distances from each node to other nodes, we are able to correctly build the fully connected circuit!!!
    private static long method2(Scanner scanner) {
        // first read in all the coords
        ArrayList<Point3D> coords = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] nums = line.split(",");
            coords.add(new Point3D(Integer.parseInt(nums[0]),Integer.parseInt(nums[1]),Integer.parseInt(nums[2])));
        }

        // now find all the distances between all the coords
        // but if a new distance is significantly higher than any of the first 1000, don't bother adding it
        SortedMap<Double, Point3D[]> distances = new TreeMap<>();

        for (int i=0; i<coords.size(); i++) {
            Point3D current = coords.get(i);
            SortedMap<Double, Point3D[]> distancescurr = new TreeMap<>();
            for (int j=0; j<coords.size(); j++) {
                if (i==j)
                    continue; // never calc distance from point to itself
                Point3D other = coords.get(j);
                if (!alreadythere(distancescurr, current, other)) {
                    Point3D newpair[] = new Point3D[] {current,other};
                    Double d = current.distance(other);
                    distancescurr.put(d,newpair);
                }
            }
            // only keep the top 100 distances
            int j = 0;
            Iterator<Double> it1 = distancescurr.keySet().iterator();
            while (it1.hasNext() && j<100) {
                Double key = it1.next();
                Point3D pair[] = distancescurr.get(key);
                distances.put(key, pair);
                j++;
            }
//            System.out.println(distances.size());
        }

        ArrayList<Point3D> added = new ArrayList<>();
        ArrayList<ArrayList<Point3D>> circuits = new ArrayList<>();
        Iterator<Double> it = distances.keySet().iterator();
        int count = 0;
        while(it.hasNext() && count < 25000) {
            Double dist = it.next();
            Point3D[] pair = distances.get(dist);
            if (circuits.isEmpty()) {
                ArrayList<Point3D> circuit = new ArrayList<>();
                circuit.add(pair[0]);
                circuit.add(pair[1]);
                circuits.add(circuit);
                added.add(pair[0]);
                added.add(pair[1]);
            } else {
                // see if this is something we can work with by searching for which circuits each side of the pair is in
                Iterator<ArrayList<Point3D>> it2 = circuits.iterator();
                ArrayList<Point3D> circuit1 = null;
                ArrayList<Point3D> circuit2 = null;
                while (it2.hasNext()) {
                    ArrayList<Point3D> circuit = it2.next();
                    if (circuit.contains(pair[0]))
                        circuit1 = circuit;
                    if (circuit.contains(pair[1]))
                        circuit2 = circuit;
                }
                if (circuit1 == null && circuit2 == null) {
                    // create a new circuit
                    ArrayList<Point3D> circuit = new ArrayList<>();
                    circuit.add(pair[0]);
                    circuit.add(pair[1]);
                    circuits.add(circuit);
                    added.add(pair[0]);
                    added.add(pair[1]);
                } else if (circuit1 == circuit2) {
                    // already in same circuit ... nothing to do
                    continue;
                } else if (circuit1 != null && circuit2 != null) {
                    circuit1.addAll(circuit2);
                    circuits.remove(circuit2);
                } else if (circuit1 != null) {
                    circuit1.add(pair[1]);
                    added.add(pair[1]);
                } else if (circuit2 != null) {
                    circuit2.add(pair[0]);
                    added.add(pair[0]);
                }
            }
            count++;
            if (added.size()==1000 && circuits.size()==1) {
                return pair[0].x*pair[1].x;
            } 
        }
        
        return -1;
    }


}
