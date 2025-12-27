package advent;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class DayB {

    static class Node {
        ArrayList<Node> parents = new ArrayList<>();
        ArrayList<Node> connections = new ArrayList<>();
        String name = "";

        // partially create this node with just the naem
        // secondparam is just to allow us to have two different constructors that take in same param type (String)
        public Node(Node parent, String name) {
            this.name = name;
            this.parents.add(parent);
        }

        public void addConnection(Node n) {
            // could check for loop here ... but for now assume there is none
            connections.add(n);
        }

        public void addParent(Node n) {
            if (this.parents.get(0) == null)
                this.parents.set(0, n);
            else
                this.parents.add(n);
        }

        @Override
        public boolean equals(Object o) {
            if (o==null)
                return false;
            Node other = (Node) o;
            return other.name.equals(this.name);
        }

        @Override
        public String toString() {
            return this.name;
        }

    }

    public static void main(String[] args) {
        File file = new File("dayb.txt");
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
        ArrayList<Node> allnodes = new ArrayList<>();
        Node fakeout = new Node(null, "out"); // just used for name comparison
        Node fakeyou = new Node(null, "you"); // just used for name comparison

        // first read in the file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int coloni = line.indexOf(":");
            Node p = new Node(null, line.substring(0, coloni));
            if (allnodes.contains(p)) {
                int i = allnodes.indexOf(p);
                p = allnodes.get(i); // use the actual node instead of the new node with different name
            } else {
                allnodes.add(p);
            }
            String parts[] = line.substring(coloni+2).split(" ");
            for (String string : parts) {
                Node c = new Node(p, string);
                if (allnodes.contains(c)) {
                    int i = allnodes.indexOf(c);
                    c = allnodes.get(i); // use the actual node instead of the new node with different name
                    c.addParent(p);
                } else {
                    allnodes.add(c);
                }
                p.connections.add(c);
            }
        }

        // first root at "you"
        Node nodey = allnodes.get(allnodes.indexOf(fakeyou));
        int n = traverse(nodey, fakeout); 
        return n;

    }

    static Node out = new Node(null,"out"); // just used for name comparison
    static Node svr = new Node(null,"svr"); // just used for name comparison
    static Node fft = new Node(null,"fft"); // just used for name comparison
    static Node dac = new Node(null,"dac"); // just used for name comparison

    // return how many unique paths go from n to "out"
    private static int traverse(Node n, Node out) {
        // System.out.println("traversing " + n.name);
        if (n.connections.size()==1 && n.connections.get(0).equals(out)) {
            return 1;
        }

        int result = 0;
        Iterator<Node> it = n.connections.iterator();
        while (it.hasNext()) {
            Node next = it.next();
            result += traverse(next, out);
        }

        return result;
    }


    // depth first takes too long for part 2
    // we need to make sure to use dynamic programming to keep from having to repeat the recursion
    static Map<String, Integer> memo = new HashMap<>();
    private static int traverse2(Node n, Node out) {
        String key = ""+n+"-"+out;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (n.equals(out)) {
            return 1;
        }

        int result = 0;
        Iterator<Node> it = n.connections.iterator();
        while (it.hasNext()) {
            Node next = it.next();
            result += traverse2(next, out);
        }
        memo.put(key, result);
        return result;
    }

    private static long method2(Scanner scanner) {
        ArrayList<Node> allnodes = new ArrayList<>();
        Node fakeout = new Node(null, "out"); // just used for name comparison
        Node fakesvr = new Node(null, "svr"); // just used for name comparison
        Node fakefft = new Node(null, "fft"); // just used for name comparison
        Node fakedac = new Node(null, "dac"); // just used for name comparison

        // first read in the file
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int coloni = line.indexOf(":");
            Node p = new Node(null, line.substring(0, coloni));
            if (allnodes.contains(p)) {
                int i = allnodes.indexOf(p);
                p = allnodes.get(i); // use the actual node instead of the new node with different name
            } else {
                allnodes.add(p);
            }
            if (p.equals(fakesvr))
                svr = p;
            else if (p.equals(fakefft))
                fft = p;
            else if (p.equals(fakedac))
                dac = p;
            String parts[] = line.substring(coloni+2).split(" ");
            for (String string : parts) {
                Node c = new Node(p, string);
                if (allnodes.contains(c)) {
                    int i = allnodes.indexOf(c);
                    c = allnodes.get(i); // use the actual node instead of the new node with different name
                } else {
                    allnodes.add(c);
                }
                if (!c.parents.contains(p))
                    c.addParent(p);
                p.connections.add(c);
                if (c.equals(fakeout))
                    out = c;
            }
        }

        // first find all the paths from svr to fft
        long tofft = traverse2(svr, fft);  // just populates fftpaths
        long fftdac = traverse2(fft, dac);
        long dacout = traverse2(dac, out);

        System.out.println(tofft);
        System.out.println(fftdac);
        System.out.println(dacout);

        return tofft*fftdac*dacout;

    }

}
