package factory_method;

public class FactoryMethodLesson {


    /* Pattern: Factory Method
     * (Creational pattern)
     * ********************
     * DESCRIPTION
     * ********************
     * EN:
     * Factory Method (also known as Virtual Constructor) -
     * generating design pattern that provides an interface to subclasses (child classes)
     * to create instances of a class. At the time of creation, they can determine
     * what class to create. In other words, this template delegates the creation of objects to successors.
     * parent class. This allows you to use in the program code not specific classes, but
     * manipulate abstract objects at a higher level.
     * Purpose: Defines an interface for creating an object, but leaves the subclasses a decision on which
     * class to instantiate. The factory method allows the class to delegate the creation of subclasses.
     * Used when:
     * - the class does not know in advance what objects of subclasses it needs to create.
     * - the class is designed so that the objects it creates are specified by subclasses.
     * - the class delegates its duties to one of several subsidiary subclasses,
     * and it is planned to localize knowledge about which class assumes these responsibilities.
     * The pattern allows you to make the code for creating objects more universal, without being tied to specific
     * classes (ConcreteProduct), and operating only with a common interface (Product); Factory method allows
     * establish a connection between parallel class hierarchies.
     * Among the drawbacks is the need to create a Creator for each new type.
     * product (ConcreteProduct).
     * In other words, the Factory Method assumes encapsulation in this component of a certain algorithm.
     * constructing and initializing something that is being constructed. In this case, the design algorithm
     * and initialization is quite complicated. In turn, the abstract factory involves only the creation
     * of some object.
     * RU:
     * Фабричный метод (англ. Factory Method также известен как Виртуальный конструктор (англ. Virtual Constructor)) —
     * порождающий шаблон проектирования, предоставляющий подклассам (дочерним классам) интерфейс
     * для создания экземпляров некоторого класса. В момент создания наследники могут определить,
     * какой класс создавать. Иными словами, данный шаблон делегирует создание объектов наследникам
     * родительского класса. Это позволяет использовать в коде программы не специфические классы, а
     * манипулировать абстрактными объектами на более высоком уровне.
     * Цель: Определяет интерфейс для создания объекта, но оставляет подклассам решение о том, какой
     * класс инстанцировать. Фабричный метод позволяет классу делегировать создание подклассов.
     * Используется, когда:
     * - классу заранее неизвестно, объекты каких подклассов ему нужно создавать.
     * - класс спроектирован так, чтобы объекты, которые он создаёт, специфицировались подклассами.
     * - класс делегирует свои обязанности одному из нескольких вспомогательных подклассов,
     * и планируется локализовать знание о том, какой класс принимает эти обязанности на себя.
     * Паттерн позволяет сделать код создания объектов более универсальным, не привязываясь к конкретным
     * классам (ConcreteProduct), а оперируя лишь общим интерфейсом (Product); Фабричный метод позволяет
     * установить связь между параллельными иерархиями классов.
     * Среди недостатков - необходимость создавать наследника Creator для каждого нового типа
     * продукта (ConcreteProduct).
     * Иными словами, Factory Method предполагает инкапсуляцию в этом компоненте некоего алгоритма
     * конструирования и инициализации чего-то, что конструируется. При этом алгоритм конструирования
     * и инициализации достаточно сложен. В свою очередь абстрактная фабрика предполагает только создание
     * какого-то объекта - и все.
     */

    public void main(){
        Creator[] creators = { new Nokia(), new Samsung() };
        for (Creator creator: creators){
            Phone phone = creator.factoryMethod();
            phone.printInfo();
        }
    }
}

abstract class Phone{
    private String name;

    protected Phone(String name){
        this.name = name;
    }

    public void printInfo(){
        System.out.printf("Phone{Name: %s}\n", this.name);
    }
}

class NokiaPhone extends Phone {

    public static final String name = "Nokia";

    public NokiaPhone() {
        super(name);
    }
}

class SamsungPhone extends Phone {

    public static final String name = "Samsung";

    public SamsungPhone() {
        super(name);
    }
}

abstract class Creator {
    public abstract Phone factoryMethod();
}

class Nokia extends Creator {
    @Override
    public Phone factoryMethod() { return new NokiaPhone(); }
}

class Samsung extends Creator {
    @Override
    public Phone factoryMethod() { return new SamsungPhone(); }
}
