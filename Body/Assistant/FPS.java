package Body.Assistant;

import Body.Main;

public class FPS {
    public static long frameIndex = 1;  //记录已渲染帧
    public static int frameInterval = 16;//期望每帧间隔时间（毫秒单位）
    public static int sleepTime; // cpu睡眠时间 越小cpu占用越高；

    public static int framePerSecond; //帧率
    public static long LastDraw; //
    public static double thisTime, lastTime;

    public FPS() {

    }
    public void calculation(){
        frameIndex = Main.f_c;
        if (frameIndex % 30 == 0) {
            double thisTime = System.currentTimeMillis();
            framePerSecond = (int) ((1000) / ((thisTime - lastTime) / 30));
            lastTime = thisTime;
        }
        sleepTime = 0;
        while (System.currentTimeMillis() - LastDraw < frameInterval) {
            try {
                Thread.sleep(1);
                sleepTime++;
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        LastDraw = System.currentTimeMillis();
    }


//    public void FPS_print() {
//        frameIndex++;
//        if (frameIndex % 30 == 0) {
//            double thisTime = System.currentTimeMillis();
//            framePerSecond = (int) ((1000) / ((thisTime - lastTime) / 30));
//            lastTime = thisTime;
//        }
//        sleepTime = 0;
//        while (System.currentTimeMillis() - LastDraw < frameInterval) {
//            try {
//                Thread.sleep(1);
//                sleepTime++;
//            } catch (InterruptedException e1) {
//                e1.printStackTrace();
//            }
//        }
//        LastDraw = System.currentTimeMillis();
//    }

}



