package com.example.telegrambot.service;

import com.example.telegrambot.model.AutoShowsRepository;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.HashMap;

public interface SendBotMessageService {

    void messageToStart(long chatId, String text);

    void messageToStartCallBack(long chatId, long messageId, String text);

    void messageToHelp(long chatId, long messageId, String text);

    void messageToFact(long chatId, long messageId, String text);

    void messageToShow(long chatId, long messageId, String text);

    void messageToShowInfo(long chatId, long messageId, String text);

    void prepareAndSendMessage(long chatId, String textToSend);

    void sendMessageWithKeyboard(long chatId, String chatUserName, String message);

    void sendPhoto(long chatId);

    void sendPhotoWithText(long chatId, String text);

    void callBackSendMessage(long chatId, String text);

    void messageToAllShows(long chatId, long messageId, AutoShowsRepository autoShowsRepository, String text);

    void sendCallBackMessageShow(long callBackChatId, String callBackCommand, HashMap<String, Long> showDescription);

    void messageToCallBack(long callBackChatId, String callBackCommand);

}
