package com.example.loginpage;

public class User {
    private User[] records;
    private String id;
    private fields fields;
    private String createTime;

    public User(String id, fields fields1, String createTime) {
        this.id = id;
        this.fields = fields1;
        this.createTime = createTime;
    }

    public User[] getarr(){
        return records;
    }

    public String getId(int i) {
        return records[i].id;
    }

    public String getfieldsPassword(int i){
        return records[i].fields.getPassword();
    }

    public String getfieldsEmail(int i) {
        return records[i].fields.getEmail();
    }

    public fields getFields() {
        return fields;
    }

    public String getCreateTime() {
        return createTime;
    }
}
