package battleship;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player();
        Player player2 = new Player();

        System.out.println("Player 1, place your ships on the game field");
        player1.getBattlefield().printBattlefield();
        player1.playerPlaceShip();

        Scanner scanner = new Scanner(System.in);
        String input = "";

        pleasePressEnter(scanner, input);

        System.out.println("Player 2, place your ships on the game field");
        player2.getBattlefield().printBattlefield();
        player2.playerPlaceShip();

        boolean playerTurn = true;
        pleasePressEnter(scanner, input);

        while(true){

            if(playerTurn){
                System.out.println("Player 1, it's your turn:\n");
                player2.getBattlefield().printFogOfWar();
                System.out.println("---------------------");
                player1.getBattlefield().printBattlefield();
                playerTurn = !timeToShoot(player2);

                if(player2.getBattlefield().getNbOfShip() == 0){
                    System.out.println("Player 1 win");
                    break;
                }
            } else {
                System.out.println("Player 2, it's your turn:\n");
                player1.getBattlefield().printFogOfWar();
                System.out.println("---------------------");
                player2.getBattlefield().printBattlefield();
                playerTurn = timeToShoot(player1);
                if(player1.getBattlefield().getNbOfShip() == 0){
                    System.out.println("Player 2 win");
                    break;
                }
            }
            pleasePressEnter(scanner, input);
        }
    }

    public static boolean timeToShoot( Player playerGettingShot){

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if(input.matches("[ABCDEFGHIJ0-9]+")
                && Integer.parseInt(input.substring(1)) >= 1
                && Integer.parseInt(input.substring(1)) <= 10 ){
            if(playerGettingShot.getBattlefield().hitAShip(input)){
                playerGettingShot.getBattlefield().handleHitShip(input);
                System.out.println(playerGettingShot.getBattlefield().getNbOfShip());
                if(playerGettingShot.getBattlefield().getNbOfShip() == 0){
                    System.out.println("You sank the last ship. You won. Congratulations!\n");
                    playerGettingShot.getBattlefield().printBattlefield();

                } else {
                    System.out.println("You sank a ship! Specify a new target:\n");
                }

            } else {
                playerGettingShot.getBattlefield().handleMissHit(input);
                System.out.println("You missed!\n");
            }
        } else {
            System.out.println("Error! You entered the wrong coordinates! Try again:\n");
            return timeToShoot(playerGettingShot);
        }
        return true;
    }

    public static void pleasePressEnter(Scanner scanner, String input){

        do {
            System.out.println("Press Enter and pass the move to another player");
            input = scanner.nextLine();
        } while(!"".equals(input));
    }
}
