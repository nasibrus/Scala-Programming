//class Main{
//  def gimmeAName(): String = "Static OOP-101"
//}
//
//
//object Main {
////  def main(args: Array[String]): Unit = {
////    print("Hello World!")
//  def gimmeAStaticName(): String = "Static OOP-101"
//
//  def main(args: Array[String]): Unit = {
//    val myMainInstance = new Main()
//
//    println(myMainInstance.gimmeAName())
//    println(Main.gimmeAStaticName())
//    println("Hello World!")
//
//
//  }
//}
//until 100 stopping in  99 , to 100 stopping in 100
//object Main {
//
//  def main(args: Array[String]): Unit = {
//    for (i: Int <- 0 to 100) {
//      if (i % 3 == 0 && i % 5 == 0 )
//        println("FizzBuzz")
//      else if (i % 5 == 0)
//        println("Buzz")
//      else if (i % 3 == 0)
//        println("Fizz")
//      else
//      println(i)
//    }
//  }
//}
object Main {

  def NthFizzBuzz(i: Int): String =
      if (i % 3 == 0 && i % 5 == 0 )
        "FizzBuzz"
      else if (i % 5 == 0)
        "Buzz"
      else if (i % 3 == 0)
        "Fizz"
      else
       i.toString

    def main(args: Array[String]): Unit = {
      for (i: Int <- 1 to 100) {
        println(NthFizzBuzz(i))
      }
    }

}


