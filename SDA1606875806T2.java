/**
 * Tugas Pemrograman 2 Struktur Data & Algoritma
 * @author Izzan Fakhril Islam (1606875806)
 * SDA - C
 */

import java.util.*;
import java.io.*;

public class SDA1606875806T2 {
    static Gudako GUDAKO_CHARACTER;
    static Coordinate[][] GUDAKO_WORLD;
    static Coordinate START_COORDINATE;
    static int MAP_ROW;
    static int MAP_COL;
    static Map<String, ArrayList<Hero>> HERO_WITH_COORDINATES;
    static Map<String, Dungeon> DUNGEON_WITH_COORDINATES;
    static int HERO_QUEUE = 0;
    static ArrayList<Hero> enrolledHero = new ArrayList<Hero>();

    public static void main(String[] inputs) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //Hero With Coordinates: Map untuk menampung lokasi pemanggilan Hero berdasarkan nilai koordinat
        HERO_WITH_COORDINATES = new HashMap<String, ArrayList<Hero>>();
        ArrayList<Hero> rawHeroList = new ArrayList<Hero>();

        //Dungeon with Coordinates: Map untuk menampung lokasi Dungeon berdasarkan nilai koordinat
        DUNGEON_WITH_COORDINATES = new HashMap<String, Dungeon>();

        String[] properties = br.readLine().split(" ");

        //Menginisiasi Jumlah Pahlawan, Jumlah Summon Point, Jumlah Dungeon, Mana awal Gudako, Panjang Baris
        //dan Panjang Kolom dalam peta Dunia Gudako
        int heroCount = Integer.parseInt(properties[0]);
        int summonCount = Integer.parseInt(properties[1]);
        int dungeonCount = Integer.parseInt(properties[2]);
        int gudakoMana = Integer.parseInt(properties[3]);
        MAP_ROW = Integer.parseInt(properties[4]);
        MAP_COL = Integer.parseInt(properties[5]);

        GUDAKO_CHARACTER = new Gudako(gudakoMana);

        //Inisiasi Array untuk dunia Gudako berdasarkan input baris dan kolom dari user
        GUDAKO_WORLD = new Coordinate[MAP_ROW+1][MAP_COL+1];


        //Membaca input dari user dan membentuk objek Hero sesuai input user
        String cmd;
        for (int i = 0; i < heroCount; i++){
            cmd = br.readLine();
            String[] heroProperties = cmd.split(";");
            Hero newHero = new Hero(heroProperties[0], Integer.parseInt(heroProperties[1]),
                    Integer.parseInt(heroProperties[2]), heroProperties[3]);
            rawHeroList.add(newHero);
        }

        //Membaca input dari user dan membentuk objek Koordinat dari tempat pemanggilan Hero (Summon Point)
        for (int i = 0; i < summonCount; i++){
            cmd = br.readLine();
            String[] summonPointProperties = cmd.split(";");
            int r = Integer.parseInt(summonPointProperties[0]);
            int c = Integer.parseInt(summonPointProperties[1]);
            Coordinate summonCoordinate = new Coordinate(r, c, 'S');
            //Menempatkan objek Summon Point di Array 2D Peta Gudako
            GUDAKO_WORLD[r][c] = summonCoordinate;
            //Menampung nama-nama Hero di Koordinat (ArrayList)
            String[] heroNamesList = summonPointProperties[2].split(",");
            ArrayList<Hero> heroInSummonList = new ArrayList<Hero>();
            for (int j = 0; j < heroNamesList.length; j++){
                for (int k = 0; k < rawHeroList.size(); k++){
                    if (rawHeroList.get(k).getName().equals(heroNamesList[j])){
                        heroInSummonList.add(rawHeroList.get(k));
                    }
                }
            }
            //Sort dari yang paling tinggi power nya
            Collections.sort(heroInSummonList);
            HERO_WITH_COORDINATES.put(summonCoordinate.toString(), heroInSummonList);

        }

        //Menginisiasi input dari user dan membentuk objek Dungeon
        for (int i = 0; i < dungeonCount; i++){
            cmd = br.readLine();
            String[] dungeonPointProperties = cmd.split(";");
            int r = Integer.parseInt(dungeonPointProperties[0]);
            int c = Integer.parseInt(dungeonPointProperties[1]);
            Coordinate dungeonCoordinate = new Coordinate(r, c, 'D');
            Dungeon newDungeon = new Dungeon(Integer.parseInt(dungeonPointProperties[2]),
                    Integer.parseInt(dungeonPointProperties[3]), dungeonPointProperties[4],
                    Integer.parseInt(dungeonPointProperties[5]));

            //Memasukkan objek Coordinate yang berisi lokasi Dungeon ke Array 2D Map
            GUDAKO_WORLD[r][c] = dungeonCoordinate;
            DUNGEON_WITH_COORDINATES.put(dungeonCoordinate.toString(), newDungeon);

        }

        //Inisialisasi input dari user untuk membentuk Array Maps 2D
        for (int i = 0; i < MAP_ROW; i++){
            cmd = br.readLine();
            char[] mapProperties = cmd.toCharArray();
            for (int j = 0; j < MAP_COL; j++){
                if (mapProperties[j] == '#'){
                    Coordinate wallCoordinate = new Coordinate(i+1, j+1, '#');
                    GUDAKO_WORLD[i+1][j+1] = wallCoordinate;
                }
                if (mapProperties[j] == 'M'){
                    START_COORDINATE = new Coordinate(i+1, j+1, 'M');
                    GUDAKO_WORLD[i+1][j+1] = START_COORDINATE;
                }
                if (mapProperties[j] == '.'){
                    Coordinate roadCoordinate = new Coordinate(i+1, j+1, '.');
                    GUDAKO_WORLD[i+1][j+1] = roadCoordinate;
                }
            }
        }

        //Perintah untuk menjalankan Gudako berdasarkan prioritas arah gerak yang sudah ditentukan
        move(START_COORDINATE.getX(), START_COORDINATE.getY());
        
        //Comparator untuk membandingkan hero berdasarkan level dan lama mengikuti Gudako
        Comparator<Hero> hero1 = new Comparator<Hero>() {
            @Override
            public int compare(Hero hero, Hero anotherHero) {
                if (hero.getLevel() == anotherHero.getLevel()){
                    return (hero.getJoinDuration() - anotherHero.getJoinDuration());
                }
                return -(hero.getLevel() - anotherHero.getLevel());
            }
        };

        Collections.sort(enrolledHero, hero1);
        
        //Menampilkan hasil dari petualangan Gudako
        System.out.println("Akhir petualangan Gudako");
        System.out.println("Level Gudako: "+GUDAKO_CHARACTER.getLevel());
        System.out.println("Level pahlawan:");
        for (int i = 0; i < enrolledHero.size(); i++){
            System.out.println(enrolledHero.get(i).getName()+": "+enrolledHero.get(i).getLevel());
        }
    }

    /**
     * Function move: Untuk menjalankan Gudako secara rekursif sesuai dengan
     * prioritas gerak yang sudah ditentukan
     * @param      r     Nilai koordinat baris titik awal perjalanan Gudako
     * @param      c     Nilai koordinat kolom titik awal perjalanan Gudako
     */

    static void move(int r, int c) {
        if (r < 1 || r > MAP_ROW || c < 1 || c > MAP_COL || GUDAKO_WORLD[r][c].isVisited()
                || GUDAKO_WORLD[r][c].getMark() == '#') return;
        GUDAKO_WORLD[r][c].markAsVisited();
        processResponse(GUDAKO_WORLD[r][c]);
        move(r-1,c);
        move(r,c+1);
        move(r+1,c);
        move(r,c-1);
    }


    /**
     * Function processResponse: Untuk memproses objek-objek yang mungkin ditemui
     * selama perjalanan Gudako 
     * @param      coorTarget  Objek Coordinate yang berisi tentang lokasi dimana posisi sekarang
     */

    static void processResponse(Coordinate coorTarget){
        char mark = coorTarget.getMark();
        String coor = coorTarget.toString();
        ArrayList<Hero> newEnrolledHero = new ArrayList<Hero>();

        //Memasuki daerah Summon
        if (mark == 'S'){
            String str = "";
            ArrayList<Hero> heroList = HERO_WITH_COORDINATES.get(coor);
            int gudakoMana = GUDAKO_CHARACTER.getMana();
            for (int i = 0; i < heroList.size() && gudakoMana > 0; i++) {
                if (heroList.get(i).getMana() <= gudakoMana){
                    HERO_QUEUE++;
                    heroList.get(i).setJoinDuration(HERO_QUEUE);
                    newEnrolledHero.add(heroList.get(i));
                    gudakoMana -= heroList.get(i).getMana();
                }
                else {
                    break;
                }
            }
            if (newEnrolledHero.isEmpty()){
                System.out.println(coorTarget.getX()+","+coorTarget.getY()+" tidak ada pahlawan yang ikut");
            }
            else {
                for (int j = 0; j < newEnrolledHero.size(); j++){
                    str = str + newEnrolledHero.get(j).getName()+",";
                    enrolledHero.add(newEnrolledHero.get(j));
                }
                System.out.println(coorTarget.getX()+","+coorTarget.getY()+" Pahlawan yang ikut:"+str.substring(0,str.length()-1));
            }
        }

        //Memasuki daerah Dungeon
        else if (mark == 'D'){
            int totalGudakoPower = 0;
            Dungeon currDungeon = DUNGEON_WITH_COORDINATES.get(coor);
            final String dungeonWeapon = currDungeon.getWeapon();

            //Sorting berdasarkan kekuatan dan lamanya ikut Gudako
            Comparator<Hero> comparator1 = new Comparator<Hero>() {
                @Override
                public int compare(Hero thisHero, Hero anotherHero) {
                    if (thisHero.getPower(dungeonWeapon) == anotherHero.getPower(dungeonWeapon)){
                        return (thisHero.getJoinDuration() - anotherHero.getJoinDuration());
                    }
                    return -(thisHero.getPower(dungeonWeapon) - anotherHero.getPower(dungeonWeapon));
                }
            };
            Collections.sort(enrolledHero, comparator1);

            int dungeonMaxHeroCount = currDungeon.getMaxHero();

            for (int i = 0; i < enrolledHero.size(); i++){
                if (i < dungeonMaxHeroCount)
                    totalGudakoPower += enrolledHero.get(i).getPower(dungeonWeapon);
            }

            //Kabur dari Dungeon
            if (currDungeon.getDungeonPower() > totalGudakoPower){
                System.out.println(coorTarget.getX()+","+coorTarget.getY()+" RUN, kekuatan maksimal sejumlah: "+totalGudakoPower);
            }

            //Bertarung di Dungeon
            else {

                int enrollHeroInDungeon = 0;
                for (int i = 0; i < enrolledHero.size() && i < dungeonMaxHeroCount; i++){
                    enrolledHero.get(i).addLevel(currDungeon.getDungeonLevel());
                    enrollHeroInDungeon++;
                }
                String str = "";
                for (int i = 0; i < currDungeon.getMaxHero() && i < enrolledHero.size(); i++){
                    str = str + enrolledHero.get(i).getName()+",";
                }
                GUDAKO_CHARACTER.addLevel(enrollHeroInDungeon*currDungeon.getDungeonLevel());
                System.out.println(coorTarget.getX()+","+coorTarget.getY()+" BATTLE, kekuatan: "+totalGudakoPower+", pahlawan: "+str.substring(0, str.length()-1));
            }
        }
    }
}

/**
 * Class untuk objek Hero
 */

class Hero implements Comparable<Hero> {
    private String name;
    private int mana;
    private int level;
    private String weapon;
    private int power;
    private int joinDuration;

    public Hero(String name, int mana, int power, String weapon){
        this.name = name;
        this.mana = mana;
        this.level = 1;
        this.weapon = weapon;
        this.power = power;
        this.joinDuration = 0;
    }

    public void addLevel(int lv){
        this.level += lv;
    }

    public int getLevel(){
        return this.level;
    }

    public void setJoinDuration(int joinDuration){
        this.joinDuration = joinDuration;
    }

    public int getJoinDuration(){
        return this.joinDuration;
    }

    public String getName(){
        return this.name;
    }

    public int getMana(){
        return this.mana;
    }

    public int getPower(String wpn){
        if (wpn.equals(this.weapon)) return this.power;
        else {
            if (wpn.equals("panah")) return this.power/2;
            else return this.power*2;
        }
    }

    @Override
    public int compareTo(Hero otherHero) {
        if (this.power == otherHero.power){
            return -(otherHero.mana - this.mana);
        }
        return (otherHero.power - this.power);
    }
}

/**
 * Class untuk objek Gudako
 */

class Gudako {
    private int level;
    private int mana;
    private boolean isFinished;

    public Gudako(int mana){
        this.level = 1;
        this.mana = mana;
        this.isFinished = false;
    }

    public void addLevel(int level){
        this.level += level;
    }

    public int getMana(){
        return this.mana;
    }

    public int getLevel() {
        return this.level;
    }
}

/**
 * Class untuk objek Coordinate
 */

class Coordinate {
    private int x;
    private int y;
    private boolean hasVisited;
    private char mark;

    public Coordinate(int x, int y, char mark){
        this.x = x;
        this.y = y;
        this.hasVisited = false;
        this.mark = mark;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void markAsVisited(){
        this.hasVisited = true;
    }

    public char getMark() {
        return this.mark;
    }

    public boolean isVisited(){
        return this.hasVisited;
    }

    @Override
    public String toString(){
        return (this.x+","+this.y);
    }
}

/**
 * Class untuk objek Dungeon
 */

class Dungeon {
    private int power;
    private String weapon;
    private int maxHero;
    private int level;

    public Dungeon(int power, int level, String weapon, int maxHero){
        this.power = power;
        this.level = level;
        this.weapon = weapon;
        this.maxHero = maxHero;
    }

    public int getDungeonLevel(){
        return this.level;
    }

    public String getWeapon(){
        return this.weapon;
    }

    public int getDungeonPower(){
        return this.power;
    }

    public int getMaxHero(){
        return this.maxHero;
    }
}
