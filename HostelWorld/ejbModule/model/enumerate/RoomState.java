package model.enumerate;

public enum RoomState {
	ONSALE,
	RESERVED,
	USING;
	
	public static String getString(RoomState state){
		switch (state) {
		case ONSALE:
			return "正在销售";
		case RESERVED:
			return "预定中";
		case USING:
			return "使用中";
		default:
			return "状态未知";
		}
	}
	
	
}
