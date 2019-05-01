package drpg.ddkeys.data;

public class Connections {

    private static int current;
    private static int nw;
    private static int n;
    private static int ne;
    private static int w;
    private static int e;
    private static int sw;
    private static int s;
    private static int se;

    public Connections(int current, int nw, int n, int ne, int w, int e, int sw, int s, int se) {
        Connections.current = current;
        Connections.nw = nw;
        Connections.n = n;
        Connections.ne = ne;
        Connections.w = w;
        Connections.e = e;
        Connections.sw = sw;
        Connections.s = s;
        Connections.se = se;
    }

    public Connections() {

    }

    public static int getCurrent() {
        return current;
    }

    public static int getNw() {
        return nw;
    }

    public static int getN() {
        return n;
    }

    public static int getNe() {
        return ne;
    }

    public static int getW() {
        return w;
    }

    public static int getE() {
        return e;
    }

    public static int getSw() {
        return sw;
    }

    public static int getS() {
        return s;
    }

    public static int getSe() {
        return se;
    }


}
