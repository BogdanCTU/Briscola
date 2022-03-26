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

public class GamePlay extends AppCompatActivity {

    //My IP   192.168.100.29 / 192.168.100.6

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
    private static String IP = "192.168.100.29";
    private static String PORT = "1433";
    private static String CLASSES = "net.sourceforge.jtds.jdbc.Driver";
    private static String DATABASE = "BriscolaDatabase";
    private static String USERNAME = "";
    private static String PASSWORD = "";
    private static String URL = "jdbc:jtds:sqlserver://" + IP + ":" + PORT + "/" + DATABASE;

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

    // On Create
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        // Internet Permissions
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        // DATABASE
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(CLASSES);
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // ERROR
        } catch (SQLException e){
            e.printStackTrace();
            // FAILURE
        }

        // ID declarations
        setButtonsID();

        // Game Data
        Methodes.CreateDeck();
        Methodes.setType();

        // Load Data
        // Prefs Not Working - :(
        //GameData = getSharedPreferences(PREFS_NAME,0);
        //SharedPreferences sp = getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        try {
            openFileOutputO();
            //openFileInputI();   // Load Data
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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

        // Player VS AI
        if(Variables.gameStyle == 1) set_EnemyCards();
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
            else on_playerCard_clicked(1);
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
            else on_playerCard_clicked(2);
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
            else on_playerCard_clicked(3);
        });

        if(Variables.gameStyle == 0) {
            enemyCard1.setOnClickListener(view -> {
                if(Variables.player_points > 60){
                    GameFinishedActivity.PlayerWins = true;
                    openGameFinishedActivity();
                }
                else if (Variables.enemy_points > 60){
                    GameFinishedActivity.PlayerWins = false;
                    openGameFinishedActivity();
                }
                else on_enemyCard_clicked(1);
            });
            enemyCard2.setOnClickListener(view -> {
                if(Variables.player_points > 60){
                    GameFinishedActivity.PlayerWins = true;
                    openGameFinishedActivity();
                }
                else if (Variables.enemy_points > 60){
                    GameFinishedActivity.PlayerWins = false;
                    openGameFinishedActivity();
                }
                else on_enemyCard_clicked(2);
            });
            enemyCard3.setOnClickListener(view -> {
                if(Variables.player_points > 60){
                    GameFinishedActivity.PlayerWins = true;
                    openGameFinishedActivity();
                }
                else if (Variables.enemy_points > 60){
                    GameFinishedActivity.PlayerWins = false;
                    openGameFinishedActivity();
                }
                else on_enemyCard_clicked(3);
            });
        }

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

    //   https://www.youtube.com/watch?v=C0TcZhpSCNQ
    // TODO do the same for FileOutputStream with bytes

    // FILE
    private void openFileOutputO() throws FileNotFoundException {   // Save Data
        outputFile = new OutputStreamWriter(openFileOutput("Data.txt",MODE_PRIVATE));
    }

    private void openFileInputI() throws FileNotFoundException {   // Load Data
        inputFile = new InputStreamReader(openFileInput("Data.txt"));
    }

    /* // Dialog -> NOT WORKING
    public void openPauseDialog(){
        PauseDialog pd = new PauseDialog();
        pd.show(getSupportFragmentManager(),"");
    }
    */

        //  //  // Methods \\  \\  \\

    //  //  // Player VS Player \\  \\  \\

    @SuppressLint("SetTextI18n")
    void on_playerCard_clicked(int buttonPressed) {
        if(Variables.gameStyle == 1 && !Variables.playerTurn) enemy_Turn();
        else {
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
                    if (Variables.gameStyle == 1) enemy_Turn();
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
    }

    @SuppressLint("SetTextI18n")
    void on_enemyCard_clicked(int buttonPressed) {
        //finish conditions
        if (Variables.usedCardsCount == 39) Variables.gameFinished = true;

        // Local Variables
        boolean enemyButtonfirstclick = false, enemyButtonPressed = false;
        int enemy_card = 0;
        if(buttonPressed == 1) {
            enemyButtonfirstclick = Variables.enemyButton1firstclick;
            enemy_card = Variables.enemy_card1;
            enemyButtonPressed = Variables.enemyButton1Pressed;
        }
        if(buttonPressed == 2) {
            enemyButtonfirstclick = Variables.enemyButton2firstclick;
            enemy_card = Variables.enemy_card2;
            enemyButtonPressed = Variables.enemyButton2Pressed;
        }
        if(buttonPressed == 3) {
            enemyButtonfirstclick = Variables.enemyButton3firstclick;
            enemy_card = Variables.enemy_card3;
            enemyButtonPressed = Variables.enemyButton3Pressed;
        }

        //playing conditions
        if(enemyButtonPressed && !Variables.gameFinished && !Variables.playerTurn){
            if(enemyButtonfirstclick){
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

                enemy_card = Variables.randCard;
                Variables.userCardsArrey[Variables.usedCardsCount] = enemy_card;
                Variables.usedCardsCount++;
                if(buttonPressed == 1) {
                    enemyCard1.setImageResource(Variables.DeckCards[enemy_card].cardIcon);
                    //Toast.makeText(this, "Changed enemy card 1!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 2) {
                    enemyCard2.setImageResource(Variables.DeckCards[enemy_card].cardIcon);
                    //Toast.makeText(this, "Changed enemy card 2!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 3) {
                    enemyCard3.setImageResource(Variables.DeckCards[enemy_card].cardIcon);
                    //Toast.makeText(this, "Changed enemy card 3!", Toast.LENGTH_SHORT).show();
                }
                enemyButtonfirstclick = false;
            }
            else{
                // dropping
                Variables.enemyDroppedCard = Variables.DeckCards[enemy_card];
                // ui->enemyTableCard->setIcon(enemyDroppedCard->cardIcon);
                if(buttonPressed == 1) {
                    enemyTableCard.setImageResource(Variables.enemyDroppedCard.cardIcon);
                    //Toast.makeText(this, "Enemy dropped card 1!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 2) {
                    enemyTableCard.setImageResource(Variables.enemyDroppedCard.cardIcon);
                    //Toast.makeText(this, "Enemy dropped card 2!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 3) {
                    enemyTableCard.setImageResource(Variables.enemyDroppedCard.cardIcon);
                    //Toast.makeText(this, "Enemy dropped card 3!", Toast.LENGTH_SHORT).show();
                }

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

                enemy_card = Variables.randCard;
                Variables.userCardsArrey[Variables.usedCardsCount] = enemy_card;
                Variables.usedCardsCount++;
                if(buttonPressed == 1) {
                    enemyCard1.setImageResource(Variables.DeckCards[enemy_card].cardIcon);
                    //Toast.makeText(this, "Changed enemy card 1!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 2) {
                    enemyCard2.setImageResource(Variables.DeckCards[enemy_card].cardIcon);
                    //Toast.makeText(this, "Changed enemy card 2!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 3) {
                    enemyCard3.setImageResource(Variables.DeckCards[enemy_card].cardIcon);
                    //Toast.makeText(this, "Changed enemy card 3!", Toast.LENGTH_SHORT).show();
                }

                Variables.droppedCards++;
                if(Variables.droppedCards == 2){
                    Variables.droppedLast = 2;
                    Variables.playerWins = Methodes.setWinner();
                    playerPointsLabel.setText("Player points: " + Variables.player_points + "   ");
                    enemyPointsLabel.setText("Enemy points: " + Variables.enemy_points + "   ");
                    if (Variables.playerWins) {
                        Variables.playerTurn = true;
                        Toast.makeText(this, "Player Wins!", Toast.LENGTH_SHORT).show();
                        //ui->lastWinLabel->setText("Last win: PLAYER");
                        //ui->playerTurnLabel->setText("Turn: PLAYER");
                    }
                    else {
                        Variables.playerTurn = false;
                        Toast.makeText(this, "Enemy Wins!", Toast.LENGTH_SHORT).show();
                        //ui->lastWinLabel->setText("Last win: ENEMY");
                        //ui->playerTurnLabel->setText("Turn: ENEMY");
                    }
                    Variables.droppedCards = 0;
                }
                else{
                    //ui->lastWinLabel->setText("Last win: ENEMY");
                    //ui->playerTurnLabel->setText("Turn: PLAYER");
                    Variables.playerTurn = true;
                }
            }
        }
        else {
            enemyButtonPressed = true;
            if (Variables.gameFinished) Toast.makeText(this, "No more cards!", Toast.LENGTH_SHORT).show();   //testing
        }

        // Global Variables
        if(buttonPressed == 1) {
            Variables.enemyButton1firstclick = enemyButtonfirstclick;
            Variables.enemy_card1 = enemy_card;
            Variables.enemyButton1Pressed = enemyButtonPressed;
        }
        if(buttonPressed == 2) {
            Variables.enemyButton2firstclick = enemyButtonfirstclick;
            Variables.enemy_card2 = enemy_card;
            Variables.enemyButton2Pressed = enemyButtonPressed;
        }
        if(buttonPressed == 3) {
            Variables.enemyButton3firstclick = enemyButtonfirstclick;
            Variables.enemy_card3 = enemy_card;
            Variables.enemyButton3Pressed = enemyButtonPressed;
        }
    }

    //  //  // Player VS Computer \\  \\  \\

    private void set_EnemyCards(){

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
        Variables.enemy_card1 = Variables.randCard;
        Variables.userCardsArrey[Variables.usedCardsCount] = Variables.randCard;
        Variables.usedCardsCount++;

        do {
            found = false;
            Variables.randCard = Methodes.getRand(1,40);
            for(int i = 0; i <= Variables.usedCardsCount; i++)
                if (Variables.randCard == Variables.userCardsArrey[i]) {
                    found = true;
                    break;
                }
        } while (found);
        Variables.enemy_card2 = Variables.randCard;
        Variables.userCardsArrey[Variables.usedCardsCount] = Variables.randCard;
        Variables.usedCardsCount++;

        do {
            found = false;
            Variables.randCard = Methodes.getRand(1,40);
            for(int i = 0; i <= Variables.usedCardsCount; i++)
                if (Variables.randCard == Variables.userCardsArrey[i]) {
                    found = true;
                    break;
                }
        } while (found);
        Variables.enemy_card3 = Variables.randCard;
        Variables.userCardsArrey[Variables.usedCardsCount] = Variables.randCard;
        Variables.usedCardsCount++;

        enemyCard1.setImageResource(Variables.DeckCards[Variables.enemy_card1].cardIcon);
        enemyCard2.setImageResource(Variables.DeckCards[Variables.enemy_card2].cardIcon);
        enemyCard3.setImageResource(Variables.DeckCards[Variables.enemy_card3].cardIcon);

    }

    @SuppressLint("SetTextI18n")
    private void enemy_Turn(){
        //finish conditions
        if (Variables.usedCardsCount == 39) Variables.gameFinished = true;

        //playing conditions
        if(!Variables.gameFinished && !Variables.playerTurn){

            // Local Variables
            int random_EnemyCard = Methodes.getRand(1,3);
            //if (random_EnemyCard == 1)
            //if (random_EnemyCard == 2)
            //if (random_EnemyCard == 3)

                // dropping
            if (random_EnemyCard == 1) Variables.enemyDroppedCard = Variables.DeckCards[Variables.enemy_card1];
            if (random_EnemyCard == 2) Variables.enemyDroppedCard = Variables.DeckCards[Variables.enemy_card2];
            if (random_EnemyCard == 3) Variables.enemyDroppedCard = Variables.DeckCards[Variables.enemy_card3];
            enemyTableCard.setImageResource(Variables.enemyDroppedCard.cardIcon);
            //try {
                //Thread.sleep(2000);
                //Toast.makeText(this, "Waiting 2s", Toast.LENGTH_SHORT).show();   //testing
            //} catch (InterruptedException e) {
               //e.printStackTrace();
            //}

                // setting hand
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

            // Setting variable and Image
            if (random_EnemyCard == 1) {
                Variables.enemy_card1 = Variables.randCard;
                enemyCard1.setImageResource(Variables.DeckCards[Variables.enemy_card1].cardIcon);
            }
            if (random_EnemyCard == 2) {
                Variables.enemy_card2 = Variables.randCard;
                enemyCard2.setImageResource(Variables.DeckCards[Variables.enemy_card2].cardIcon);
            }
            if (random_EnemyCard == 3) {
                Variables.enemy_card3 = Variables.randCard;
                enemyCard3.setImageResource(Variables.DeckCards[Variables.enemy_card3].cardIcon);
            }
            Variables.userCardsArrey[Variables.usedCardsCount] = Variables.randCard;
            Variables.usedCardsCount++;

            Variables.droppedCards++;
            if (Variables.droppedCards == 2) {
                Variables.droppedLast = 2;
                Variables.playerWins = Methodes.setWinner();
                playerPointsLabel.setText("Player points: " + Variables.player_points + "   ");
                enemyPointsLabel.setText("Enemy points: " + Variables.enemy_points + "   ");
                if (Variables.playerWins) {
                    Variables.playerTurn = true;
                    Toast.makeText(this, "Player Wins!", Toast.LENGTH_SHORT).show();
                } else {
                    Variables.playerTurn = false;
                    Toast.makeText(this, "Enemy Wins!", Toast.LENGTH_SHORT).show();
                }
                Variables.droppedCards = 0;
            } else {
                Variables.playerTurn = true;
            }
        }
        else{
            enemyTableCard.setVisibility(View.INVISIBLE);
        }
        if (!Variables.playerTurn) set_EnemyCards();
    }

    /*
    public void PausePopup(View view) {
        // Buttons Instantiate

        Button resumeButton;
        Button restartButton;

        // Dialog
        pauseDialog.setContentView(R.layout.pause_popup);
        resumeButton = (Button)findViewById(R.id.ResumeButton);


        pauseDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pauseDialog.show();
    }
     */

    // EOF - End Of File
}