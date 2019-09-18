package servlet.history;

import java.util.Comparator;

import model.Hostel;

public class HostelComparator implements Comparator<Hostel>{

	@Override
	public int compare(Hostel o1, Hostel o2) {
		return -o1.getTotalIncome().subtract(o2.getTotalIncome()).intValue();
	}
	
}
