package viceCity.models.guns;

public class Rifle extends BaseGun{
    private static final int BULLETS_PER_BARREL = 50;
    private static final int TOTAL_BULLETS = 500;
    private static final int BULLETS_PER_SHOT = 5;
    private static final int ZERO_CONSTANT = 0;

    public Rifle(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if(getBulletsPerBarrel() == ZERO_CONSTANT && getTotalBullets() > ZERO_CONSTANT){
            this.setTotalBullets(getTotalBullets() - BULLETS_PER_BARREL);
            this.setBulletsPerBarrel(BULLETS_PER_BARREL);
        }
        if(getBulletsPerBarrel() > ZERO_CONSTANT){
            this.setBulletsPerBarrel(getBulletsPerBarrel() - BULLETS_PER_SHOT);
        }
        return BULLETS_PER_SHOT;
    }
}
