package com.example.telegrambot.service;

import com.example.telegrambot.model.AutoShowsRepository;
import java.util.HashMap;

public interface SendBotMessageService {

    void messageToStart(long chatId, String text);

    void messageToStartCallBack(long chatId, String text);

    void messageToHelp(long chatId, String text);

    void messageToFact(long chatId, String text);

    void messageToShow(long chatId, String text);

    void prepareAndSendMessage(long chatId, String textToSend);

    void sendMessageWithKeyboard(long chatId, String chatUserName, String message);

    void sendPhoto(long chatId);

    void sendPhotoWithText(long chatId, String text);

    void callBackSendMessage(long chatId, String text);

    void sendCallBackMessageButtonsShow(long chatId, AutoShowsRepository autoShowsRepository, String text);

    void sendCallBackMessageShow(long callBackChatId, String callBackCommand, HashMap<String, Long> showDescription);

    void messageToCallBack(long callBackChatId, String callBackCommand);

    void messageAboutShow(long chatId, long showId, String text);


}
