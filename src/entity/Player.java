package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(10, 16, 28, 28);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 22;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        // PLAYER STATS
        maxLife = 6;
        life = 6;
    }

    public void getImage() {

            up1 = setup("/resources/player/upIdle");
            up2 = setup("/resources/player/upWalk1");
            up3 = setup("/resources/player/upWalk2");
            down1 = setup("/resources/player/downIdle");
            down2 = setup("/resources/player/downWalk1");
            down3 = setup("/resources/player/downWalk2");
            left1 = setup("/resources/player/leftIdle");
            left2 = setup("/resources/player/leftWalk1");
            left3 = setup("/resources/player/leftWalk2");
            right1 = setup("/resources/player/rightIdle");
            right2 = setup("/resources/player/rightWalk1");
            right3 = setup("/resources/player/rightWalk2");

    }


    public void update() {

        if (keyH.shiftPressed)
            speed = 8;
        else
            speed = 4;

        if (keyH.leftPressed || keyH.rightPressed || keyH.upPressed || keyH.downPressed) {

            if (keyH.leftPressed == true) {
                direction = "left";

            } else if (keyH.rightPressed == true) {
                direction = "right";

            }
            if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            }

            // CHECK TILE COLLISION

            collisionOn = false;

            if(!keyH.spacePressed){
                gp.cChecker.checkTile(this);
            }

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            gp.keyH.ePressed = false;

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {

                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            // STATS
            if (life < 0)
                life = 0;
        }


        spriteCounter++;
        if (spriteCounter > 5) {
            if (spriteNum < 4)
                spriteNum++;
            else
                spriteNum = 1;
            spriteCounter = 0;
        }
        if (!(keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed))
            spriteNum = 1;
    }

    public void pickUpObject(int i) {

        if (i != 999) {

        }
    }

    private void interactNPC(int npcIndex) {
        if (npcIndex != 999 && gp.keyH.ePressed) {
            gp.gameState = gp.dialogueState;
            gp.npc[npcIndex].speak();
        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1)
                    image = up1;
                if (spriteNum == 2)
                    image = up2;
                if (spriteNum == 3)
                    image = up1;
                if (spriteNum == 4)
                    image = up3;
                break;
            case "down":
                if (spriteNum == 1)
                    image = down1;
                if (spriteNum == 2)
                    image = down2;
                if (spriteNum == 3)
                    image = down1;
                if (spriteNum == 4)
                    image = down3;
                break;
            case "left":
                if (spriteNum == 1)
                    image = left1;
                if (spriteNum == 2)
                    image = left2;
                if (spriteNum == 3)
                    image = left1;
                if (spriteNum == 4)
                    image = left3;
                break;
            case "right":
                if (spriteNum == 1)
                    image = right1;
                if (spriteNum == 2)
                    image = right2;
                if (spriteNum == 3)
                    image = right1;
                if (spriteNum == 4)
                    image = right3;
                break;
        }
        g2.drawImage(image, screenX, screenY,null);

        // DRAW COLLISION BOX
        if (keyH.drawCollisionBox){
            g2.setColor(Color.red);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }


    }
}
