import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Hotel {
	private String hotelName;
	private String hotelAddress;
	private static int MAX_ROOM_NUMBER;
	private Room[] rooms;
	private Scanner scan;
	private String path;

	public Hotel() {
		this.MAX_ROOM_NUMBER = 20;
		this.scan = new Scanner(System.in);
		this.rooms = new Room[MAX_ROOM_NUMBER];
		for (int i = 0; i < MAX_ROOM_NUMBER; i++) {
			rooms[i] = new Room(i + 1, hotelAddress, hotelAddress);
			
		}
		readDataFromFile();

	}

	public Hotel(String hotelName, String hotelAddress, int mAX_ROOM_NUMBER, String path) {// A construction containing
																							// the name of the hotel,
																							// hotel address and number
																							// of rooms
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		MAX_ROOM_NUMBER = mAX_ROOM_NUMBER;
		this.rooms = new Room[MAX_ROOM_NUMBER];
		for (int i = 0; i < MAX_ROOM_NUMBER; i++) {
			rooms[i] = new Room(i + 1, path, path);
		}
		this.path ("../Aproject2/path");
		this.scan = new Scanner(System.in);
		this.readDataFromFile();

	}

	private void path(String string) {
		readDataFromFile();

	}

	public void reserveRoom() {// Room reservation method

		System.out.println("please enter customer's name ");
		String customerName = scan.nextLine();
		listAvaliableRooms();// View unbooked rooms
		int roomNumber = askForRoomNumber();
		System.out.println("Thank you!, The room number: " + roomNumber + " has been reserved for you");
		if (rooms[roomNumber].isRoomAvailable()) {
			rooms[roomNumber].reserve();
			rooms[roomNumber].setCustomerName(customerName);
			selectRoomType(roomNumber);
		} else {
			System.out.println("Sorry, the room is already reserved.");
		}

	}

	public void selectRoomType(int roomNumber) {// Choose the room type from the ones shown
		System.out.println("Please select one of the following room types: ");
		System.out.println("Now ! Select room type" + " \nSingle" + " \nDouble " + " \nking  " + " \nDeluxe ");

		String roomType = scan.next();
		while (!checkRoomType(roomType)) {
			System.out.println("Sorry, there is no room type:" + roomType);
			System.out.println("Please select on of the follwoing room type:\nSingle\nDouble\nKing\nDeluxe ");

			roomType = scan.next();

		}
		if (checkRoomType(roomType)) {
			System.out.println("Thank you, the room type: " + roomType + " has been selected for you");
		}
		rooms[roomNumber].setRoomType(roomType);
	}

	public void changeRoomType() {// Change room type
		int roomNumber = askForRoomNumber();
		if (roomNumber != -1 && rooms[roomNumber - 1].isRoomAvailable()) {

			String oldtype = rooms[roomNumber].getRoomType();
			selectRoomType(roomNumber);
			String newtype = rooms[roomNumber].getRoomType();
			System.out.println("Thank you, your room type has been changed from: " + oldtype + " to:" + newtype);
		}

	}

	public void deleteReservedRoom() {// Delete the reservation from the room that is entered
		int roomNumber = askForRoomNumber();
		if (roomNumber != -1) {

			rooms[roomNumber].delete();
			System.out.println("Thank you, the room number:" + roomNumber + " has been deleted");
		} else {
			System.out.println("The room number: " + roomNumber + " is not booked yet");
		}
	}

	public void deleteAllReservedRooms() {// Delete all previously booked reservations
		for (Room room : rooms) {
			if (!room.isRoomAvailable()) {
				room.delete();
			}
		}
		System.out.println("Sorry ,there is no reserved room to be deleted.");

	}

	public void showAllReservedRoomsInformation() {
		System.out.println("Room Number              Room Type              Customer Name ");
		for (int i = 0; i < rooms.length; i++) {

			if (!rooms[i].isRoomAvailable()) {

				System.out.println(rooms[i].getRoomNumber() + "\t \t         " + rooms[i].getRoomType()
						+ "\t \t        " + rooms[i].getCustomerName());
			}

		}
	}

	private boolean checkRoomType(String roomType) {// Check if the name of the room entered is correct or not
		String[] validTypes = { "Single", "Double", "King", "Deluxe" };
		for (String type : validTypes) {
			if (type.equalsIgnoreCase(roomType)) {
				return true;
			}
		}
		return false;

	}

	private int askForRoomNumber() {// Enter the room number and check if the room is valid or not

		System.out.println("\nplease enter a valid room number between 1 and  " + MAX_ROOM_NUMBER);
		int roomNumber = scan.nextInt();

		if (roomNumber < 1 || roomNumber > MAX_ROOM_NUMBER) {
			System.out.println("sorry, you have entered an invalid room number");
			System.out.println("please enter a valid room number between 1 and " + MAX_ROOM_NUMBER + ":");
			roomNumber = scan.nextInt();

			return -1;

		}
		return roomNumber;

	}

	private void listAvaliableRooms() {// View the list of reserved rooms
		System.out.println("Available rooms :");

		for (int i = 0; i < rooms.length; i++) {
			if (i == rooms.length / 2) {
				System.out.println("");
			}
			if (rooms[i].isRoomAvailable()) {

				System.out.print(rooms[i].getRoomNumber() + "   ");
			}

		}

	}

	public void readDataFromFile() {
		try {
			File file = new File("path");
			this.path= file.getPath();
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] customerData = line.split(",");
				int i = Integer.parseInt(customerData[1]);
				String roomType = customerData[2];
				String customerName = customerData[0];
				rooms[i].reserve();
				rooms[i].setCustomerName(customerData[0]);
				rooms[i].setRoomType(customerData[2]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (Exception e) {
			System.out.println(e);

		}
	
	}

	public void updateCustomersFile() {
		try {
			System.out.println(this.path);
		 FileWriter writer = new FileWriter(this.path);
			for (int i = 0; i < rooms.length; i++) {
			
					 writer.write(rooms[i].getCustomerName()+","+rooms[i].getRoomNumber()+","+rooms[i].getRoomType()+"\n");
writer.close();
				}
			}

		 catch (IOException e) {
			System.out.println("Error: Cannot update the file");
		}
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
}
