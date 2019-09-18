package model.enumerate;

public enum HostelState {
	NOT_OPEN,
	AUDITING,
	OPEN;
	
	public static String getString(HostelState state){
		switch (state) {
		case NOT_OPEN:
			return "未审批";
		case AUDITING:
			return "审批中";
		case OPEN:
			return "已开店";
		default:
			return "状态未知";
		}
	}
}
