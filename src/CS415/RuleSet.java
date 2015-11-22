package CS415;

public abstract class RuleSet {

	public static int NUMBER_OF_STATES;
	public static String RULE_NAME;
	public static String DESCRIPTION;
	
	public abstract void applyRuleset(Grid input, Grid target);
	
}
