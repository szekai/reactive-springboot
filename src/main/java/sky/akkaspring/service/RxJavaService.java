package sky.akkaspring.service;

import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.akkaspring.model.Message;
import sky.akkaspring.model.MessageAcknowledgement;

import java.util.concurrent.TimeUnit;

@Service
public class RxJavaService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final  Long DELAY = 1000L;
    public Observable<MessageAcknowledgement> handleMessage(Message message) {
        logger.info("About to Acknowledge");
        return Observable.just(message)
                .delay(DELAY, TimeUnit.MILLISECONDS)
                .flatMap(msg -> {
//                    if (msg.isThrowException()) {
//                        return Observable.error(new IllegalStateException("Throwing a deliberate exception!"));
//                    }
                    return Observable.just(new MessageAcknowledgement(message.getPayload(),message.getId(), "From RxJavaService"));
                });
    }
}
