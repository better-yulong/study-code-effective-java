package com.zyl.effective.demo.third.singleton;

public enum SingletonEnum {
	
	INSTANCE_SUCC("00","SUCCESS"),
	INSTANCE_FAIL("99","FAIL");
	
	private String key ;
	private String value;
	
	SingletonEnum(String key,String value){
		this.key = key;
		this.value = value;
	}
	
	static String getValue(String key){
		for(SingletonEnum se:SingletonEnum.values()){
			if(se.key.equals(key))
					return se.value;
		}
		return null;
	}

}
