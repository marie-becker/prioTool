package se1_projekt.strategies;

public interface IStrategy {
    //Strategy Interface (Strategy Pattern)

    void analyzeAll();
    boolean analyzeById(int id);
    boolean analyzeDetail(int id);
    void analyzeHints(int id);

}
