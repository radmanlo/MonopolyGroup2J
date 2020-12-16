package models.location;
import models.Player;

public class MayfestTile extends Location{
    private int collectedTax;

    MayfestTile(){
        super();
    }

    @Override
    public void activate() {
        super.activate();
    }

    public void addTax(int tax){
        this.collectedTax += tax;
    }

    public void giveTaxMoney(Player player){ // TODO Should not get player as param
        player.setMoney(player.getMoney()+this.collectedTax);
        this.collectedTax = 0;
    }
}
