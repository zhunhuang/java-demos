package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 黄准 on 17-9-17.
 * Email:nolan.zhun@gmail.com
 */
@Component
public class MessagePrinter {

    final private MessageService service;

    @Autowired
    public MessagePrinter(MessageService messageService) {
        this.service = messageService;
    }

    public void printMessage() {
        System.out.println(this.service.getMessage());
    }
}
