package com.llmcu.tulingjiagou.l01jvm.day03.converter;

import com.llmcu.tulingjiagou.l01jvm.day03.dto.User;

public class UserConverter {
    public static User convert(User user)
    {
        User user2 = new User();
        user2.setId(user.getId());
        return user2;
    }
    public User convert2(User user)
    {
        User user2 = new User();
        user2.setId(user.getId());
        return user2;
    }
}
