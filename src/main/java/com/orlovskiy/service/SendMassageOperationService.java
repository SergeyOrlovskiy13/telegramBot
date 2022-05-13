package com.orlovskiy.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

import static com.orlovskiy.constant.VarConstant.*;
import static java.lang.Math.toIntExact;
import static java.util.Arrays.asList;

public class SendMassageOperationService {
     private final String GREETING_MASSAGE = "Hello, let's start planing!!!";
     private final String PLANING_MASSAGE = "Input deals, then select button \" stop \" ";
     private final String END_MASSAGE = "Planning is end, for show select button \" show \" ";
     private final String INSTRUCTION = "Load instruction???";
     private final ButtonsService buttonsService = new ButtonsService();
    public SendMessage createGreetingInformation(Update update ){
      SendMessage sendMessage =  simpleMassage(update, GREETING_MASSAGE) ;
        ReplyKeyboardMarkup keyboardMarkup =
                buttonsService.setButton(buttonsService.createKeyBoard(asList(START_PLANNING, END_PLANNING,SHOW_DEALS)));
        sendMessage.setReplyMarkup(keyboardMarkup);
        return sendMessage;
    }
    public SendMessage createPlaningMassage(Update update ){
        return simpleMassage(update, PLANING_MASSAGE) ;
    }

    public SendMessage endPlaningMassage(Update update ){
        return simpleMassage(update, END_MASSAGE) ;
    }

    public SendMessage simpleMassage(Update update, String massage) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText(massage);
        return sendMessage;
    }

    public SendMessage simpleMassage(Update update, List<String> massages) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        StringBuilder massage = new StringBuilder();
        for (String s: massages){
            massage.append(s).append("\n");
        }
        sendMessage.setText(massage.toString());
        return sendMessage;
    }

    public SendMessage createInstruction(Update update) {
        SendMessage sendMessage = simpleMassage(update, INSTRUCTION);
        InlineKeyboardMarkup inlineKeyboardMarkup =
                buttonsService.setInLineKeyBoard(buttonsService.createInLineButton(YES));
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    public EditMessageText createMassage(Update update, String instruction) {
        EditMessageText text = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatid = update.getCallbackQuery().getMessage().getChatId();
        text.setChatId(String.valueOf(chatid));
        text.setMessageId(toIntExact(mesId));
        text.setText(instruction);
        return  text;

    }
}
