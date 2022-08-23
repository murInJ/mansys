import DB.SQLite.SQLiteTK;
import DB.SQLite.column.DBcolumn;
import DB.SQLite.column.columnDefinition.columnDefinition;
import DB.SQLite.column.columnType.columnType;
import IO.FileTK;
import picocli.CommandLine;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @title: table
 * @Author MurInj
 * @Date: 2022/8/22 20:19
 * @Version 1.0
 */

@CommandLine.Command(name = "table", mixinStandardHelpOptions = true,
        description = "mansys table mode")
public class table {
    @CommandLine.Command(name = "use",description = "use table")
    public void use(@CommandLine.Parameters(description = "table name") String tableName){
        mansys.workplace.tb = tableName;
    }

    @CommandLine.Command(name = "create", description = "create a table")
    public void createTable(@CommandLine.Option(names = "-n",required = true) String name,
                            @CommandLine.Option(names = "--sql") String sqlPath) throws IOException, SQLException, ClassNotFoundException {
        String dbFile_path = Definition.DB_PATH + "\\" + mansys.workplace.db + ".db";
        SQLiteTK sqlDB = new SQLiteTK(dbFile_path,true);

        if(sqlPath != null){
            String sql = FileTK.readFile(sqlPath);
            sqlDB.executeUpdate(sql);
        }
        else{
            sqlDB.CreateTable(name);
        }

    }

    @CommandLine.Command(name = "addCol", description = "add column")
    public void addCol(
                       @CommandLine.Option(description = "column name",required = true,names = "-n") String colName,
                       @CommandLine.Option(description = "column type",required = true,names = "-t") String colType,
                       @CommandLine.Option(names = "-d") String[] def) throws Exception {
        String dbFile_path = Definition.DB_PATH + "\\" + mansys.workplace.db + ".db";
        SQLiteTK sqlDB = new SQLiteTK(dbFile_path,true);
        columnType type =  columnType.str2ColType(colType);
        DBcolumn col = new DBcolumn(colName,type);
        if(def != null){
            for(String d : def){
                col.AddDefinition(columnDefinition.str2colDef(d));
            }
        }
        sqlDB.AddColumn(mansys.workplace.tb,col);
    }


    @CommandLine.Command(name = "insert", description = "insert data")
    public void insert(
                       @CommandLine.Parameters(description = "column") String column,
                       @CommandLine.Parameters(description = "value") String value) throws IOException, SQLException, ClassNotFoundException {
        String dbFile_path = Definition.DB_PATH + "\\" + mansys.workplace.db + ".db";
        SQLiteTK sqlDB = new SQLiteTK(dbFile_path,true);
        sqlDB.Insert(mansys.workplace.tb,column,value);
    }

    @CommandLine.Command(name = "remove", description = "remove data")
    public void remove(@CommandLine.Parameters(description = "remove condition") String condition) throws SQLException, IOException, ClassNotFoundException {
        String dbFile_path = Definition.DB_PATH + "\\" + mansys.workplace.db + ".db";
        SQLiteTK sqlDB = new SQLiteTK(dbFile_path,true);
        sqlDB.Delete(mansys.workplace.tb,condition);
    }

    @CommandLine.Command(name = "query",description = "select data")
    public void query(
            @CommandLine.Parameters(description = "column") String column,
            @CommandLine.Option(description = "condition",names = "-c") String condition) throws SQLException, IOException, ClassNotFoundException {
        String dbFile_path = Definition.DB_PATH + "\\" + mansys.workplace.db + ".db";
        SQLiteTK sqlDB = new SQLiteTK(dbFile_path,true);
        List<Object> res;
        if(condition == null){
            res = sqlDB.Query(mansys.workplace.tb,column);
        }else{
            res = sqlDB.Query(mansys.workplace.tb,column,condition);
        }
        for(Object o : res){
            System.out.println(o);
        }
    }

   @CommandLine.Command(name = "columns",description = "show columns name")
    public void columns() throws SQLException, IOException, ClassNotFoundException {
       String dbFile_path = Definition.DB_PATH + "\\" + mansys.workplace.db + ".db";
       SQLiteTK sqlDB = new SQLiteTK(dbFile_path,true);
       List<Object> res = sqlDB.getColumnsName(mansys.workplace.tb);
       for(Object o : res){
           System.out.printf("- %s\n",o);
       }
   }

    @CommandLine.Command(name = "list",description = "list tables")
    public void listTables() throws SQLException, IOException, ClassNotFoundException {
        String dbFile_path = Definition.DB_PATH + "\\" + mansys.workplace.db + ".db";
        SQLiteTK sqlDB = new SQLiteTK(dbFile_path,true);
        List<Object> res = sqlDB.TablesList();
        for(Object o : res){
            System.out.printf("- %s\n",o);
        }
    }
}
