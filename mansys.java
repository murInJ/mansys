import IO.FileTK;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import picocli.CommandLine;


import java.io.IOException;

/**
 * @title: mansys
 * @Author MurInj
 * @Date: 2022/8/22 12:41
 * @Version 1.0
 */

@CommandLine.Command(name = "mansys", mixinStandardHelpOptions = true, version = "mansys alpha-0.0.2",
        description = "a light manage sqlite system",subcommands = {
        db.class,
        table.class,
})
public class mansys {
    public static Definition.Workplace workplace;
    public static void main(String... args) throws IOException {
        init();
        int exitCode = new CommandLine(new mansys()).execute(args);
        exit();
        System.exit(exitCode);
    }

    @CommandLine.Command(name = "workplace")
    public void workplace(){
        System.out.println(workplace.toString());
    }

    public static void init() throws IOException {
        FileTK.check(Definition.DB_PATH);
        if(!FileTK.check(Definition.WORKSPACE_PATH,false)){
            workplace = new Definition.Workplace(null,null);
        }
        else{
            String wkStr = FileTK.readFile(Definition.WORKSPACE_PATH);
            workplace = JSON.parseObject(wkStr, Definition.Workplace.class);
        }
    }

    public static void exit() throws IOException {
        String jsonStr = JSON.toJSONString(workplace, SerializerFeature.WriteMapNullValue);
        FileTK.writeFile(Definition.WORKSPACE_PATH,jsonStr);
    }


}
