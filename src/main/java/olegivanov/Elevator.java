package olegivanov;


public class Elevator {
    public static final int FLOORS = 20; // 20 этажей
    // Свойства
    private int currentFloor;
    private State state;
    private boolean peopleMoving;

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
        System.out.println(this.toString());
    }

    public State getState() {
        return state;
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
        System.out.println("Осторожно  двери закрываются");
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
            //    throw new Exception("Никуда не едем получается");
            return;
        }

        if (fromFloor < toFloor) {
            this.setState(State.ЕДЕТ_ВВЕРХ);
            for (int i = fromFloor + 1; i <= toFloor; i++) {
                try {
                    Thread.sleep(1000);
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
                    Thread.sleep(1000);
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
        return "Текущий этаж: " + this.currentFloor + ", состояние: " + this.state;
    }
}
