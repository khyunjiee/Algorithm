package june;

import java.util.HashSet;
import java.util.Set;

public class Ssafy {
    public static void main(String[] args) {
        Set s = new HashSet<>();
        s.add("s");
        s.add("g");
        s.add("gw");
        s.add("d");
        s.add("s");

        System.out.println(s.size());
    }
}

interface Run {
    void run();
}

class My implements Run {
    public void run() {}
}

