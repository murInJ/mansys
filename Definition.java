/**
 * @title: Definition
 * @Author MurInj
 * @Date: 2022/8/22 13:14
 * @Version 1.0
 */

public class Definition {
    public static String DB_PATH = ".\\db";
    public static String WORKSPACE_PATH = ".\\workspace.json";
    public static class Workplace{
        public String db;
        public String tb;

        public Workplace() {
        }

        public Workplace(String db, String tb){
            this.db = db;
            this.tb = tb;
        }

        @Override
        public String toString() {
            return "Workplace{" +
                    "db='" + db + '\'' +
                    ", tb='" + tb + '\'' +
                    '}';
        }

        public String getDb() {
            return db;
        }

        public void setDb(String db) {
            this.db = db;
        }

        public String getTb() {
            return tb;
        }

        public void setTb(String tb) {
            this.tb = tb;
        }
    }
}
