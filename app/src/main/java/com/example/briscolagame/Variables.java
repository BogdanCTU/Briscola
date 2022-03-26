package com.example.briscolagame;

public class Variables {

    // ENEMY
    public static boolean enemyButton1Pressed = true;
    public static boolean enemyButton2Pressed = true;
    public static boolean enemyButton3Pressed = true;
    public static boolean enemyButton1firstclick = true;
    public static boolean enemyButton2firstclick = true;
    public static boolean enemyButton3firstclick = true;
    public static int enemy_card1 = 0, enemy_card2 = 0, enemy_card3 = 0, enemy_table_card = 0, enemy_points = 0;

    // PLAYER
    public static boolean playerButton1Pressed = true;
    public static boolean playerButton2Pressed = true;
    public static boolean playerButton3Pressed = true;
    public static boolean playerButton1firstclick = true;
    public static boolean playerButton2firstclick = true;
    public static boolean playerButton3firstclick = true;
    public static int player_card1 = 0, player_card2 = 0, player_card3 = 0, player_table_card = 0, player_points = 0;

    // DECK
    public static int [] userCardsArrey = new int [39];
    public static int usedCardsCount = 0;
    public static boolean gameFinished = false;
    public static int randCard;

    // GAMEPLAY
    public static CardClass[] DeckCards = new CardClass[41];
    public static String pth = ":/00.png";
    public static CardClass playerDroppedCard;
    public static CardClass enemyDroppedCard;
    public static boolean playerTurn = true, playerLastDropped = false, playerWins = false, playerFirstDrop = true;
    public static String winnerType;
    public static int droppedCards = 0;   //when 2 reset and verify winner
    public static int droppedLast = 0;   //droppedFirst : 1 PLAYER / 2 ENEMY

    // Function
    public static int gameStyle = 0;   // 0 pvp   -   1 pvc easy   -   2 pvc hard

    // Online Game Play
    public static int player_number = 0;   // 1 player 2 enemy

    //EOF - End Of File
}
