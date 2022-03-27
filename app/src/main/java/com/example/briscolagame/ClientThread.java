package com.example.briscolagame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private String ipAddress;
    private int port;
    private String sendMsg;
    //private TextView resultView;

    // Constructor
    public ClientThread(String sendMsg) {
        this.ipAddress = "192.168.100.29";
        this.port = 8001;
        this.sendMsg = sendMsg;
    }

    // Methods
    public void runthread() {
        try {
            String ipAddress_ = "192.168.100.29";
            int port_ = 8001;
            Socket clientSocket = new Socket(ipAddress_, port_);

            // Sending message
            PrintWriter printWriter = Helper.getWriter(clientSocket);
            printWriter.println(sendMsg+"\r\n");

            // Reciving message
            BufferedReader bufferedReader = Helper.getReader(clientSocket);
            final String result = bufferedReader.readLine();
            SetData(result);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SetData(String message){
// Mesagge
        String sep = "-";
        String[] recived_message = message.split(sep);

        // Setting Variables
        Variables.player_number = Integer.parseInt(recived_message[0]);               // 0
        // Enemy
        Variables.enemyButton1Pressed = Boolean.parseBoolean(recived_message[1]);
        Variables.enemyButton2Pressed = Boolean.parseBoolean(recived_message[2]);
        Variables.enemyButton3Pressed = Boolean.parseBoolean(recived_message[3]);
        Variables.enemyButton1firstclick = Boolean.parseBoolean(recived_message[4]);
        Variables.enemyButton2firstclick = Boolean.parseBoolean(recived_message[5]);     // 5
        Variables.enemyButton3firstclick = Boolean.parseBoolean(recived_message[6]);
        Variables.enemy_card1 = Integer.parseInt(recived_message[7]);
        Variables.enemy_card2 = Integer.parseInt(recived_message[8]);
        Variables.enemy_card3 = Integer.parseInt(recived_message[9]);
        Variables.enemy_table_card = Integer.parseInt(recived_message[10]);           // 10
        Variables.enemy_points = Integer.parseInt(recived_message[11]);
        // Player
        Variables.playerButton1Pressed = Boolean.parseBoolean(recived_message[12]);
        Variables.playerButton2Pressed = Boolean.parseBoolean(recived_message[13]);
        Variables.playerButton3Pressed = Boolean.parseBoolean(recived_message[14]);
        Variables.playerButton1firstclick = Boolean.parseBoolean(recived_message[15]);     // 15
        Variables.playerButton2firstclick = Boolean.parseBoolean(recived_message[16]);
        Variables.playerButton3firstclick = Boolean.parseBoolean(recived_message[17]);
        Variables.player_card1 = Integer.parseInt(recived_message[18]);
        Variables.player_card2 = Integer.parseInt(recived_message[19]);
        Variables.player_card3 = Integer.parseInt(recived_message[20]);                 // 20
        Variables.player_table_card = Integer.parseInt(recived_message[21]);
        Variables.player_points = Integer.parseInt(recived_message[22]);
        // Game Play
        Variables.usedCardsCount = Integer.parseInt(recived_message[23]);
        Variables.gameFinished = Boolean.parseBoolean(recived_message[24]);
        Variables.playerTurn = Boolean.parseBoolean(recived_message[25]);                  // 25
        Variables.playerLastDropped = Boolean.parseBoolean(recived_message[26]);
        Variables.playerWins = Boolean.parseBoolean(recived_message[27]);
        Variables.playerFirstDrop = Boolean.parseBoolean(recived_message[28]);
        Variables.winnerType = recived_message[29];
        Variables.droppedCards = Integer.parseInt(recived_message[30]);                 // 30
        Variables.droppedLast = Integer.parseInt(recived_message[31]);
        // Deck
        Variables.userCardsArrey[0] = Integer.parseInt(recived_message[32]);            // 32
        Variables.userCardsArrey[1] = Integer.parseInt(recived_message[33]);
        Variables.userCardsArrey[2] = Integer.parseInt(recived_message[34]);
        Variables.userCardsArrey[3] = Integer.parseInt(recived_message[35]);            // 35
        Variables.userCardsArrey[4] = Integer.parseInt(recived_message[36]);
        Variables.userCardsArrey[5] = Integer.parseInt(recived_message[37]);
        Variables.userCardsArrey[6] = Integer.parseInt(recived_message[38]);
        Variables.userCardsArrey[7] = Integer.parseInt(recived_message[39]);
        Variables.userCardsArrey[8] = Integer.parseInt(recived_message[40]);            // 40
        Variables.userCardsArrey[9] = Integer.parseInt(recived_message[41]);
        Variables.userCardsArrey[10] = Integer.parseInt(recived_message[42]);
        Variables.userCardsArrey[11] = Integer.parseInt(recived_message[43]);
        Variables.userCardsArrey[12] = Integer.parseInt(recived_message[44]);
        Variables.userCardsArrey[13] = Integer.parseInt(recived_message[45]);           // 45
        Variables.userCardsArrey[14] = Integer.parseInt(recived_message[46]);
        Variables.userCardsArrey[15] = Integer.parseInt(recived_message[47]);
        Variables.userCardsArrey[16] = Integer.parseInt(recived_message[48]);
        Variables.userCardsArrey[17] = Integer.parseInt(recived_message[49]);
        Variables.userCardsArrey[18] = Integer.parseInt(recived_message[50]);           // 50
        Variables.userCardsArrey[19] = Integer.parseInt(recived_message[51]);
        Variables.userCardsArrey[20] = Integer.parseInt(recived_message[52]);
        Variables.userCardsArrey[21] = Integer.parseInt(recived_message[53]);
        Variables.userCardsArrey[22] = Integer.parseInt(recived_message[54]);
        Variables.userCardsArrey[23] = Integer.parseInt(recived_message[55]);
        Variables.userCardsArrey[24] = Integer.parseInt(recived_message[56]);
        Variables.userCardsArrey[25] = Integer.parseInt(recived_message[57]);
        Variables.userCardsArrey[26] = Integer.parseInt(recived_message[58]);
        Variables.userCardsArrey[27] = Integer.parseInt(recived_message[59]);
        Variables.userCardsArrey[28] = Integer.parseInt(recived_message[60]);           // 60
        Variables.userCardsArrey[29] = Integer.parseInt(recived_message[61]);
        Variables.userCardsArrey[30] = Integer.parseInt(recived_message[62]);
        Variables.userCardsArrey[31] = Integer.parseInt(recived_message[63]);
        Variables.userCardsArrey[32] = Integer.parseInt(recived_message[64]);
        Variables.userCardsArrey[33] = Integer.parseInt(recived_message[65]);
        Variables.userCardsArrey[34] = Integer.parseInt(recived_message[66]);
        Variables.userCardsArrey[35] = Integer.parseInt(recived_message[67]);
        Variables.userCardsArrey[36] = Integer.parseInt(recived_message[68]);
        Variables.userCardsArrey[37] = Integer.parseInt(recived_message[69]);
        Variables.userCardsArrey[38] = Integer.parseInt(recived_message[70]);           // 70
        Variables.userCardsArrey[39] = Integer.parseInt(recived_message[71]);
    }

    // EOF - End Of File
}
