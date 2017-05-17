package sky.akkaspring.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.akkaspring.di.SpringExtension;
import sky.akkaspring.model.Message;

import java.util.concurrent.CompletableFuture;

@Service
public class CompletableFutureService {
    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringExtension springExtension;

    public CompletableFuture<Message> get(String payload, Long id){
        CompletableFuture<Message> future = new CompletableFuture<>();
        ActorRef workerActor = actorSystem.actorOf(springExtension.props("futureWorkerActor", future), "future-worker-actor");
        workerActor.tell(new Message(payload, id), null);
        return future;
    }
}
