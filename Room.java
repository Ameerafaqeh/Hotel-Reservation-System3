
public class Room {
	//The rum class that contains information about rum
	private int roomNumber;
	private String roomType;
	private boolean isRoomAvailable;
	private String customerName;
 
	public Room() {
	
	}

	public Room(int roomNumber, String data, String data2) {
		this.roomNumber = roomNumber;
		this.isRoomAvailable = true;
	}

	public boolean isRoomAvailable() {
		return isRoomAvailable;
	}

	public void reserve() {
		isRoomAvailable = false;

	}

	public void delete() {
		isRoomAvailable = true;

	}

	public int getRoomNumber() {
		return roomNumber - 1;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
