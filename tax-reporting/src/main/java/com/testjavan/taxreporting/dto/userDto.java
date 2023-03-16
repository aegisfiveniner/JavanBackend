package com.testjavan.taxreporting.dto;

public class userDto {
    private Long id;
	private String username;
	private String email;
	private String password;
    // private Set<Role> roles = new HashSet<>();

    
    public userDto(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    public userDto() {
    }

    public Long getId() {
        return id;
    }
   
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
