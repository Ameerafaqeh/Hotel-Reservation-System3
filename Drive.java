import java.util.Scanner;

public class Drive {
	//Air control class on the main menu inputs
		public static void main(String[] args) {
			Hotel hotel = new Hotel();
			int n;

			do {
				printMainMenu();

				Scanner in = new Scanner(System.in);

				n = in.nextInt();
				switch (n) {
				case 1:
					hotel.reserveRoom();//Reservation method

					break;
				case 2:
					hotel.changeRoomType();//Method change room type
					break;
				case 3:
					hotel.deleteReservedRoom();//Method delete room reservation
					break;
				case 4:
					hotel.deleteAllReservedRooms();//Method delete all room reservations
					break;
				case 5:
					hotel.showAllReservedRoomsInformation();//View all reservations
					
					break;

				case 6:
					System.out.println("Exit System.");//Exit the program and terminate execution
                       hotel.updateCustomersFile();
                       System.out.println("all information is update to the file");
					break;

				default://In the event of entering a number why it is not included in the list numbers
					System.out.println("sorry,you have enter a wrong number ");

				}
			} while (n != 6);
		}

		public static void printMainMenu() { // The main menu method that prints the menu
			System.out.println("--------------------------------------------------");
			System.out.println("please Select an Option(1-6):\n1- Reserve an available room.\r\n"
					+ "2- Change room type.\r\n" + "3- Delete the reserved room \r\n" + "4- Delete all reserved room.\r\n"
					+ "5- show all reserved room information.\r\n" + "6- Exit System .\r\n");
			System.out.println("--------------------------------------------------");
		}
}
