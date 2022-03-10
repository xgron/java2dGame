package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject{

    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {

        this.gp = gp;

        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/heart_blank.png"));
            image = toolbox.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = toolbox.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = toolbox.scaleImage(image3, gp.tileSize, gp.tileSize);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
