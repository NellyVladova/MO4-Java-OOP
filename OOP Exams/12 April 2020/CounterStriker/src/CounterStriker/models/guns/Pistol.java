package CounterStriker.models.guns;

public class Pistol extends GunImpl{
    private static final int BULLETS_PER_FIRE = 1;
    public Pistol(String name, int bulletsCount) {
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
