package com.example.hassan.outdoor;
import java.util.ArrayList;

public class Conversation {
    private ArrayList<NormalUser> participants ;
    private ArrayList<Message> messages ;

    public ArrayList<NormalUser> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<NormalUser> participants) {
        this.participants = participants;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void addParticipant(NormalUser newUser)
    {

    }

    public void addMessage(NormalUser messageWriter)
    {

    }

    public void removeParticipant(NormalUser removedUser)
    {

    }
}

