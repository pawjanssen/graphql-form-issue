When adding quarkus-smallrye-graphql, the form based login mechanism stops working. When POSTing to /j_security_check without the graphql dependency, everything works fine, and I can access my secured resource. When adding the graphql dependency, the POST to /j_security_check the request fails with the exception below. The only differency is the added dependency in the POM.

The issue can be reproduced with this project. When commenting the graphql dependency in the pom.xml, the form based login works.


```
java.lang.IllegalStateException: Request has already been read
	at io.vertx.core.http.impl.HttpServerRequestImpl.checkEnded(HttpServerRequestImpl.java:593)
	at io.vertx.core.http.impl.HttpServerRequestImpl.setExpectMultipart(HttpServerRequestImpl.java:431)
	at io.quarkus.vertx.http.runtime.AbstractRequestWrapper.setExpectMultipart(AbstractRequestWrapper.java:193)
	at io.vertx.ext.web.impl.HttpServerRequestWrapper.setExpectMultipart(HttpServerRequestWrapper.java:286)
	at io.quarkus.vertx.http.runtime.security.FormAuthenticationMechanism.runFormAuth(FormAuthenticationMechanism.java:52)
	at io.quarkus.vertx.http.runtime.security.FormAuthenticationMechanism.authenticate(FormAuthenticationMechanism.java:163)
	at io.quarkus.vertx.http.runtime.security.HttpAuthenticator.attemptAuthentication(HttpAuthenticator.java:100)
	at io.quarkus.vertx.http.runtime.security.HttpAuthenticator_ClientProxy.attemptAuthentication(HttpAuthenticator_ClientProxy.zig:157)
	at io.quarkus.vertx.http.runtime.security.HttpSecurityRecorder$2.handle(HttpSecurityRecorder.java:101)
	at io.quarkus.vertx.http.runtime.security.HttpSecurityRecorder$2.handle(HttpSecurityRecorder.java:51)
	at io.vertx.ext.web.impl.RouteState.handleContext(RouteState.java:1038)
	at io.vertx.ext.web.impl.RoutingContextImplBase.iterateNext(RoutingContextImplBase.java:137)
	at io.vertx.ext.web.impl.RoutingContextImpl.next(RoutingContextImpl.java:132)
	at io.vertx.ext.web.handler.impl.BodyHandlerImpl$BHandler.doEnd(BodyHandlerImpl.java:296)
	at io.vertx.ext.web.handler.impl.BodyHandlerImpl$BHandler.end(BodyHandlerImpl.java:276)
	at io.vertx.ext.web.handler.impl.BodyHandlerImpl.lambda$handle$0(BodyHandlerImpl.java:87)
	at io.vertx.core.http.impl.HttpServerRequestImpl.onEnd(HttpServerRequestImpl.java:523)
	at io.vertx.core.http.impl.HttpServerRequestImpl.lambda$pendingQueue$1(HttpServerRequestImpl.java:117)
	at io.vertx.core.streams.impl.InboundBuffer.handleEvent(InboundBuffer.java:237)
	at io.vertx.core.streams.impl.InboundBuffer.drain(InboundBuffer.java:224)
	at io.vertx.core.streams.impl.InboundBuffer.lambda$fetch$0(InboundBuffer.java:277)
	at io.vertx.core.impl.ContextImpl.executeTask(ContextImpl.java:366)
	at io.vertx.core.impl.EventLoopContext.lambda$executeAsync$0(EventLoopContext.java:38)
	at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:164)
	at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:472)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:497)
	at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:989)
	at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.base/java.lang.Thread.run(Thread.java:832)
```
