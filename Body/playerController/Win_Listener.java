package Body.playerController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Body.Entity.Noel.noel;
import static Body.Entity.NoelStateList.*;


public class Win_Listener implements KeyListener, ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        noel.setNoel_previous_state_name(noel.getNoel_current_state_name());

        //通过上下左右键根据noel对象更改noel对象状态
        switch (noel.getNoel_current_state_name()) {
            case Stand_R -> {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    noel.setNoel_current_state_name(Walk_R);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    noel.setNoel_current_state_name(Walk_L);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    noel.setNoel_current_state_name(Jump_R);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    noel.setNoel_current_state_name(Crouch_R);
                }
            }
            case Stand_L -> {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    noel.setNoel_current_state_name(Walk_R);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    noel.setNoel_current_state_name(Walk_L);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    noel.setNoel_current_state_name(Jump_L);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    noel.setNoel_current_state_name(Crouch_L);
                }
            }

            case Walk_R -> {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    noel.setNoel_current_state_name(Walk_L);
                }
            }
            case Walk_L -> {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    noel.setNoel_current_state_name(Walk_R);
                }
            }


            case Run_R -> {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    noel.setNoel_current_state_name(Run_L);
                }
            }
            case Run_L -> {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    noel.setNoel_current_state_name(Run_R);
                }
            }

            case Ready_to_Run_L -> {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    noel.setCanRun();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    noel.setNoel_current_state_name(Walk_R);
                }
            }
            case Ready_to_Run_R -> {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    noel.setCanRun();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    noel.setNoel_current_state_name(Walk_L);
                }
            }
            case Run_After_R -> {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    noel.setNoel_current_state_name(Run_R);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    noel.setNoel_current_state_name(Run_L);
                }
            }
            case Run_After_L -> {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    noel.setNoel_current_state_name(Run_R);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    noel.setNoel_current_state_name(Run_L);
                }
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        //通过上下左右键根据noel对象更改noel对象状态
        noel.setNoel_previous_state_name(noel.getNoel_current_state_name());
        switch (noel.getNoel_current_state_name()) {

            case Stand_R, Walk_R -> {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    noel.setNoel_current_state_name(Ready_to_Run_R);
                }
            }
            case Stand_L, Walk_L -> {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    noel.setNoel_current_state_name(Ready_to_Run_L);
                }
            }
            case Jump_R -> {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    noel.setNoel_current_state_name(Stand_R);
                }
            }
            case Jump_L -> {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    noel.setNoel_current_state_name(Stand_L);
                }
            }
            case Crouch_R -> {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    noel.setNoel_current_state_name(Stand_R);
                }
            }
            case Crouch_L -> {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    noel.setNoel_current_state_name(Stand_L);
                }
            }


            case Run_R -> {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    noel.setNoel_current_state_name(Run_After_R);
                }
            }


            case Run_L -> {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    noel.setNoel_current_state_name(Run_After_L);
                }
            }
        }
    }

}




