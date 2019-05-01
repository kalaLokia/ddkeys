package drpg.ddkeys.data;

public class TravelTime {
    private static long nw;
    private static long n;
    private static long ne;
    private static long w;
    private static long e;
    private static long sw;
    private static long s;
    private static long se;

    public TravelTime(long nw, long n, long ne, long w, long e, long sw, long s, long se) {
        TravelTime.nw = nw;
        TravelTime.n = n;
        TravelTime.ne = ne;
        TravelTime.w = w;
        TravelTime.e = e;
        TravelTime.sw = sw;
        TravelTime.s = s;
        TravelTime.se = se;
    }

    public TravelTime() {

    }

    public static long getNw() {
        return nw;
    }

    public static long getN() {
        return n;
    }

    public static long getNe() {
        return ne;
    }

    public static long getW() {
        return w;
    }

    public static long getE() {
        return e;
    }

    public static long getSw() {
        return sw;
    }

    public static long getS() {
        return s;
    }

    public static long getSe() {
        return se;
    }
}
