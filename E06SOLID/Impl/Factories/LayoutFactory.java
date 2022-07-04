package E06SOLID.Impl.Factories;

import E06SOLID.Impl.Layouts.SimpleLayout;
import E06SOLID.Impl.Layouts.XmlLayout;
import E06SOLID.Interfaces.Factory;
import E06SOLID.Interfaces.Layout;

public class LayoutFactory implements Factory<Layout> {
    @Override
    public Layout produce(String input) {
        Layout layout = null;

        if(input.equals("SimpleLayout")){
            layout = new SimpleLayout();
        } else if (input.equals("XmlLayout")){
            layout = new XmlLayout();
        }

        return layout;
    }
}
