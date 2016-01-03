package com.example.hassan.outdoor;

public class Payment {
    private CreditCard card ;

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public boolean checkBalance()
    {
        return  false;
    }

    public void pay(int subscription)
    {

    }
}

