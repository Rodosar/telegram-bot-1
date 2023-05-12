package com.example.telegrambot.command.DefaultCommand;

import com.example.telegrambot.command.Command;
import com.example.telegrambot.service.SendBotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;


@Slf4j
public class HelpCommand implements Command {

    private SendBotMessageService sendBotMessageService;

    final static String PHOTO = "src/main/resources/images/help.jpg";

    final static String HELP_TEXT = "Этот бот предназначен для поиска автомобильных выставок, просмотра информации по этим выставкам и другой полезной информации";

    public HelpCommand(SendBotMessageService sendBotMessageService){
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {

        long chatId;
        long messageId = 0;

        if (update.hasMessage()){
            chatId = update.getMessage().getChatId();
            sendBotMessageService.prepareAndSendMessage(chatId, HELP_TEXT); //сделать метод с кнопками
        } else {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            messageId = update.getCallbackQuery().getMessage().getMessageId();
            sendBotMessageService.messageToHelp(chatId,messageId, HELP_TEXT);
        }

    }
}
