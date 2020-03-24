package com.mobile.frontend.data;

import com.mobile.frontend.data.model.Model;
import com.mobile.frontend.data.model.UserModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<UserModel> login(String username, String password, Model model) {

        try {
            ArrayList<UserModel> users = model.getUsers();
            for (UserModel user : users) {
                if (user.getEmail().equals(username) && user.getPassword().equals(password)) {
                    model.setLoggedUser(user);
                    return new Result.Success<UserModel>(user);
                }
            }
            return new Result.Error(new IOException("Invalid user name or password"));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
