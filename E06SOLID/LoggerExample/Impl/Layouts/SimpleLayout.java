package E06SOLID.Impl.Layouts;

import E06SOLID.Enums.ReportLevel;
import E06SOLID.Interfaces.Layout;

public class SimpleLayout implements Layout {
    @Override
    public String format(String time, String message, ReportLevel reportLevel) {
        return String.format("%s - %s - %s", time, reportLevel, message);
    }
}
