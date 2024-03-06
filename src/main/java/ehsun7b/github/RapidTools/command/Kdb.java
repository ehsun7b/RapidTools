package ehsun7b.github.RapidTools.command;

import ehsun7b.github.RapidTools.shell.ShellScriptRunner;
import jakarta.validation.constraints.Pattern;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class Kdb {

    @ShellMethod(key = "kdb")
    public String kdb(
            @ShellOption(defaultValue = "dev")
            @Pattern(regexp = "dev|pro|sta", flags = Pattern.Flag.CASE_INSENSITIVE)
            String env
    ) {

        new Thread(new ShellScriptRunner("/Users/ehsun/dev/scripts/kdb.sh", List.of("daytrader", env), System.out)).start();
        return "KDB " + env;
    }
}
