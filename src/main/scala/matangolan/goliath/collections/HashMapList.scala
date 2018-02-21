package matangolan.goliath.collections

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class HashMapList[K, V] {

  private[this] val map = new mutable.HashMap[K, Option[ListBuffer[V]]]

  def put(key: K)(value: V): Unit = {
    map.getOrElse(key, None) match {
      case None   =>  map(key) = Some(ListBuffer[V](value))
      case _      =>  map(key).get += value
    }
  }

  def get(key: K): Seq[V] = map(key).get

  def size(): Int = map.size

  def isEmpty(): Boolean = size()==0

}

object HashMapList{

  def apply[K,V](key: K, value: V) = {
    val map = new HashMapList[K,V]()
    map.put(key)(value)
    map
  }

  def apply[K,V]() = new HashMapList[K,V]()
}
