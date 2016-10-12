﻿using System.Collections.Generic;

namespace ProgramTree {
    public enum AssignType { Assign, AssignPlus, AssignMinus, AssignMult, AssignDivide };

    // базовый класс для всех узлов    
    public class Node { }

     // базовый класс для всех выражений
    public class ExprNode : Node { }

    public class IdNode : ExprNode {
        public string Name { get; set; }
        public IdNode(string name) { Name = name; }
    }

    public class IntNumNode : ExprNode {
        public int Num { get; set; }
        public IntNumNode(int num) { Num = num; }
    }

    // базовый класс для всех операторов
    public class StatementNode : Node { }

    public class AssignNode : StatementNode {
        public IdNode Id { get; set; }
        public ExprNode Expr { get; set; }
        public AssignType AssOp { get; set; }
        public AssignNode(IdNode id, ExprNode expr, AssignType assop = AssignType.Assign) {
            Id = id;
            Expr = expr;
            AssOp = assop;
        }
    }

    public class CycleNode : StatementNode {
        public ExprNode Expr { get; set; }
        public StatementNode Stat { get; set; }
        public CycleNode(ExprNode expr, StatementNode stat) {
            Expr = expr;
            Stat = stat;
        }
    }

    public class WhileNode : StatementNode {
        public ExprNode Expr { get; set; }
        public StatementNode Stat { get; set; }
        public WhileNode(ExprNode expr, StatementNode stat) {
            Expr = expr;
            Stat = stat;
        }
    }

    public class RepeatNode : StatementNode {
        public ExprNode Expr { get; set; }
        public StatementNode Stat { get; set; }
        public RepeatNode(ExprNode expr, StatementNode stat) {
            Expr = expr;
            Stat = stat;
        }
    }

    public class ForNode : StatementNode {
        public AssignNode Ass { get; set; }
        public ExprNode Expr { get; set; }
        public StatementNode Stat { get; set; }
        public ForNode(AssignNode a, ExprNode e, StatementNode s) {
            Ass = a;
            Expr = e;
            Stat = s;
        }
    }

    public class WriteNode : StatementNode {
        public ExprNode Expr { get; set; }
        public WriteNode(ExprNode e) {
            Expr = e;
        }
    }

    public class BlockNode : StatementNode {
        public List<StatementNode> StList = new List<StatementNode>();
        public BlockNode(StatementNode stat) {
            Add(stat);
        }
        public void Add(StatementNode stat) {
            StList.Add(stat);
        }
    }

}