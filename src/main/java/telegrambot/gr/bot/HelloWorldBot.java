package telegrambot.gr.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegrambot.gr.config.BotConfig;
import telegrambot.gr.service.MenuService;

@Component
public class HelloWorldBot extends TelegramLongPollingBot {

    private static Logger logger = LoggerFactory.getLogger(HelloWorldBot.class);

    private final BotConfig botConfig;
    private final MenuService menuService;

    public HelloWorldBot(BotConfig botConfig, MenuService menuService) {
        this.botConfig = botConfig;
        this.menuService = menuService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.info("Received update: {}", update);
        if (update.hasMessage() && update.getMessage().hasText()) {
            handleIncomingMessage(update);
        } else if (update.hasCallbackQuery()) {
            handleCallbackQuery(update);
        }
    }
    private void handleIncomingMessage(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        if ("/start".equals(text)) {
            sendMessage(chatId, "Вітаю! Ось головне меню:", menuService.getMainMenu());
        } else {
            sendMessage(chatId, "Невідома команда!");
        }
    }

    private void handleCallbackQuery(Update update) {
        String data = update.getCallbackQuery().getData();
        Long chatId = update.getCallbackQuery().getMessage().getChatId();

        switch (data) {
            case "menu1_button1":
                sendMessage(chatId, "Кнопка 1");
                break;
            case "menu1_button2":
                sendMessage(chatId, "Кнопка 2");
                break;
            case "menu1_next":
                sendMessage(chatId, "Меню 2:", menuService.getMenu2());
                break;
            case "menu2_button1":
                sendMessage(chatId, "Кнопка 1");
                break;
            case "menu2_button2":
                sendMessage(chatId, "Кнопка 2");
                break;
            case "menu2_back":
                sendMessage(chatId, "Головне меню:", menuService.getMainMenu());
                break;
            default:
                sendMessage(chatId, "Невідома команда!");
                break;
        }
    }

    private void sendMessage(Long chatId, String text) {
        sendMessage(chatId, text, null);
    }

    private void sendMessage(Long chatId, String text, InlineKeyboardMarkup keyboard) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        if (keyboard != null) {
            message.setReplyMarkup(keyboard);
        }
        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Error sending message", e);
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }
}
