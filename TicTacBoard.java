public class TicTacBoard
{
    private int _targetSum;
    private int _boardSize;
    private Cell[] _winLine;
    private Cell[][] _board;  
    
    public TicTacBoard(int boardSize, int targetSum)
    {
		if (targetSum > boardSize)
			throw new IllegalArgumentException("Target sum cannot be greater than board size");

        _boardSize = boardSize;
        _targetSum = targetSum;
    }
        
    public void initalized()
    {
        _board = new Cell[_boardSize][_boardSize];
            
        for (int i = 0; i < _boardSize; i++)
        {
            for (int j = 0; j < _boardSize; j++)
            {
                _board[i][j] = new Cell(j, i);
            }
        }
    }
        
    public boolean isOpponentWin(Opponent oppenent)
    {
        return hasHorizontalAndVertical(getCellType(oppenent)) || hasDioganal(getCellType(oppenent));
    }
        
    public void activeWinLine()
    {
        for (int i = 0; i < _winLine.length; i++)
		{
			Cell cell = _winLine[i];

			if (cell == null)
			{
				System.out.println("WTF!");
				continue;
			}

			_board[cell.getY()][cell.getX()].IsActive = true;
		}
    }

	public Cell getCell(int x, int y)
	{
		return _board[y][x];
	}
        
    public Cell[][] getBoard()
    {
        return _board;
    }
        
    public void setCellValue(int x, int y, Opponent opponent)
    {
        _board[x][y].Type = getCellType(opponent);
    }
        
    public void reset()
    {
        for (int i = 0; i < _boardSize; i++)
        {
            for (int j = 0; j < _boardSize; j++)
            {
                _board[i][j].reset();
            }
        }
    }
        
    private CellType getCellType(Opponent opponent)
    {
        if (opponent == Opponent.Tic)
            return CellType.Tic;
        else
            return CellType.Tac;
    }
        
    private boolean hasHorizontalAndVertical(CellType type)
    {
        int vSum, hSum;
        Cell[] fristWinLine = new Cell[_targetSum], secondWinLine  = new Cell[_targetSum];
        
        for (int i = 0; i < _boardSize; i++)
        {
            vSum = hSum = 0;
            
            for (int j = 0; j < _boardSize; j++)
            {
                if (_board[i][j].Type == type)
                    fristWinLine[hSum++] = _board[i][j];
                else
                    hSum = 0;
                
                if (_board[j][i].Type == type)
                    secondWinLine[vSum++] = _board[j][i];
                else
                    vSum = 0;
                
                if (vSum == _targetSum || hSum == _targetSum)
                {
                    if (hSum > vSum)
                        _winLine = fristWinLine;
                    else
                        _winLine = secondWinLine;
                    
                    return true;
                }
            }
        }
        
        return false;
    }
        
    private boolean hasDioganal(CellType type)
    {
        int lSum, rSum;
        Cell[] fristWinLine = new Cell[_targetSum], secondWinLine  = new Cell[_targetSum];
        
        for (int i = _boardSize - 1; i >= 0; i--)
        {
            lSum = rSum = 0;
            int y = i;
            
            for (int j = 0; j < _boardSize - i; j++)
            {
                if (_board[y][j].Type == type)
                    fristWinLine[lSum++] = _board[y][j];
                else
                    lSum = 0;
                
                if (_board[j][y].Type == type)
                    secondWinLine[rSum++] = _board[j][y];
                else
                    rSum = 0;
                
                if (lSum == _targetSum || rSum == _targetSum)
                {
                    if (lSum > rSum)
                        _winLine = fristWinLine;
                    else
                        _winLine = secondWinLine;
                    
                    return true;
                }
                
                ++y;
            }
            
            lSum = rSum = 0;
            y = i;
            
            for (int j = _boardSize - 1; j >= i; j--)
            {
                if (_board[j][y].Type == type)
                    fristWinLine[lSum++] = _board[j][y];
                else 
                    lSum = 0;
                
                if (_board[_boardSize - 1 - y][_boardSize - 1 - j].Type == type)
                    secondWinLine[rSum++] = _board[_boardSize - 1 - y][_boardSize - 1 - j];
                else 
                    rSum = 0;
                
                if (lSum == _targetSum || rSum == _targetSum)
                {
                    if (lSum > rSum)
                        _winLine = fristWinLine;
                    else
                        _winLine = secondWinLine;
                    
                    return true;
                }
                
                ++y;
            }
        }
        
        return false;
    }
}
