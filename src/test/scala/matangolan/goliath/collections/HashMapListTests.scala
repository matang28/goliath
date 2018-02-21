package matangolan.goliath.collections

import matangolan.goliath._


class HashMapListTests extends DefaultSpec{

  "A HashMapList" should "be empty after initialization" in {

    val ts = HashMapList[String, String]()

    ts size() should be (0)
    ts isEmpty() should be (true)

  }

  it should "add new entry if the map is empty" in {

    val ts = HashMapList[String, String]()
    val key = "hello"
    val value = "world"

    ts.put(key)(value)

    ts size() should be (1)
    ts.get(key).size should be (1)
    ts.get(key).head should be (value)
  }

  it should "append new entry to the list when the key exists" in {

    val key = "hello"
    val value1 = "world"
    val value2 = "matan"
    val ts = HashMapList[String, String](key, value1)

    ts.put(key)(value2)

    ts size() should be (1)
    ts.get(key).size should be (2)
    ts.get(key).head should be (value1)
    ts.get(key)(1) should be (value2)
  }

  it should "append new entry to the list when the key isn't exists" in {

    val key1 = "hello"
    val key2 = "oh my god"
    val value1 = "world"
    val value2 = "goldline"
    val ts = HashMapList[String, String](key1, value1)

    ts.put(key2)(value2)

    ts size() should be (2)
    ts.get(key1).size should be (1)
    ts.get(key1).head should be (value1)
    ts.get(key2).size should be (1)
    ts.get(key2).head should be (value2)
  }

}
