package com.example.briscolagame;

import android.content.SharedPreferences;
import android.os.Handler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.util.Random;
import java.io.FileInputStream;

public class Methodes {

    public static void CreateDeck() {

        // null Card
        Variables.DeckCards[0] = new CardClass(0, "n", 0, R.drawable.amata);

        // Heart Cards
        Variables.DeckCards[1] = new CardClass(1, "c", 11, R.drawable.c1);
        Variables.DeckCards[2] = new CardClass(2, "c", 0, R.drawable.c2);
        Variables.DeckCards[3] = new CardClass(3, "c", 10, R.drawable.c3);
        Variables.DeckCards[4] = new CardClass(4, "c", 0, R.drawable.c4);
        Variables.DeckCards[5] = new CardClass(5, "c", 0, R.drawable.c5);
        Variables.DeckCards[6] = new CardClass(6, "c", 0, R.drawable.c6);
        Variables.DeckCards[7] = new CardClass(7, "c", 0, R.drawable.c7);
        Variables.DeckCards[8] = new CardClass(8, "c", 2, R.drawable.cj);
        Variables.DeckCards[9] = new CardClass(9, "c", 3, R.drawable.cq);
        Variables.DeckCards[10] = new CardClass(10, "c", 4, R.drawable.ck);

        // Flowers Cards
        Variables.DeckCards[11] = new CardClass(11, "f", 11, R.drawable.f1);
        Variables.DeckCards[12] = new CardClass(12, "f", 0, R.drawable.f2);
        Variables.DeckCards[13] = new CardClass(13, "f", 10, R.drawable.f3);
        Variables.DeckCards[14] = new CardClass(14, "f", 0, R.drawable.f4);
        Variables.DeckCards[15] = new CardClass(15, "f", 0, R.drawable.f5);
        Variables.DeckCards[16] = new CardClass(16, "f", 0, R.drawable.f6);
        Variables.DeckCards[17] = new CardClass(17, "f", 0, R.drawable.f7);
        Variables.DeckCards[18] = new CardClass(18, "f", 2, R.drawable.fj);
        Variables.DeckCards[19] = new CardClass(19, "f", 3, R.drawable.fq);
        Variables.DeckCards[20] = new CardClass(20, "f", 4, R.drawable.fk);

        // Black Heart Cards
        Variables.DeckCards[21] = new CardClass(21, "p", 11, R.drawable.p1);
        Variables.DeckCards[22] = new CardClass(22, "p", 0, R.drawable.p2);
        Variables.DeckCards[23] = new CardClass(23, "p", 10, R.drawable.p3);
        Variables.DeckCards[24] = new CardClass(24, "p", 0, R.drawable.p4);
        Variables.DeckCards[25] = new CardClass(25, "p", 0, R.drawable.p5);
        Variables.DeckCards[26] = new CardClass(26, "p", 0, R.drawable.p6);
        Variables.DeckCards[27] = new CardClass(27, "p", 0, R.drawable.p7);
        Variables.DeckCards[28] = new CardClass(28, "p", 2, R.drawable.pj);
        Variables.DeckCards[29] = new CardClass(29, "p", 3, R.drawable.pq);
        Variables.DeckCards[30] = new CardClass(30, "p", 4, R.drawable.pk);

        // Square Cards
        Variables.DeckCards[31] = new CardClass(31, "r", 11, R.drawable.r1);
        Variables.DeckCards[32] = new CardClass(32, "r", 0, R.drawable.r2);
        Variables.DeckCards[33] = new CardClass(33, "r", 10, R.drawable.r3);
        Variables.DeckCards[34] = new CardClass(34, "r", 0, R.drawable.r4);
        Variables.DeckCards[35] = new CardClass(35, "r", 0, R.drawable.r5);
        Variables.DeckCards[36] = new CardClass(36, "r", 0, R.drawable.r6);
        Variables.DeckCards[37] = new CardClass(37, "r", 0, R.drawable.r7);
        Variables.DeckCards[38] = new CardClass(38, "r", 2, R.drawable.rj);
        Variables.DeckCards[39] = new CardClass(39, "r", 3, R.drawable.rq);
        Variables.DeckCards[40] = new CardClass(40, "r", 4, R.drawable.rk);

    }

    public static int getRand(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static void setType() {
        int type;
        type = getRand(1, 4);
        if (type == 1) Variables.winnerType = "c";
        else if (type == 2) Variables.winnerType = "f";
        else if (type == 3) Variables.winnerType = "p";
        else if (type == 4) Variables.winnerType = "r";
    }

    public static boolean setWinner() {
        boolean playerWin = false;

        if (!Variables.playerDroppedCard.cardType.equals(Variables.winnerType) && !Variables.enemyDroppedCard.cardType.equals(Variables.winnerType)) {

            // PLAYER DROPPED LAST -> VERIFY PLAYER CARDS
            if (Variables.droppedLast == 1) {
                if (!Variables.enemyDroppedCard.cardType.equals(Variables.playerDroppedCard.cardType))
                    playerWin = false;
                else {
                    if (Variables.enemyDroppedCard.cardValue == 0 && Variables.playerDroppedCard.cardValue == 0) {
                        if (Variables.enemyDroppedCard.cardNumber < Variables.playerDroppedCard.cardNumber)
                            playerWin = true;
                        else playerWin = false;
                    } else if (Variables.enemyDroppedCard.cardValue == 0 && Variables.playerDroppedCard.cardValue != 0)
                        playerWin = true;
                    else if (Variables.enemyDroppedCard.cardValue != 0 && Variables.playerDroppedCard.cardValue == 0)
                        playerWin = false;
                    else {
                        if (Variables.enemyDroppedCard.cardValue < Variables.playerDroppedCard.cardValue)
                            playerWin = true;
                        else playerWin = false;
                    }
                }
                Variables.playerLastDropped = false;
            }

            // PLAYER DROPPED FIRST -> VERIFY PLAYER CARDS
            else if (Variables.droppedLast == 2) {
                if (!Variables.enemyDroppedCard.cardType.equals(Variables.playerDroppedCard.cardType))
                    playerWin = true;
                else {
                    if (Variables.enemyDroppedCard.cardValue == 0 && Variables.playerDroppedCard.cardValue == 0) {
                        if (Variables.enemyDroppedCard.cardNumber < Variables.playerDroppedCard.cardNumber)
                            playerWin = true;
                        else playerWin = false;
                    } else if (Variables.enemyDroppedCard.cardValue == 0 && Variables.playerDroppedCard.cardValue != 0)
                        playerWin = true;
                    else if (Variables.enemyDroppedCard.cardValue != 0 && Variables.playerDroppedCard.cardValue == 0)
                        playerWin = false;
                    else {
                        if (Variables.enemyDroppedCard.cardValue < Variables.playerDroppedCard.cardValue)
                            playerWin = true;
                        else playerWin = false;
                    }
                }
            }
        } else if (Variables.playerDroppedCard.cardType.equals(Variables.winnerType) && !Variables.enemyDroppedCard.cardType.equals(Variables.winnerType)) {
            playerWin = true;
        } else if (!Variables.playerDroppedCard.cardType.equals(Variables.winnerType) && Variables.enemyDroppedCard.cardType.equals(Variables.winnerType)) {
            playerWin = false;
        } else {   //same card type as Game Card
            if (Variables.playerDroppedCard.cardValue == 0 && Variables.enemyDroppedCard.cardValue == 0) {
                if (Variables.enemyDroppedCard.cardNumber < Variables.playerDroppedCard.cardNumber)
                    playerWin = true;
                else playerWin = false;
            } else if (Variables.enemyDroppedCard.cardValue == 0 && Variables.playerDroppedCard.cardValue != 0)
                playerWin = true;
            else if (Variables.enemyDroppedCard.cardValue != 0 && Variables.playerDroppedCard.cardValue == 0)
                playerWin = false;
            else {
                if (Variables.enemyDroppedCard.cardValue < Variables.playerDroppedCard.cardValue)
                    playerWin = true;
                else playerWin = false;
            }
        }

        // TODO

        delay(2); //wait 2 sec
        // ui->playerTableCard->setIcon(icon0);
        // ui->enemyTableCard->setIcon(icon0);
        if (playerWin) {
            Variables.player_points = Variables.player_points + Variables.enemyDroppedCard.cardValue + Variables.playerDroppedCard.cardValue;
            //ui->playerPointsLabel->setText("Points: " + QString::number(player_points));
        } else {
            Variables.enemy_points = Variables.enemy_points + Variables.enemyDroppedCard.cardValue + Variables.playerDroppedCard.cardValue;
            //ui->enemyPointsLabel->setText("Points: " + QString::number(enemy_points));
        }

        return playerWin;
    }

    public static void delay(int time) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, time * 1000L);
    }

    public static void resetVariables() {
        //resetting variables
        Variables.enemyButton1Pressed = true;
        Variables.enemyButton2Pressed = true;
        Variables.enemyButton3Pressed = true;
        Variables.enemyButton1firstclick = true;
        Variables.enemyButton2firstclick = true;
        Variables.enemyButton3firstclick = true;
        Variables.enemy_card1 = 0;
        Variables.enemy_card2 = 0;
        Variables.enemy_card3 = 0;
        Variables.enemy_table_card = 0;
        Variables.enemy_points = 0;

        // PLAYER
        Variables.playerButton1Pressed = true;
        Variables.playerButton2Pressed = true;
        Variables.playerButton3Pressed = true;
        Variables.playerButton1firstclick = true;
        Variables.playerButton2firstclick = true;
        Variables.playerButton3firstclick = true;
        Variables.player_card1 = 0;
        Variables.player_card2 = 0;
        Variables.player_card3 = 0;
        Variables.player_table_card = 0;
        Variables.player_points = 0;

        // DECk
        for (int i = 0; i <= 39; i++) {
            Variables.userCardsArrey[i] = -1;
        }
        Variables.usedCardsCount = 0;
        Variables.gameFinished = false;

        // GAMEPLAY
        Variables.playerTurn = true;
        Variables.playerLastDropped = false;
        Variables.playerWins = false;
        Variables.playerFirstDrop = true;
        Variables.droppedCards = 0;   //when 2 reset and verify winner
        Variables.droppedLast = 0;   //droppedFirst : 1 PLAYER / 2 ENEMY
    }

    public static void SaveGameData() {
        SharedPreferences.Editor editor = GamePlay.GameData.edit();

        // Saving Data.txt

        // Enemy
        editor.putBoolean("enemyButton1Pressed", Variables.enemyButton1Pressed);
        editor.putBoolean("enemyButton2Pressed", Variables.enemyButton2Pressed);
        editor.putBoolean("enemyButton3Pressed", Variables.enemyButton3Pressed);
        editor.putBoolean("enemyButton1firstclick", Variables.enemyButton1firstclick);
        editor.putBoolean("enemyButton2firstclick", Variables.enemyButton2firstclick);
        editor.putBoolean("enemyButton3firstclick", Variables.enemyButton3firstclick);
        editor.putInt("enemy_card1", Variables.enemy_card1);
        editor.putInt("enemy_card2", Variables.enemy_card2);
        editor.putInt("enemy_card3", Variables.enemy_card3);
        editor.putInt("enemy_table_card", Variables.enemy_table_card);
        editor.putInt("enemy_points", Variables.enemy_points);

        // Player
        editor.putBoolean("playerButton1Pressed", Variables.playerButton1Pressed);
        editor.putBoolean("playerButton2Pressed", Variables.playerButton2Pressed);
        editor.putBoolean("playerButton3Pressed", Variables.playerButton3Pressed);
        editor.putBoolean("playerButton1firstclick", Variables.playerButton1firstclick);
        editor.putBoolean("playerButton2firstclick", Variables.playerButton2firstclick);
        editor.putBoolean("playerButton3firstclick", Variables.playerButton3firstclick);
        editor.putInt("player_card1", Variables.player_card1);
        editor.putInt("player_card2", Variables.player_card2);
        editor.putInt("player_card3", Variables.player_card3);
        editor.putInt("player_table_card", Variables.player_table_card);
        editor.putInt("player_points", Variables.player_points);

        // DECK
        editor.putInt("usedCardsCount", Variables.usedCardsCount);
        for (int i = 0; i <= Variables.usedCardsCount; i++) {
            if (Variables.usedCardsCount != 0) editor.putInt("usedCardsCount" + i, Variables.userCardsArrey[i]);
        }
        editor.putBoolean("gameFinished", Variables.gameFinished);

        // GAMEPLAY
        editor.putBoolean("playerTurn", Variables.playerTurn);
        editor.putBoolean("playerLastDropped", Variables.playerLastDropped);
        editor.putBoolean("playerWins", Variables.playerWins);
        editor.putBoolean("playerFirstDrop", Variables.playerFirstDrop);
        editor.putInt("droppedCards", Variables.droppedCards);
        editor.putInt("droppedLast", Variables.droppedLast);

        editor.commit();
    }

    public static void LoadGameData() {
        // Enemy
        Variables.enemyButton1Pressed = GamePlay.sp.getBoolean("enemyButton1Pressed", Variables.enemyButton1Pressed);
        Variables.enemyButton2Pressed = GamePlay.sp.getBoolean("enemyButton2Pressed", Variables.enemyButton2Pressed);
        Variables.enemyButton3Pressed = GamePlay.sp.getBoolean("enemyButton3Pressed", Variables.enemyButton3Pressed);
        Variables.enemyButton1firstclick = GamePlay.sp.getBoolean("enemyButton1firstclick", Variables.enemyButton1firstclick);
        Variables.enemyButton2firstclick = GamePlay.sp.getBoolean("enemyButton2firstclick", Variables.enemyButton2firstclick);
        Variables.enemyButton3firstclick = GamePlay.sp.getBoolean("enemyButton3firstclick", Variables.enemyButton3firstclick);
        Variables.enemy_card1 = GamePlay.sp.getInt("enemy_card1", Variables.enemy_card1);
        Variables.enemy_card2 = GamePlay.sp.getInt("enemy_card2", Variables.enemy_card2);
        Variables.enemy_card3 = GamePlay.sp.getInt("enemy_card3", Variables.enemy_card3);
        Variables.enemy_table_card = GamePlay.sp.getInt("enemy_table_card", Variables.enemy_table_card);
        Variables.enemy_points = GamePlay.sp.getInt("enemy_points", Variables.enemy_points);

        // Player
        Variables.playerButton1Pressed = GamePlay.sp.getBoolean("playerButton1Pressed", Variables.playerButton1Pressed);
        Variables.playerButton2Pressed = GamePlay.sp.getBoolean("playerButton2Pressed", Variables.playerButton2Pressed);
        Variables.playerButton3Pressed = GamePlay.sp.getBoolean("playerButton3Pressed", Variables.playerButton3Pressed);
        Variables.playerButton1firstclick = GamePlay.sp.getBoolean("playerButton1firstclick", Variables.playerButton1firstclick);
        Variables.playerButton2firstclick = GamePlay.sp.getBoolean("playerButton2firstclick", Variables.playerButton2firstclick);
        Variables.playerButton3firstclick = GamePlay.sp.getBoolean("playerButton3firstclick", Variables.playerButton3firstclick);
        Variables.player_card1 = GamePlay.sp.getInt("player_card1", Variables.player_card1);
        Variables.player_card2 = GamePlay.sp.getInt("player_card2", Variables.player_card2);
        Variables.player_card3 = GamePlay.sp.getInt("player_card3", Variables.player_card3);
        Variables.player_table_card = GamePlay.sp.getInt("player_table_card", Variables.player_table_card);
        Variables.player_points = GamePlay.sp.getInt("player_points", Variables.player_points);

        // DECK
        Variables.usedCardsCount = GamePlay.sp.getInt("usedCardsCount", Variables.usedCardsCount);
        for (int i = 0; i <= Variables.usedCardsCount; i++) {
            if (Variables.usedCardsCount != 0) Variables.userCardsArrey[i] = GamePlay.sp.getInt("usedCardsCount" + i, Variables.userCardsArrey[i]);
        }
        Variables.gameFinished = GamePlay.sp.getBoolean("gameFinished", Variables.gameFinished);

        // GAMEPLAY
        Variables.playerTurn = GamePlay.sp.getBoolean("playerTurn", Variables.playerTurn);
        Variables.playerLastDropped = GamePlay.sp.getBoolean("playerLastDropped", Variables.playerLastDropped);
        Variables.playerWins = GamePlay.sp.getBoolean("playerWins", Variables.playerWins);
        Variables.playerFirstDrop = GamePlay.sp.getBoolean("playerFirstDrop", Variables.playerFirstDrop);
        Variables.droppedCards = GamePlay.sp.getInt("droppedCards", Variables.droppedCards);
        Variables.droppedLast = GamePlay.sp.getInt("droppedLast", Variables.droppedLast);
    }

    public static boolean SaveDataFile() throws FileNotFoundException {
        //File file = new File("app/src/main/java/com/example/briscolagame/Data.txt");
        try (OutputStreamWriter outputStream = new OutputStreamWriter("Data", MODE_PRIVATE)) {
            // ENEMY \\
            outputStream.write((byte)(Variables.enemyButton1Pressed?1:0));
            outputStream.write((byte)(Variables.enemyButton2Pressed?1:0));
            outputStream.write((byte)(Variables.enemyButton3Pressed?1:0));
            outputStream.write((byte)(Variables.enemyButton1firstclick?1:0));
            outputStream.write((byte)(Variables.enemyButton2firstclick?1:0));
            outputStream.write((byte)(Variables.enemyButton3firstclick?1:0));
            outputStream.write((intToBytes(Variables.enemy_card1)));
            outputStream.write((intToBytes(Variables.enemy_card2)));
            outputStream.write((intToBytes(Variables.enemy_card3)));
            outputStream.write((intToBytes(Variables.enemy_table_card)));
            outputStream.write((intToBytes(Variables.enemy_points)));

            // Player \\
            outputStream.write((byte)(Variables.playerButton1Pressed?1:0));
            outputStream.write((byte)(Variables.playerButton2Pressed?1:0));
            outputStream.write((byte)(Variables.playerButton3Pressed?1:0));
            outputStream.write((byte)(Variables.playerButton1firstclick?1:0));
            outputStream.write((byte)(Variables.playerButton2firstclick?1:0));
            outputStream.write((byte)(Variables.playerButton3firstclick?1:0));
            outputStream.write((intToBytes(Variables.player_card1)));
            outputStream.write((intToBytes(Variables.player_card2)));
            outputStream.write((intToBytes(Variables.player_card3)));
            outputStream.write((intToBytes(Variables.player_table_card)));
            outputStream.write((intToBytes(Variables.player_points)));

            // DECK \\
            outputStream.write((intToBytes(Variables.usedCardsCount)));
            for (int i = 0; i <= Variables.usedCardsCount; i++) {
                if (Variables.usedCardsCount != 0) outputStream.write((intToBytes(Variables.userCardsArrey[i])));
            }
            outputStream.write((byte)(Variables.gameFinished?1:0));

            // GAMEPLAY \\
            outputStream.write((byte)(Variables.playerTurn?1:0));
            outputStream.write((byte)(Variables.playerLastDropped?1:0));
            outputStream.write((byte)(Variables.playerWins?1:0));
            outputStream.write((byte)(Variables.playerFirstDrop?1:0));
            outputStream.write((intToBytes(Variables.droppedCards)));
            outputStream.write((intToBytes(Variables.droppedLast)));

            outputStream.close();
            return true;
        } catch (IOException e) {
            //Toast.makeText(this, "Unable to open outPutStream!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
    }

    private void writeText(String test){
        OutputStreamWriter o = new
    }

    private static byte[] intToBytes(final int i) {
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putInt(i);
        return bb.array();
    }

    public static boolean LoadDataFile()throws FileNotFoundException {
        //File file = new File("app/src/main/java/com/example/briscolagame/Data.txt");
        try (FileInputStream inputStream = new FileInputStream("Data")) {
            byte[] b = new byte[8];
            // ENEMY \\
            int a = inputStream.read();
            Variables.enemyButton2Pressed = (a != 0);
            a = inputStream.read();
            Variables.enemyButton2Pressed = (a != 0);
            a = inputStream.read();
            Variables.enemyButton3Pressed = (a != 0);
            a = inputStream.read();
            Variables.enemyButton1firstclick = (a != 0);
            a = inputStream.read();
            Variables.enemyButton2firstclick = (a != 0);
            a = inputStream.read();
            Variables.enemyButton3firstclick = (a != 0);
            Variables.enemy_card1 = inputStream.read(b,0,8);
            Variables.enemy_card2 = inputStream.read(b,0,8);
            Variables.enemy_card3 = inputStream.read(b,0,8);
            Variables.enemy_table_card = inputStream.read(b,0,8);
            Variables.enemy_points = inputStream.read(b,0,8);

            // Player \\
            a = inputStream.read();
            Variables.playerButton1Pressed = (a != 0);
            a = inputStream.read();
            Variables.playerButton2Pressed = (a != 0);
            a = inputStream.read();
            Variables.playerButton3Pressed = (a != 0);
            a = inputStream.read();
            Variables.playerButton1firstclick = (a != 0);
            a = inputStream.read();
            Variables.playerButton2firstclick = (a != 0);
            a = inputStream.read();
            Variables.playerButton3firstclick = (a != 0);
            Variables.player_card1 = inputStream.read(b,0,8);
            Variables.player_card2 = inputStream.read(b,0,8);
            Variables.player_card3 = inputStream.read(b,0,8);
            Variables.player_table_card = inputStream.read(b,0,8);
            Variables.player_points = inputStream.read(b,0,8);

            // DECK \\
            Variables.usedCardsCount = inputStream.read(b,0,8);
            for (int i = 0; i <= Variables.usedCardsCount; i++) {
                if (Variables.usedCardsCount != 0)  Variables.userCardsArrey[i] = inputStream.read(b,0,8);
            }
            a = inputStream.read();
            Variables.gameFinished = (a != 0);

            // GAMEPLAY \\
            a = inputStream.read();
            Variables.playerTurn = (a != 0);
            a = inputStream.read();
            Variables.playerLastDropped = (a != 0);
            a = inputStream.read();
            Variables.playerWins = (a != 0);
            a = inputStream.read();
            Variables.playerFirstDrop = (a != 0);
            Variables.droppedCards = inputStream.read(b,0,8);
            Variables.droppedLast = inputStream.read(b,0,8);

            inputStream.close();
            return true;
        } catch (IOException e) {
            //Toast.makeText(this, "Unable to open outPutStream!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
    }

    // EOF - End Of File
}