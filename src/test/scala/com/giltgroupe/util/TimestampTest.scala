package com.giltgroupe.util

import java.sql.{Timestamp => SqlTimestamp}
import java.util.Date
import org.scalatest.testng.TestNGSuite
import org.testng.annotations.Test
import org.scalatest.matchers.ShouldMatchers

/**
 * Document Me.
 *
 * @author Eric Bowman
 * @since 17/09/2011 08:51
 */

class TimestampTest extends TestNGSuite with ShouldMatchers {

  @Test
  def testBasics() {
    Timestamp.setArtificialNow(0);  // disable anything somebody else may have turned on
    val before = new Timestamp(new Date)
    Thread.sleep(1)
    val after = new Timestamp(System.currentTimeMillis())

    assert(after.toDate.after(before.toDate))
    assert(before.getTime < after.getTime)
    assert(before.## != after.##)
    new Timestamp(0).toString should be("Thu, 1 Jan 1970 00:00:00 UTC")
    assert(before.compareTo(after) < 0)
    assert(after.compareTo(before) > 0)
    before.compareTo(before) should be(0)

    val first = new Timestamp()
    Thread.sleep(1)
    val second = new Timestamp()
    assert(first.getTime < second.getTime)

    before.toDate should be(new Date(before.getTime))
    before.toSqlTimestamp should be(new SqlTimestamp(before.getTime))
    assert (before == before.toDate)
  }

  @Test
  def testEquals {
    val before = new Timestamp(123456L)
    val after  = new Timestamp(654321L)
    assert(!before.equals(123456L)) // equals does not compare with other types
    assert(before == 123456L)       // ... but == does
    assert(before != 654321L)
    assert(before.equals(Timestamp(123456L)))
    assert(before == Timestamp(123456L))
    assert(before != after)
    assert(!before.equals(new Date(123456L))) // equals does not compare with other types
    assert(before == new Date(123456L))       // ... but == does
    assert(before != new Date(654321L))

    assert(!before.equals(null))
    assert(!before.equals(new AnyRef))
  }

  @Test
  def testPlusAndMinus {
    Timestamp(100) + 200 should be(Timestamp(300))
    Timestamp(300) - 200 should be(Timestamp(100))
    Timestamp(100) - 200 should be(Timestamp(-100))

    Timestamp(100) + Timestamp(200) should be(Timestamp(300))
    Timestamp(300) - Timestamp(200) should be(Timestamp(100))

    Timestamp(100) + new Date(200) should be(Timestamp(300))
    Timestamp(300) - new Date(200) should be(Timestamp(100))

    -Timestamp(123) should be(Timestamp(-123))
  }

  @Test
  def testLogicFunctions {
    Timestamp(100) > 200 should be(false)
    Timestamp(100) > 100 should be(false)
    Timestamp(200) > 100 should be(true)
    Timestamp(100) >= 200 should be(false)
    Timestamp(100) >= 100 should be(true)
    Timestamp(200) >= 100 should be(true)
    Timestamp(100) <= 200 should be(true)
    Timestamp(100) <= 100 should be(true)
    Timestamp(200) <= 100 should be(false)
    Timestamp(100) < 200 should be(true)
    Timestamp(100) < 100 should be(false)
    Timestamp(200) < 100 should be(false)
    Timestamp(200) == 100 should be(false)
    Timestamp(200) == 200 should be(true)

    Timestamp(100) > Timestamp(200) should be(false)
    Timestamp(100) > Timestamp(100) should be(false)
    Timestamp(200) > Timestamp(100) should be(true)
    Timestamp(100) >= Timestamp(200) should be(false)
    Timestamp(100) >= Timestamp(100) should be(true)
    Timestamp(200) >= Timestamp(100) should be(true)
    Timestamp(100) <= Timestamp(200) should be(true)
    Timestamp(100) <= Timestamp(100) should be(true)
    Timestamp(200) <= Timestamp(100) should be(false)
    Timestamp(100) < Timestamp(200) should be(true)
    Timestamp(100) < Timestamp(100) should be(false)
    Timestamp(200) < Timestamp(100) should be(false)
    Timestamp(200) == Timestamp(100) should be(false)
    Timestamp(200) == Timestamp(200) should be(true)

    Timestamp(100) > new Date(200) should be(false)
    Timestamp(100) > new Date(100) should be(false)
    Timestamp(200) > new Date(100) should be(true)
    Timestamp(100) >= new Date(200) should be(false)
    Timestamp(100) >= new Date(100) should be(true)
    Timestamp(200) >= new Date(100) should be(true)
    Timestamp(100) <= new Date(200) should be(true)
    Timestamp(100) <= new Date(100) should be(true)
    Timestamp(200) <= new Date(100) should be(false)
    Timestamp(100) < new Date(200) should be(true)
    Timestamp(100) < new Date(100) should be(false)
    Timestamp(200) < new Date(100) should be(false)
    Timestamp(200) == new Date(100) should be(false)
    Timestamp(200) == new Date(200) should be(true)

    Timestamp(200) == new SqlTimestamp(100) should be(false)
    Timestamp(200) == new SqlTimestamp(200) should be(true)

    Timestamp(200) == "200" should be(false)
    Timestamp(200) == null should be(false)
  }

  @Test
  def testArtificialNowClosure {
    val expectedReturnValue = "did something"
    val actualReturnValue = Timestamp.withArtificialNow(100) {
      assert(Timestamp().getTime == 100)
      assert(Timestamp(200).getTime == 200)
      expectedReturnValue
    }

    actualReturnValue should be (expectedReturnValue)
    assert(Timestamp().getTime != 100)
  }

  @Test
  def testBeforeAfter {
    Timestamp(100) before Timestamp(200) should be (true)
    Timestamp(200) after Timestamp(100) should be (true)
    Timestamp(100) before Timestamp(100) should be (false)
    Timestamp(100) after Timestamp(100) should be (false)

    Timestamp(100) before new Date(200) should be (true)
    Timestamp(200) after new Date(100) should be (true)
    Timestamp(100) before new Date(100) should be (false)
    Timestamp(100) after new Date(100) should be (false)

    Timestamp(100) before 200 should be (true)
    Timestamp(200) after 100 should be (true)
    Timestamp(100) before 100 should be (false)
    Timestamp(100) after 100 should be (false)
  }
}
