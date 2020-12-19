package models.location;

import java.util.ArrayList;

import models.Player;

public class Disciplinary extends Location{
    private ArrayList<Player> prisoners;

    Disciplinary(){
        super();
    }

    @Override
    public void activate() {
        super.activate();
    }

    public void freePlayer(Player player){
        for (int i = 0; i < prisoners.size(); i++){
            if (prisoners.get(i).equals(player)){
                prisoners.remove(i);
                return;
            }
        }
    }

    public void arrestPlayer(Player player){
        prisoners.add(player);
    }
}
