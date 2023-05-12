package com.example.telegrambot.command.DefaultCommand;

import com.example.telegrambot.model.User;
import com.example.telegrambot.model.UserRepository;
import com.example.telegrambot.command.Command;
import com.example.telegrambot.service.SendBotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.Timestamp;


@Slf4j
public class StartCommand implements Command {

    private UserRepository userRepository;

    private SendBotMessageService sendBotMessageService;

    final static String START = "Здравствуйте! :oncoming_automobile: \nЭтот бот предназначен для предоставления информации по автомобильным выставкам в г. Санкт-Петербург.\nЧтобы ознакомиться с возможностями и доступными командами нажмите /help" ;


    public StartCommand(SendBotMessageService sendBotMessageService, UserRepository userRepository) {
        this.sendBotMessageService = sendBotMessageService;
        this.userRepository = userRepository;
    }

    @Override
    public void execute(Update update) {

        long chatId;
        long messageId = 0;

        if (update.hasMessage()){
            chatId = update.getMessage().getChatId();
            Message message = update.getMessage();
            registerUser(chatId, message);
            sendBotMessageService.messageToStart(chatId, START);
        } else {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            messageId = update.getCallbackQuery().getMessage().getMessageId();
            sendBotMessageService.messageToStartCallBack(chatId, messageId, START);
        }

    }



    private void registerUser(long chatId, Message message){//регистрация пользователя
        if(userRepository.findById(chatId).isEmpty()){

            Chat chat = message.getChat();

            User user = new User();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));

            userRepository.save(user);
            log.info("User saved: " + user);
        }
    }
}
