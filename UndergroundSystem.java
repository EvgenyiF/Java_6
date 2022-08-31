import java.util.HashMap;

class UndergroundSystem {

    HashMap<String, HashMap<String, Trip>> tripMap;
    HashMap<Integer, CheckIn> checkInMap;

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        System.out.println(undergroundSystem.getAverageTime("Paradise", "Cambridge"));
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
        undergroundSystem.checkIn(10, "Leyton", 24);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
        undergroundSystem.checkOut(10, "Waterloo", 38);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
    }

    public UndergroundSystem() {
        tripMap = new HashMap<>();
        checkInMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        CheckIn checkIn = new CheckIn(stationName, t);
        checkInMap.put(id, checkIn);

    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn checkIn = checkInMap.get(id);
        checkInMap.remove(id);
        String station1 = checkIn.stationName;
        HashMap<String, Trip> map = tripMap.computeIfAbsent(station1, k -> new HashMap<>());
        Trip trip = map.get(stationName);
        if (trip == null) {
            trip = new Trip();
            map.put(stationName, trip);
        }
        trip.add(t - checkIn.time);

    }

    public double getAverageTime(String startStation, String endStation) {
        Trip trip = tripMap.get(startStation).get(endStation);
        return trip.totalTime / trip.count;
    }

    static class Trip {
        int count;
        double totalTime;

        public void add(double time) {
            count++;
            totalTime += time;
        }
    }

    static class CheckIn {
        String stationName;
        int time;

        public CheckIn(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }
}