package com.example.telegrambot.service;

import com.example.telegrambot.command.AdminCommand.AddShowCommand;
import com.example.telegrambot.command.Command;
import com.example.telegrambot.command.DefaultCommand.HelpCommand;
import com.example.telegrambot.command.AdminCommand.SendCommand;
import com.example.telegrambot.command.DefaultCommand.StartCommand;
import com.example.telegrambot.command.DefaultCommand.UnknownCommand;
import com.example.telegrambot.command.UserCommand.CallBackMoreInfoCommand;
import com.example.telegrambot.command.UserCommand.ExhibitionsNowCommand;
import com.example.telegrambot.command.UserCommand.InterestingFact;
import com.example.telegrambot.command.UserCommand.MoreInfoCommand;
import com.example.telegrambot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

import static com.example.telegrambot.command.CommandName.*;


@Component
public class CommandContainer {

    private Command unknownCommand;

    @Autowired
    private IsAdmin isAdmin;

    private AutoShowsRepository autoShowsRepository;

    private SendBotMessageService sendBotMessageService;

    private HashMap<String, Command> adminCommandMap = new HashMap();
    private HashMap<String, Command> userCommandMap = new HashMap();

    public void fillMap(SendBotMessageService sendBotMessageService, UserRepository userRepository,
                        AutoShowsRepository autoShowsRepository, FactsRepository factsRepository){

        adminCommandMap.put(START.getCommandName(),new StartCommand(sendBotMessageService, userRepository));            // /start
        adminCommandMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));                             // /help
        adminCommandMap.put(SEND.getCommandName(), new SendCommand(sendBotMessageService, userRepository));             // /send
        adminCommandMap.put(ADDSHOW.getCommandName(), new AddShowCommand(sendBotMessageService,autoShowsRepository));   // /addshow

        userCommandMap.put(START.getCommandName(),new StartCommand(sendBotMessageService, userRepository));
        userCommandMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));
        userCommandMap.put(EXHIBITIONS.getCommandName(),new ExhibitionsNowCommand(sendBotMessageService, autoShowsRepository));

        userCommandMap.put(FACT.getCommandName(), new InterestingFact(sendBotMessageService, factsRepository));
        userCommandMap.put(MOREINFO.getCommandName(), new MoreInfoCommand(sendBotMessageService, autoShowsRepository));

        unknownCommand = new UnknownCommand(sendBotMessageService);

        this.autoShowsRepository = autoShowsRepository;
        this.sendBotMessageService =sendBotMessageService;
    }

    public Command findCommand(String chatUserName, String commandIdentifier) {

        Command commandOrDefault;

        if(isAdmin.checkAdmin(chatUserName)){
            commandOrDefault = adminCommandMap.getOrDefault(commandIdentifier, unknownCommand);
        } else {
            commandOrDefault = userCommandMap.getOrDefault(commandIdentifier, unknownCommand);
        }

        return commandOrDefault;
    }

    public Command findShowCommand(String callBackCommand, HashMap<String, Long> showDescription){
        Command showCommand;

        if(showDescription.containsKey(callBackCommand)){
            long showCommandId = showDescription.get(callBackCommand);
            Optional<AutoShows> autoShow = autoShowsRepository.findById(showCommandId);
            AutoShows autoShows = autoShow.get();
            showCommand = new CallBackMoreInfoCommand(sendBotMessageService, autoShowsRepository, autoShows);
        } else {
            showCommand = null;
        }


        return showCommand;
    }

    public boolean isCommand(String chatUserName, String callBackCommand) {

        boolean command = false;

        if(isAdmin.checkAdmin(chatUserName)){
            command = adminCommandMap.containsKey(callBackCommand);
        } else {
            command = userCommandMap.containsKey(callBackCommand);
        }
        return command;
    }


}
