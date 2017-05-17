package sky.akkaspring.controller;

import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sky.akkaspring.model.Message;
import sky.akkaspring.model.MessageAcknowledgement;
import sky.akkaspring.service.RxJavaService;

@RestController
public class RxJavaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RxJavaService rxJavaService;

    @RequestMapping(path = "/handleMessageRxJava", method = RequestMethod.POST)
    public Observable<MessageAcknowledgement> handleMessage(@RequestBody Message message) {
        logger.info("Got Message..");
        return this.rxJavaService.handleMessage(message);
    }
}
