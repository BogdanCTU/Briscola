package com.example.briscolagame;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class GamePlay extends AppCompatActivity {

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
    public static final String PREFS_NAME = "GameplayData";
    public static SharedPreferences GameData;
    public static SharedPreferences sp;

    // On Create

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        // ID declarations
        setButtonsID();

        // Game Data
        Methodes.CreateDeck();
        Methodes.setType();

        // Load Data
        // Prefs Not Working - :(
        //GameData = getSharedPreferences(PREFS_NAME,0);
        //SharedPreferences sp = getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        // File
        //Methodes.L();
        if(Methodes.LoadDataFile() == true)

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



        if(Variables.gameStyle == 1) set_EnemyCards();

        //pauseDialog = new Dialog(this);


        // Set Image Button
        setButtonsImage(){

        }


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

        playerCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Variables.gameStyle == 0) on_playerCard_clicked(1);
                else if(Variables.gameStyle == 1) {
                    on_playerCard_clicked(1);
                }
            }
        });

        playerCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on_playerCard_clicked(2);
            }
        });

        playerCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on_playerCard_clicked(3);
            }
        });

        enemyCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on_enemyCard_clicked(1);
            }
        });

        enemyCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on_enemyCard_clicked(2);
            }
        });

        enemyCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on_enemyCard_clicked(3);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseClicked();
            }
        });

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
        //Methodes.SaveGameData();   // Saving Game Data.txt
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

    /* // Dialog -> NOT WORKING
    public void openPauseDialog(){
        PauseDialog pd = new PauseDialog();
        pd.show(getSupportFragmentManager(),"");
    }
    */

    // Methods

    //  //  // Player VS Player \\  \\  \\

    @SuppressLint("SetTextI18n")
    void on_playerCard_clicked(int buttonPressed) {
        //finish conditions
        if (Variables.usedCardsCount == 39) Variables.gameFinished = true;

        // Local Variables
        boolean playerButtonfirstclick = false, playerButtonPressed = false;
        int player_card= 0;

        if(buttonPressed == 1) {
            playerButtonfirstclick = Variables.playerButton1firstclick;
            player_card = Variables.player_card1;
            playerButtonPressed = Variables.playerButton1Pressed;
        }
        if(buttonPressed == 2) {
            playerButtonfirstclick = Variables.playerButton2firstclick;
            player_card = Variables.player_card2;
            playerButtonPressed = Variables.playerButton2Pressed;
        }
        if(buttonPressed == 3) {
            playerButtonfirstclick = Variables.playerButton3firstclick;
            player_card = Variables.player_card3;
            playerButtonPressed = Variables.playerButton3Pressed;
        }

        //playing conditions
        if(playerButtonPressed && !Variables.gameFinished && Variables.playerTurn){
            if(playerButtonfirstclick){

                // Random number between low and high
                Variables.randCard = Methodes.getRand(1,40);
                boolean found = false;
                do {
                    found = false;
                    Variables.randCard = Methodes.getRand(1,40);
                    for(int i = 0; i <= Variables.usedCardsCount; i++) if(Variables.randCard == Variables.userCardsArrey[i]) found = true;
                } while (found);
                player_card = Variables.randCard;
                // Generated ok

                //Setting Hand Card to generated one
                Variables.userCardsArrey[Variables.usedCardsCount] = player_card;
                Variables.usedCardsCount++;
                if(buttonPressed == 1) {
                    playerCard1.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed card 1!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 2) {
                    playerCard2.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed card 2!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 3) {
                    playerCard3.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed card 3!", Toast.LENGTH_SHORT).show();
                }
                playerButtonfirstclick = false;
                //End First Click
            }
            else {
                // Dropping Card
                Variables.playerDroppedCard = Variables.DeckCards[player_card];
                //ui->playerTableCard->setIcon(playerDroppedCard->cardIcon);   // c++
                if(buttonPressed == 1) {
                    playerTableCard.setImageResource(Variables.playerDroppedCard.cardIcon);
                    //Toast.makeText(this, "You dropped card 1!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 2) {
                    playerTableCard.setImageResource(Variables.playerDroppedCard.cardIcon);
                    //Toast.makeText(this, "You dropped card 2!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 3) {
                    playerTableCard.setImageResource(Variables.playerDroppedCard.cardIcon);
                    //Toast.makeText(this, "You dropped card 3!", Toast.LENGTH_SHORT).show();
                }
                // END Dropping Card

                // Setting Hand After Drop
                boolean found = false;
                do {
                    found = false;
                    Variables.randCard = Methodes.getRand(1,40);
                    for(int i = 0; i <= Variables.usedCardsCount; i++) if(Variables.randCard == Variables.userCardsArrey[i]) found = true;
                } while (found);
                player_card = Variables.randCard;
                // Generating OK

                //Setting Hand
                Variables.userCardsArrey[Variables.usedCardsCount] = Variables.player_card3;
                Variables.usedCardsCount++;

                if(buttonPressed == 1) {
                    playerCard1.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed card 1!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 2) {
                    playerCard2.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed card 2!", Toast.LENGTH_SHORT).show();
                }
                if(buttonPressed == 3) {
                    playerCard3.setImageResource(Variables.DeckCards[player_card].cardIcon);
                    //Toast.makeText(this, "Changed card 3!", Toast.LENGTH_SHORT).show();
                }
                // END Setting Hand

                // GAMEPLAY
                if(Variables.playerFirstDrop) {
                    // FIRST CARD DROPPED
                    Variables.droppedCards = 1;
                    Variables.playerFirstDrop = false;
                    Variables.playerTurn = false;
                }
                else{
                    // TODO UI
                    Variables.playerLastDropped = true;
                    Variables.droppedCards++;
                    if(Variables.droppedCards == 2){
                        Variables.droppedLast = 1;
                        Variables.playerWins = Methodes.setWinner();
                        playerPointsLabel.setText("Player points: " + Variables.player_points + "   ");
                        enemyPointsLabel.setText("Enemy points: " + Variables.enemy_points + "   ");
                        if (Variables.playerWins) {
                            Variables.playerTurn = true;
                            Toast.makeText(this, "Player Wins!", Toast.LENGTH_SHORT).show();
                            //->lastWinLabel->setText("Last win: PLAYER");
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
                    else {   // Dropped cards == 1
                        Variables.playerTurn = false;
                        //ui->lastWinLabel->setText("Last win: PLAYER");
                        //ui->playerTurnLabel->setText("Turn: ENEMY");
                    }
                }
                if(Variables.gameStyle == 1) enemy_Turn();
            }
        }
        else {
            playerButtonPressed = true;
            if (Variables.gameFinished) Toast.makeText(this, "No more cards!", Toast.LENGTH_SHORT).show();   //testing
        }

        // Global Variables
        if(buttonPressed == 1) {
            Variables.playerButton1firstclick = playerButtonfirstclick;
            Variables.player_card1 = player_card;
            Variables.playerButton1Pressed = playerButtonPressed;
        }
        if(buttonPressed == 2) {
            Variables.playerButton2firstclick = playerButtonfirstclick;
            Variables.player_card2 = player_card;
            Variables.playerButton2Pressed = playerButtonPressed;
        }
        if(buttonPressed == 3) {
            Variables.playerButton3firstclick = playerButtonfirstclick;
            Variables.player_card3 = player_card;
            Variables.playerButton3Pressed = playerButtonPressed;
        }
    }

    void on_playerCard_released(int playerCard) {
        if(playerCard == 1) {
            if (!Variables.playerButton1Pressed) {
                playerCard1.setImageResource(Variables.DeckCards[0].cardIcon);
            }
        }
        if(playerCard == 2) {
            if (!Variables.playerButton2Pressed) {
                playerCard2.setImageResource(Variables.DeckCards[0].cardIcon);
            }
        }
        if(playerCard == 3) {
            if (!Variables.playerButton3Pressed) {
                playerCard3.setImageResource(Variables.DeckCards[0].cardIcon);
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

                boolean found = false;
                do {
                    found = false;
                    Variables.randCard = Methodes.getRand(1,40);
                    for(int i = 0; i <= Variables.usedCardsCount; i++) if(Variables.randCard == Variables.userCardsArrey[i]) found = true;
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
                boolean found = false;
                do {
                    found = false;
                    Variables.randCard = Methodes.getRand(1,40);
                    for(int i = 0; i <= Variables.usedCardsCount; i++) if(Variables.randCard == Variables.userCardsArrey[i]) found = true;
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
                    if (Variables.playerWins == true) {
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

        boolean found = false;
        do {
            found = false;
            Variables.randCard = Methodes.getRand(1,40);
            for(int i = 0; i <= Variables.usedCardsCount; i++) if(Variables.randCard == Variables.userCardsArrey[i]) found = true;
        } while (found);
        Variables.enemy_card1 = Variables.randCard;
        Variables.userCardsArrey[Variables.usedCardsCount] = Variables.randCard;
        Variables.usedCardsCount++;

        do {
            found = false;
            Variables.randCard = Methodes.getRand(1,40);
            for(int i = 0; i <= Variables.usedCardsCount; i++) if(Variables.randCard == Variables.userCardsArrey[i]) found = true;
        } while (found);
        Variables.enemy_card2 = Variables.randCard;
        Variables.userCardsArrey[Variables.usedCardsCount] = Variables.randCard;
        Variables.usedCardsCount++;

        do {
            found = false;
            Variables.randCard = Methodes.getRand(1,40);
            for(int i = 0; i <= Variables.usedCardsCount; i++) if(Variables.randCard == Variables.userCardsArrey[i]) found = true;
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
        try {
            Thread.sleep(2000);
            //Toast.makeText(this, "Waiting 2s", Toast.LENGTH_SHORT).show();   //testing
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        //finish conditions
        if (Variables.usedCardsCount == 39) Variables.gameFinished = true;

        // Local Variables
        int random_EnemyCard = Methodes.getRand(1,3);
        //if (random_EnemyCard == 1)
        //if (random_EnemyCard == 2)
        //if (random_EnemyCard == 3)

        // dropping
        if (random_EnemyCard == 1) Variables.enemyDroppedCard = Variables.DeckCards[Variables.enemy_card1];
        if (random_EnemyCard == 2) Variables.enemyDroppedCard = Variables.DeckCards[Variables.enemy_card1];
        if (random_EnemyCard == 3) Variables.enemyDroppedCard = Variables.DeckCards[Variables.enemy_card1];
        enemyTableCard.setImageResource(Variables.enemyDroppedCard.cardIcon);
        try {
            Thread.sleep(2000);
            Toast.makeText(this, "Waiting 2s", Toast.LENGTH_SHORT).show();   //testing
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // setting hand
        boolean found = false;
        do {
            found = false;
            Variables.randCard = Methodes.getRand(1,40);
            for(int i = 0; i <= Variables.usedCardsCount; i++) if(Variables.randCard == Variables.userCardsArrey[i]) found = true;
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
        else {
            Variables.playerTurn = true;
        }
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