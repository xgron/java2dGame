package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_DarkKnight extends Entity{

    public NPC_DarkKnight(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {

        up1 = setup("/resources/NPC/darkKnight/upIdle");
        up2 = setup("/resources/NPC/darkKnight/upWalk1");
        up3 = setup("/resources/NPC/darkKnight/upWalk2");
        down1 = setup("/resources/NPC/darkKnight/downIdle");
        down2 = setup("/resources/NPC/darkKnight/downWalk1");
        down3 = setup("/resources/NPC/darkKnight/downWalk2");
        left1 = setup("/resources/NPC/darkKnight/leftIdle");
        left2 = setup("/resources/NPC/darkKnight/leftWalk1");
        left3 = setup("/resources/NPC/darkKnight/leftWalk2");
        right1 = setup("/resources/NPC/darkKnight/rightIdle");
        right2 = setup("/resources/NPC/darkKnight/rightWalk1");
        right3 = setup("/resources/NPC/darkKnight/rightWalk2");

    }
    public void setDialogue() {

        dialogues[0] = "Greetings, young warrior.";
        dialogues[1] = "To become a great knight, \n you have to be brave and humble.";
        dialogues[2] = "Do not let the my armor intimidate you. \n I fight for the people.";
        dialogues[3] = "My sword is called the Shard of Amethyst.";
        dialogues[4] = "If you came to the island \n looking for treasure, \n you are in for a disappointment.";
    }
    public void setAction() {

        actionLockInterval ++;

        if (actionLockInterval == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1; // random number between 1 to 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            int j = random.nextInt(100) + 1;
            if (j > 25)
                speed = 0;
            else
                speed = 1;

            actionLockInterval = 0;
        }

    }
    public void speak() {
        super.speak();
    }

}
