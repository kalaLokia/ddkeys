package drpg.ddkeys.data;

public class NPCs {

    private static String npc1;
    private static String npc2;
    private static String npc3;
    private static String npc4;

    public NPCs(String npc1, String npc2, String npc3, String npc4) {
        NPCs.npc1 = npc1;
        NPCs.npc2 = npc2;
        NPCs.npc3 = npc3;
        NPCs.npc4 = npc4;
    }

    public NPCs() {

    }

    public static String getNpc1() {
        return npc1;
    }

    public static String getNpc2() {
        return npc2;
    }

    public static String getNpc3() {
        return npc3;
    }

    public static String getNpc4() {
        return npc4;
    }


}
