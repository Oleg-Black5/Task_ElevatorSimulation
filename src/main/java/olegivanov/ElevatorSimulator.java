package olegivanov;

public class ElevatorSimulator {
    private static final int FLOOR_INIT = 1; // Начальный этаж
    private static final int ELEVATOR_NUM = 2; // Кол-во лифтов
    private static Elevator[] elevators = new Elevator[ELEVATOR_NUM];
    private static Floor floor;

    //private static Floor[] floors = new Floor[Elevator.FLOORS];
    public static void main(String[] args) {
        for (int i = 0; i < elevators.length; i++) {
            elevators[i] = new Elevator(FLOOR_INIT);
        }
        floor = new Floor.Builder()
                .floorElevator1(FLOOR_INIT)
                .floorElevatorState1(State.СТОИТ_С_ОТКРЫТЫМИ_ДВЕРЯМИ)
                .floorElevator2(FLOOR_INIT)
                .floorElevatorState2(State.СТОИТ_С_ОТКРЫТЫМИ_ДВЕРЯМИ)
                .build();

        Passenger passenger1 = new Passenger(70);
        Passenger passenger2 = new Passenger(90);
        // Имитация действий пассажиров
        // Сценарий 1
        doScenario(passenger1, floor, 1, 14);
        // Сценарий 2
        doScenario(passenger2, floor, 15, 1);
        // Завершение программы
        System.out.println("Работа программы завершена.");
    }

    private static void doScenario(Passenger passenger, Floor floor, int setupFloor, int destFloor) {
        passenger.setFloorNum(setupFloor);
        System.out.printf("Предпренимается попытка доставить %s на %d этаж \n", passenger.toString(), destFloor);
        floor.pressCallButton();
        int elevatorNum = floor.checkElevator(setupFloor);
        if (elevatorNum == -1) {
            elevatorNum = bestElevator(setupFloor);
        }
        elevators[elevatorNum].move(elevators[elevatorNum].getCurrentFloor(), setupFloor);
        floor.unPressCallButton();
        elevators[elevatorNum].pressOpenDoorButton();
        passenger.enterElevator(elevators[elevatorNum]);
        elevators[elevatorNum].peopleNotMoving();
        elevators[elevatorNum].pressFloorButton(destFloor);
        elevators[elevatorNum].pressOpenDoorButton();
        passenger.exitElevator(elevators[elevatorNum]);
        elevators[elevatorNum].setState(State.СТОИТ_С_ОТКРЫТЫМИ_ДВЕРЯМИ);
        System.out.println(passenger.toString() + " доставлен");
        System.out.println("");
    }

    public static int bestElevator(int currentFloor) {
        int minDist = 0;
        for (int i = 1; i < elevators.length; i++) {
            if (Math.abs(elevators[i].getCurrentFloor() - currentFloor) <
                    Math.abs(elevators[i - 1].getCurrentFloor() - currentFloor)) {
                minDist = i;
            }
        }
        System.out.println("Выбран лифт " + (minDist + 1));
        return minDist;
    }


}
