package viceCity.models.guns;

public class Pistol extends BaseGun{
    private static final int BULLETS_PER_BARREL = 10;
    private static final int TOTAL_BULLETS = 100;
    private static final int ZERO_CONSTANT = 0;
    private static final int BULLETS_PER_SHOT = 1;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (getBulletsPerBarrel() == ZERO_CONSTANT && getTotalBullets() > ZERO_CONSTANT) {
            this.setTotalBullets(getTotalBullets() - BULLETS_PER_BARREL);
            this.setBulletsPerBarrel(BULLETS_PER_BARREL);
        }

        if (getBulletsPerBarrel() > ZERO_CONSTANT) {
            this.setBulletsPerBarrel(getBulletsPerBarrel() - BULLETS_PER_SHOT);
        }

        return BULLETS_PER_SHOT;
    }
}
