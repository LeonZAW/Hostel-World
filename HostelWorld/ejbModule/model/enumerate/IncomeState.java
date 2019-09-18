package model.enumerate;

public enum IncomeState {
	AUDITING,
	COMPLETE,
	AUDITING_RESERVE;
	
	public static String getString(IncomeState state){
		switch (state) {
		case AUDITING:
			return "款项已提交系统";
		case COMPLETE:
			return "交易已完成";
		case AUDITING_RESERVE:
			return "款项已提交系统";
		default:
			return "状态未知";
		}
	}
}
