package olegivanov;

import java.util.Arrays;
import java.util.Scanner;

public class ElevatorSimulator {

    private static final int FLOOR_INIT = 1; // Начальный этаж
    private static final int ELEVATOR_NUM = 2; // Кол-во лифтов
    private static Elevator[] elevators = new Elevator[ELEVATOR_NUM];
    private static Floor[] floors = new Floor[Elevator.FLOORS];
    private static Floor floor;

    //private static Scanner scanner = new Scanner(System.in);

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
//        for (int i = 1; i < floors.length; i++) {
//            floors[i] = new Floor.Builder()
//                    .floorElevator1(FLOOR_INIT)
//                    .floorElevatorState1(State.СТОИТ_С_ОТКРЫТЫМИ_ДВЕРЯМИ)
//                    .floorElevator2(FLOOR_INIT)
//                    .floorElevatorState2(State.СТОИТ_С_ОТКРЫТЫМИ_ДВЕРЯМИ)
//                    .build();
//        }
        int setupFloor = 1;
        int destFloor = 15;
        Passenger passenger1 = new Passenger(70);
        Passenger passenger2 = new Passenger(90);
        // Сценарий 1
        passenger1.setFloorNum(setupFloor);
        floor.pressCallButton();
        int elevatorNum = floor.checkElevator(setupFloor);
        if (elevatorNum == -1) {
            elevatorNum = bestElevator(setupFloor);
        }
        elevators[elevatorNum].move(elevators[elevatorNum].getCurrentFloor(), setupFloor);
        floor.unPressCallButton();
        elevators[elevatorNum].pressOpenDoorButton();
        passenger1.enterElevator(elevators[elevatorNum]);
        elevators[elevatorNum].peopleNotMoving();
        elevators[elevatorNum].pressFloorButton(destFloor);
        elevators[elevatorNum].pressOpenDoorButton();
        passenger1.exitElevator(elevators[elevatorNum]);
        elevators[elevatorNum].setState(State.СТОИТ_С_ОТКРЫТЫМИ_ДВЕРЯМИ);
        System.out.println(elevators[elevatorNum].toString());
        System.out.println("Пассажир доставлен");

        // Имитация действий пассажиров
        //voyage(passenger1, );


        // Завершение программы
        System.out.println("Работа программы завершена.");
    }

    private static void voyage(Passenger passenger, Floor floor, int setupFloor, int destinationFloor) {
        //System.out.println("Пассажир 1 вызывает лифт на 1 этаже.");

//        elevators[0].call(1);
//        System.out.println("Пассажир 1 нажимает кнопку 14 этажа.");
//        elevators[0].pressButton(14);
//        System.out.println("Лифт едет вверх.");
//        while (elevators[0].floor < 14) {
//            elevators[0].move();
//        }
//        System.out.println("Лифт приехал на 14 этаж.");
//        elevators[0].door();
//        System.out.println("Пассажир 1 выходит из лифта.");
//        elevators[0].pressButton(1);
    }

    public static int bestElevator(int currentFloor) {
        int minDist = 0;
        for (int i = 1; i < elevators.length; i++) {
            if ((elevators[i].getCurrentFloor() - currentFloor) < (elevators[i - 1].getCurrentFloor() - currentFloor)) {
                minDist = i;
            }
        }
        return minDist;
    }
//        private static void passenger2 () {
//            System.out.println("Пассажир 2 вызывает лифт на 15 этаже.");
//            elevators[1].call(15);
//            System.out.println("Пассажир 2 нажимает кнопку 1 этажа.");
//            elevators[1].pressButton(1);
//            System.out.println("Лифт едет вниз.");
//            while (elevators[1].floor > 1) {
//                elevators[1].move();
//            }
//            System.out.println("Лифт приехал на 1 этаж.");
//            elevators[1].door();
//            System.out.println("Пассажир 2 выходит из лифта.");
//            elevators[1].pressButton(15);
//        }

}
