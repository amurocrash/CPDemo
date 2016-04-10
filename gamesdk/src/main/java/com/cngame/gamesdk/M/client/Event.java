package com.cngame.gamesdk.M.client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Event
{
	public enum EventType
	{
		LOGIN_SUCCEED,
		LOGIN_FAILED,
		REGISTER_SUCCESS,
		REGISTER_FAILED;
	}
	
	EventType value();
}
