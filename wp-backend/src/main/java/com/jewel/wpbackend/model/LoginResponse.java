package com.jewel.wpbackend.model;

public class LoginResponse {
	
	private String token;
	
	public LoginResponse(ResponseBuilder builder) {
		this.token = builder.getToken();
	}
	
	
	public String getToken() {
		return token;
	}

	public static ResponseBuilder builder() {
		return new ResponseBuilder();
	}
	
	public static class ResponseBuilder{
		
		private String token;
		
		public ResponseBuilder setToken(String token) {
			this.token = token;
			return this;
		}
		
		public LoginResponse build() {
			return new LoginResponse(this);
		}
		
		public String getToken() {
			return token;
		}

	}
}
