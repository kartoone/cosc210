package chapter8;

public class TriangleEquilateral extends Triangle {

    public TriangleEquilateral(int sidelength, String color) {
        super(sidelength, sidelength, color);
    }

    @Override
    protected void display(String c) {
        String base = (c+" ").repeat(width);
        for (int i=0, lspaces=width-1, mspaces=-1; i<height-1; i++, lspaces--, mspaces+=2) {
            String left = " ".repeat(lspaces);
            String middle = c;
            if (i > 0) {
                middle += " ".repeat(mspaces) + c;
            }
            System.out.println(left + middle);
        }
        System.out.println(base);    
    }

}
