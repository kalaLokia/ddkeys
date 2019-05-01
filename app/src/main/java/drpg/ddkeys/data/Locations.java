package drpg.ddkeys.data;

import drpg.ddkeys.R;

import static drpg.ddkeys.master.master_init.KEYBOARD_CONTEXT;
import static drpg.ddkeys.master.master_init.keyboard;
import static drpg.ddkeys.master.master_init.kingdom;
import static drpg.ddkeys.master.master_init.place_descr;
import static drpg.ddkeys.master.master_init.travelkeyboard;

public final class Locations {
    public static final String[] locations = {
            "\u2620",                       //0
            "Silverkeep",                   //1
            "Copperfall",                   //2
            "Mournstead",                   //3
            "Rivermouth",                   //4
            "Copperfall Summit",           //5
            "Copperfall Swamp",            //6
            "Temple of Moukn",             //7
            "Dsian's House",                //8
            "Silverkeep Altar",            //9
            "Redhorn",                      //10
            "Miree",                        //11
            "Copperfall Altar",            //12
            "Redhorn Dungeon",             //13

            "Frostridge Shore",            //14
            "Frostridge",                   //15
            "Frostridge Tavern",           //16
            "Mirror Isle",                  //17
            "Black Mountain",              //18

            "Sariila",                      //19
            "Dingerou Cavern",             //20
            "Bazzar of Lights",            //21
            "Scorched Desert",             //22
            "Sandy Tomp",                   //23
            "Tower of Eternity",           //24
            "Sariila Altar",               //25

            "Buckleport",                   //26
            "Ashgate",                      //27
            "Lemontree Plaza",             //28
            "Maple Grove",                  //29
            "Bibliothecam Magnam",         //30
            "Dawnberg",                     //31
            "Sanctum of Serenity",         //32
            "The Sick Dahlia",             //33
            "Nightmare's Heaven",          //34

            "Island of Desolation",        //35
            "Ravengate",                    //36
            "Ravengate Altar",             //37
            "Adventure's Resturant",       //38
            "Infernal Caverns",            //39
            "Thornwood",                    //40
            "Thornwood Graveyard",         //41
            "Primeval Lagoon",             //42
            //Newly added places
            "Lake of 100 Winters"          //43
    };
    public static final int[] chop_restr = {5, 9, 12, 14, 18, 20, 22, 23, 24, 25, 26, 27, 28, 30, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 42};
    public static final int[] forage_restr = {5, 9, 12, 14, 18, 20, 22, 23, 24, 25, 26, 27, 29, 30, 31, 32, 33, 35, 36, 37, 38, 39, 40, 41, 42};
    public static final int[] mine_restr = {5, 14, 18, 22, 23, 24, 26, 27, 28, 29, 30, 32, 33, 35, 36, 38, 39, 40, 41, 42};
    public static final int[] fish_restr = {5, 9, 12, 14, 18, 20, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41};

    public static void myPlace(int myplace) {
        switch (myplace) {
            case 0:
                new Connections(0, 0, 0, 0, 0, 0, 0, 0, 0);
                break;
            case 1:
                new Connections(1, 0, 2, 0, 8, 9, 0, 3, 0);
                new TravelTime(0, 40000, 0, 10000, 5000, 0, 25000, 0);
                new NPCs("Axhor", "Fallen", null, null);
                kingdom = "ILLERIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place1);

                break;
            case 2:
                new Connections(2, 0, 7, 12, 6, 5, 0, 1, 0);
                new TravelTime(0, 60000, 20000, 30000, 100000, 0, 40000, 0);
                new NPCs(null, null, null, null);
                kingdom = "ILLERIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place2);
                break;
            case 3:
                new Connections(3, 0, 1, 0, 0, 0, 4, 0, 0);
                new TravelTime(0, 25000, 0, 0, 0, 30000, 0, 0);
                new NPCs(null, null, null, null);
                kingdom = "ILLERIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place3);
                break;
            case 4:
                new Connections(4, 14, 10, 3, 0, 0, 35, 19, 0);
                new TravelTime(300000, 30000, 30000, 0, 0, 180000, 250000, 0);
                new NPCs("Dave", null, null, null);
                kingdom = "ILLERIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place4);
                break;
            case 5:
                new Connections(5, 0, 11, 0, 2, 0, 0, 0, 0);
                new TravelTime(0, 23000, 0, 100000, 0, 0, 0, 0);
                new NPCs("Oracle", "Monk", null, null);
                kingdom = "ILLERIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place5);
                break;
            case 6:
                new Connections(6, 0, 0, 0, 0, 2, 0, 0, 0);
                new TravelTime(0, 0, 0, 0, 30000, 0, 0, 0);
                new NPCs("Priest", null, null, null);
                kingdom = "ILLERIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place6);
                break;
            case 7:
                new Connections(7, 0, 0, 0, 0, 0, 0, 2, 0);
                new TravelTime(0, 0, 0, 0, 0, 0, 60000, 0);
                new NPCs("Pope Seth", "Scarlett", null, null);
                kingdom = "ILLERIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place7);
                break;
            case 8:
                new Connections(8, 0, 0, 0, 0, 1, 0, 0, 0);
                new TravelTime(0, 0, 0, 0, 10000, 0, 0, 0);
                new NPCs("Dsian", null, null, null);
                kingdom = "ILLERIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place8);
                break;
            case 9:
                new Connections(9, 0, 0, 0, 1, 0, 0, 0, 0);
                new TravelTime(0, 0, 0, 5000, 0, 0, 0, 0);
                new NPCs("Rune Guru", null, null, null);
                kingdom = "ILLERIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place9);
                break;
            case 10:
                new Connections(10, 0, 13, 0, 0, 0, 0, 4, 0);
                new TravelTime(0, 15000, 0, 0, 0, 0, 30000, 0);
                new NPCs(null, null, null, null);
                kingdom = "ILLERIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place10);
                break;
            case 11:
                new Connections(11, 0, 0, 0, 0, 0, 0, 5, 0);
                new TravelTime(0, 0, 0, 0, 0, 0, 23000, 0);
                new NPCs("Elliot", null, null, null);
                kingdom = "ILLERIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place11);
                break;
            case 12:
                new Connections(12, 0, 0, 0, 0, 0, 2, 0, 0);
                new TravelTime(0, 0, 0, 0, 0, 20000, 0, 0);
                new NPCs(null, null, null, null);
                kingdom = "ILLERIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place12);
                break;
            case 13:
                new Connections(13, 0, 0, 0, 0, 0, 0, 10, 0);
                new TravelTime(0, 0, 0, 0, 0, 0, 15000, 0);
                new NPCs(null, null, null, null);
                kingdom = "ILLERIA";
                place_descr = "";
                break;
            case 14:
                new Connections(14, 0, 15, 0, 17, 0, 0, 0, 4);
                new TravelTime(0, 32000, 0, 20000, 0, 0, 0, 30000);
                new NPCs("John", null, null, null);
                kingdom = "FROSTRIDGE";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place20);
                break;
            case 15:
                new Connections(15, 0, 16, 0, 0, 18, 0, 14, 0);
                new TravelTime(0, 5000, 0, 0, 30000, 0, 32000, 0);
                new NPCs("Nieves", null, null, null);
                kingdom = "FROSTRIDGE";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place21);
                break;
            case 16:
                new Connections(16, 0, 0, 0, 0, 0, 0, 15, 0);
                new TravelTime(0, 0, 0, 0, 0, 0, 5000, 0);
                new NPCs(null, null, null, null);
                kingdom = "FROSTRIDGE";
                place_descr = "";
                break;
            case 17:
                new Connections(17, 0, 43, 0, 0, 14, 0, 0, 0);
                new TravelTime(0, 30000, 0, 0, 20000, 0, 0, 0);
                new NPCs(null, null, null, null);
                kingdom = "FROSTRIDGE";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place23);
                break;
            case 18:
                new Connections(18, 0, 0, 0, 15, 0, 0, 0, 0);
                new TravelTime(0, 0, 0, 30000, 0, 0, 0, 0);
                new NPCs("Illyena", null, null, null);
                kingdom = "FROSTRIDGE";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place24);
                break;
            case 19:
                new Connections(19, 0, 4, 20, 0, 21, 0, 22, 25);
                new TravelTime(0, 250000, 15000, 0, 20000, 0, 15000, 10000);
                new NPCs(null, null, null, null);
                kingdom = "SARIILA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place30);
                break;
            case 20:
                new Connections(20, 0, 0, 0, 0, 0, 19, 0, 0);
                new TravelTime(0, 0, 0, 0, 0, 15000, 0, 0);
                new NPCs(null, null, null, null);
                kingdom = "SARIILA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place31);
                break;
            case 21:
                new Connections(21, 0, 0, 0, 19, 0, 0, 0, 0);
                new TravelTime(0, 0, 0, 20000, 0, 0, 0, 0);
                new NPCs("Bakoushi", null, null, null);
                kingdom = "SARIILA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place32);
                break;
            case 22:
                new Connections(22, 0, 19, 0, 0, 0, 0, 23, 24);
                new TravelTime(0, 15000, 0, 0, 0, 0, 15000, 15000);
                new NPCs(null, null, null, null);
                kingdom = "SARIILA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place33);
                break;
            case 23:
                new Connections(23, 0, 22, 0, 0, 0, 0, 0, 0);
                new TravelTime(0, 15000, 0, 0, 0, 0, 0, 0);
                new NPCs(null, null, null, null);
                kingdom = "SARIILA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place34);
                break;
            case 24:
                new Connections(24, 22, 0, 0, 0, 0, 0, 26, 0);
                new TravelTime(15000, 0, 0, 0, 0, 0, 120000, 0);
                new NPCs(null, null, null, null);
                kingdom = "SARIILA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place35);
                break;
            case 25:
                new Connections(25, 19, 0, 0, 0, 0, 0, 0, 0);
                new TravelTime(10000, 0, 0, 0, 0, 0, 0, 0);
                new NPCs(null, null, null, null);
                kingdom = "SARIILA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place36);
                break;
            case 26:
                new Connections(26, 0, 24, 27, 0, 0, 0, 0, 0);
                new TravelTime(0, 120000, 10000, 0, 0, 0, 0, 0);
                new NPCs("Catrina", "Nathalie", null, null);
                kingdom = "PRIMEVAL";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place40);
                break;
            case 27:
                new Connections(27, 0, 0, 28, 0, 0, 26, 0, 0);
                new TravelTime(0, 0, 10000, 0, 0, 10000, 0, 0);
                new NPCs("Nadia", "Isabelle", null, null);
                kingdom = "PRIMEVAL";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place41);
                break;
            case 28:
                new Connections(28, 29, 32, 0, 30, 33, 27, 34, 31);
                new TravelTime(10000, 10000, 0, 10000, 10000, 0, 10000, 10000);
                new NPCs("Alex", "Astrid", "Felix", null);
                kingdom = "PRIMEVAL";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place42);
                break;
            case 29:
                new Connections(29, 0, 0, 0, 0, 0, 0, 0, 28);
                new TravelTime(0, 0, 0, 0, 0, 0, 0, 10000);
                new NPCs("Madelenine", "Julian", null, null);
                kingdom = "PRIMEVAL";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place43);
                break;
            case 30:
                new Connections(30, 0, 0, 0, 0, 28, 0, 0, 0);
                new TravelTime(0, 0, 0, 0, 10000, 0, 0, 0);
                new NPCs("Maria", "James", "Clark", null);
                kingdom = "PRIMEVAL";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place44);
                break;
            case 31:
                new Connections(31, 28, 0, 0, 0, 0, 0, 0, 0);
                new TravelTime(10000, 0, 0, 0, 0, 0, 0, 0);
                new NPCs(null, null, null, null);
                kingdom = "PRIMEVAL";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place45);
                break;
            case 32:
                new Connections(32, 0, 0, 0, 0, 0, 0, 28, 0);
                new TravelTime(0, 0, 0, 0, 0, 0, 10000, 0);
                new NPCs("Peter", "Hubert", null, null);
                kingdom = "PRIMEVAL";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place46);
                break;
            case 33:
                new Connections(33, 0, 0, 0, 28, 0, 0, 0, 0);
                new TravelTime(0, 0, 0, 10000, 0, 0, 0, 0);
                new NPCs("Elizabath", "John", "Gregory", "Mathilda");
                kingdom = "PRIMEVAL";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place47);
                break;
            case 34:
                new Connections(34, 0, 28, 0, 0, 0, 0, 0, 0);
                new TravelTime(0, 10000, 0, 0, 0, 0, 0, 0);
                new NPCs("Nightmare", "Jesie", null, null);
                kingdom = "PRIMEVAL";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place48);
                break;
            case 35:
                new Connections(35, 36, 0, 4, 0, 0, 0, 0, 0);
                new TravelTime(40000, 0, 180000, 0, 0, 0, 0, 0);
                new NPCs("Kadri", null, null, null);
                kingdom = "LIRIANIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place50);
                break;
            case 36:
                new Connections(36, 0, 39, 37, 0, 0, 40, 38, 35);
                new TravelTime(0, 40000, 10000, 0, 0, 0, 20000, 40000);
                new NPCs(null, null, null, null);
                kingdom = "LIRIANIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place51);
                break;
            case 37:
                new Connections(37, 0, 0, 0, 0, 0, 36, 0, 0);
                new TravelTime(0, 0, 0, 0, 0, 10000, 0, 0);
                new NPCs(null, null, null, null);
                kingdom = "LIRIANIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place52);
                break;
            case 38:
                new Connections(38, 0, 36, 0, 0, 0, 0, 0, 0);
                new TravelTime(0, 20000, 0, 0, 0, 0, 0, 0);
                new NPCs("Charles", "Wendy", null, null);
                kingdom = "LIRIANIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place53);
                break;
            case 39:
                new Connections(39, 0, 0, 0, 0, 0, 0, 36, 0);
                new TravelTime(0, 0, 0, 0, 0, 0, 40000, 0);
                new NPCs(null, null, null, null);
                kingdom = "LIRIANIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place54);
                break;
            case 40:
                new Connections(40, 0, 0, 36, 0, 0, 41, 42, 0);
                new TravelTime(0, 0, 20000, 0, 0, 10000, 15000, 0);
                new NPCs(null, null, null, null);
                kingdom = "LIRIANIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place55);
                break;
            case 41:
                new Connections(41, 0, 0, 40, 0, 0, 0, 0, 0);
                new TravelTime(0, 0, 10000, 0, 0, 0, 0, 0);
                new NPCs(null, null, null, null);
                kingdom = "LIRIANIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place56);
                break;
            case 42:
                new Connections(42, 0, 40, 0, 0, 0, 0, 0, 0);
                new TravelTime(0, 15000, 0, 0, 0, 0, 0, 0);
                new NPCs(null, null, null, null);
                kingdom = "LIRIANIA";
                place_descr = KEYBOARD_CONTEXT.getResources().getString(R.string.place57);
                break;
            case 43:
                new Connections(43, 0, 0, 0, 0, 0, 0, 17, 0);
                new TravelTime(0, 0, 0, 0, 0, 0, 30000, 0);
                new NPCs(null, null, null, null);
                kingdom = "FROSTRIDGE";
                place_descr = "";
                break;
        }
        keyboard.getKeys().get(8).label = locations[Connections.getCurrent()];
        travelkeyboard.getKeys().get(12).label = NPCs.getNpc1();
        travelkeyboard.getKeys().get(13).label = NPCs.getNpc2();
        travelkeyboard.getKeys().get(14).label = NPCs.getNpc3();
        travelkeyboard.getKeys().get(15).label = NPCs.getNpc4();
    }

}
