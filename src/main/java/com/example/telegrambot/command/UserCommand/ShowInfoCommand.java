package com.example.telegrambot.command.UserCommand;

import com.example.telegrambot.command.Command;
import com.example.telegrambot.model.AutoShows;
import com.example.telegrambot.model.AutoShowsRepository;
import com.example.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowInfoCommand implements Command {

    private AutoShowsRepository autoShowsRepository;

    private SendBotMessageService sendBotMessageService;

    public ShowInfoCommand(AutoShowsRepository autoShowsRepository, SendBotMessageService sendBotMessageService) {
        this.autoShowsRepository = autoShowsRepository;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {

        long chatId = update.getCallbackQuery().getMessage().getChatId();;
        long messageId = update.getCallbackQuery().getMessage().getMessageId();
        String data = update.getCallbackQuery().getData();
        String showId = data.replace("showinfo", "");

        Optional<AutoShows> autoShow = autoShowsRepository.findById(Long.valueOf(showId));
        AutoShows showInfo = autoShow.get();
        String text = showInfo.toStringShow();
        sendBotMessageService.messageToShowInfo(chatId, messageId, text);

        /*InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        //List<InlineKeyboardButton> buttons = new ArrayList<>();

        Iterable<AutoShows> autoShows = autoShowsRepository.findAll();
        for(AutoShows show : autoShows){

            InlineKeyboardButton autoShowButton = new InlineKeyboardButton();
            autoShowButton.setText(show.getDescription());
            autoShowButton.setCallbackData("showinfo" + String.valueOf(show.getId()));

            List<InlineKeyboardButton> rowInLine = new ArrayList<>();
            rowInLine.add(autoShowButton);
            rowsInLine.add(rowInLine);

        }
        inlineKeyboardMarkup.setKeyboard(rowsInLine);

        sendBotMessageService.messageToShowInfo(chatId, messageId, inlineKeyboardMarkup);*/
    }
}
