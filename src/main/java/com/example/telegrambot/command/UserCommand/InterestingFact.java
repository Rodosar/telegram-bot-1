package com.example.telegrambot.command.UserCommand;

import com.example.telegrambot.command.Command;
import com.example.telegrambot.service.CommandContainer;
import com.example.telegrambot.model.Facts;
import com.example.telegrambot.model.FactsRepository;
import com.example.telegrambot.service.SendBotMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.*;

//todo доделать чтобы всегда рандомные факты выдавались
@Component
public class InterestingFact implements Command {

    SendBotMessageService sendBotMessageService;

    FactsRepository factsRepository;

    CommandContainer commandContainer;

    private Map<Long, Integer> factsMap;
    private ArrayList<Long> factsArray;
    private ArrayList<Long> removeFactsArray;

    public InterestingFact(SendBotMessageService sendBotMessageService, FactsRepository factsRepository) {
        this.sendBotMessageService = sendBotMessageService;
        this.factsRepository = factsRepository;

        factsMap = new HashMap<>();
        factsArray = new ArrayList<>();
        Iterable<Facts> facts = factsRepository.findAll();
        for (Facts fact : facts) {
            long factId = fact.getId();
            int t = Integer.parseInt(fact.getFact());
            factsMap.put(factId, t);
            factsArray.add(factId);
        }
    }

    @Override
    public void execute(Update update) {

        Random random = new Random();
        long factId = factsArray.get(random.nextInt(factsArray.size()));
        Optional<Facts> getFact = factsRepository.findById(factId);
        Facts fact = getFact.get();

        long chatId;
        if (update.hasMessage()){
            chatId = update.getMessage().getChatId();
        } else {
            chatId = update.getCallbackQuery().getMessage().getChatId();
        }
        sendBotMessageService.messageToFact(chatId, fact.toString());

    }


}
