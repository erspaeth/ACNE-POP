package CS415;

public interface IRuleSet {
		
	public void applyRuleset(Grid input, Grid target);
	public String getRuleName();
	public String getRuleDescription();
}
