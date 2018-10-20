package abstract_factory;

import java.util.ArrayList;
import java.util.List;

public class AbstractFactoryLesson {

    /* Pattern: Abstract Factory
     * (Creational pattern)
     * ********************
     * DESCRIPTION
     * ********************
     * EN:
     * The Abstract Factory pattern is a design method that focuses on production of entire families
     * of factories, the type of which depends on the possible states of the program, either on
     * directly transferred data (encapsulation of the factory type). In turn, the factories themselves
     * can also encapsulate types of manufactured objects depending on the types of listed above
     * getting program states.
     * The client code does not know (or does not take into account) exactly which objects it gets
     * from these factories because it uses a universal interface for their creation. The pattern
     * delimits the details of the implementation of a plurality of objects from their general use
     * in the code, since the creation of an object is carried out using methods provided by the
     * factory interface.
     * This programming pattern differs from a classic factory in the types of objects that can be made.
     * In the case of a factory, these are the final types of entities, the abstract factory is the type
     * of the factory itself.
     * In other words, Abstract Factory - generating design pattern,
     * provides an interface for creating families of interrelated or interdependent objects,
     * not specifying their specific classes. The pattern is implemented by creating an
     * abstract class Factory, which is an interface for creating system components (for example,
     * for windowed interface it can create windows and buttons). Then classes are written that
     * implement this interface.
     * Cons: - it is difficult to add support for a new kind of product
     * RU:
     * Паттерн "Абстрактная фабрика" представляет собой метод проектирования, который ориентирован на
     * изготовление целых семейств фабрик, тип которых зависит от возможных состояний програмы,
     * либо напрямую от переданых данных (инкапсуляция типа фабрики). В свою очередь сами фабрики могут
     * также инкапсулировать типы изготавливаемых объектов в зависимости от вышеперечисленых видов
     * получения состояний програмы.
     * Этот шаблон програмирования отличается от класической фабрики типами изготавлимаемых объектов.
     * В случае фабрики это конечные типы сущностей, абстрактной фабрики - сами типы фабрики.
     * Иными словами, Абстрактная фабрика (англ. Abstract factory) — порождающий шаблон проектирования,
     * предоставляет интерфейс для создания семейств взаимосвязанных или взаимозависимых объектов,
     * не специфицируя их конкретных классов. Шаблон реализуется созданием абстрактного класса Factory,
     * который представляет собой интерфейс для создания компонентов системы (например, для оконного
     * интерфейса он может создавать окна и кнопки). Затем пишутся классы, реализующие этот интерфейс.
     * Минусы: - сложно добавить поддержку нового вида продуктов
     */

    public void main() throws Exception{

        AbstractGUIFactory abstractFactory = new AbstractGUIFactory();

        String randomAppearance = randomAppearance();
        System.out.println("Current OS: " + randomAppearance);

        IGUIFactory GUIFactory = abstractFactory.createFactory(randomAppearance);

        GUIFactory.createButton().paint();
        GUIFactory.createLogo().print();
    }

    /**
     * This is just for the sake of testing this program, and doesn't have to do
     * with Abstract Factory pattern.
     * @return random OS name
     */
    private String randomAppearance() {

        List<String> listOfAppearances = new ArrayList<>();

        listOfAppearances.add("Linux");
        listOfAppearances.add("OSX");
        listOfAppearances.add("error");

        int randomNumber = new java.util.Random().nextInt(3);

        return listOfAppearances.get(randomNumber);
    }
}

class AbstractGUIFactory {

    //income argument "factoryType" can be easily removed, if
    //program type of OS will be checked inside the method (program has
    //previously/right in method detected and created some status like "typeOfOS")
    public IGUIFactory createFactory(String factoryType) throws Exception {
        //instead of factoryType you can switch smth like "System.getProperty("os.name")"
        switch (factoryType) {
            case "Linux": return new LinuxFactory();
            case "OSX": return new OSXFactory();

            default: throw new Exception("No such operating system");
        }
    }
}

interface IButton {
    void paint();
}

interface ILogo {
    void print();
}

class LinuxButton implements IButton {
    @Override
    public void paint() { System.out.println("LinuxButton"); }
}

class OSXButton implements IButton {
    @Override
    public void paint() { System.out.println("OSXButton"); }
}

class LinuxLogo implements ILogo {
    @Override
    public void print() { System.out.println("LinuxLogo"); }
}

class OSXLogo implements ILogo {
    @Override
    public void print() { System.out.println("OSXLogo"); }
}

interface IGUIFactory {
    IButton createButton();
    ILogo createLogo();
}

class LinuxFactory implements IGUIFactory {
    @Override
    public IButton createButton() { return new LinuxButton(); }

    @Override
    public ILogo createLogo() { return new LinuxLogo(); }
}

class OSXFactory implements IGUIFactory {
    @Override
    public IButton createButton() { return new OSXButton(); }

    @Override
    public ILogo createLogo() { return new OSXLogo(); }
}
