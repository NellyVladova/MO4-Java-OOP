package CounterStriker.models.guns;

public class Rifle extends GunImpl{
    private static final int BULLETS_PER_FIRE = 10;
    private static final int NO_BULLETS = 0;

    public Rifle(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (super.getBulletsCount() < BULLETS_PER_FIRE) {
            return 0;
        }
        super.decreaseBullets(BULLETS_PER_FIRE);
        return BULLETS_PER_FIRE;
    }
}
