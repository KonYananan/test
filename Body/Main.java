package Body;

import Body.Assistant.Clock;
import Body.Assistant.FPS;
import Body.Entity.Obstruction;
import Body.RanderRelated.Src;
import Body.RanderRelated.Windows;

import static Body.Entity.Noel.noel;

public class Main {
    public static long f_c = 1L;


    public static void main(String[] args) throws InterruptedException {
        FPS fps = new FPS();//帧率计算对象
        Src.init(); //资源导入
        Obstruction.init(); //障碍实体初始化
        Windows win = new Windows(); // 创建窗口



        while (true) {
            f_c++;
            Clock.now_time();//计时器更新
            noel.update(); // 人物状态更新
            win.repaint(); // 渲染
            fps.calculation();// 算帧
            System.out.println(noel.getOnGround());
        }
    }
}