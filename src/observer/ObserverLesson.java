package observer;

import java.util.ArrayList;
import java.util.List;

/* Pattern: Observer
 * ********************
 * DESCRIPTION
 * ********************
 * EN:
 * This is a programming pattern that allows one or more classes, so called "Observers",
 * follow the changes in other classes, so called "Subjects" (in other words, subjects of observation).
 * When a Subject changes, one or more Observer receive information about the incident and according to the prescribed
 * functional respond to this situation. Subject should not have information about Observer fields, while providing
 * methods for encapsulating their fields. Subject has a specific Observer list, which contents can track its status.
 * Observer must have access to the Subject fields in order to somehow react to changes in certain states of this class.
 * In other words, there is a Subject object that maintains a list of its observers and automatically notifies them
 * about any changes in their states, usually by calling one of their methods, which they will later call
 * a suitable method in the Observer to notify it of the change.
 * Problems with this pattern:
 * - A one-to-many dependency between objects should be defined without making the objects tightly coupled.
 * - It should be ensured that when one object changes state an open-ended number of dependent objects are updated automatically.
 * - It should be possible that one object can notify an open-ended number of other objects.
 * RU:
 * Это паттерн програмирования, который позволяет одному или более класов, так званым "Следящим"("Observers"),
 * следить за изменениями в других класах, так званых "Предметах"(Subjects), иными словами, предметах наблюдения.
 * При изменениях в Subject, один или более Observer получают информацию о произошедшем и согласно прописаному
 * функционалу реагируют на эту ситуацию. Subject не должен иметь информации о полях Observer, при этом предоставляя
 * методы для инкапсуляции своих полей. Subject имеет конкретный список Observer, которые могут отслеживать его состояния.
 * В свою очередь Observer должен иметь доступ к полям Subject чтобы как-тореагировать на изменения тех или иных состояний данного класса.
 * Иными словами, существует объект Subject, который поддерживает список своих наблюдателей и автоматически уведомляет их
 * о любых изменениях своих состояний, как правило вызовом одного из своих методов, которых в последствии вызовет
 * подходящий метод в Observer, чтобы известить его об изменении.
 * Проблемы этого паттерна:
 * - Должна быть определена зависимость «один-ко-многим» между объектами без жесткой привязки объектов.
 * - Должна быть обеспечена возможность АВТОМАТИЧЕСКОГО оповещения, в некторых случаях, БОЛЬШОГО кол-ва объектов.
 * - Должно быть возможным автоматическое оповещение, в некторых случаях, БОЛЬШОГО кол-ва объектов.
 */
public class ObserverLesson{

    public void main(){
        ConcreteSubject subject = new ConcreteSubject();
        ConcreteObserver1 observer1 = new ConcreteObserver1("Observer 1", subject);
        ConcreteObserver2 observer2 = new ConcreteObserver2("Observer 2", subject);
        subject.attach(observer1);
        subject.attach(observer2);
        subject.setValue(3);
        subject.setValue(8);
    }
}

interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObserver();
}

interface Observer {
    void update();
}

class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    private int value;

    public void setValue(int value) {
        this.value = value;
        notifyObserver();
    }

    public int getValue() {
        return value;
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for (Observer o : observers) {
            o.update();
        }
    }
}

class ConcreteObserver1 implements Observer {

    private ConcreteSubject subject;
    private String name;

    public ConcreteObserver1(String name, ConcreteSubject subject) {
        this.subject = subject;
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(""+this.name+": notified about changes in Subject('value' changed to "+subject.getValue());
    }

}

class ConcreteObserver2 implements Observer {

    private ConcreteSubject subject;
    private String name;

    public ConcreteObserver2(String name, ConcreteSubject subject) {
        this.subject = subject;
        this.name = name;
    }

    @Override
    public void update() {
        String out = "";
        for (int i = 0; i < subject.getValue(); i++) {
            out += "*";
        }
        System.out.println(""+this.name+": notified about changes in Subject('value' changed to "+subject.getValue());
    }
}