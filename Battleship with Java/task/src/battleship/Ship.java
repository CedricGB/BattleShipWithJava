package battleship;

import java.util.ArrayList;

public class Ship {

    private int length;
    private ArrayList<String> partPosition;
    private String firstPart;
    private String lastPart;


    public Ship(String firstPart, String lastPart){
        this.firstPart = firstPart;
        this.lastPart = lastPart;
        this.partPosition = new ArrayList<>();
        this.length = 0;
        calculateLength();
    }

    private void calculateLength(){

        int firstPartNum = getIntPart(this.firstPart);
        int secondPartNum = getIntPart(this.lastPart);

        if(this.firstPart.substring(0,1).equals(this.lastPart.substring(0,1))){
            if(firstPartNum < secondPartNum){

                for(int i = firstPartNum; i <= secondPartNum; i++) {
                    if(i == firstPartNum){
                        this.partPosition.add(this.firstPart);
                    } else {
                        this.partPosition.add(this.firstPart.substring(0,1) + i);
                    }
                    this.length++;
                }
            } else {
                for(int i = secondPartNum; i <= firstPartNum; i++) {

                    if(i == secondPartNum) {
                        this.partPosition.add(this.lastPart);
                    } else {
                        this.partPosition.add(this.firstPart.substring(0,1) + i);
                    }
                    this.length++;
                }
            }
        } else {
            char firstL = this.firstPart.charAt(0);
            char lastL = this.lastPart.charAt(0);

            if( firstL < lastL){
                for(char i = firstL; i <= lastL; i++){
                    this.partPosition.add(String.valueOf(i) + firstPartNum);
                    this.length++;

                }
            } else {
                for(char i = lastL; i <= firstL; i++){
                    this.partPosition.add(String.valueOf(i) + firstPartNum);
                    this.length++;

                }
            }
        }

    }
    private Integer getIntPart(String part){

        return Integer.parseInt(part.substring(1));
    }

    public void infoShip(){
        System.out.println("Length: " + this.length);
        System.out.print("Parts:");
        for(String s : this.partPosition){
            System.out.print(" " + s);
        }
    }


}
