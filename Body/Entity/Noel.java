package Body.Entity;

import Body.RanderRelated.Src;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Noel implements Runnable {
    public static Noel noel = new Noel("noel", 300, 300);


    private final String name;
    //    private boolean onGround = false;
    public boolean Crashed = false;
    private boolean xAxisMove = true;
    private boolean yAxisMove = true;
    private boolean onGround = false;

    private boolean canRun = false;
    public int xSpeed = 0;
    public int ySpeed = 0;
    public float ySpeedf = 0;
    private int x, y;
    private int height;
    private int width;


    private NoelStateList noel_current_state_name;//fsm
    private NoelStateList noel_previous_state_name;//fsm
    public static List<BufferedImage> noel_current_state_animate_list = null;//当前状态
    private BufferedImage noel_current_key_frame = null; // 从noel当前状态动画中获取到的关键帧

    public Noel(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.height = 110;
        this.width = 25;
        this.noel_current_state_name = NoelStateList.Stand_R; //初始状态
        this.noel_previous_state_name = NoelStateList.Stand_R; //初始状态
    }

    //让你有判断是不是站在地上的能力
    private boolean isCrashed() {
        xAxisMove = true;
        yAxisMove = true;

        int x = noel.getX() + 60;
        int y = noel.getY() + 30;
        //遍历所有障碍物，进行碰撞检测
        for (Obstruction obstruction : Obstruction.ObstructionMap.values()) {
            if (x > obstruction.getX() && x < obstruction.getX() + obstruction.getWidth() && y > obstruction.getY() && y < obstruction.getY() + obstruction.getHeight()) {
                return true;
            }
        }
        return false;
    }
    //是不是站在什么东西上面
    private boolean isOnGround() {
        int x = noel.getX() + 60;
        int y = noel.getY() + 140;
        boolean temp = false;
        for (Obstruction obstruction : Obstruction.ObstructionMap.values()) {
            if (obstruction.getX() < x && x < obstruction.getX() + obstruction.getWidth() - noel.width && y > obstruction.getY()) {
                temp = true;
            }
        }
        return temp;
    }


    public void update() {

        noel.Crashed = isCrashed();
        noel.onGround = isOnGround();
        switch (noel_current_state_name) {
            //站立状态
            case Stand_R -> {
                noel.setHeight(110);
                xSpeed = 0;
                noel_current_state_animate_list = Src.stand_R;
            }
            case Stand_L -> {
                noel.setHeight(110);
                xSpeed = 0;
                noel_current_state_animate_list = Src.stand_L;
            }
            //行走状态
            case Walk_R -> {
                xSpeed = 3;
                noel_current_state_animate_list = Src.walk_R;
            }
            case Walk_L -> {
                xSpeed = -3;
                noel_current_state_animate_list = Src.walk_L;
            }
            //跑
            case Ready_to_Run_R -> {
                xSpeed = 0;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (canRun) {
                            noel.setNoel_current_state_name(NoelStateList.Run_R);
                            setCantRun();
                        } else if (noel.getNoel_current_state_name() == NoelStateList.Ready_to_Run_R) {
                            noel.setNoel_current_state_name(NoelStateList.Stand_R);
                        }
                    }
                }, 150);

            }

            case Ready_to_Run_L -> {
                xSpeed = 0;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (canRun) {
                            noel.setNoel_current_state_name(NoelStateList.Run_L);
                            setCantRun();
                        } else if (noel.getNoel_current_state_name() == NoelStateList.Ready_to_Run_L) {
                            noel.setNoel_current_state_name(NoelStateList.Stand_L);
                        }
                    }
                }, 150);

            }

            case Run_R -> {
                xSpeed = 8;
                noel_current_state_animate_list = Src.run_R;
            }
            case Run_L -> {
                xSpeed = -8;
                noel_current_state_animate_list = Src.run_L;
            }


            case Run_After_R -> {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (noel.getNoel_current_state_name() == NoelStateList.Run_After_R) {
                            noel.setNoel_current_state_name(NoelStateList.Stand_R);
                            noel.setCantRun();
                        }

                    }
                }, 80);
            }
            case Run_After_L -> {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (noel.getNoel_current_state_name() == NoelStateList.Run_After_L) {
                            noel.setNoel_current_state_name(NoelStateList.Stand_L);
                            noel.setCantRun();
                        }
                    }
                }, 80);
            }


            //蹲下
            case Crouch_R -> {
                noel.setHeight(75);
                xSpeed = 0;
                noel_current_state_animate_list = Src.crouch_R;
            }
            case Crouch_L -> {
                noel.setHeight(75);
                xSpeed = 0;
                noel_current_state_animate_list = Src.crouch_L;
            }
            //原地起跳
            case Jump_L -> {
                xSpeed = 0;
                ySpeed = -10;
//                Timer timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        while (ySpeed < 0) {
//                            ySpeed++;
//                            try {
//                                Thread.sleep(10);
//                            } catch (InterruptedException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                        noel.setNoel_current_state_name(NoelStateList.Fall_L);
//                    }
//                }, 0);
                noel_current_state_animate_list = Src.jump_L;
            }
            case Jump_R -> {
                xSpeed = 0;
                ySpeedf = -1;
                y = (int) (y + ySpeedf);
                noel_current_state_animate_list = Src.jump_R;
            }


        }
        if(onGround){
            ySpeedf = 0;
        }else{
           ySpeedf = ySpeedf + 0.3f;
        }


        x += xSpeed;
        y += ySpeedf;
    }


    @Override
    public void run() {
        //动画载入
        while (true) {

            for (int i = 0; i < noel_current_state_animate_list.toArray().length; i++) {
                noel_current_key_frame = noel_current_state_animate_list.get(i);
                if (i == noel_current_state_animate_list.toArray().length - 1) {
                    i = -1;
                }
                try {
                    Thread.sleep((int) (1000 / noel_current_state_animate_list.toArray().length));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }


    public BufferedImage getNoel_current_key_frame() {
        return noel_current_key_frame;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public NoelStateList getNoel_current_state_name() {
        return noel_current_state_name;
    }

    public void setNoel_current_state_name(NoelStateList state) {
        this.noel_current_state_name = state;
    }

    public NoelStateList getNoel_previous_state_name() {
        return noel_previous_state_name;
    }

    public void setNoel_previous_state_name(NoelStateList state) {
        this.noel_previous_state_name = state;
    }

    public void setCantRun() {
        noel.canRun = false;
    }

    public void setCanRun() {
        noel.canRun = true;
    }

    public boolean getCanRun() {
        return noel.canRun;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }


    public boolean isxAxisMove() {
        return xAxisMove;
    }

    public void setxAxisMove(boolean xAxisMove) {
        this.xAxisMove = xAxisMove;
    }

    public boolean isyAxisMove() {
        return yAxisMove;
    }

    public void setyAxisMove(boolean yAxisMove) {
        this.yAxisMove = yAxisMove;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
    public boolean getOnGround() {
        return onGround;
    }
}

