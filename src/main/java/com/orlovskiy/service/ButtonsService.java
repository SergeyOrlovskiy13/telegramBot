package com.orlovskiy.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ButtonsService {
    public ReplyKeyboardMarkup setButton (List<KeyboardRow> keyboardRowList){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;

    }

    public List<KeyboardRow> createKeyBoard(List<String> buttonsName){
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        for (String buttonName : buttonsName){
            keyboardRow.add(new KeyboardButton(buttonName));
        }
        keyboardRows.add(keyboardRow);
        return keyboardRows;
    }

    public List< List<InlineKeyboardButton>> createInLineButton(String buttonName){
        List<List<InlineKeyboardButton>> keyBoard = new ArrayList<>();
        List<InlineKeyboardButton> keyboardRow = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(buttonName);
        inlineKeyboardButton.setCallbackData(buttonName);
        keyboardRow.add(inlineKeyboardButton);
        keyBoard.add(keyboardRow);
        return keyBoard;
    }

    public InlineKeyboardMarkup setInLineKeyBoard(List< List<InlineKeyboardButton>> list){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(list);
        return inlineKeyboardMarkup;
    }


}
