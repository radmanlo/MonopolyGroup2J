package models.location;

import models.Player;

public class IncomeTaxTile extends Location{
    private final int TAX_VALUE = 20; //TODO change it

    IncomeTaxTile(){
        super();
    }

    /**
     *
     */
    @Override
    public void activate() {
        super.activate();
    }

    /**
     *
     * @param player
     */
    public void deductTax(Player player){
        player.setUsableMoney(player.getUsableMoney() - this.TAX_VALUE);
    }
}
