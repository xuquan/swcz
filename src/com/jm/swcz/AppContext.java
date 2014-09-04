package com.jm.swcz;

import android.content.Context;

public class AppContext {
	
	private static Context ctx = null;
	
	public static Context getAppContext(){
		return ctx;
	}
	
	public static void setAppContext(Context ctx){
		AppContext.ctx = ctx;
	}
}
