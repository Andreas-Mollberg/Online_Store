package org.example;

public class User {

        private int id;
        private String username;
        private String password;

        public User(int id, String username) {
            this.id = id;
            this.username = username;
        }

        public User(int id, String username, String password) {
            this(id, username);
            this.password = password;
        }

        public User(String username, String password) {
            this(0, username, password);
        }

        public User(String username) {
            this(0, username);
        }

        public User() {
            this(0, "");
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }


        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }



}
