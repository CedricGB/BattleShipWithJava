package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Battlefield battlefield = new Battlefield();
        battlefield.printBattlefield();

        System.out.println("Enter the coordinates of the ship:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] listInput = input.split(" ");

        if(listInput.length == 2
                && listInput[0].matches("[ABCDEFGHIJ0-9]+")
                && listInput[1].matches("[ABCDEFGHIJ0-9]+")
                && (listInput[0].length() == 2 || listInput[0].length() == 3)
                && (listInput[1].length() == 2 || listInput[1].length() == 3)
                && Integer.parseInt(listInput[0].substring(1)) < 11
                && Integer.parseInt(listInput[0].substring(1)) > 0
                && Integer.parseInt(listInput[1].substring(1)) < 11
                && Integer.parseInt(listInput[1].substring(1)) > 0
                && (!listInput[0].substring(0,1).equals(listInput[1].substring(0,1))
                    && Integer.parseInt(listInput[0].substring(1)) == Integer.parseInt(listInput[1].substring(1))
                    || (listInput[0].substring(0,1).equals(listInput[1].substring(0,1))
                    && Integer.parseInt(listInput[0].substring(1)) != Integer.parseInt(listInput[1].substring(1))))){

            Ship ship = new Ship(listInput[0], listInput[1]);
            ship.infoShip();

        } else  {
            System.out.println("Error");

        }
    }



}




