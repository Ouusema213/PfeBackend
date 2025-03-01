package com.example.auth_microservices.dto;

public class UserDto {


        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String role;
        private String password;

        // Getters
        public String getUsername() {
            return username;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }

        public String getRole() {
            return role;
        }

        // Setters
        public void setUsername(String username) {
            this.username = username;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setRole(String role) {
            this.role = role;
        }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    }
