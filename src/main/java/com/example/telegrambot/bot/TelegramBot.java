package com.example.telegrambot.bot;

import com.example.telegrambot.command.DefaultCommand.HelpCommand;
import com.example.telegrambot.service.CommandContainer;
import com.example.telegrambot.config.BotConfig;
import com.example.telegrambot.model.*;
import com.example.telegrambot.service.SendBotMessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.HashMap;


@Slf4j
@Component
@PropertySource("classpath:images/")
public class TelegramBot extends TelegramLongPollingBot{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdsRepository adsRepository;

    @Autowired
    private AutoShowsRepository autoShowsRepository;

    @Autowired
    private FactsRepository factsRepository;

    @Autowired
    private CommandContainer commandContainer;

    @Lazy
    @Autowired
    private SendBotMessageServiceImpl sendBotMessageService;

    final BotConfig config;

    final static boolean isCallBack = true;

    private HashMap<Boolean, String> commandMap;
    private HashMap<String, Long> showDescription;
    private String showCommandName;

    public TelegramBot(BotConfig config){
        this.config=config;
        commandMap = new HashMap<>();
        showDescription = new HashMap<>();


    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        commandContainer.fillMap(sendBotMessageService, userRepository, autoShowsRepository, factsRepository);
        Iterable<AutoShows> autoShow = autoShowsRepository.findAll();
        for(AutoShows show : autoShow){
            showDescription.put(show.getDescription(), show.getId());
        }

        if(update.hasMessage() && update.getMessage().hasText()){
            String command = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            commandContainer.findCommand(chatId,command).execute(update);
        }
        else if(update.hasCallbackQuery()){
            String callBackCommand = update.getCallbackQuery().getData();
            long callBackChatId = update.getCallbackQuery().getMessage().getChatId();
            commandContainer.findCommand(callBackChatId, callBackCommand).execute(update);
        }
    }
}
