package main;

import entity.Entity;
import object.OBJ_Heart;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font ui_font, victory_font;

    BufferedImage heart_full, heart_half, heart_blank;

    public boolean messageOn = false;
    public String message = "";
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: first screen, 1: class selection

    public UI(GamePanel gp) {
        this.gp = gp;

        ui_font = new Font("High Tower Text", Font.PLAIN, 40);
        victory_font = new Font("Britannic Bold", Font.PLAIN, 80);


        // CREATE HUD OBJECT
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;
        g2.setFont(ui_font);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }

        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }


    }

    private void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;

        for (int i = 0; i < gp.player.maxLife / 2; i++) {
            g2.drawImage(heart_blank, x, y, null);
            x += gp.tileSize;
        }
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        for (int i = 0; i < gp.player.life / 2; i++) {
            g2.drawImage(heart_full, x, y, null);
            x += gp.tileSize;
        }
        if (gp.player.life % 2 != 0)
            g2.drawImage(heart_half, x, y, null);

    }

    private void drawTitleScreen() {

        //SELECTION IMAGE
        BufferedImage menuImage = null;
        Toolbox toolbox = new Toolbox();
        try {
            menuImage = toolbox.scaleImage(ImageIO.read(getClass().getResourceAsStream("/resources/objects/arrow.png")), gp.tileSize / 2, gp.tileSize / 2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (titleScreenState == 0) {
            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 68F));
            String text = "Epic Adventure Quest";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            // SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);

            // MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // LITTLE KNIGHT IMAGE
            x = gp.screenWidth / 2 - gp.tileSize;
            y += gp.tileSize * 2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 4;
            if (commandNum == 0) {
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
                g2.drawImage(menuImage, x - gp.tileSize, y - gp.tileSize / 2, null);
            }
            g2.drawString(text, x, y);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            if (commandNum == 1) {
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
                g2.drawImage(menuImage, x - gp.tileSize, y - gp.tileSize / 2, null);
            }
            g2.drawString(text, x, y);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
            text = "QUIT GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            if (commandNum == 2) {
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
                g2.drawImage(menuImage, x - gp.tileSize, y - gp.tileSize / 2, null);
            }
            g2.drawString(text, x, y);
        } else if (titleScreenState == 1) {

            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(38F));

            String text = "Select your class!";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Edgy Assassin";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 0)
                g2.drawImage(menuImage, x - gp.tileSize, y - gp.tileSize / 2, null);

            text = "Broody Warlock";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1)
                g2.drawImage(menuImage, x - gp.tileSize, y - gp.tileSize / 2, null);

            text = "Lone Wolf Archer";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2)
                g2.drawImage(menuImage, x - gp.tileSize, y - gp.tileSize / 2, null);

            text = "Return";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3)
                g2.drawImage(menuImage, x - gp.tileSize, y - gp.tileSize / 2, null);
        }


    }

    private void drawDialogueScreen() {

        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);


    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
