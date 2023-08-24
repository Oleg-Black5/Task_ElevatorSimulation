package olegivanov;

public class Floor {

    // Свойства
    private int floorElevator1;
    private State floorElevatorState1;
    private int floorElevator2;
    private State floorElevatorState2;
    private boolean callButton;

    // Builder
    public static class Builder {
        private int floorElevator1;
        private State floorElevatorState1;
        private int floorElevator2;
        private State floorElevatorState2;
        private boolean callButton = false;

        public Builder floorElevator1(int val) {
            floorElevator1 = val;
            return this;
        }

        public Builder floorElevator2(int val) {
            floorElevator2 = val;
            return this;
        }

        public Builder floorElevatorState1(State val) {
            floorElevatorState1 = val;
            return this;
        }

        public Builder floorElevatorState2(State val) {
            floorElevatorState2 = val;
            return this;
        }

        public Builder callButton(boolean val) {
            callButton = val;
            return this;
        }

        public Builder() {
        }

        public Floor build() {
            return new Floor(this);
        }
    }

    private Floor(Builder builder) {
        floorElevator1 = builder.floorElevator1;
        floorElevator2 = builder.floorElevator2;
        floorElevatorState1 = builder.floorElevatorState1;
        floorElevatorState2 = builder.floorElevatorState2;
        callButton = builder.callButton;
    }
//    public Floor(int floorElevator1, State floorElevatorState1, int floorElevator2, State floorElevatorState2, boolean callButton) {
//        this.floorElevator1 = floorElevator1;
//        this.floorElevatorState1 = floorElevatorState1;
//        this.floorElevator2 = floorElevator2;
//        this.floorElevatorState2 = floorElevatorState2;
//        this.callButton = callButton;
//    }

    // Методы
    public int getFloorElevator1() {
        return floorElevator1;
    }

    public State getFloorElevatorState1() {
        return floorElevatorState1;
    }

    public int getFloorElevator2() {
        return floorElevator2;
    }

    public State getFloorElevatorState2() {
        return floorElevatorState2;
    }

    public void setFloorElevator1(int floorElevator1) {
        this.floorElevator1 = floorElevator1;
    }

    public void setFloorElevatorState1(State floorElevatorState1) {
        this.floorElevatorState1 = floorElevatorState1;
    }

    public void setFloorElevator2(int floorElevator2) {
        this.floorElevator2 = floorElevator2;
    }

    public void setFloorElevatorState2(State floorElevatorState2) {
        this.floorElevatorState2 = floorElevatorState2;
    }

    public void pressCallButton() {
        this.callButton = true;
    }
    public void unPressCallButton() {
        this.callButton = false;
    }
    public int checkElevator(int currentFloor) {
        if (!(this.floorElevator1 ==currentFloor || this.floorElevator2==currentFloor)){
            System.out.printf("Ни одного лифта нет на этаже! Надо нажать на кнопку вызова");
            return -1;
        }

        if (this.floorElevatorState1 == State.СТОИТ_С_ОТКРЫТЫМИ_ДВЕРЯМИ &&
                this.floorElevatorState2 == State.СТОИТ_С_ОТКРЫТЫМИ_ДВЕРЯМИ &&
                this.floorElevator1==this.floorElevator2
        ) {
            return (int) Math.random();
        } else if (this.floorElevatorState1 == State.СТОИТ_С_ОТКРЫТЫМИ_ДВЕРЯМИ
        ) {
            System.out.printf("Выбран лифт 1");
                       return 0;
        } else {
            System.out.printf("Выбран лифт 2");
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Текущий этаж лифта 1: " + this.floorElevator1 + ", состояние лифта 1: " + this.floorElevatorState1 +
                ", текущий этаж лифта 2: " + this.floorElevator2 + ", состояние лифта 2: " + this.floorElevatorState2 +
                ", статус кнопки вызова лифта: " + this.callButton;
    }
}
