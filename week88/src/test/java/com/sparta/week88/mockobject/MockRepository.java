package com.sparta.week88.mockobject;



import java.util.ArrayList;
import com.sparta.week88.models.User;

import java.util.List;
import java.util.Optional;

public class MockRepository {
    //여기는 저장만해주면 될것같아.
    //그리고 맨마지막 중복케이스에서 걸리도록하면 될듯해
    private List<User> users = new ArrayList<>();

    public User save(User user)
    {
        for (User exuser : users)
        {
            if(exuser.getNickName().equals(user.getNickName()))
                throw new IllegalArgumentException("중복"); // 걸리면 중복임
        }
        users.add(user);// 안걸리면 추가함
        return user;
    }

}
