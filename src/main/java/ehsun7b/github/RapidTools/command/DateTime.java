package ehsun7b.github.RapidTools.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@ShellComponent
public class DateTime {

    @ShellMethod(key = "time")
    public String time(
            @ShellOption(defaultValue = "Australia/Sydney") String zone
    ) {
        return LocalTime.now(ZoneId.of(zone)).format(DateTimeFormatter.ISO_TIME);
    }

    @ShellMethod(key = "date")
    public String date(
            @ShellOption(defaultValue = "Australia/Sydney") String zone
    ) {
        return LocalDate.now(ZoneId.of(zone)).format(DateTimeFormatter.ISO_DATE);
    }

    @ShellMethod(key = "now")
    public String now(
            @ShellOption(defaultValue = "Australia/Sydney") String zone
    ) {
        return LocalDateTime.now(ZoneId.of(zone)).format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
