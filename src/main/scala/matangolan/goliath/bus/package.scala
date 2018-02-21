package matangolan.goliath

import java.time.{LocalDateTime, ZoneId}

package object bus {

  /**
    * An abstract definition of event.
    * @param timestamp the timestamp of the event (defaults to now() in UTC).
    */
  abstract class Event(timestamp: LocalDateTime = LocalDateTime.now(ZoneId.of("UTC")))

  /**
    * A function (handler) to execute when an event is published.
    * @tparam T The event type
    */
  type EventHandler[T >: Event] = (T) => Unit

}
