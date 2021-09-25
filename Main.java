import java.util.Scanner;

/*
	------------------------------
	|  ::  |   ::  |    2 | 2048 |
	------------------------------
	| 2048 |  4096 |    4 | 2048 |
	------------------------------
	| 2048 |  4096 | 1024 | 2048 |
	------------------------------
	| 2048 |  4096 | 1024 | 2048 |
	------------------------------
*/

class Main 
{
	public static void main(String[] args) 
  	{
		var input = new Scanner(System.in);
		var game = new TicTacGame(7, 4);

		game.initalized();

		boolean again = false;

		do
		{
			if (again) game.reset(); 

			do			
			{
				game.swap();
				
				int x = 0, y = 0;
				game.renderBoard();

				do
				{
					System.out.print("Write x: ");
					x = input.nextInt();

					System.out.print("\nWrite y: ");
					y = input.nextInt();

					System.out.println("");
				}
				while (!game.positionIsValid(x, y));

				game.setCellValue(x - 1, y - 1);
			}
			while (!game.isEnd());

			System.out.println("Do you want to play again");
			System.out.println("Yes: 1 | No: 0");

			again = input.nextInt() == 1;
		}
		while (again);
  	}
}