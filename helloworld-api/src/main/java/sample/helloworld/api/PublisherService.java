package sample.helloworld.api;

import com.lightbend.lagom.javadsl.api.*;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import sample.helloworld.api.GreetingMessage;

import static com.lightbend.lagom.javadsl.api.Service.*;

public interface PublisherService extends Service {

  @Override
  default Descriptor descriptor() {
    return named("helloservice")
            // here we declare the topic(s) this service will publish to
            .publishing(
                    topic("greetings", this::greetingsTopic)
            )
            .withAutoAcl(true);
  }
  // The topic handle
  Topic<GreetingMessage> greetingsTopic();

}
