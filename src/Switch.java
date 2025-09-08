public class Switch {
    public static void main(String[] args) {
        int digit = 1;
        String digitName = "";
        switch (digit)
        {
        case 1: 
        case 2: digitName = "one or two"; break;
        case 3: 
        case 4: digitName = "three or four"; break;
        case 5: 
        case 6: digitName = "five or six"; break;
        case 7: digitName = "seven"; break;
        case 8: digitName = "eight"; break;
        case 9: digitName = "nine"; break;
        default: digitName = ""; break;
        }
    }
}
