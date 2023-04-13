package tests.imageProcessesTest

import imageProcessing.conversionSet.PaulBourkeConversionSet
import org.scalatest.FunSuite

class PaulBarkerSetTest extends FunSuite{

  private val conversionSet = new PaulBourkeConversionSet

  test("Basic test"){
    // on position 10 is 'o'
    val value = 10

    assert(!conversionSet.getConvertedChar(value).equals(' '))
    assert(conversionSet.getConvertedChar(value).equals('o'))
  }

  test("Increase by set length"){
    // on position 10 is 'o'
    val value = 10

    // length of Paul Barker Set of Chars
    val len = 70
    assert(conversionSet.getConvertedChar(value + len).equals('o'))
  }

  test("Black character"){
    // black char == '$'
    val value = 0
    assert(conversionSet.getConvertedChar(value).equals('$'))
  }

  test("White character"){
    // white char == ' '
    val value = 69
    assert(conversionSet.getConvertedChar(value).equals(' '))
  }

  test("Value smaller than 0"){
    // -1 == 69 (white char)
    var value = -1
    assert(conversionSet.getConvertedChar(value).equals(' '))

    // -10 == 70 - 10 == 60
    assert(conversionSet.getConvertedChar(-10).equals(conversionSet.getConvertedChar(60)))

  }

  test("Value bigger than set length"){
    // 250 == 250 % 70 == 40
    assert(conversionSet.getConvertedChar(250).equals(conversionSet.getConvertedChar(40)))
  }
}
