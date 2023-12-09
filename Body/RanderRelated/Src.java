package Body.RanderRelated;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Src {
    //到达Images路径
    public static String path = "/Images";

    public static BufferedImage bg_level_1 = null;
    public static BufferedImage bg_level_2 = null;
    public static BufferedImage bg_level_3 = null;
    public static List<BufferedImage> crouch_L = new ArrayList<>();
    public static List<BufferedImage> fall_L = new ArrayList<>();
    public static List<BufferedImage> jump_L = new ArrayList<>();
    public static List<BufferedImage> run_L = new ArrayList<>();
    public static List<BufferedImage> run_stop_L = new ArrayList<>();
    public static List<BufferedImage> stand_L = new ArrayList<>();
//    public static List<BufferedImage> stand2crouch_L = new ArrayList<>();
    public static List<BufferedImage> walk_L = new ArrayList<>();
    public static List<BufferedImage> crouch_R = new ArrayList<>();
    public static List<BufferedImage> fall_R = new ArrayList<>();
    public static List<BufferedImage> jump_R = new ArrayList<>();
    public static List<BufferedImage> run_R = new ArrayList<>();
    public static List<BufferedImage> run_stop_R = new ArrayList<>();
    public static List<BufferedImage> stand_R = new ArrayList<>();
//    public static List<BufferedImage> stand2crouch_R = new ArrayList<>();
    public static List<BufferedImage> walk_R = new ArrayList<>();

    public static void load_images_list(List<BufferedImage> target_List, String file_name, int list_length) {
        for (int i = 0; i < list_length; i++) {
            try {
                if (i <= 9) {
                    target_List.add(ImageIO.read(Objects.requireNonNull(Src.class.getResource(path + "/alice" + "/" + file_name + "/000" + i + ".png"))));
                } else {
                    target_List.add(ImageIO.read(Objects.requireNonNull(Src.class.getResource(path + "/alice" + "/" + file_name + "/00" + i + ".png"))));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void reverse_images(List<BufferedImage> target_list, List<BufferedImage> origen_list) {
        for (int i = 0; i < origen_list.toArray().length; i++) {
            BufferedImage temp = new BufferedImage(origen_list.get(i).getWidth(), origen_list.get(i).getHeight(), origen_list.get(i).getType());
            Graphics2D temp_g = temp.createGraphics();
            temp_g.scale(-1, 1);
            temp_g.drawImage(origen_list.get(i), -origen_list.get(i).getWidth(), 0, null);
            temp_g.dispose();
            target_list.add(temp);
        }

    }

    public static void init() {
        //载入背景
        try {
            bg_level_1 = ImageIO.read(Objects.requireNonNull(Src.class.getResource(path + "/bg" + "/level_1.jpg")));
            bg_level_2 = ImageIO.read(Objects.requireNonNull(Src.class.getResource(path + "/bg" + "/level_2.jpg")));
            bg_level_3 = ImageIO.read(Objects.requireNonNull(Src.class.getResource(path + "/bg" + "/level_1.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //载入下蹲
        load_images_list(crouch_L, "crouch@L", 10);
        reverse_images(crouch_R, crouch_L);

        //载入下落
        load_images_list(fall_L, "fall@L", 8);
        reverse_images(fall_R, fall_L);



        //载入跳
        load_images_list(jump_L, "jump@L", 6);
        reverse_images(jump_R, jump_L);


        //载入跑
        load_images_list(run_L, "run@L", 12);
        reverse_images(run_R, run_L);

        //载入跑停
        load_images_list(run_stop_L, "run_stop@L", 8);
        reverse_images(run_stop_R, run_stop_L);

        //载入站1
        load_images_list(stand_L, "stand@L", 12);
        reverse_images(stand_R, stand_L);

        //载入站2蹲
//        load_images_list(stand2crouch_L, "stand_2crouch@L", 6);
//        reverse_images(stand2crouch_R, stand2crouch_L);



        //载入走
        load_images_list(walk_L, "walk@L", 12);
        reverse_images(walk_R, walk_L);


    }
}

