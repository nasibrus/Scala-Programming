package tests.imageProcessesTest

import imageProcessing.conversionSet.SimpleConversionSet
import org.scalatest.FunSuite

class SimpleSetTest extends FunSuite{

  private val conversionSet = new SimpleConversionSet

  test("Basic test"){
    // on position 10 is 'o'
    // on position 3 is char '*'
    val value = 3

    assert(!conversionSet.getConvertedChar(value).equals(' '))
    assert(conversionSet.getConvertedChar(value).equals('*'))
  }

  test("Increase by set length"){
    // on position 10 is 'o'
    val value = 3

    // length of Paul Barker Set of Chars
    val len = 10
    assert(conversionSet.getConvertedChar(value + len).equals('*'))
  }

  test("Value smaller than 0"){
    // -1 == 10 - 1 (white char)
    val value = -1
    assert(conversionSet.getConvertedChar(value).equals(' '))

    // -8 == 10 - 8 == 2
    assert(conversionSet.getConvertedChar(-8).equals(conversionSet.getConvertedChar(2)))

  }

  test("Value bigger than set length"){
    // 68 == 68 % 10 == 8
    assert(conversionSet.getConvertedChar(68).equals(conversionSet.getConvertedChar(8)))
  }
}
