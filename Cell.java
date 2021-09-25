public class Cell
{
    private int _x;
    private int _y;
        
    public CellType Type;
	public boolean IsActive;
        
    public Cell(int x, int y)
    {
        _x = x;
        _y = y;
        Type = CellType.Empty;
    }
        
    public int getX()
    {
        return _x;
    }
        
    public int getY()
    {
        return _y;
    }

	public void reset()
	{
		Type = CellType.Empty;
		IsActive = false;
	}

	@Override
	public String toString()
	{
		String text = Type == CellType.Tic ? "X" : (Type == CellType.Tac ? "O" : " ");

		if (IsActive)
			text = text.toLowerCase();

		return text;
	}
}
