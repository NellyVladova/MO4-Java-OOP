package fairyShop.models;

import java.util.ArrayDeque;

public class ShopImpl implements Shop{
    @Override
    public void craft(Present present, Helper helper) {
        ArrayDeque<Instrument> instrumentsCollection = new ArrayDeque<>(helper.getInstruments());
        Instrument instrument = instrumentsCollection.poll();

        while (helper.canWork() && instrument != null && !present.isDone()){
            while (!instrument.isBroken() || !present.isDone()){
                helper.work();
                instrument.use();
                present.getCrafted();
            }
            instrument = instrumentsCollection.poll();
        }
    }
}
