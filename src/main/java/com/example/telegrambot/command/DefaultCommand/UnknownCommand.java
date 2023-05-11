package com.example.telegrambot.command.DefaultCommand;

import com.example.telegrambot.command.Command;
import com.example.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command {
    public static final String UNKNOWN_MESSAGE = "Не понимаю вас \uD83D\uDE1F, напишите /help чтобы узнать что я понимаю.";


    private SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.getMessage().getChatId();
        sendBotMessageService.prepareAndSendMessage(chatId, UNKNOWN_MESSAGE);
    }
}
