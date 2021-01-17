package files;

public class StrategyManager {
	private Save strategy;

	public void save(String filePath) {
		strategy.saveFile(filePath);
	}

	public void setStrategy(Save strategy) {
		this.strategy = strategy;
	}
}