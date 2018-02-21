package matangolan

package object goliath {

  import org.scalamock.scalatest.MockFactory
  import org.scalatest._

  trait DefaultSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors with MockFactory

}
