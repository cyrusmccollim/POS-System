public class Logger {
    private static String LOG;

    public static void addLog(String log){
        LOG += "\n" + log;
    }

    public static void printLog(){
        System.out.println(LOG);
    }
}
