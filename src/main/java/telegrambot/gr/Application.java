package telegrambot.gr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegrambot.gr.bot.HelloWorldBot;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private final HelloWorldBot helloWorldBot;

    public Application(HelloWorldBot helloWorldBot) {
        this.helloWorldBot = helloWorldBot;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(helloWorldBot);
            logger.info("Bot successfully registered!");
        } catch (Exception e) {
            logger.error("Error registering bot", e);
        }
    }

}
