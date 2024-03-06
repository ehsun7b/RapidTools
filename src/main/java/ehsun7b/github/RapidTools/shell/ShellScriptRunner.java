package ehsun7b.github.RapidTools.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShellScriptRunner implements Runnable {
    private final Logger log = LoggerFactory.getLogger(ShellScriptRunner.class);

    private final String script;
    private final List<String> scriptAndParams;
    private final PrintStream out;
    private final UUID id;
    private LocalDateTime startLocalTime;
    private Long start;

    public ShellScriptRunner(String script, List<String> params, PrintStream out) {
        this.script = script;
        this.out = out;
        id = UUID.randomUUID();
        scriptAndParams = new ArrayList<>();
        scriptAndParams.add(script);
        scriptAndParams.addAll(params);
    }

    @Override
    public void run() {
        try {
            startLocalTime = LocalDateTime.now();
            start = System.nanoTime();
            final var processBuilder = new ProcessBuilder(scriptAndParams);
            log.info(logPrefix() + "Started");
            var process = processBuilder.start();
            printStream(process.getInputStream());
            final var exitCode = process.waitFor();
            final var endTime = System.nanoTime();
            final var duration = endTime - start;
            log.info(logPrefix() + "Exited with code: " + exitCode);
            log.info(logPrefix() + "Execution time: " + duration + " nanoseconds" + " Started at: " + startLocalTime);
        } catch (IOException | InterruptedException e) {
            log.error(logPrefix() + " failed. " + e.getMessage());
        }
    }

    private String logPrefix() {
        return "[ID: " + shorten(id.toString()) + " - " + shorten(script) + "]: ";
    }

    private String shorten(String str) {
        return str.substring(0, 4) + "..." + str.substring(str.length() - 4);
    }

    private void printStream(final InputStream inputStream) throws IOException {
        new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .forEach(out::println);
    }
}
