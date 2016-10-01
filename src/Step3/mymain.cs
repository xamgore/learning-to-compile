using System;
using System.IO;
using SimpleScanner;
using ScannerHelper;
using System.Collections.Generic;
using System.Linq;

namespace Main
{
    static class mymain
    {
        static void Main(string[] args)
        {
            // Чтобы вещественные числа распознавались и отображались в формате 3.14 (а не 3,14 как в русской Culture)
            System.Threading.Thread.CurrentThread.CurrentCulture = new System.Globalization.CultureInfo("en-US");

            var fname = @"..\..\a.txt";
            Console.WriteLine(File.ReadAllText(fname));
            Console.WriteLine("—————————————————————————");

            Scanner scanner = new Scanner(new FileStream(fname, FileMode.Open));
            var tokens = iterateThrough(scanner);

            tokens.printSourceAndTokensOf(scanner);
            //tokens.getIdentifiersSummary(scanner);
            //tokens.sumIntsAndReals(scanner);
        }

        static IEnumerable<Tok> iterateThrough(Scanner scanner) {
            int tok = 0;

            do {
                tok = scanner.yylex();

                if (tok == (int) Tok.EOF)
                    yield break;
                else
                    yield return (Tok) tok;
            } while (true);
        }

        static void printSourceAndTokensOf(this IEnumerable<Tok> seq, Scanner scanner) { 
            Console.WriteLine(string.Join("\n", seq.Select(scanner.TokToString)));
            Console.WriteLine("—————————————————————————");
            Console.ReadKey();
        }

        /// Подсчитать количество, среднюю, минимальную и максимальную длину всех идентификаторов
        static void getIdentifiersSummary(this IEnumerable<Tok> seq, Scanner scanner) {
            var ids = seq.Where(x => x == Tok.ID).Select(x => scanner.yytext.Length).ToArray();

            Console.WriteLine($"Count: {ids.Count()}");
            Console.WriteLine($"Avg: {1.0 * ids.Sum() / ids.Count()}");
            Console.WriteLine($"Max: {ids.Max()}");
            Console.WriteLine($"Min: {ids.Min()}");
            Console.WriteLine("—————————————————————————");
        }

        // Найти сумму всех целых и сумму всех вещественных в файле
        static void sumIntsAndReals(this IEnumerable<Tok> seq, Scanner scanner) {
            var iSum = 0; var rSum = 0.0;

            foreach (var tok in seq) {
                if (tok == Tok.INUM)
                    iSum += scanner.LexValueInt;
                if (tok == Tok.RNUM)
                    rSum += scanner.LexValueDouble;
            }

            Console.WriteLine($"Int sum: {iSum}\nReal sum: {rSum}\n");
            Console.WriteLine("—————————————————————————");
        }
    }
}
