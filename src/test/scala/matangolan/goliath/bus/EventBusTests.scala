package matangolan.goliath.bus

import java.util.concurrent.{CountDownLatch, TimeUnit}

import matangolan.goliath._

class EventBusTests extends DefaultSpec{

  object TestEvent extends Event
  object SecondEvent extends Event

  "Sequential-EventBus" should "trigger a subscribed handler when event is published" in {

    val bus = new SequentialEventBus("test_event_bus")
    val latch = new CountDownLatch(1)

    bus.subscribe(TestEvent){ev=>
      latch.countDown()
    }

    bus.publish(TestEvent)
    latch.await(10, TimeUnit.SECONDS)

    latch.getCount should be (0)
  }

  it should "trigger two subscribed handler when event is published" in {

    val bus = new SequentialEventBus("test_event_bus")
    val latch = new CountDownLatch(2)

    bus.subscribe(TestEvent){ev=>latch.countDown()}

    bus.subscribe(TestEvent){ev=>latch.countDown()}

    bus.publish(TestEvent)
    latch.await(10, TimeUnit.SECONDS)

    latch.getCount should be (0)

  }

  it should "trigger the correct handler when there is more then one event" in {

    val bus = new SequentialEventBus("test_event_bus")
    val latch = new CountDownLatch(2)

    bus.subscribe(SecondEvent){_=>latch.countDown()}
    bus.subscribe(SecondEvent){_=>latch.countDown()}

    bus.subscribe(TestEvent){_=>latch.countDown()}

    bus.publish(SecondEvent)
    latch.await(10, TimeUnit.SECONDS)

    latch.getCount should be (0)

  }

}
