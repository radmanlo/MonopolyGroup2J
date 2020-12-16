package models.location;

public class StartTile extends Location{
    private final int PRIZE_VALUE = 500; // TODO change this number

    StartTile(){
    }

    @Override
    public void activate() { // TODO to be implemented
        super.activate();
    }

    public int getPrize(){
        return this.PRIZE_VALUE;
    }
}
