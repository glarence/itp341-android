package itp341.zhao.glarence.a7;

import java.io.Serializable;

/**
 * Created by glarencezhao on 3/23/16.
 */
public class CoffeeOrder implements Serializable{

    private String _size;
    private String _brew;
    private String _instr;

    private Boolean _sugar;
    private Boolean _milk;

    public CoffeeOrder(){
        super();

        _size = "";
        _brew = "";
        _instr = "";

        _sugar = false;
        _milk = false;

    }

    public CoffeeOrder(String size, String brew, String instr, Boolean sugar, Boolean milk){

        _size = size;
        _brew = brew;
        _instr = instr;

        _sugar = sugar;
        _milk = milk;

    }

    public String getSize(){

        return _size;

    }

    public void setSize(String size){

        _size = size;

    }

    public String getBrew(){

        return _brew;

    }

    public void setBrew(String brew){

        _brew = brew;

    }

    public String getInstr(){

        return _instr;

    }

    public void setInstr(String instr){

        _instr = instr;

    }

    public boolean isSugar(){

        return _sugar;

    }

    public void changeSugar(){

        _sugar = !_sugar;

    }

    public boolean isMilk(){

        return _milk;

    }

    public void changeMilk(){

        _milk = !_milk;

    }

}
