package E06SOLID.Interfaces;

import E06SOLID.Enums.ReportLevel;

public interface Layout {
    String format(String time, String message, ReportLevel reportLevel);
}
