package holiday;

/**
 * Serve as a specific exception to the Holiday chatbot
 */
public class BotException extends Exception {
    public BotException(String msg) {
        super(msg);
    }
}
