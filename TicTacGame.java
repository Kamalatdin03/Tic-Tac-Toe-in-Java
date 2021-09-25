public class TicTacGame
{
	private int _moveCount;
	private int _boardSize;
	private int _targetSum;
	private Opponent _curretOpponent;
	private TicTacBoard _board;

	public TicTacGame(int boardSize, int targetSum)
	{
		_boardSize = boardSize;
		_targetSum = targetSum;
		_board = new TicTacBoard(boardSize, targetSum);
	}

	public void initalized()
	{
		_board.initalized();
		_moveCount = _boardSize * _boardSize;
	}

	public void swap()
	{
		_moveCount--;

		String opponent = "";

		if (_curretOpponent == Opponent.Tic)
		{
			_curretOpponent = Opponent.Tac;
			opponent = "O";
		}
		else
		{
			_curretOpponent = Opponent.Tic;
			opponent = "X";
		}

		System.out.println("Turn opponent: " + opponent);
	}

	public void setCellValue(int x, int y)
	{
		_board.setCellValue(y, x, _curretOpponent);
	}

	public boolean isEnd()
	{
		boolean gameIsEnd = _moveCount <= 0;

		if (gameIsEnd) System.out.println("Draw");

		if (!gameIsEnd && _board.isOpponentWin(_curretOpponent))
		{
			if (_curretOpponent == Opponent.Tic) System.out.println("Win oponent X");
			else System.out.println("Win oponent O");

			_board.activeWinLine();

			gameIsEnd =  true;
		}

		if (gameIsEnd) renderBoard();

		return gameIsEnd;
	}

	public void renderBoard()
  	{
	  	String text = "";
	  	int x = 0, y = 0;

		Cell[][] board = _board.getBoard();

		for (int i = 0; i < (3 + (_boardSize - 1) * 2); i++)
		{
			for (int j = 0; j < (_boardSize * 4 + 1); j++)
			{
				if (i % 2 == 0) text += "-";
				else
				{
					if (j % 4 == 0) text += "|";
					else
					{
						if (j % 2 == 0) text += board[y][x++].toString();
						else text += " ";
					}
				}
			}

			if (i % 2 != 0) y++;
			x = 0;

			text += "\n";
		}

		System.out.println(text);
	}

	public void reset()
	{
		_board.reset();
		_moveCount = _boardSize * _boardSize;
	}

	public boolean positionIsValid(int x, int y)
	{
		boolean isValid = x > 0 && x <= _boardSize && y > 0 && y <= _boardSize;

		if (isValid) isValid &= _board.getCell(x - 1, y - 1).Type == CellType.Empty;

		return isValid;
	}
}