package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, spacePressed, ePressed;

    // DEBUG
    public boolean checkDrawTime, drawCollisionBox;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            if (gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;

                    if (gp.ui.commandNum < 0)
                        gp.ui.commandNum = 2;
                }

                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;

                    if (gp.ui.commandNum > 2)
                        gp.ui.commandNum = 0;
                }
                if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
                    if (gp.ui.commandNum == 0) {
                        gp.ui.titleScreenState = 1;
//                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 1) {

                    }
                    if (gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                }
            }
            else if (gp.ui.titleScreenState == 1) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;

                    if (gp.ui.commandNum < 0)
                        gp.ui.commandNum = 3;
                }

                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;

                    if (gp.ui.commandNum > 3)
                        gp.ui.commandNum = 0;
                }
                if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
                    if (gp.ui.commandNum == 0) {
                        System.out.println("You chose Assassin!");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 1) {
                        System.out.println("You chose Warlock!");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 2) {
                        System.out.println("You chose Archer!");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 3) {
                        gp.ui.titleScreenState = 0;
                    }
                }
            }

        }

        //PLAY STATE
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }

            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }

            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }

            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }

            if (code == KeyEvent.VK_SHIFT)
                shiftPressed = true;

            if (code == KeyEvent.VK_SPACE)
                spacePressed = true;

            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
                gp.stopMusic();
            }
            if (code == KeyEvent.VK_E) {
                ePressed = true;
            }


            // DEBUG
            if (code == KeyEvent.VK_T) {
                if (!checkDrawTime)
                    checkDrawTime = true;
                else if (checkDrawTime)
                    checkDrawTime = false;
            }
            if (code == KeyEvent.VK_Y) {
                if (!drawCollisionBox)
                    drawCollisionBox = true;
                else if (drawCollisionBox)
                    drawCollisionBox = false;
            }
        }
        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
        }

        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_E) {
                gp.gameState = gp.playState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SHIFT)
            shiftPressed = false;

        if (code == KeyEvent.VK_SPACE)
            spacePressed = false;
    }
}
