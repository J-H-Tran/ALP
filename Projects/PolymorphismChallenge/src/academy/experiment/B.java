package academy.experiment;

import academy.learnprogramming.A;
import org.w3c.dom.ls.LSOutput;

public class B extends A {

    public void printUsingInheritance() {
        System.out.println(a);
    }

    public void printUsingInstantiation() {
        B b = new B();
        System.out.println(b.a);
        //we'll able to use 'a' in a similar manner we use a private variable in B class by instantiation of B class

        A a = new A();
        //System.out.println(a.a); // a has protected acces in package academy.learnprogramming
    }

    /*
    * So, if we are allowed to make a class protected then we can access it inside the package very easily.
    * But, for accessing that class outside of the package we first need to extend that entity (inheritance)
    * in which that class is defined in, which again, is in its package.
    *
    * And since a package cannot be extended (it can be imported) defining a class as protected will make it
    * similar to defining it as default, which it already is/we can do. So, there is no benefit to defining
    * a class as protected it will cause ambiguity.
    *
    * Cannot use 'a' outside of 'B' without another class inheriting 'B'
    * */
}
