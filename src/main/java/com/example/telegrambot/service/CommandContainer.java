package com.example.telegrambot.service;

import com.example.telegrambot.command.AdminCommand.AddShowCommand;
import com.example.telegrambot.command.Command;
import com.example.telegrambot.command.DefaultCommand.HelpCommand;
import com.example.telegrambot.command.AdminCommand.SendCommand;
import com.example.telegrambot.command.DefaultCommand.StartCommand;
import com.example.telegrambot.command.DefaultCommand.UnknownCommand;
import com.example.telegrambot.command.UserCommand.*;
import com.example.telegrambot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

import static com.example.telegrambot.command.CommandName.*;


@Component
public class CommandContainer {

    private Command unknownCommand;
    private Command showInfoCommand;

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
        adminCommandMap.put(ADDSHOW.getCommandName(), new AddShowCommand(sendBotMessageService,autoShowsRepository));
        adminCommandMap.put(EXHIBITIONS.getCommandName(),new ExhibitionsNowCommand(sendBotMessageService, autoShowsRepository));// /addshow
        adminCommandMap.put(FACT.getCommandName(), new InterestingFact(sendBotMessageService, factsRepository));
        adminCommandMap.put(MOREINFO.getCommandName(), new MoreInfoCommand(sendBotMessageService, autoShowsRepository));

        userCommandMap.put(START.getCommandName(),new StartCommand(sendBotMessageService, userRepository));
        userCommandMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));
        userCommandMap.put(EXHIBITIONS.getCommandName(),new ExhibitionsNowCommand(sendBotMessageService, autoShowsRepository));

        userCommandMap.put(FACT.getCommandName(), new InterestingFact(sendBotMessageService, factsRepository));
        userCommandMap.put(MOREINFO.getCommandName(), new MoreInfoCommand(sendBotMessageService, autoShowsRepository));

        unknownCommand = new UnknownCommand(sendBotMessageService);


        this.autoShowsRepository = autoShowsRepository;
        this.sendBotMessageService =sendBotMessageService;
    }

    public Command findCommand(long userId, String commandIdentifier) {

        Command commandOrDefault;

        if(commandIdentifier.contains("showinfo")){
            commandOrDefault = new ShowInfoCommand(autoShowsRepository, sendBotMessageService);
            return commandOrDefault;
        }
        if(isAdmin.checkAdmin(userId)){

           commandOrDefault = adminCommandMap.getOrDefault(commandIdentifier, unknownCommand);

        } else {
            commandOrDefault = userCommandMap.getOrDefault(commandIdentifier, unknownCommand);
        }

        return commandOrDefault;
    }

}
