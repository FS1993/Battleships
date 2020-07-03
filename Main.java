
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static char[][] createMap() {
        char[][] map = new char[10][16];
        for(int i = 0; i <10; i++) {
            for (int j = 0; j<16; j++) {
                if (j == 0 || j == 15) {
                    map[i][j] = Integer.toString(i).charAt(0);
                }
                else if (j == 1 || j == 14 || (j >= 3 && j <= 12)) {
                    map[i][j] = ' ';
                }
                else if (j == 2 || j == 13) {
                    map[i][j] = '|';
                }
            }
        }
       return map; 
    }
    public static void printMap(char[][] map){
        System.out.println("   0123456789");
        for(int i = 0; i <10; i++) {
            for (int j = 0; j<16; j++) {
                if (j == 15) {
                    System.out.println(map[i][j]);
                }
                else
                    System.out.print(map[i][j]);
            } 
        }
        System.out.println("   0123456789");
    }
    
    public static char[][] deployShips(char[][] map) {
        int numberOfShips = 0;
        Scanner input = new Scanner(System.in);
        while (numberOfShips < 5){
            
            System.out.print("Enter X coordinate for your ship: ");
            int x = input.nextInt();
            while (x > 9 || x < 0) {
                System.out.print("Enter valid X coordinate for your ship: ");
                x = input.nextInt();
            }
            System.out.print("Enter Y coordinate for your ship: ");
            int y = input.nextInt();
            while (y > 9 || y < 0) {
                System.out.print("Enter valid Y coordinate for your ship: ");
                y = input.nextInt();
            }
            if (map[x][y+3] != '@'){
                map[x][y+3] = '@';
                numberOfShips++;
            }
            else {
                System.out.println("There is a ship already in this location");
            }
        }
        return map;
    }
    public static char[][] deployComputerShips (char[][] map, char[][] map2) {
        int numberOfShips = 0;
        int k, l;
        Random r = new Random();
        System.out.println("Computer is deploying ships:");
        while (numberOfShips < 5){
            k = r.nextInt(10);
            l = r.nextInt(10)+3;
            if (map[k][l] != '@'){
                map2[k][l] = '@';
                numberOfShips++;
                System.out.println("Ship "+numberOfShips+" deployed.");
                    }
            else {
                while (map[k][l] == '@') {
                    k = r.nextInt(10);
                    l = r.nextInt(10)+3;
                    if (map[k][l] != '@'){
                        map2[k][l] = '@';
                        numberOfShips++;
                        System.out.println("Ship "+numberOfShips+" deployed.");
                    }
                }
            }
        }
        return map2;
    }
    
    public static void battle(char[][] map, char[][] map2){
        Random r = new Random();
        int numberOfShips1 = 5, numberOfShips2 = 5;
        Scanner input = new Scanner(System.in);
        System.out.println("Battle is commencing!");
        while (numberOfShips1 > 0 && numberOfShips2 > 0) {
            //Player's turn
            System.out.println("YOUR TURN.");
            System.out.print("Enter X coordinate: ");
            int x = input.nextInt();
            while (x > 9 || x < 0) {
                System.out.print("Enter valid X coordinate: ");
                x = input.nextInt();
            }
            System.out.print("Enter Y coordinate: ");
            int y = input.nextInt();
            while (y > 9 || y < 0) {
                System.out.print("Enter valid Y coordinate: ");
                y = input.nextInt();
            }
            if (map2[x][y+3] == '@'){
                System.out.println("BOOM! You sunk a ship!");
                map[x][y+3] = '!';
                map2[x][y+3] = 'x';
                numberOfShips2--;
            }
            else if (map[x][y+3] == '@')  {
                System.out.println("Oh no, you sunk your own ship!");
                map[x][y+3] = 'x';
                map2[x][y+3] = '!';
                numberOfShips1--;
            }
            else {
                System.out.println("You missed.");
                map[x][y+3] = '-';
            }
            if (numberOfShips2 == 0){
                break;
            }
            //Computer's turn
            x = r.nextInt(10);
            y = r.nextInt(10);
            while (map2[x][y+3] == '-' || map2[x][y+3] == '!' || map2[x][y+3] == '@') {
                x = r.nextInt(10);
                y = r.nextInt(10);
            }
            if (map[x][y+3] == '@'){
                System.out.println("Your ship has been sunk!");
                map[x][y+3] = 'x';
                map2[x][y+3] = '!';
                numberOfShips1--;
            }
            else {
                System.out.println("Computer missed.");
                map2[x][y+3] = '-';
            }
            printMap(map);
            System.out.println("Your ships: "+numberOfShips1+", Computer's ships: "+numberOfShips2);
                  
        }
        //Results
        if (numberOfShips1 == 0){
            System.out.println("You lost!");            
        }
        else if (numberOfShips2 == 0){
            System.out.println("You won!");
        }
    }
    
    public static void main(String[] args) {
    	
        char[][] map = createMap();
        char[][] map2 = createMap();
        printMap(map);
        map = deployShips(map);
        printMap(map);
        deployComputerShips(map, map2);
        //printMap(map2);
        battle(map, map2);
    }
    
}
