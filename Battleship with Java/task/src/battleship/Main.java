package battleship;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Battlefield battlefield = new Battlefield();
        battlefield.printBattlefield();


        for(int i = 0 ; i < battlefield.listOShips.size()  ; ){

            System.out.println("Enter the coordinates of " + battlefield.listOShips.get(i).getName() + " (" + battlefield.listOShips.get(i).getLength() +" cells)" );
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


                battlefield.listOShips.get(i).setFirstPart(listInput[0]);
                battlefield.listOShips.get(i).setLastPart(listInput[1]);



                if(battlefield.listOShips.get(i).getLength() == battlefield.listOShips.get(i).calculateLength()){

                        battlefield.listOShips.get(i).populateListPosition();

                    if(battlefield.placeShip(battlefield.listOShips.get(i))){
                        battlefield.printBattlefield();
                        i++;
                    }

                } else {
                    System.out.println("Error ! Wrong length of the " + battlefield.listOShips.get(i).getName() + "! Try again");

                }
            } else  {
                System.out.println("Error");
            }
        }

        System.out.println("The game starts!\n");
        battlefield.printFogOfWar();
        while(true){

            System.out.println("Take a shot!");

            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();

            if(input.matches("[ABCDEFGHIJ0-9]+")
                && Integer.parseInt(input.substring(1)) >= 1
                && Integer.parseInt(input.substring(1)) <= 10 ){
                if(battlefield.hitAShip(input)){
                    System.out.println("You hit a ship!\n");
                    battlefield.printBattlefield();
                } else {
                    System.out.println("You missed!\n");
                }
            } else {
                System.out.println("Error! You entered the wrong coordinates! Try again:\n");
                continue;
            }

            break;

        }

    }



}




