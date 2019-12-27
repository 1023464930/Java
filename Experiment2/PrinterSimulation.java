package Experiment2;

public class PrinterSimulation
{
    public static void main(String[] args)
    {
        Fifo s=new Fifo();
        s.loadFile("C:\\Users\\Gabriel\\IdeaProjects\\MyJava\\src\\JavaAlgorithms\\arbitrary.run",
                "C:\\Users\\Gabriel\\IdeaProjects\\MyJava\\src\\JavaAlgorithms\\arbitrary.out");
        s.simulator();
    }
}
