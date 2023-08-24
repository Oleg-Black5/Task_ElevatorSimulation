package olegivanov;

import lombok.Getter;

@Getter
public class Floor {

    private Elevator elevator1;
    private Elevator elevator2;
    private boolean callButton;

    public Floor(Elevator elevator1,Elevator elevator2) {
        this.elevator1=elevator1;
        this.elevator2=elevator2;
    }

    public void pressCallButton() {
        this.callButton = true;
        System.out.println("Нажата кнопка вызова лифта");
    }

    public void unPressCallButton() {
        this.callButton = false;
    }

    public int checkElevator(int currentFloor) {
        if (!(this.elevator1.getCurrentFloor() == currentFloor || this.elevator2.getCurrentFloor() == currentFloor)) {
            System.out.println("Ни одного свободного лифта нет на этом этаже!");
            return -1;
        }

        if (this.elevator1.getState() == State.СТОИТ_С_ОТКРЫТЫМИ_ДВЕРЯМИ &&
                this.elevator2.getState() == State.СТОИТ_С_ОТКРЫТЫМИ_ДВЕРЯМИ &&
                this.elevator1.getCurrentFloor() == elevator2.getCurrentFloor()
        ) {
            return (int) Math.random();
        } else if (this.elevator1.getState() == State.СТОИТ_С_ОТКРЫТЫМИ_ДВЕРЯМИ
        ) {
            System.out.println("Выбран лифт №1");
            return 0;
        } else {
            System.out.println("Выбран лифт №2");
            return 1;
        }
    }

    @Override
    public String toString() {
        return "ЭТАЖ: Текущий этаж лифта 1- " + this.elevator1.getCurrentFloor() + ", состояние лифта 1- " + this.elevator1.getState() +
                ", текущий этаж лифта 2- " + this.elevator2.getCurrentFloor() + ", состояние лифта 2- " + this.elevator2.getState() +
                ", статус кнопки вызова лифта- " + this.callButton;
    }
}
