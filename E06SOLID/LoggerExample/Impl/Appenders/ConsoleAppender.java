package E06SOLID.Impl.Appenders;

import E06SOLID.Enums.ReportLevel;
import E06SOLID.Interfaces.Layout;

public class ConsoleAppender extends BaseAppender {

    public ConsoleAppender(Layout layout) {
        super(layout);
    }

    @Override
    public void append(String time, String message, ReportLevel reportLevel) {
        if (this.canAppend(reportLevel)) {
            String formattedMessage = this.layout.format(time, message, reportLevel);
            increaseMessageCount();
            System.out.println(formattedMessage);
        }
    }
}
