package sky.akkaspring;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;
import sky.akkaspring.di.SpringExtension;
import sky.akkaspring.model.Greet;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AkkaSpringApplicationTests {
	@Autowired
	private ActorSystem actorSystem;

	@Autowired
	private SpringExtension springExtension;

	@After
	public void tearDown() throws Exception {
		actorSystem.terminate();
		Await.result(actorSystem.whenTerminated(), Duration.Inf());
	}

	@Test
	public void whenCallingGreetingActor_thenActorGreetsTheCaller() throws Exception {
		ActorRef greeter = actorSystem.actorOf(springExtension.props("greetingActor"), "greeter");

		FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
		Timeout timeout = Timeout.durationToTimeout(duration);

		Future<Object> result = ask(greeter, new Greet("John"), timeout);

		Assert.assertEquals("Hello, John", Await.result(result, duration));
	}

}
