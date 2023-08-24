package olegivanov;

public class Passenger {
    private int weight;
    private int FloorNum;

    public Passenger(int weight) {
        this.weight = weight;
        this.FloorNum = -1;
    }

    public int getWeight() {
        return weight;
    }

    public void setFloorNum(int FloorNum) {
        this.FloorNum = FloorNum;
    }

    public int getFloorNum() {
        return FloorNum;
    }

    public void enterElevator(Elevator elevator) {
        elevator.peopleMoving();
        System.out.println("Пассажир входит в лифт.");
    }
    public void exitElevator(Elevator elevator) {
        elevator.peopleMoving();
        System.out.println("Пассажир выходит из лифта.");
    }

    @Override
    public String toString() {
        return "Пассажир с этажа №"  + this.FloorNum;
    }

}
