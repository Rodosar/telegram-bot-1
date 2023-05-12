package com.example.telegrambot.service;

import com.example.telegrambot.bot.TelegramBot;
import com.example.telegrambot.model.AutoShows;
import com.example.telegrambot.model.AutoShowsRepository;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class SendBotMessageServiceImpl implements SendBotMessageService{

    @Autowired
    private IsAdmin isAdmin;

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private CommandContainer commandContainer;

    private String PHOTO = "";

    @Override
    public void messageToStart(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(EmojiParser.parseToUnicode(text));
        sendMessage.setChatId(String.valueOf(chatId));

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();

        InlineKeyboardButton editShow = new InlineKeyboardButton();
        editShow.setText(EmojiParser.parseToUnicode(":question: Помощь"));
        editShow.setCallbackData("/help");

        InlineKeyboardButton addShow = new InlineKeyboardButton();
        addShow.setText(EmojiParser.parseToUnicode(":red_car: Выставки"));
        addShow.setCallbackData("/exhibitions");

        InlineKeyboardButton addShow1 = new InlineKeyboardButton();
        addShow1.setText(EmojiParser.parseToUnicode(":page_facing_up: Интересный факт"));
        addShow1.setCallbackData("/fact");

        rowInLine1.add(editShow);
        rowInLine1.add(addShow);
        rowInLine2.add(addShow1);

        rowsInLine.add(rowInLine1);
        rowsInLine.add(rowInLine2);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error send photo: " + e.getMessage());
        }
    }

    @Override
    public void messageToStartCallBack(long chatId, long messageId, String text) {

        EditMessageText sendMessage = new EditMessageText();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(EmojiParser.parseToUnicode(text));
        sendMessage.setMessageId((int) messageId);


        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();

        InlineKeyboardButton editShow = new InlineKeyboardButton();
        editShow.setText("Помощь");
        editShow.setCallbackData("/help");

        InlineKeyboardButton addShow = new InlineKeyboardButton();
        addShow.setText(EmojiParser.parseToUnicode(":red_car: Выставки"));
        addShow.setCallbackData("/exhibitions");

        InlineKeyboardButton addShow1 = new InlineKeyboardButton();
        addShow1.setText(EmojiParser.parseToUnicode(":page_facing_up: Интересный факт"));
        addShow1.setCallbackData("/fact");

        rowInLine1.add(editShow);
        rowInLine1.add(addShow);
        rowInLine2.add(addShow1);

        rowsInLine.add(rowInLine1);
        rowsInLine.add(rowInLine2);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error send photo: " + e.getMessage());
        }
    }

    @Override
    public void messageToHelp(long chatId, long messageId, String text) {
        EditMessageText sendMessage = new EditMessageText();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(EmojiParser.parseToUnicode(text));
        sendMessage.setMessageId((int) messageId);


        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();

        InlineKeyboardButton editShow = new InlineKeyboardButton();
        editShow.setText(EmojiParser.parseToUnicode(":red_car: Выставки"));
        editShow.setCallbackData("/exhibitions");

        InlineKeyboardButton addShow = new InlineKeyboardButton();
        addShow.setText(EmojiParser.parseToUnicode(":page_facing_up: Интересный факт"));
        addShow.setCallbackData("/fact");

        InlineKeyboardButton addShow1 = new InlineKeyboardButton();
        addShow1.setText("Главное меню");
        addShow1.setCallbackData("/start");

        rowInLine1.add(editShow);
        rowInLine1.add(addShow);
        rowInLine2.add(addShow1);

        rowsInLine.add(rowInLine1);
        rowsInLine.add(rowInLine2);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error send photo: " + e.getMessage());
        }
    }

    @Override
    public void messageToFact(long chatId, long messageId, String text) {
        EditMessageText sendMessage = new EditMessageText();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(EmojiParser.parseToUnicode(text));
        sendMessage.setMessageId((int) messageId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText(EmojiParser.parseToUnicode(":page_facing_up: Расскажи ещё факт!"));
        button1.setCallbackData("/fact");

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText(EmojiParser.parseToUnicode(":red_car: Выставки"));
        button2.setCallbackData("/exhibitions");

        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Главное меню");
        button3.setCallbackData("/start");

        rowInLine1.add(button1);
        rowInLine2.add(button2);
        rowInLine2.add(button3);

        rowsInLine.add(rowInLine1);
        rowsInLine.add(rowInLine2);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error send photo: " + e.getMessage());
        }
    }

    @Override
    public void messageToShow(long chatId, long messageId, String text) {
        EditMessageText sendMessage = new EditMessageText();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(EmojiParser.parseToUnicode(text));
        sendMessage.setMessageId((int) messageId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText(EmojiParser.parseToUnicode(":red_car: Подробнее о выставках"));
        button1.setCallbackData("/moreinfo");

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText(EmojiParser.parseToUnicode(":page_facing_up: Интересный факт"));
        button2.setCallbackData("/fact");

        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Главное меню");
        button3.setCallbackData("/start");

        rowInLine1.add(button1);
        rowInLine2.add(button2);
        rowInLine2.add(button3);

        rowsInLine.add(rowInLine1);
        rowsInLine.add(rowInLine2);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error send photo: " + e.getMessage());
        }
    }

    @Override
    public void messageToAllShows(long chatId, long messageId, AutoShowsRepository autoShowsRepository, String text) {

        EditMessageText sendMessage = new EditMessageText();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(EmojiParser.parseToUnicode(text));
        sendMessage.setMessageId((int) messageId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        Iterable<AutoShows> autoShows = autoShowsRepository.findAll();
        for(AutoShows show : autoShows){

            InlineKeyboardButton autoShowButton = new InlineKeyboardButton();
            autoShowButton.setText(show.getDescription());
            autoShowButton.setCallbackData("showinfo" + String.valueOf(show.getId()));

            List<InlineKeyboardButton> row1 = new ArrayList<>();
            row1.add(autoShowButton);
            rows.add(row1);

        }
        List<InlineKeyboardButton> row2 = new ArrayList<>();

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText(EmojiParser.parseToUnicode(":page_facing_up: Интересный факт"));
        button2.setCallbackData("/fact");

        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Главное меню");
        button3.setCallbackData("/start");

        row2.add(button2);
        row2.add(button3);
        rows.add(row2);


        inlineKeyboardMarkup.setKeyboard(rows);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error send photo: " + e.getMessage());
        }
    }

    @Override
    public void messageToShowInfo(long chatId, long messageId, String text) {
        EditMessageText sendMessage = new EditMessageText();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(text);
        sendMessage.setMessageId((int) messageId);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText(EmojiParser.parseToUnicode(":back: Назад к списку"));
        button1.setCallbackData("/moreinfo");

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText(EmojiParser.parseToUnicode(":page_facing_up: Интересный факт"));
        button2.setCallbackData("/fact");

        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Главное меню");
        button3.setCallbackData("/start");

        rowInLine1.add(button1);
        rowInLine2.add(button2);
        rowInLine2.add(button3);

        rowsInLine.add(rowInLine1);
        rowsInLine.add(rowInLine2);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error send photo: " + e.getMessage());
        }
    }

    @Override
    public void prepareAndSendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            log.error("Error execute message: " + e.getMessage());
        }
    }

    public void sendMessageWithKeyboard(long chatId, String chatUserName, String textToSend) {  //отправка сообщения при старте с клавиатурой
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(EmojiParser.parseToUnicode(textToSend));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        if(isAdmin.checkAdmin(chatId)){
            row.add("Добавить автовыставку");
            row.add("Редактировать автовыставку");
            row.add("Отправить сообщение пользователям");
            row2.add("Какие сейчас проводятся автомобильные выставки?");
            row2.add("Расскажи какой-нибудь факт!");

            keyboardRows.add(row);
            keyboardRows.add(row2);
        }
        else {
            row.add("Какие сейчас проводятся автомобильные выставки?");
            row2.add("Подробная информация про выставку");
            row2.add("Расскажи какой-нибудь факт!");
            keyboardRows.add(row);
            keyboardRows.add(row2);
        }

        replyKeyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(replyKeyboardMarkup);

        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            log.error("Error execute message: " + e.getMessage());
        }
    }

    @Override
    public void sendPhoto(long chatId) {

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(String.valueOf(chatId));
        sendPhoto.setPhoto(new InputFile(new File(PHOTO)));

        try {
            telegramBot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error("Error execute message: " + e.getMessage());
        }
    }

    @Override
    public void sendPhotoWithText(long chatId, String text) {

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(String.valueOf(chatId));
        sendPhoto.setPhoto(new InputFile(new File(PHOTO)));
        String textToSend = EmojiParser.parseToUnicode(text);
        sendPhoto.setCaption(textToSend);

        try {
            telegramBot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error("Error send photo: " + e.getMessage());
        }
    }

    @Override
    public void callBackSendMessage(long chatId, String text) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(String.valueOf(chatId));

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        InlineKeyboardButton editShow = new InlineKeyboardButton();
        editShow.setText("Отправить уведомление");
        editShow.setCallbackData("/send");

        InlineKeyboardButton addShow = new InlineKeyboardButton();
        addShow.setText("Добавить автовыставку");
        addShow.setCallbackData("/addshow");

        rowInLine.add(editShow);
        rowInLine.add(addShow);

        rowsInLine.add(rowInLine);

        inlineKeyboardMarkup.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error send photo: " + e.getMessage());
        }
    }



    @Override
    public void sendCallBackMessageShow(long callBackChatId, String callBackCommand, HashMap<String, Long> showDescription){

        /*if(showDescription.containsKey(callBackCommand)){
            long showCommandId = showDescription.get(callBackCommand);
            Optional<AutoShows> autoShow = autoShowsRepository.findById(showCommandId);
            AutoShows autoShows = autoShow.get();
            showCommand = new CallBackMoreInfoCommand(sendBotMessageService, autoShowsRepository, autoShows);
        }*/
    }

    @Override
    public void messageToCallBack(long callBackChatId, String callBackCommand) {

        String textToSend = null;

        switch (callBackCommand){

            case ("/addshow"):
                textToSend = "Добавьте выставку!";
                break;

            case ("/send"):
                textToSend = "Введите уведомление для всех пользователей!";
                break;

            default:
                break;
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(textToSend);
        sendMessage.setChatId(String.valueOf(callBackChatId));

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error send photo: " + e.getMessage());
        }

    }

}
