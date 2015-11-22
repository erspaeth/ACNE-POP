package CS415;

public abstract class RuleSet {
//
//	public static int NUMBER_OF_STATES;
//	public static String RULE_NAME;
//	public static String DESCRIPTION;
	
	public abstract void applyRuleset(Grid input, Grid target);
	
	@Override public abstract String toString();
	
	public abstract int getNumberOfStates();
	
	public abstract String getDescription();
	
}
