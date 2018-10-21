package builder;

import java.util.Date;

public class BuilderLesson {

    /* Pattern: Builder
     * (Creational pattern)
     * ********************
     * DESCRIPTION
     * ********************
     * EN:
     * Builder - generating design pattern provides a way to create composite object Separates the construction
     *  of a complex object from its representation so that as a result of one and the same design process can
     * get different views.
     * Among the uses you can note:
     * - the algorithm of creating a complex object should not depend on which parts the object consists of and how
     * they are interconnected;
     * - the design process must provide different views of the constructed object.
     * Also one of the purposes of this pattern is to get rid of the designer with a bunch of parameters
     * and at the same time make the class immutable.
     * The Builder pattern is first and foremost a convenient way to increase the clarity of the code and encapsulate
     * the process of changing the object.
     * RU:
     * Строитель (англ. Builder) — порождающий шаблон проектирования предоставляет способ создания составного
     * объекта. Отделяет конструирование сложного объекта от его представления так, что в результате одного
     * и того же процесса конструирования могут получаться разные представления.
     * Среди применений можно отметить:
     * - алгоритм создания сложного объекта не должен зависеть от того, из каких частей состоит объект и как
     *   они стыкуются между собой;
     * - процесс конструирования должен обеспечивать различные представления конструируемого объекта.
     * Также одно из назначений этого паттерна в том, чтобы избавиться от конструтора с кучей параметров
     * и при этом сделать класс немутабельным.
     * Шаблон "Строитель" это в первую очередь удобный способ для увелечения ясности кода и инкапсуляции
     * самого процеса изменения объекта.
     */
    public void main(){
        //you can also use class called "Director" that can manage the BuilderImpl to create
        //things using some business logic in case you want to hide process of build

        //when class is mutable
        OrderMutableBuilder orderMutableBuilder = new OrderMutableBuilder();
        orderMutableBuilder.setName("Order 1").setDate(new Date());
        System.out.println(orderMutableBuilder.build());

        //when class is immutable
        OrderImmutable.OrderImmutableBuilder orderImmutableBuilder = OrderImmutable.getBuilder();
        orderImmutableBuilder.setName("Order 2").setDate(new Date());
        System.out.println(orderImmutableBuilder.build());
    }
}

//mutable class
class OrderMutable {

    private String name;
    private Date date;

    public String getName() {
        return this.name;
    }

    public Date getDate() {
        return (Date) this.date.clone();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}

class OrderMutableBuilder {

    private OrderMutable order;

    public OrderMutableBuilder(){ this.order = new OrderMutable(); }

    public OrderMutableBuilder setName(String name){
        this.order.setName(name);
        return this;
    }

    public OrderMutableBuilder setDate(Date date){
        this.order.setDate((Date) date.clone());
        return this;
    }

    public OrderMutable build(){
        return this.order;
    }
}

final class OrderImmutable {

    private String name;
    private Date date;

    private OrderImmutable(){ }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public static OrderImmutableBuilder getBuilder(){
        return new OrderImmutable().new OrderImmutableBuilder();
    }

    @Override
    public String toString() {
        return "OrderImmutable{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
    //inner non-static class
    public class OrderImmutableBuilder {

        private OrderImmutableBuilder(){ }

        public OrderImmutable build(){
            return OrderImmutable.this;
        }

        public OrderImmutableBuilder setName(String name){
            OrderImmutable.this.name = name;
            return this;
        }

        public OrderImmutableBuilder setDate(Date date){
            OrderImmutable.this.date = (Date) date.clone();
            return this;
        }
    }
}