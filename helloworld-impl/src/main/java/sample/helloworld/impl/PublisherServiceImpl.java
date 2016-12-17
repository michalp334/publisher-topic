/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.helloworld.impl;

import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.broker.TopicProducer;
import sample.helloworld.api.GreetingMessage;
import sample.helloworld.api.PublisherService;


/**
 * Implementation of the HelloService.
 */
public class PublisherServiceImpl implements PublisherService {


    public Topic<GreetingMessage> greetingsTopic() {
        return TopicProducer.singleStreamWithOffset(offset -> {
            return persistentEntityRegistry
                    .eventStream(HelloEventTag.INSTANCE, offset)
                    .map(this::convertEvent);
        });
    }

}
