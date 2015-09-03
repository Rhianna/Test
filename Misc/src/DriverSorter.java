import java.util.*;

public class DriverSorter {
	public static void main(String[] args) {
		DriverSorter ds = new DriverSorter();
		ds.add('A', 1, 10);
		ds.add('B', 5, 8);
		ds.report();
		System.out.println();
		
		DriverSorter ds2 = new DriverSorter();
		ds2.add('A', 1, 2);
		ds2.add('B', 1, 3);
		ds2.add('C', 1, 4);
		ds2.add('D', 2, 3);
		ds2.add('E', 2, 4);
		ds2.add('F', 2, 4);
		ds2.add('A', 1, 2); // duplicate
		ds2.report();
	}
	
	private TreeMap<Integer, LinkedHashSet<Event>> events = new TreeMap<>();
	public void add(char driver, int in, int out) {
		LinkedHashSet<Event> list = events.get(in);
		if (list == null) list = new LinkedHashSet<Event>();
		list.add(new Event(driver, in, EventType.IN));
		events.put(in, list);
		
		list = events.get(out);
		if (list == null) list = new LinkedHashSet<Event>();
		list.add(new Event(driver, out, EventType.OUT));
		events.put(out, list);
	}
	
	public void report() {
		LinkedHashSet<Character> driverList = new LinkedHashSet<>();
		for (Integer time : events.keySet()) {
			System.out.print("At time " + time + ": ");
			for (Event event : events.get(time)) {
				if (event.getType().equals(EventType.IN)) {
					driverList.add(event.getDriver());
				} else {
					driverList.remove(event.getDriver());
				}
			}
			System.out.println(driverList.size() + " drivers. " + driverList);
		}
	}
}

enum EventType {IN, OUT}

class Event implements Comparable<Event> {
	private char driver;
	private int time;
	private EventType type;
	public Event(char driver, int time, EventType type) {
		this.driver = driver; this.time = time; this.type = type;
	}
	
	public int compareTo(Event other) {
		if (time != other.time) return time - other.time;
		if (driver != other.driver) return driver - other.driver;
		if (type.equals(other.type)) return 0;
		return 1; // A little sloppy, but there are only two values.
	}
	
	public char getDriver() { return driver; }
	public int time() { return time; }
	public EventType getType() { return type; }
}
