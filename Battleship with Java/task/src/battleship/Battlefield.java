package battleship;

import java.util.ArrayList;

public class Battlefield {

    ArrayList<ArrayList<String>> battlefield;


    public Battlefield(){
        this.battlefield = new ArrayList<>();
        populateBattlefield();
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

            this.battlefield.add(tempList);
       //     System.out.println("ok");
        }
    }
    public void printBattlefield(){

     //   System.out.println("Size " + this.battlefield.size());
        System.out.println("Size : " + this.battlefield.get(0).size());

        for(int i = 0; i < 11;i++){
            for(int j = 0; j < 11; j++){
                System.out.print(this.battlefield.get(i).get(j));
                if(j != 10){
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

}
