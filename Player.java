package battleship;

import java.util.Scanner;

public class Player {
    private Battlefield battlefield;


    public Player(){
        this.battlefield = new Battlefield();
    }

    public Battlefield getBattlefield(){
        return this.battlefield;
    }

    public void playerPlaceShip(){

        for(int i = 0 ; i < this.battlefield.listOShips.size()  ; ){

            System.out.println("Enter the coordinates of " + this.battlefield.listOShips.get(i).getName() + " (" + this.battlefield.listOShips.get(i).getLength() +" cells)" );
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


                this.battlefield.listOShips.get(i).setFirstPart(listInput[0]);
                this.battlefield.listOShips.get(i).setLastPart(listInput[1]);



                if(this.battlefield.listOShips.get(i).getLength() == this.battlefield.listOShips.get(i).calculateLength()){

                    this.battlefield.listOShips.get(i).populateListPosition();

                    if(this.battlefield.placeShip(this.battlefield.listOShips.get(i))){
                        this.battlefield.printBattlefield();
                        i++;
                    }

                } else {
                    System.out.println("Error ! Wrong length of the " + this.battlefield.listOShips.get(i).getName() + "! Try again");

                }
            } else  {
                System.out.println("Error");
            }
        }
    }


}
