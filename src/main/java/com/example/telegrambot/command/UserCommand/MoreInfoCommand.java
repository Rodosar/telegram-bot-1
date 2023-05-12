package com.example.telegrambot.command.UserCommand;

import com.example.telegrambot.command.Command;
import com.example.telegrambot.model.AutoShowsRepository;
import com.example.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MoreInfoCommand implements Command {

    private SendBotMessageService sendBotMessageService;

    private AutoShowsRepository autoShowsRepository;

    public MoreInfoCommand(SendBotMessageService sendBotMessageService, AutoShowsRepository autoShowsRepository){
        this.sendBotMessageService = sendBotMessageService;
        this.autoShowsRepository = autoShowsRepository;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        long messageId = update.getCallbackQuery().getMessage().getMessageId();

        sendBotMessageService.messageToAllShows(chatId, messageId, autoShowsRepository, "Выберите автомобильную выставку");
    }
}
