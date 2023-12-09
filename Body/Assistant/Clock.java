package Body.Assistant;


import Body.Main;

public class Clock {

    public static long total_second = 0;
    public static long second = 0;
    public static long minute = 0;
    public static long hour = 0;

    public static void now_time(){
        total_second = Main.f_c / 60;
        minute = total_second % 3600 / 60;
        hour = total_second / 3600;
        second = total_second % 60;
    }
}
