import java.util.HashMap;
import java.util.Map;

class ParkingSystem {
    public static void main(String[] args) {
        ParkingSystem obj = new ParkingSystem(1, 1, 0);
        System.out.print(obj.addCar(1) + " ");
        System.out.print(obj.addCar(2) + " ");
        System.out.print(obj.addCar(3) + " ");
        System.out.print(obj.addCar(1) + " ");
    }

    Map<Integer, Integer> map = new HashMap<>();

    public ParkingSystem(int big, int medium, int small) {
        map.put(1, big);
        map.put(2, medium);
        map.put(3, small);
    }

    public boolean addCar(int carType) {
        map.put(carType, map.get(carType) - 1);
        return map.get(carType) >= 0;
    }
}
