package factory;

/* Pattern: Factory
 * ********************
 * DESCRIPTION
 * ********************
 * EN:
 *   This is a programming pattern that essentially encapsulates the ability to insert
 * objects of classes beyond the level of abstraction - a factory, the return type of
 * production at the factory depends on certain states in the program or directly
 * parameters that are transmitted to the factory.
 *   Other words, the user class does not need to know what kind of interface
 * implementation it creates, this is determined by the states of some other
 * objects or directly by the input data.
 * RU:
 *   Это паттерн програмирования, который по сути инкапсулирует возможность проинстанциировать обьекты
 * класов череез уровеннь абстракции - фабрика, возвращяемый тип производства на фабрике зависит от
 * определённых состояний в програме либо напрямую параметров которые передаються в фабрику.
 */
public class FactoryLesson {

    public void main(){
        int flag = 1; //status for DarkChocolate
        Factory factory = new ChocoFactory(flag);
        factory.create().printPrice();                  //outputs "10"
        new ChocoFactory(0).create().printPrice(); //outputs "15"
    }
}

//what are we creating? - Chocolate
//chocolate object called entity
interface Chocolate{

    void printPrice();
}

class WhiteChocolate implements Chocolate{

    private final int price = 15;

    @Override
    public void printPrice() { System.out.println("Price: " + this.price); }
}

class DarkChocolate implements Chocolate{

    private final int price = 10;

    @Override
    public void printPrice() { System.out.println("Price: " + this.price); }
}

//with the help of what we creating entities? - Factory
abstract class Factory{
    //state
    int flag;
    //constructor is protected to prevent instantiation
    protected Factory(int flag){ this.flag = flag; }
    //method, that returns product
    abstract Chocolate create();
}

//factory "implementation"
class ChocoFactory extends Factory{

    public ChocoFactory(int flag) {
        super(flag);
    }

    @Override
    Chocolate create() {
        switch (flag){
            case 0: return new WhiteChocolate();
            case 1: return new DarkChocolate();
            default: throw new IllegalArgumentException();
        }
    }
}
