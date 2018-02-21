package matangolan.goliath.bus

import matangolan.goliath.collections.HashMapList

import scala.reflect.runtime.universe
import scala.reflect.runtime.universe._

/**
  * Event bus is a basic mechanism to manage pub-sub communication.
  * You can extend the event class to create your own event.
  *
  * use the subscribe method to add handlers to be called when an event
  * is published.
  */
trait EventBus{

  /**
    * Will trigger handlers bound to event.
    * @param event the event to be published.
    * @param tag the type-tag of the event.
    * @tparam T the Type of the event
    */
  def publish[T >: Event](event: T)(implicit tag: TypeTag[T])

  /**
    * Will bound an event handler to event.
    * @param eventHandler the event handler.
    * @tparam T the event.
    */
  def subscribe[T >: Event](event: T)(eventHandler: EventHandler[Any])(implicit tag: TypeTag[T])
}

/**
  * A basic implementation of Event Bus that uses a mapping of event names to event handlers.
  * when publishing event the all mapped handlers will be invoked sequential.
  * @param name
  */
class SequentialEventBus(name: String) extends EventBus {

  private[this] val handlers = HashMapList[String, EventHandler[Any]]()

  override def publish[T >: Event](event: T)(implicit tag: universe.TypeTag[T]): Unit ={

    val eventName = tag.tpe.toString

    // Run each handler subscribed to this event
    handlers.get(eventName).foreach { h:EventHandler[Any] =>
      h(event)
    }
  }

  override def subscribe[T >:Event ](event: T)(eventHandler: EventHandler[Any])(implicit tag: TypeTag[T]): Unit = {

    val eventName = tag.tpe.toString

    handlers.put(eventName)(eventHandler)
  }
}

/**
  * A factory to create event buses.
  */
object EventBus{
  implicit val eventBus: EventBus = new SequentialEventBus("default")
}
