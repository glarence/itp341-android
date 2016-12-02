package itp341.zhao.glarence.a9;

/**
 * Created by glarencezhao on 4/18/16.
 */
public class Hero {

    private long _id;
    private String name;
    private String power1;
    private String power2;
    private int health;
    private int numWins;
    private int numLosses;
    private int numTies;

    public Hero(){}

    public Hero(long _id, String name, String power1, String power2,
                int health, int numWins, int numLosses, int numTies){
        this._id = _id;
        this.name = name;
        this.power1 = power1;
        this.power2 = power2;
        this.health = health;
        this.numWins = numWins;
        this.numLosses = numLosses;
        this.numTies = numTies;
    }

    public String toString(){
        String str = "";
        return str;
    }

    public long getID(){
        return _id;
    }

    public void setID(long _id){
        this._id = _id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getFirstPower(){
        return power1;
    }

    public void setFirstPower(String power1){
        this.power1 = power1;
    }

    public String getSecondPower(){
        return power2;
    }

    public void setSecondPower(String power2){
        this.power2 = power2;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getNumWins(){
        return numWins;
    }

    public void setNumWins(int numWins){
        this.numWins = numWins;
    }

    public int getNumLosses(){
        return numLosses;
    }

    public void setNumLosses(int numLosses){
        this.numLosses = numLosses;
    }

    public int getNumTies(){
        return numTies;
    }

    public void setNumTies(int numTies){
        this.numTies = numTies;
    }

    public boolean isAlive(){
        if(health > 0)
            return true;
        return false;
    }
}
