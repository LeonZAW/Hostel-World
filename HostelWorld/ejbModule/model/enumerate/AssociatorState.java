package model.enumerate;

public enum AssociatorState {
	NOT_ACTIVATED,
	ACTIVATED,
	SUSPENDED,
//	DUE,
	STOP;
	
	public static String getString(AssociatorState state){
		switch (state) {
		case NOT_ACTIVATED:
			return "未激活";
		case ACTIVATED:
			return "已激活";
		case SUSPENDED:
			return "挂起";
		case STOP:
			return "停用";
		default:
			return "状态未知";
		}
	}
}
