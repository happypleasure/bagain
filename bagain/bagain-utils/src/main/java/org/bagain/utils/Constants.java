package org.bagain.utils;

public interface Constants{
	
	public interface CacheKPre{
		public static final String Token = "token";
	}
	
	public interface ResultCode{
		public static final int Success = 1;
		public static final int Common_error = 10010;
		public static final int Token_null = 10011;
		public static final int Token_inValid = 10011;
	}
}