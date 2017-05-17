package sky.akkaspring.actor;

import akka.actor.UntypedAbstractActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sky.akkaspring.model.Message;
import sky.akkaspring.service.BusinessService;

import java.util.concurrent.CompletableFuture;

@Component("futureWorkerActor")
@Scope("prototype")
public class FutureWorkerActor extends UntypedAbstractActor {
    @Autowired
    private BusinessService businessService;

    final private CompletableFuture<Message> future;

    public FutureWorkerActor(CompletableFuture<Message> future){
        this.future = future;
    }

    @Override
    public void onReceive(Object message) throws Exception{
        businessService.perform(this + " " + message);

        if(message instanceof  Message){
            future.complete((Message) message);
        } else {
            unhandled(message);
        }

        getContext().stop(self());
    }
}
