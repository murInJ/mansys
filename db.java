import picocli.CommandLine;
import src.main.java.DB.SQLite.SQLiteTK;
import src.main.java.FileTK;

import java.io.File;
import java.io.IOException;

/**
 * @title: db
 * @Author MurInj
 * @Date: 2022/8/22 12:49
 * @Version 1.0
 */

@CommandLine.Command(name = "db", mixinStandardHelpOptions = true,
        description = "mansys db mode")
public class db {

    @CommandLine.Command(name = "list", description = "get db list")
    public void listDB() throws IOException {
        FileTK.check(Definition.DB_PATH);
        String[] fileList = new File(Definition.DB_PATH).list();
        for(int i = 0; i < fileList.length;++i){
            System.out.printf("%d. %s\n",i+1,fileList[i].split("\\.")[0]);
        }
        if(fileList.length == 0) System.out.println("Have no db yet !");
    }

    @CommandLine.Command(name = "create", description = "create a db")
    public void createDB(@CommandLine.Option(names = "-n") String name) throws IOException {
        SQLiteTK.CreateDB(Definition.DB_PATH,name);
    }
}
