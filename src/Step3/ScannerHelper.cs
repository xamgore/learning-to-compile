namespace ScannerHelper
{
    public enum Tok { EOF = 0, COMMENT_START, COMMENT_END,
        STRING, COMMENT, ID, INUM, RNUM, COLON, SEMICOLON, ASSIGN, BEGIN, END, CYCLE };
}