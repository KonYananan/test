package Body.RanderRelated;

import Body.Assistant.FPS;
import Body.Entity.Obstruction;
import Body.playerController.Win_Listener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Body.Assistant.Clock.*;
import static Body.Entity.Noel.noel;


public class Windows extends JFrame {
    Thread noel_state_thread = new Thread(noel);

    public Image offScreenImage = null;
    private ArrayList<BackGround> all_bg = new ArrayList<>();

    {
        all_bg.add(new BackGround(1));
        all_bg.add(new BackGround(2));
        all_bg.add(new BackGround(3));
    }


    public Windows() {
        this.setTitle("noel_dev");
        this.setVisible(true);
        this.setSize(1440, 720);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //红叉退出
        Win_Listener wl = new Win_Listener();

        this.addKeyListener(wl);
        noel.update();
        noel_state_thread.start();
    }


    @Override
    public void paint(Graphics g) {

        //缓存空画面板
        if (offScreenImage == null) {
            offScreenImage = createImage(1440, 720);
        }

        Graphics g_temp = offScreenImage.getGraphics();
        //清空背板
        g_temp.clearRect(0, 0, this.getWidth(), this.getHeight());

        //FPS_时钟显示
        {
            g_temp.setColor(Color.black);
            g_temp.setFont(new Font("Arial", Font.PLAIN, 20));
            g_temp.drawString("FPS:" + FPS.framePerSecond + "  " + "Tread Sleep: " + FPS.sleepTime + "ms", 50, 50);
            g_temp.drawString("time:" + hour + ":" + minute + ":" + second, 50, 80);
        }

        //人物状态显示
        {
            g_temp.drawString("noel_line_state: " + !(noel_state_thread.isInterrupted()), 50, 100);
            g_temp.drawString("noel_previous_state: " + noel.getNoel_previous_state_name(), 50, 120);
            g_temp.drawString("noel_current_state : " + noel.getNoel_current_state_name(), 50, 140);
            g_temp.drawString("noel_location:" + "X:" + noel.getX() + "Y:" + noel.getY(), 50, 160);
            g_temp.drawString("noel_isCanRun:" + noel.getCanRun(), 50, 180);
            g_temp.drawString("crashed:" + noel.Crashed, 50, 200);
            g_temp.drawString("noel_isOnGround:" + noel.getOnGround(), 50, 220);
//            g_temp.drawString("now_status:      " + Win_Listener.Listened_status, 50, 100);
        }


        //背景载入

        g_temp.drawImage(Obstruction.ObstructionMap.get("Ground").getImage(), Obstruction.ObstructionMap.get("Ground").getX(), Obstruction.ObstructionMap.get("Ground").getY(), Obstruction.ObstructionMap.get("Ground").getWidth(), Obstruction.ObstructionMap.get("Ground").getHeight(), this);
        g_temp.drawImage(Obstruction.ObstructionMap.get("Wall_L").getImage(), Obstruction.ObstructionMap.get("Wall_L").getX(), Obstruction.ObstructionMap.get("Wall_L").getY(), Obstruction.ObstructionMap.get("Wall_L").getWidth(), Obstruction.ObstructionMap.get("Wall_L").getHeight(), this);
        g_temp.drawImage(Obstruction.ObstructionMap.get("Wall_R").getImage(), Obstruction.ObstructionMap.get("Wall_R").getX(), Obstruction.ObstructionMap.get("Wall_R").getY(), Obstruction.ObstructionMap.get("Wall_R").getWidth(), Obstruction.ObstructionMap.get("Wall_R").getHeight(), this);


        //人物载入
        g_temp.drawImage(noel.getNoel_current_key_frame(), noel.getX(), noel.getY(), 150, 150, this);
        g_temp.drawRect(noel.getX(), noel.getY(), 150, 150);

        g_temp.drawLine(noel.getX() + 75, noel.getY(), noel.getX() + 75, noel.getY() + 150);//中轴线
        g_temp.drawRect(noel.getX() + 60, noel.getY() + 140 - noel.getHeight(), noel.getWidth(), noel.getHeight());
//        g_temp.drawLine(noel.getX() + 60, noel.getY()+20, noel.getX()+60, noel.getY() + 130);//左边界
//        g_temp.drawLine(noel.getX() + 85, noel.getY()+20, noel.getX()+85, noel.getY() + 130);//右边界


        //投至窗口
        g.drawImage(offScreenImage, 0, 0, this.getWidth(), this.getHeight(), this);


    }
}



