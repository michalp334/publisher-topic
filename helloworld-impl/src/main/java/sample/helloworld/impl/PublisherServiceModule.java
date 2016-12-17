/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.helloworld.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import sample.helloworld.api.PublisherService;

/**
 * The module that binds the HelloService so that it can be served.
 */
public class PublisherServiceModule extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {
    bindServices(serviceBinding(PublisherService.class, PublisherServiceImpl.class));
  }
}
