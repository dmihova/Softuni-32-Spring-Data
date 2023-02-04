package su.productsshop.entity.user;

import java.util.List;

public class UsersCountWrapperDTO {

        private Integer usersCount;
        private List<UserWrapperDetailsDTO> users;

        public UsersCountWrapperDTO(List<UserWrapperDetailsDTO> users) {
            this.users = users;
            this.usersCount = users.size();
        }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserWrapperDetailsDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserWrapperDetailsDTO> users) {
        this.users = users;
    }
}
