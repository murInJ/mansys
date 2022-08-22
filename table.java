import picocli.CommandLine;
import src.main.java.DB.SQLite.SQLiteTK;
import src.main.java.DB.SQLite.column.DBcolumn;
import src.main.java.DB.SQLite.column.columnType.columnType;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @title: table
 * @Author MurInj
 * @Date: 2022/8/22 20:19
 * @Version 1.0
 */

@CommandLine.Command(name = "table", mixinStandardHelpOptions = true,
        description = "mansys table mode")
public class table {
    @CommandLine.Parameters(description = "db in use")
    String dbName;

    @CommandLine.Command(name = "create", description = "create a table")
    public void createTable(@CommandLine.Option(names = "-n") String name) throws IOException, SQLException, ClassNotFoundException {
        String dbFile_path = Definition.DB_PATH + "\\" + dbName + "\\.db";
        SQLiteTK sqlDB = new SQLiteTK(dbFile_path,true);
        sqlDB.CreateTable(name);
    }

    @CommandLine.Command(name = "addCol", description = "add column")
    public void addCol(@CommandLine.Parameters(description = "table name") String tableName,
                       @CommandLine.Parameters(description = "column name") String colName,
                       @CommandLine.Parameters(description = "column type") String colType,
                       @CommandLine.Option(names = "-d") String[] def) throws Exception {
        String dbFile_path = Definition.DB_PATH + "\\" + dbName + "\\.db";
        SQLiteTK sqlDB = new SQLiteTK(dbFile_path,true);
        columnType type =  columnType.str2ColType(colType);
        DBcolumn col = new DBcolumn(colName,type);
        sqlDB.AddColumn(tableName,col);
    }


    @CommandLine.Command(name = "insert", description = "insert data")
    public void insert(@CommandLine.Parameters(description = "table name") String tableName,
                       @CommandLine.Parameters(description = "column") String column,
                       @CommandLine.Parameters(description = "value") String value) throws IOException, SQLException, ClassNotFoundException {
        String dbFile_path = Definition.DB_PATH + "\\" + dbName + "\\.db";
        SQLiteTK sqlDB = new SQLiteTK(dbFile_path,true);
        sqlDB.Insert(tableName,column,value);
    }


}
