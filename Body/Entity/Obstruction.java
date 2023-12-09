package Body.Entity;

import Body.RanderRelated.Src;

import java.awt.image.BufferedImage;
import java.util.HashMap;

//障碍物实体类
public class Obstruction {
    //障碍物容器创建
    public static HashMap<String, Obstruction> ObstructionMap = new HashMap<>();
    //对象属性
    private int x, y;
    private int width, height;
    private BufferedImage image;
    public Obstruction() {
    }
    public Obstruction(int x, int y, int width, int height, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public static void init() {
        ObstructionMap.put("Ground", new Obstruction(0, 680, 1440, 50, Src.bg_level_1));
        ObstructionMap.put("Wall_L", new Obstruction(0, 180, 50, 500, Src.bg_level_1));
        ObstructionMap.put("Wall_R", new Obstruction(1390, 180, 50, 500, Src.bg_level_1));

    }


    public int getX() {
        return x;
    }



    public int getY() {
        return y;
    }



    public int getWidth() {
        return width;
    }



    public int getHeight() {
        return height;
    }



    public BufferedImage getImage() {
        return image;
    }
}
