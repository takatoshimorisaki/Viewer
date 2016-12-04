package mori;

public enum E_RGB{

	E_RED(0),

	E_GREEN(1),

	E_BLUE(2);

	private int mValue;

	private E_RGB(int aValue){
		mValue = aValue;
	}

	public int mGetValue(){
		return mValue;
	}

}

