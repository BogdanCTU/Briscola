package com.example.briscolagame;

public class CardClass {

    // Parameters

    public int cardNumber;
    public String cardType;
    public int cardValue;
    public int cardIcon;

    // Constructors

    CardClass(int Number, String Type, int Value, int Icon)
    {
        this.cardNumber = Number;
        this.cardType = Type;
        this.cardValue = Value;
        this.cardIcon = Icon;
    }

    CardClass(CardClass param) {
        this.cardNumber = param.cardNumber;
        this.cardType = param.cardType;
        this.cardValue = param.cardValue;
        this.cardIcon = param.cardIcon;
    }

    // EOF - End Of File
}
