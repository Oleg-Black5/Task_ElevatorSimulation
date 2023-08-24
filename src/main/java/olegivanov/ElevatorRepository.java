package olegivanov;

import java.util.HashMap;
import java.util.Map;

public class ElevatorRepository {
    Map<Integer, Elevator> elevatorRepository = new HashMap<>();
    public ElevatorRepository (){

    }
    void init(){
        elevatorRepository.put(1, new Elevator(1));
        elevatorRepository.put(2, new Elevator(1));
    }
}
