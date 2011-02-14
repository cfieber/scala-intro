import java.util.Date

//////////////////////////////////////////////////////////////////////////////
// Class
//
class ExampleClass(private val createdOn : Date,
                   val immutableAttribute : String, var mutableAttribute : String) {

    def this(mutable : String) = this(new Date(), "Default Value", mutable)

    override def toString() = {
        createdOn + ": " + immutableAttribute + ", " + mutableAttribute
    }
}

val ex = new ExampleClass(new Date(), "Immutable", "Mutable")
println(ex)
val ex2 = new ExampleClass("Using the alternate construcor")
println(ex2)

// can't even access the private val
//println(ex.createdOn) // doesn't work

// can read but can't change the 'val' constructor arg
println(ex.immutableAttribute)
//ex.immutableAttribute = "Something new" // doesn't work

// can change mutable
ex.mutableAttribute = "Something Else"
println(ex)

//////////////////////////////////////////////////////////////////////////////
// Object - equivalent of static methods in Java
//
object ExampleObject {
    def buildMeAnExampleClass(mutable : String) = new ExampleClass(mutable)

    def apply(mutable : String) = { println("apply called"); new ExampleClass(mutable) }
}

println(ExampleObject.buildMeAnExampleClass("That sure is a long method name"))
println(ExampleObject.apply("That's a bit shorter, but why did you call it apply?"))
println(ExampleObject("Ahh... now I get it"))


//////////////////////////////////////////////////////////////////////////////
// Companions
//
private class Transactional {
    private def beginXA() = println("begin")
    private def commitXA() = println("commit")
    private def rollbackXA() = println("rollback")
    def op1 = println("op1")
    def op2 = println("op2")
}

object Transactional {
    def use(transactionalOperations : (Transactional) => Unit) = {
        val xa = new Transactional
        try {
            xa.beginXA
            transactionalOperations(xa)
            xa.commitXA
        } catch {
            case _ => xa.rollbackXA
        }
    }
}


val operations = (t: Transactional) => {
    t.op1
    t.op2
    t.op1
}

val failure = (t: Transactional) => {
    t.op1
    throw new RuntimeException("Boom goes the dynamite!")
}

Transactional.use(operations)

Transactional.use(failure)

//////////////////////////////////////////////////////////////////////////////
// Case Classes - simple data structures
//
case class Person(firstName: String, lastName: String) //defaults to 'val'

println(new Person("Cam", "Fieber"))
println(Person("Adam", "Jordens"))

println(Person("Cliff", "McCollum").lastName)

case class CanIAddMethods(s: String) {
    def getSomeS = s
}

println(new CanIAddMethods("Yep?").getSomeS)