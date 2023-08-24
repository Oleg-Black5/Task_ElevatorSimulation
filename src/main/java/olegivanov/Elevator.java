package olegivanov;


import lombok.Getter;

@Getter
public class Elevator {
    public static final int FLOORS = 20; // 20 этажей
    public static final int SPEED = 1000; // Скорость движения лифта 1 этаж/1000 мсек.

    private int currentFloor;
    private State state;
    private boolean peopleMoving;

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
        System.out.println(this.toString());
    }

    public void setState(State state) {
        this.state = state;
        System.out.println(this.toString());
    }

    public boolean isPeopleMoving() {
        return peopleMoving;
    }

    public Elevator(int currentFloor) {
        this.currentFloor = currentFloor;
        this.state = State.СТОИТ_С_ОТКРЫТЫМИ_ДВЕРЯМИ;
        peopleNotMoving();
    }

    public void pressFloorButton(int floor) {
        if (floor > 0 && floor <= FLOORS) {
            System.out.println("Нажата кнопка этажа №" + floor);
            pressCloseDoorButton();
            move(this.currentFloor, floor);
        }
    }

    public void pressCloseDoorButton() {
        if (isPeopleMoving()) {
            System.out.println("Не могу закрыть двери");
            return;
        }
        System.out.println("Осторожно! Двери закрываются!");
        this.state = State.ЗАКРЫВАЕТ_ДВЕРИ;
    }

    public void pressOpenDoorButton() {
        System.out.println("Двери открываются ");
        this.state = State.ОТКРЫВАЕТ_ДВЕРИ;
    }

    public void pressCallDispatcherButton() {
        System.out.println("Нажата кнопка вызова диспетчера");
    }

    public void move(int fromFloor, int toFloor) {
        if (fromFloor == toFloor) {
            //    throw new Exception("Никуда не едем");
            return;
        }

        if (fromFloor < toFloor) {
            this.setState(State.ЕДЕТ_ВВЕРХ);
            for (int i = fromFloor + 1; i <= toFloor; i++) {

                try {
                    Thread.sleep(SPEED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.currentFloor++;
                System.out.println(this.toString());
                //System.out.println("Лифт находится на этаже " + i);
            }
        } else {
            this.setState(State.ЕДЕТ_ВНИЗ);
            for (int i = fromFloor - 1; i >= toFloor; i--) {
                this.currentFloor--;

                try {
                    Thread.sleep(SPEED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(this.toString());
                //System.out.println("Лифт находится на этаже " + i);
            }
        }
    }

    public void peopleMoving() {
        this.peopleMoving = true;
    }

    public void peopleNotMoving() {
        this.peopleMoving = false;
    }

    @Override
    public String toString() {
        return "Отчет по лифту: текущий этаж- " + this.currentFloor + ", состояние- " + this.state;
    }
}
