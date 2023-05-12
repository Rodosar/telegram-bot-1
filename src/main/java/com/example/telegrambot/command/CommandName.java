package com.example.telegrambot.command;

public enum CommandName {


    START ("/start"),
    HELP("/help"),
    SEND ("/send"),
    ADDADS("/addads"),
    EDITADS("/editads"),
    DELETEADS("/deleteads"),
    ADDSHOW("/addshow"),
    EDITSHOW("/editshow"),
    DELETESHOW("/deleteshow"),

    EXHIBITIONS("/exhibitions"),
    FACT("/fact"),
    MOREINFO("/moreinfo");


    private String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
