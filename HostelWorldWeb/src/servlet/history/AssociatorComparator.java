package servlet.history;

import java.util.Comparator;

import model.Card;

public class AssociatorComparator implements Comparator<Card>{

	@Override
	public int compare(Card o1, Card o2) {
		return -o1.getTotalConsum().subtract(o2.getTotalConsum()).intValue();
	}
	
}
