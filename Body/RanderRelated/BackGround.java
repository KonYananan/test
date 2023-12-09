package Body.RanderRelated;

import java.awt.image.BufferedImage;

public class BackGround {

    private int level;
    private BufferedImage bgImage = null;

    public BackGround() {
    }

    public BackGround(int level) {
        this.level = level;

        if (level == 1) {
            bgImage = Src.bg_level_1;

            System.out.println("第一关");


        } else if (level == 2) {
            bgImage = Src.bg_level_2;
            System.out.println("第二关");
        } else {
            bgImage = Src.bg_level_3;
            System.out.println("第三关");
        }
    }

    public BufferedImage getBgImage() {
        return bgImage;
    }

    public int getLevel() {
        return level;
    }
}
