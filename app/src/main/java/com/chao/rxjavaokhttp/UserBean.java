package com.chao.rxjavaokhttp;

/**
 * Created by yang2 on 2017/9/20.
 */

public class UserBean extends BaseBean{


    /**
     * status : 0
     * msg : 登陆成功
     * data : {"id":"0000000000000001","username":"大涵哥","password":"","phone":"13666913334","createTime":1502069908000,"updateTime":1502674714000,"role":1}
     */

    private int status;
    private String msg;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 0000000000000001
         * username : 大涵哥
         * password :
         * phone : 13666913334
         * createTime : 1502069908000
         * updateTime : 1502674714000
         * role : 1
         */

        private String id;
        private String username;
        private String password;
        private String phone;
        private long createTime;
        private long updateTime;
        private int role;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }
    }
}
