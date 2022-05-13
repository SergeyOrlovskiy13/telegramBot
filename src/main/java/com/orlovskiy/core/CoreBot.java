package com.orlovskiy.core;

import com.orlovskiy.service.SendMassageOperationService;
import com.orlovskiy.store.HashMapStore;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.util.Locale;

import static com.orlovskiy.constant.VarConstant.*;

public class CoreBot extends TelegramLongPollingBot {
    SendMassageOperationService sendMassageOperationService = new SendMassageOperationService();
    private HashMapStore hashMapStore = new HashMapStore();
    private boolean startPlanning;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            switch (update.getMessage().getText()){
                case START:
                    executeMassage( sendMassageOperationService.createGreetingInformation(update));
                    executeMassage( sendMassageOperationService.createInstruction(update));

                    break;
                case START_PLANNING:
                    startPlanning = true;
                    executeMassage(sendMassageOperationService.createPlaningMassage(update));
                    break;
                case END_PLANNING:
                    startPlanning= false;
                    executeMassage(sendMassageOperationService.endPlaningMassage(update));
                    break;
                case SHOW_DEALS:
                    if (startPlanning == false){
                    executeMassage(sendMassageOperationService.simpleMassage(update, hashMapStore.selectAll(LocalDate.now())));
                    }
                default:
                    if (startPlanning==true) {
                        hashMapStore.save(LocalDate.now(), update.getMessage().getText());
                    }
            }

        }
        if (update.hasCallbackQuery()){
            String instruction = " Bot for crate deals on today. For Using bot look forward his instruction";
            String callbackDate = update.getCallbackQuery().getData();
            switch (callbackDate.toLowerCase()){
                case YES:
                    EditMessageText text = sendMassageOperationService.createMassage(update,instruction);
                    executeMassage(text);
            }
        }

    }

    @Override
    public String getBotUsername() {
        return "me_new_13121985_bot";
    }

    @Override
    public String getBotToken() {
        return "5107653387:AAF6sEPVWE1rWl2xdgsyydXCtVKLlJwEaLc";
    }

    private <T extends BotApiMethod> void executeMassage(T sendMassage){
        try {
            execute(sendMassage);
        } catch (TelegramApiException e) {
            System.out.println("Can't send massage " + e.getCause());
        }
    }


}
