package battleship;

import java.util.ArrayList;

public class Ship {

    private int length;
    private ArrayList<String> partPosition;
    private String firstPart;
    private String lastPart;
    private String name;
    private int life;


    public Ship(int length, String name){
        this.name = name;
        this.length = length;
        this.partPosition = new ArrayList<>();
        this.life = length;

    }
    public  Ship(String firstPart, String lastPart, String name){
        this(firstPart,lastPart);
        this.name = name;

    }
    public Ship(String firstPart, String lastPart){
        this.firstPart = firstPart;
        this.lastPart = lastPart;
        this.partPosition = new ArrayList<>();
        this.length = calculateLength();

        if(this.name == null){
            this.name = "ship";
        }
    }

    public int calculateLength(){
        int count = 0;
        int firstPartNum = getIntPart(this.firstPart);
        int secondPartNum = getIntPart(this.lastPart);

        if(this.firstPart.substring(0,1).equals(this.lastPart.substring(0,1))){
            if(firstPartNum < secondPartNum){

                for(int i = firstPartNum; i <= secondPartNum; i++) {

                    count++;
                }
            } else {
                for(int i = secondPartNum; i <= firstPartNum; i++) {


                    count++;
                }
            }
        } else {
            char firstL = this.firstPart.charAt(0);
            char lastL = this.lastPart.charAt(0);

            if( firstL < lastL){
                for(char i = firstL; i <= lastL; i++){

                    count++;

                }
            } else {
                for(char i = lastL; i <= firstL; i++){

                    count++;

                }
            }
        }
        return count;
    }

    public void populateListPosition(){

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

                }
            } else {
                for(int i = secondPartNum; i <= firstPartNum; i++) {

                    if(i == secondPartNum) {
                        this.partPosition.add(this.lastPart);
                    } else {
                        this.partPosition.add(this.firstPart.substring(0,1) + i);
                    }

                }
            }
        } else {
            char firstL = this.firstPart.charAt(0);
            char lastL = this.lastPart.charAt(0);

            if( firstL < lastL){
                for(char i = firstL; i <= lastL; i++){
                    this.partPosition.add(String.valueOf(i) + firstPartNum);


                }
            } else {
                for(char i = lastL; i <= firstL; i++){
                    this.partPosition.add(String.valueOf(i) + firstPartNum);


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

    public Integer getLength(){

        if(this.length == 0 ){
            this.length = calculateLength();
        }
        return this.length;
    }

    public void setFirstPart(String firstPart){
        this.firstPart = firstPart;
    }

    public void setLastPart(String lastPart){
        this.lastPart = lastPart;
    }

    public String getFirstPart(){
        return this.firstPart;
    }

    public String getLastPart(){
        return this.lastPart;
    }

    public ArrayList<String> getPartPosition(){
        return this.partPosition;
    }

    public String getName(){
        return this.name;
    }

    public void lifeHit(){
        this.life--;
    }

    public int getLife(){
        return this.life;
    }

}
