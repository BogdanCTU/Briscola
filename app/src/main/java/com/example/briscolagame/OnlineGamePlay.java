package com.example.briscolagame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OnlineGamePlay extends AppCompatActivity {

    //My IP   192.168.100.6

    // IInstantiez clasa thread u un mesaj ce vreau sa trimit, adica un string cu variabilele mele


    // PLAYER BUTTONS
    private ImageButton playerCard1;
    private ImageButton playerCard2;
    private ImageButton playerCard3;
    private ImageButton playerTableCard;
    private ImageButton enemyCard1;
    private ImageButton enemyCard2;
    private ImageButton enemyCard3;
    private ImageButton enemyTableCard;
    private ImageButton pauseButton;

    // Text Labels
    private TextView playerPointsLabel;
    private TextView enemyPointsLabel;
    private TextView cardWinnerTypeLabel;

    //Saving Data.txt
    //public static final String PREFS_NAME = "GameplayData";
    public static SharedPreferences GameData;
    public static SharedPreferences sp;

    // FILE
    public static OutputStreamWriter outputFile;
    public static InputStreamReader inputFile;

    // DATABASE
    private final String IP = "192.168.100.29";
    private final String PORT = "1433";
    private final String CLASSES = "net.sourceforge.jtds.jdbc.Driver";
    private final String DATABASE = "BriscolaDatabase";
    private final String USERNAME = "";
    private final String PASSWORD = "";
    private final String URL = "jdbc:jtds:sqlserver://" + IP + ":" + PORT + "/" + DATABASE;

    private Connection CONNECTION = null;

    public void DATABASE_READ() throws SQLException {
        if(CONNECTION != null){
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from Briscola;");

            //resultSet = statement.executeQuery("Insert ")

            // Reading Data
            resultSet.next();

            //Enemy
            Variables.enemyButton1Pressed = resultSet.getInt(1) == 0;
            Variables.enemyButton2Pressed = resultSet.getInt(2) == 0;
            Variables.enemyButton3Pressed = resultSet.getInt(3) == 0;
            Variables.enemyButton1firstclick = resultSet.getInt(4) == 0;
            Variables.enemyButton2firstclick = resultSet.getInt(5) == 0;
            Variables.enemyButton3firstclick = resultSet.getInt(6) == 0;
            Variables.enemy_card1 = resultSet.getInt(7);
            Variables.enemy_card2 = resultSet.getInt(8);
            Variables.enemy_card3 = resultSet.getInt(9);
            Variables.enemy_table_card = resultSet.getInt(10);
            Variables.enemy_points = resultSet.getInt(11);

            //Player
            Variables.playerButton1Pressed = resultSet.getInt(12) == 0;
            Variables.playerButton2Pressed = resultSet.getInt(13) == 0;
            Variables.playerButton3Pressed = resultSet.getInt(14) == 0;
            Variables.playerButton1firstclick = resultSet.getInt(15) == 0;
            Variables.playerButton2firstclick = resultSet.getInt(16) == 0;
            Variables.playerButton3firstclick = resultSet.getInt(17) == 0;
            Variables.player_card1 = resultSet.getInt(18);
            Variables.player_card2 = resultSet.getInt(19);
            Variables.player_card3 = resultSet.getInt(20);
            Variables.player_table_card = resultSet.getInt(21);
            Variables.player_points = resultSet.getInt(22);

            //Gameplay
            Variables.usedCardsCount = resultSet.getInt(23);
            Variables.gameFinished = resultSet.getInt(24) == 0;
            Variables.playerTurn = resultSet.getInt(25) == 0;
            Variables.playerLastDropped = resultSet.getInt(26) == 0;
            Variables.playerWins = resultSet.getInt(27) == 0;
            Variables.playerFirstDrop = resultSet.getInt(28) == 0;
            Variables.winnerType = resultSet.getString(29);
            Variables.droppedCards = resultSet.getInt(30);
            Variables.droppedLast = resultSet.getInt(31);

            //used cards from 0 to 39
            Variables.userCardsArrey[0] = resultSet.getInt(32);
            Variables.userCardsArrey[1] = resultSet.getInt(33);
            Variables.userCardsArrey[2] = resultSet.getInt(34);
            Variables.userCardsArrey[3] = resultSet.getInt(35);
            Variables.userCardsArrey[4] = resultSet.getInt(36);
            Variables.userCardsArrey[5] = resultSet.getInt(37);
            Variables.userCardsArrey[6] = resultSet.getInt(38);
            Variables.userCardsArrey[7] = resultSet.getInt(39);
            Variables.userCardsArrey[8] = resultSet.getInt(40);
            Variables.userCardsArrey[9] = resultSet.getInt(41);
            Variables.userCardsArrey[10] = resultSet.getInt(42);
            Variables.userCardsArrey[11] = resultSet.getInt(43);
            Variables.userCardsArrey[12] = resultSet.getInt(44);
            Variables.userCardsArrey[13] = resultSet.getInt(45);
            Variables.userCardsArrey[14] = resultSet.getInt(46);
            Variables.userCardsArrey[15] = resultSet.getInt(47);
            Variables.userCardsArrey[16] = resultSet.getInt(48);
            Variables.userCardsArrey[17] = resultSet.getInt(49);
            Variables.userCardsArrey[18] = resultSet.getInt(50);
            Variables.userCardsArrey[19] = resultSet.getInt(51);
            Variables.userCardsArrey[20] = resultSet.getInt(52);
            Variables.userCardsArrey[21] = resultSet.getInt(53);
            Variables.userCardsArrey[22] = resultSet.getInt(54);
            Variables.userCardsArrey[23] = resultSet.getInt(55);
            Variables.userCardsArrey[24] = resultSet.getInt(56);
            Variables.userCardsArrey[25] = resultSet.getInt(57);
            Variables.userCardsArrey[26] = resultSet.getInt(58);
            Variables.userCardsArrey[27] = resultSet.getInt(59);
            Variables.userCardsArrey[28] = resultSet.getInt(60);
            Variables.userCardsArrey[29] = resultSet.getInt(61);
            Variables.userCardsArrey[30] = resultSet.getInt(62);
            Variables.userCardsArrey[31] = resultSet.getInt(63);
            Variables.userCardsArrey[32] = resultSet.getInt(64);
            Variables.userCardsArrey[33] = resultSet.getInt(65);
            Variables.userCardsArrey[34] = resultSet.getInt(66);
            Variables.userCardsArrey[35] = resultSet.getInt(67);
            Variables.userCardsArrey[36] = resultSet.getInt(68);
            Variables.userCardsArrey[37] = resultSet.getInt(69);
            Variables.userCardsArrey[38] = resultSet.getInt(70);
            Variables.userCardsArrey[39] = resultSet.getInt(71);
        }
    }

    public static void SendData() {
        String message;
        message = Variables.enemyButton1Pressed + "-" +
                Variables.enemyButton2Pressed + "-" +
                Variables.enemyButton3Pressed + "-" +
                Variables.enemyButton1firstclick + "-" +
                Variables.enemyButton2firstclick + "-" +
                Variables.enemyButton3firstclick + "-" +
                Variables.enemy_card1 + "-" +
                Variables.enemy_card2 + "-" +
                Variables.enemy_card3 + "-" +
                Variables.enemy_table_card + "-" +
                Variables.enemy_points + "-" +
                Variables.playerButton1Pressed + "-" +
                Variables.playerButton2Pressed + "-" +
                Variables.playerButton3Pressed + "-" +
                Variables.playerButton1firstclick + "-" +
                Variables.playerButton2firstclick + "-" +
                Variables.playerButton3firstclick + "-" +
                Variables.player_card1 + "-" +
                Variables.player_card2 + "-" +
                Variables.player_card3 + "-" +
                Variables.player_table_card + "-" +
                Variables.player_points + "-" +
                Variables.usedCardsCount + "-" +
                Variables.gameFinished:1
    }


    // On Create
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_online_game_play);
        setContentView(R.layout.activity_game_play);

        // Internet Permissions
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        // DATABASE
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(CLASSES);
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();   // ERROR
        }

        // ID declarations
        setButtonsID();

        // Game Data
        Methodes.CreateDeck();
        Methodes.setType();

        switch (Variables.winnerType) {
            case "c":
                cardWinnerTypeLabel.setText("   Main match card: HEART ♥");
                break;
            case "f":
                cardWinnerTypeLabel.setText("   Main match card: CLUBS ♣");
                break;
            case "p":
                cardWinnerTypeLabel.setText("   Main match card: SPADES ♠");
                break;
            case "r":
                cardWinnerTypeLabel.setText("   Main match card: DIAMONDS ♦");
                break;
        }

        // Declare Buttons \\
        setButtonListeners();

        // Set Image Button
        setButtonsImage();
    }

    private void setButtonsID(){
        cardWinnerTypeLabel = findViewById(R.id.WinnerTypeLabel);
        playerPointsLabel = findViewById(R.id.PlayerPointsLabel);
        enemyPointsLabel = findViewById(R.id.EnemyPointsLabel);
        enemyTableCard = findViewById(R.id.enemyDroppedCard);
        pauseButton = (ImageButton)findViewById(R.id.pauseButton);
        playerCard1 = findViewById(R.id.playerCard1);
        playerCard2 = findViewById(R.id.playerCard2);
        playerCard3 = findViewById(R.id.playerCard3);
        playerTableCard = findViewById(R.id.playerDroppedCard);
        enemyCard1 = findViewById(R.id.enemyCard1);
        enemyCard2 = findViewById(R.id.enemyCard2);
        enemyCard3 = findViewById(R.id.enemyCard3);
    }

    private void setButtonListeners(){

        playerCard1.setOnClickListener(view -> {
            if(Variables.player_points > 60){
                GameFinishedActivity.PlayerWins = true;
                openGameFinishedActivity();
            }
            else if (Variables.enemy_points > 60){
                GameFinishedActivity.PlayerWins = false;
                openGameFinishedActivity();
            }
            else {
                if (Variables.player_number == 1) on_playerCard_clicked_player(1);
                else if (Variables.player_number == 2) on_playerCard_clicked_enemy(1);
            }
        });

        playerCard2.setOnClickListener(view -> {
            if(Variables.player_points > 60){
                GameFinishedActivity.PlayerWins = true;
                openGameFinishedActivity();
            }
            else if (Variables.enemy_points > 60){
                GameFinishedActivity.PlayerWins = false;
                openGameFinishedActivity();
            }
            else {
                if (Variables.player_number == 1) on_playerCard_clicked_player(2);
                else if (Variables.player_number == 2) on_playerCard_clicked_enemy(2);
            }
        });

        playerCard3.setOnClickListener(view -> {
            if(Variables.player_points > 60){
                GameFinishedActivity.PlayerWins = true;
                openGameFinishedActivity();
            }
            else if (Variables.enemy_points > 60){
                GameFinishedActivity.PlayerWins = false;
                openGameFinishedActivity();
            }
            else {
                if (Variables.player_number == 1) on_playerCard_clicked_player(3);
                else if (Variables.player_number == 2) on_playerCard_clicked_enemy(3);
            }
        });

        pauseButton.setOnClickListener(view -> pauseClicked());
    }

    private void setButtonsImage(){
        playerCard1.setImageResource(Variables.DeckCards[Variables.player_card1].cardIcon);
        playerCard2.setImageResource(Variables.DeckCards[Variables.player_card2].cardIcon);
        playerCard3.setImageResource(Variables.DeckCards[Variables.player_card3].cardIcon);
        playerTableCard.setImageResource(Variables.DeckCards[Variables.player_table_card].cardIcon);
        enemyCard1.setImageResource(Variables.DeckCards[Variables.enemy_card1].cardIcon);
        enemyCard2.setImageResource(Variables.DeckCards[Variables.enemy_card2].cardIcon);
        enemyCard3.setImageResource(Variables.DeckCards[Variables.enemy_card3].cardIcon);
        enemyTableCard.setImageResource(Variables.DeckCards[Variables.enemy_table_card].cardIcon);
    }

    private void pauseClicked(){
        // File Save Data
        try {
            if(Methodes.SaveDataFile()) Toast.makeText(this, "outputDataStream opened!", Toast.LENGTH_SHORT).show();   //testing
            else Toast.makeText(this, getString(R.string.unable), Toast.LENGTH_SHORT).show();
            openPauseActivity();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void openPauseActivity(){
        Intent intent = new Intent(this, PauseActivity.class);
        startActivity(intent);
    }

    public void openGameFinishedActivity(){
        Intent intent = new Intent(this, GameFinishedActivity.class);
        startActivity(intent);
    }

    //  //  // Methods \\  \\  \\

    //  //  // Player VS Player - Online \\  \\  \\

    @SuppressLint("SetTextI18n")
    void on_playerCard_clicked_player(int buttonPressed) {
        //finish conditions
        if (Variables.usedCardsCount == 39) Variables.gameFinished = true;

        // Local Variables
        boolean playerButtonfirstclick = false, playerButtonPressed = false;
        int player_card = 0;

        if (buttonPressed == 1) {
            playerButtonfirstclick = Variables.playerButton1firstclick;
            player_card = Variables.player_card1;
            playerButtonPressed = Variables.playerButton1Pressed;
        }
        if (buttonPressed == 2) {
            playerButtonfirstclick = Variables.playerButton2firstclick;
            player_card = Variables.player_card2;
            playerButtonPressed = Variables.playerButton2Pressed;
        }
        if (buttonPressed == 3) {
            playerButtonfirstclick = Variables.playerButton3firstclick;
            player_card = Variables.player_card3;
            playerButtonPressed = Variables.playerButton3Pressed;
        }

        //playing conditions
        if (playerButtonPressed && !Variables.gameFinished && Variables.playerTurn) {
            if (playerButtonfirstclick) {
                // Random number between low and high
                Variables.randCard = Methodes.getRand(1, 40);
                boolean found;
                do {
                    found = false;
                    Variables.randCard = Methodes.getRand(1, 40);
                    for (int i = 0; i <= Variables.usedCardsCount; i++)
                        if (Variables.randCard == Variables.userCardsArrey[i]) {
                            found = true;
                            break;
                        }
                } while (found);
                player_card = Variables.randCard;
                // Generated ok

                //Setting Hand Card to generated one
                Variables.userCardsArrey[Variables.usedCardsCount] = player_card;
                Variables.usedCardsCount++;
                if (buttonPressed == 1) {
                    playerCard1.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed card 1!", Toast.LENGTH_SHORT).show();
                }
                if (buttonPressed == 2) {
                    playerCard2.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed card 2!", Toast.LENGTH_SHORT).show();
                }
                if (buttonPressed == 3) {
                    playerCard3.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed card 3!", Toast.LENGTH_SHORT).show();
                }
                playerButtonfirstclick = false;
                //End First Click
            } else {
                // Dropping Card
                Variables.playerDroppedCard = Variables.DeckCards[player_card];
                //ui->playerTableCard->setIcon(playerDroppedCard->cardIcon);   // c++
                if (buttonPressed == 1) {
                    playerTableCard.setImageResource(Variables.playerDroppedCard.cardIcon);
                    //Toast.makeText(this, "You dropped card 1!", Toast.LENGTH_SHORT).show();
                }
                if (buttonPressed == 2) {
                    playerTableCard.setImageResource(Variables.playerDroppedCard.cardIcon);
                    //Toast.makeText(this, "You dropped card 2!", Toast.LENGTH_SHORT).show();
                }
                if (buttonPressed == 3) {
                    playerTableCard.setImageResource(Variables.playerDroppedCard.cardIcon);
                    //Toast.makeText(this, "You dropped card 3!", Toast.LENGTH_SHORT).show();
                }
                // END Dropping Card
                //if(Variables.gameStyle == 1) {
                //try {
                //Thread.sleep(2000);
                //Toast.makeText(this, "Waiting 2s", Toast.LENGTH_SHORT).show();   //testing
                //} catch (InterruptedException e) {
                // e.printStackTrace();
                //}
                //}

                // Setting Hand After Drop
                boolean found;
                do {
                    found = false;
                    Variables.randCard = Methodes.getRand(1, 40);
                    for (int i = 0; i <= Variables.usedCardsCount; i++)
                        if (Variables.randCard == Variables.userCardsArrey[i]) {
                            found = true;
                            break;
                        }
                } while (found);
                player_card = Variables.randCard;
                // Generating OK

                //Setting Hand
                Variables.userCardsArrey[Variables.usedCardsCount] = Variables.player_card3;
                Variables.usedCardsCount++;

                if (buttonPressed == 1) {
                    playerCard1.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed card 1!", Toast.LENGTH_SHORT).show();
                }
                if (buttonPressed == 2) {
                    playerCard2.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed card 2!", Toast.LENGTH_SHORT).show();
                }
                if (buttonPressed == 3) {
                    playerCard3.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed card 3!", Toast.LENGTH_SHORT).show();
                }
                // END Setting Hand

                // GAMEPLAY
                if (Variables.playerFirstDrop) {
                    // FIRST CARD DROPPED
                    Variables.droppedCards = 1;
                    Variables.playerFirstDrop = false;
                    Variables.playerTurn = false;
                } else {
                    // TODO UI
                    Variables.playerLastDropped = true;
                    Variables.droppedCards++;
                    if (Variables.droppedCards == 2) {
                        Variables.droppedLast = 1;
                        Variables.playerWins = Methodes.setWinner();
                        playerPointsLabel.setText("Player points: " + Variables.player_points + "   ");
                        enemyPointsLabel.setText("Enemy points: " + Variables.enemy_points + "   ");
                        if (Variables.playerWins) {
                            Variables.playerTurn = true;
                            Toast.makeText(this, "Player Wins!", Toast.LENGTH_SHORT).show();
                            //->lastWinLabel->setText("Last win: PLAYER");
                            //ui->playerTurnLabel->setText("Turn: PLAYER");
                        } else {
                            Variables.playerTurn = false;
                            Toast.makeText(this, "Enemy Wins!", Toast.LENGTH_SHORT).show();
                            //ui->lastWinLabel->setText("Last win: ENEMY");
                            //ui->playerTurnLabel->setText("Turn: ENEMY");
                        }
                        Variables.droppedCards = 0;
                    } else {   // Dropped cards == 1
                        Variables.playerTurn = false;
                        //ui->lastWinLabel->setText("Last win: PLAYER");
                        //ui->playerTurnLabel->setText("Turn: ENEMY");
                    }
                }
            }
        } else {
            playerButtonPressed = true;
            if (Variables.gameFinished)
                Toast.makeText(this, "No more cards!", Toast.LENGTH_SHORT).show();   //testing
        }

        // Global Variables
        if (buttonPressed == 1) {
            Variables.playerButton1firstclick = playerButtonfirstclick;
            Variables.player_card1 = player_card;
            Variables.playerButton1Pressed = playerButtonPressed;
        }
        if (buttonPressed == 2) {
            Variables.playerButton2firstclick = playerButtonfirstclick;
            Variables.player_card2 = player_card;
            Variables.playerButton2Pressed = playerButtonPressed;
        }
        if (buttonPressed == 3) {
            Variables.playerButton3firstclick = playerButtonfirstclick;
            Variables.player_card3 = player_card;
            Variables.playerButton3Pressed = playerButtonPressed;
        }
    }

    @SuppressLint("SetTextI18n")
    void on_playerCard_clicked_enemy(int buttonPressed) {
        //finish conditions
        if (Variables.usedCardsCount == 39) Variables.gameFinished = true;

        // Local Variables
        boolean playerButtonfirstclick = false, playerButtonPressed = false;
        int player_card = 0;
        if(buttonPressed == 1) {
            playerButtonfirstclick = Variables.enemyButton1firstclick;
            player_card = Variables.enemy_card1;
            playerButtonPressed = Variables.enemyButton1Pressed;
        }
        if(buttonPressed == 2) {
            playerButtonfirstclick = Variables.enemyButton2firstclick;
            player_card = Variables.enemy_card2;
            playerButtonPressed = Variables.enemyButton2Pressed;
        }
        if(buttonPressed == 3) {
            playerButtonfirstclick = Variables.enemyButton3firstclick;
            player_card = Variables.enemy_card3;
            playerButtonPressed = Variables.enemyButton3Pressed;
        }

        //playing conditions
        if(playerButtonPressed && !Variables.gameFinished && !Variables.playerTurn){
            if(playerButtonfirstclick){
                // Random number between low and high
                Variables.randCard = Methodes.getRand(1,40);

                boolean found;
                do {
                    found = false;
                    Variables.randCard = Methodes.getRand(1,40);
                    for(int i = 0; i <= Variables.usedCardsCount; i++)
                        if (Variables.randCard == Variables.userCardsArrey[i]) {
                            found = true;
                            break;
                        }
                } while (found);

                player_card = Variables.randCard;
                Variables.userCardsArrey[Variables.usedCardsCount] = player_card;
                Variables.usedCardsCount++;
                if(buttonPressed == 1) {
                    playerCard1.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed enemy card 1!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 2) {
                    playerCard2.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed enemy card 2!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 3) {
                    playerCard3.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed enemy card 3!", Toast.LENGTH_SHORT).show();
                }
                playerButtonfirstclick = false;
            }
            else{
                // dropping
                Variables.enemyDroppedCard = Variables.DeckCards[player_card];
                if(buttonPressed == 1) playerTableCard.setImageResource(Variables.enemyDroppedCard.cardIcon);
                if(buttonPressed == 2) playerTableCard.setImageResource(Variables.enemyDroppedCard.cardIcon);
                if(buttonPressed == 3) playerTableCard.setImageResource(Variables.enemyDroppedCard.cardIcon);

                // setting hand
                boolean found;
                do {
                    found = false;
                    Variables.randCard = Methodes.getRand(1,40);
                    for(int i = 0; i <= Variables.usedCardsCount; i++)
                        if (Variables.randCard == Variables.userCardsArrey[i]) {
                            found = true;
                            break;
                        }
                } while (found);

                player_card = Variables.randCard;
                Variables.userCardsArrey[Variables.usedCardsCount] = player_card;
                Variables.usedCardsCount++;
                if(buttonPressed == 1) playerTableCard.setImageResource(Variables.enemyDroppedCard.cardIcon);
                if(buttonPressed == 2) playerTableCard.setImageResource(Variables.enemyDroppedCard.cardIcon);
                if(buttonPressed == 3) playerTableCard.setImageResource(Variables.enemyDroppedCard.cardIcon);

                Variables.droppedCards++;
                if(Variables.droppedCards == 2){
                    Variables.droppedLast = 2;
                    Variables.playerWins = Methodes.setWinner();
                    playerPointsLabel.setText("Player points: " + Variables.player_points + "   ");
                    enemyPointsLabel.setText("Enemy points: " + Variables.enemy_points + "   ");
                    if (Variables.playerWins) {
                        Variables.playerTurn = true;
                        Toast.makeText(this, "Player Wins!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Variables.playerTurn = false;
                        Toast.makeText(this, "Enemy Wins!", Toast.LENGTH_SHORT).show();
                    }
                    Variables.droppedCards = 0;
                }
                else Variables.playerTurn = true;
            }
        }
        else {
            playerButtonPressed = true;
            if (Variables.gameFinished) Toast.makeText(this, "No more cards!", Toast.LENGTH_SHORT).show();   // testing
        }

        // Global Variables
        if(buttonPressed == 1) {
            Variables.enemyButton1firstclick = playerButtonfirstclick;
            Variables.enemy_card1 = player_card;
            Variables.enemyButton1Pressed = playerButtonPressed;
        }
        if(buttonPressed == 2) {
            Variables.enemyButton2firstclick = playerButtonfirstclick;
            Variables.enemy_card2 = player_card;
            Variables.enemyButton2Pressed = playerButtonPressed;
        }
        if(buttonPressed == 3) {
            Variables.enemyButton3firstclick = playerButtonfirstclick;
            Variables.enemy_card3 = player_card;
            Variables.enemyButton3Pressed = playerButtonPressed;
        }
    }

    // EOF - End Of File
}