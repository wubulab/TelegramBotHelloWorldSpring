package telegrambot.gr.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    public InlineKeyboardMarkup getMainMenu() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton btn1 = createButton("Кнопка 1", "menu1_button1");
        InlineKeyboardButton btn2 = createButton("Кнопка 2", "menu1_button2");
        row1.add(btn1);
        row1.add(btn2);
        rows.add(row1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton btnNext = createButton("Далі", "menu1_next");
        row2.add(btnNext);
        rows.add(row2);

        keyboard.setKeyboard(rows);
        return keyboard;
    }

    public InlineKeyboardMarkup getMenu2() {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton btn1 = createButton("Кнопка 1", "menu2_button1");
        InlineKeyboardButton btn2 = createButton("Кнопка 2", "menu2_button2");
        row1.add(btn1);
        row1.add(btn2);
        rows.add(row1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton btnBack = createButton("Назад", "menu2_back");
        row2.add(btnBack);
        rows.add(row2);

        keyboard.setKeyboard(rows);
        return keyboard;
    }

    private InlineKeyboardButton createButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}
