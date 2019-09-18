package model.enumerate;

public enum ReserveState {
	RESERVED,
	CANCEL,
	COMPLETE;
	
	public static String getString(ReserveState state){
		switch (state) {
		case RESERVED:
			return "预定中";
		case CANCEL:
			return "预定已取消";
		case COMPLETE:
			return "预定已完成";
		default:
			return "状态未知";
		}
	}
}
