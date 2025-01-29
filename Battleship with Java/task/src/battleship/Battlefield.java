package battleship;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Battlefield {

    private ArrayList<ArrayList<String>> battlefield;
    private ArrayList<ArrayList<String>> fogOfWar;
    ArrayList<Ship> listOShips;
    int nbOfShip;


    public Battlefield(){
        this.battlefield = new ArrayList<>();
        this.fogOfWar = new ArrayList<>();
        this.listOShips = new ArrayList<>();
        populateBattlefield();
        populateListOfShips();
        this.nbOfShip = 0;
    }

    private void populateBattlefield(){

        for(int i = 0; i < 11;i++){
            ArrayList<String> tempList = new ArrayList<>();
            for(int j = 0; j < 11; j++){
                // First Line
                if( i == 0 && j == 0){
                    tempList.add(" ");
                } else if(i == 0) {
                    tempList.add(String.valueOf(j));
                } else if( j == 0){
                    tempList.add(String.valueOf(Character.valueOf((char) ('A' + i - 1))));
                } else {
                    tempList.add("~");
                }
            }

            // Create a new Cell for fogOfWar
            ArrayList<String> fogListCopy = new ArrayList<>(tempList);

            this.battlefield.add(tempList);
            this.fogOfWar.add(fogListCopy);
       //     System.out.println("ok");
        }
    }
    public void printBattlefield(){

     //   System.out.println("Size " + this.battlefield.size());


        for(int i = 0; i < 11;i++){
            for(int j = 0; j < 11; j++){
                System.out.print(this.battlefield.get(i).get(j));
                if(j != 10){
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void printFogOfWar(){
        for(int i = 0; i < 11;i++){
            for(int j = 0; j < 11; j++){
                System.out.print(this.fogOfWar.get(i).get(j));
                if(j != 10){
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private void populateListOfShips(){


        this.listOShips.add(new Ship(5, "Aircraft Carrier"));
        this.listOShips.add(new Ship(4, "Battleship"));
        this.listOShips.add(new Ship(3, "Submarine"));

        this.listOShips.add(new Ship(3, "Cruiser"));
        this.listOShips.add(new Ship(2, "Destroyer"));

    }

    public boolean placeShip(Ship ship){


        // Utilisez select Row pour choisir l'array puis remplir l'array
        // Looping in the partPosition list of the Ship Object using selectRow to be able to place the O on the right line

        // First loop to verify no cell are already occupied
        for(String position : ship.getPartPosition()) {



            if (!"~".equals(this.battlefield.get(selectRow(position.substring(0, 1))).get(Integer.parseInt(position.substring(1))))){
                System.out.println("Error! You placed it too close to another one. Try again: ");
                return false;
            }

            int row = selectRow(position.substring(0, 1));
            int col = Integer.parseInt(position.substring(1,2));
            // test if ship is near an another one
            if(row - 1 >= 1){
                if(this.battlefield.get(row - 1).get(col).equals("O")){

                    System.out.println("Error! You placed it too close to another one. Try again: ");
                    ship.getPartPosition().clear();
                    return false;
                }
            }
            if(row + 1 < 11){
                if(this.battlefield.get(row + 1).get(col).equals("O")){

                    System.out.println("Error! You placed it too close to another one. Try again: ");
                    ship.getPartPosition().clear();
                    return false;
                }
            }

            if(col - 1 >= 1){
                if(this.battlefield.get(row).get(col - 1).equals("O")){
                    System.out.println("Error! You placed it too close to another one. Try again: ");
                    ship.getPartPosition().clear();
                    return false;
                }
            }

            if(col + 1 < 11){
                if(this.battlefield.get(row).get(col + 1).equals("O")){
                    System.out.println("Error! You placed it too close to another one. Try again: ");
                    ship.getPartPosition().clear();
                    return false;
                }
            }

        }
        // Second Loop to place

        for(String position : ship.getPartPosition()) {

            int row = selectRow(position.substring(0, 1));
            int col = Integer.parseInt(position.substring(1));


            if ("~".equals(this.battlefield.get(row).get(col))) {

                this.battlefield.get(selectRow(position.substring(0, 1))).set(col, "O");

            }else {
                return false;
            }

        }
        this.nbOfShip++;
        return true;

    }

    public int selectRow(String letter){

        String[] listLetter = {"A", "B", "C", "D", "E", "F",  "G","H", "I", "J"};
        for(int i = 0; i < listLetter.length; i++){

            if(letter.equals(listLetter[i])){
                return  i +1;
            }
        }

        return 0;

    }

    public boolean hitAShip(String hit){

        for(Ship ship : this.listOShips){

            if(ship.getPartPosition().contains(hit)){

                return true;
            }
        }
        return false;

    }

    public void handleHitShip(String hit){

        int row = selectRow(hit.substring(0, 1));
        int col = Integer.parseInt(hit.substring(1));

        for(Ship ship : this.listOShips){

            if(ship.getPartPosition().contains(hit)){

                ship.lifeHit();
                this.fogOfWar.get(row).set(col, "X");
                this.battlefield.get(row).set(col,"X");

                if(ship.getLife() == 0 ){
                    this.nbOfShip--;
                } else{
                    System.out.println("You hit a ship!\n");

                }
                return;

            }
        }


    }

    public void handleMissHit(String hit){

        int row = selectRow(hit.substring(0, 1));
        int col = Integer.parseInt(hit.substring(1));

        this.fogOfWar.get(row).set(col, "M");
        this.battlefield.get(row).set(col, "M");
        printFogOfWar();
    }

    public int getNbOfShip(){
        return this.nbOfShip;
    }

}
