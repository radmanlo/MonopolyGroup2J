package resources.model.location;

import resources.Player;

public class IncomeTaxTile extends Location{
    private final int TAX_VALUE = 20; //TODO change it

    IncomeTaxTile(){
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
        player.setMoney(player.getMoney() - this.TAX_VALUE);
    }
}
