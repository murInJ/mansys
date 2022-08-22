import picocli.CommandLine;
import src.main.java.FileTK;

import java.io.IOException;

/**
 * @title: mansys
 * @Author MurInj
 * @Date: 2022/8/22 12:41
 * @Version 1.0
 */

@CommandLine.Command(name = "mansys", mixinStandardHelpOptions = true, version = "mansys alpha-0.0.0",
        description = "a light manage sqlite system",subcommands = {
        db.class,
})
public class mansys {
    public static void main(String... args) {
        int exitCode = new CommandLine(new mansys()).execute(args);
        System.exit(exitCode);
    }

    @CommandLine.Command(name = "init", description = "init mansys project struct")
    public static void init() throws IOException {
        FileTK.check(Definition.DB_PATH);
    }
}
